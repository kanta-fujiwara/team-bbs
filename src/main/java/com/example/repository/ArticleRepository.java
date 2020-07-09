package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

/**
 * articlesテーブルを操作するリポジトリ.
 *
 * @author kanta.fujiwara
 */
@Repository
public class ArticleRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final String TABLE_NAME = "articles";

	private static final RowMapper<Article> ROW_MAPPER = new BeanPropertyRowMapper<>(Article.class);

	/**
	 * 記事情報を全件取得する.
	 *
	 * 各記事に対するコメントのリストも同時に取得される.
	 *
	 * @return 記事情報のリスト。 データが存在しない場合は空のリストが返る。
	 */
	public List<Article> findAllWithComments() {
		String sql = "" + "SELECT" + "  articles.id as id," + "  articles.name as name,"
				+ "  articles.content as content," + "  comments.id as comment_id," + "  comments.name as comment_name,"
				+ "  comments.content as comment_content " + "FROM articles LEFT JOIN comments"
				+ "  ON articles.id = comments.article_id " + "ORDER BY id DESC, comment_id DESC;";

		ResultSetExtractor<List<Article>> rse = (rs) -> {
			// 結果を保持するリスト
			List<Article> articleList = new ArrayList<>();
			Article currentArticle = new Article();

			while (rs.next()) {
				Integer articleId = rs.getInt("id");

				// 記事IDが変わったら新しいArticleを追加する
				if (articleId != currentArticle.getId()) {
					currentArticle = ROW_MAPPER.mapRow(rs, 0);
					articleList.add(currentArticle);
				}

				// コメントが存在すればcurrenArticleに追加
				int commentId = rs.getInt("comment_id"); // nullのときは0が返る
				if (commentId > 0) {
					Comment comment = new Comment();
					comment.setId(commentId);
					comment.setName(rs.getString("comment_name"));
					comment.setContent(rs.getString("comment_content"));
					comment.setArticleId(articleId);

					currentArticle.getCommentList().add(comment);
				}
			}

			return articleList;
		};

		return template.query(sql, rse);
	}

	/**
	 * 記事情報を登録する.
	 *
	 * @param article 登録する記事情報。 IDは自動採番されるため無視される。
	 */
	public void insert(Article article) {
		String sql = "" + "INSERT INTO " + TABLE_NAME + "  (name, content)" + "VALUES" + "  (:name, :content);";

		SqlParameterSource params = new BeanPropertySqlParameterSource(article);

		template.update(sql, params);
	}

	/**
	 * 記事情報を削除する.
	 *
	 * @param id 削除する記事のID。IDが一致する記事がない場合は何も起こらない。
	 */
	public void deleteById(int id) {
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = :id";

		SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);

		template.update(sql, params);
	}
}
