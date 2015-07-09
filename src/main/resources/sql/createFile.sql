-- 홈페이지에서 데이터를 연동하기 위하여 홈페이지와 기존 모바일 홈페이지를 연결시켜 놓은 테이블
CREATE TABLE db_allpet.pet_home_basic_info (
  S_DOMAIN varchar(128) NOT NULL,
  S_SID varchar(128)  DEFAULT NULL,
  S_HOMEPAGE_LOC varchar(128) DEFAULT NULL,
  S_HOSPITAL_ID varchar(128) DEFAULT NULL,
  PRIMARY KEY (S_DOMAIN)
);

CREATE TABLE tb_board (
	board_Seq int(10) PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(20)  NULL DEFAULT NULL,
	passwd VARCHAR(20)  NULL DEFAULT NULL,
	email VARCHAR(50)  NULL DEFAULT NULL,
	title VARCHAR(255) NULL DEFAULT NULL,
	content TEXT  NULL DEFAULT NULL,
	reg_date DATETIME  NULL DEFAULT NULL,
	rcount  int(5) NOT NULL DEFAULT 0,
	master_Seq  int(10) NULL DEFAULT 0,
	reply_num   int(3) NULL DEFAULT 0,
	step   int(3) NULL DEFAULT 0,
	board_ssid VARCHAR(128)  NOT NULL,
	board_type VARCHAR(2)  NOT NULL
);

