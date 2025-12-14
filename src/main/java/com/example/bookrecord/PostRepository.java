package com.example.bookrecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepository {
	private final JdbcTemplate db;
	
	public PostRepository(JdbcTemplate db) {
		this.db= db;
	}
	
	// データベースから全ての投稿を取得してPostリストとして返す
	public List<Post> searchDB() {
		String sql = "SELECT * FROM posts";
		List<Map<String, Object>> resultList = db.queryForList(sql);
		List<Post> postList = new ArrayList<>();
		for (Map<String, Object> result : resultList) {
			Post post = new Post();
			post.setId((Long) result.get("id"));
			post.setTitle((String) result.get("title"));
			post.setAuthor((String) result.get("author"));
			post.setMemo((String) result.get("memo"));			
			// borrow_date / return_date　のnullチェック
			post.setBorrowDate(
				result.get("borrow_date") != null
				? ((java.sql.Date) result.get("borrow_date")).toLocalDate()
				: null
			);
			post.setReturnDate(
				result.get("return_date") != null
			    ? ((java.sql.Date) result.get("return_date")).toLocalDate()
			    : null
			);

			post.setFavorite((Boolean) result.get("favorite"));
			
			postList.add(post);
		}
		return postList;
	}
	
	// 登録処理
	public void insertDB(Post post) {
		String sql = "INSERT INTO posts (title, author, memo,borrow_date,return_date) VALUES (?, ?, ?, ?, ?)";
		db.update(sql, post.getTitle(), post.getAuthor(), post.getMemo(), post.getBorrowDate(), post.getReturnDate() );
	}
	
	// 削除処理
	public void deleteById(Long id) {
		String sql = "DELETE FROM posts WHERE id = ?"; 
		db.update(sql, id);
	}
	
	// お気に入り切り替え
	public void toggleFavorite(Long id) {
		String sql = "UPDATE posts SET favorite = NOT favorite WHERE id =?";
		db.update(sql, id);
	}
}
