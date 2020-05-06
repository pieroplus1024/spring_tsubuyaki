/* ユーザーマスタ */
CREATE TABLE IF NOT EXISTS m_user (
	id INTEGER PRIMARY KEY,
	user_name VARCHAR(50),
	mail VARCHAR(50),
	password VARCHAR(100),
	gender_id INTEGER,
	todofuken_id INTEGER,
	age INTEGER,
	role VARCHAR(50),
	enabled BOOLEAN
);

/* 趣味_ユーザ紐付け */
CREATE TABLE IF NOT EXISTS mid_user_hobby (
	id INTEGER PRIMARY KEY,
	user_id INTEGER,
	hobby_id INTEGER
	);

/* 趣味 */
CREATE TABLE IF NOT EXISTS m_hobby (
	id INTEGER PRIMARY KEY,
	hobby_name VARCHAR(50)
);

/* 都道府県マスタ */
CREATE TABLE IF NOT EXISTS m_todofuken (
	id INTEGER PRIMARY KEY,
	todofuken_name VARCHAR(50)
);

/* 性別マスタ */
CREATE TABLE IF NOT EXISTS m_gender (
	id INTEGER PRIMARY KEY,
	gender_name VARCHAR(50)
);

/* つぶやき */
CREATE TABLE IF NOT EXISTS tsubuyaki (
	id INTEGER PRIMARY KEY,
	tsubuyaki VARCHAR(200),
	user_id INTEGER,
	delete_flg CHAR,
	create_date DATETIME,
	update_date DATETIME
);

/* フォロー */
CREATE TABLE IF NOT EXISTS follow (
	id INTEGER PRIMARY KEY,
	user_id INTEGER,
	follow_user_id INTEGER,
	create_date DATETIME,
	update_date DATETIME
);

/* フォロワー */
CREATE TABLE IF NOT EXISTS follower (
	id INTEGER PRIMARY KEY,
	user_id INTEGER,
	follower_user_id INTEGER,
	create_date DATETIME,
	update_date DATETIME
);