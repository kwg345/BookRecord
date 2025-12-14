--postsテーブルが既に存在する場合は削除
DROP TABLE IF EXISTS posts;

--postsテーブルを作成
CREATE TABLE posts (
	id Long NOT NULL PRIMARY KEY AUTO_INCREMENT,--主キー、自動増加
	title VARCHAR(255) NOT NULL,				--タイトル、NULL禁止
	author VARCHAR(255) NOT NULL,				--著者、NULL禁止
	memo TEXT NOT NULL,							--メモ、NULL禁止
	borrow_date DATE, 							--借りた日
	return_date DATE,							--返却予定日
	favorite BOOLEAN DEFAULT FALSE				--お気に入りフラグ
 );