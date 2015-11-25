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
	deleteYn VARCHAR(1)  NOT NULL default 'N',
	board_type VARCHAR(2)  NOT NULL
);

CREATE TABLE tb_board_attach (
	attach_Seq int(10) PRIMARY KEY AUTO_INCREMENT,
	board_Seq int(10) ,
	file_name VARCHAR(100)  NULL DEFAULT NULL,
	file_size int(20)  NULL DEFAULT 0,
	file_path VARCHAR(100)  NULL DEFAULT NULL,
	file_type VARCHAR(100)  NULL DEFAULT NULL
);

CREATE TABLE tb_board_svma (
	board_Seq int(10) PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(20)  NULL DEFAULT NULL,
	passwd VARCHAR(20)  NULL DEFAULT NULL,
	email VARCHAR(50)  NULL DEFAULT NULL,
	title VARCHAR(255) NULL DEFAULT NULL,
	content TEXT  NULL DEFAULT NULL,
	reg_date DATETIME  NULL DEFAULT NULL,
	edu_date DATETIME  NULL DEFAULT NULL,
	rcount  int(5) NOT NULL DEFAULT 0,
	master_Seq  int(10) NULL DEFAULT 0,
	reply_num   int(3) NULL DEFAULT 0,
	step   int(3) NULL DEFAULT 0,
	board_ssid VARCHAR(128)  NOT NULL,
	deleteYn VARCHAR(1)  NOT NULL default 'N',
	board_type VARCHAR(2)  NOT NULL
);

CREATE TABLE tb_board_svma_edu (
	board_Seq int(10) PRIMARY KEY AUTO_INCREMENT,
	parentBoardSeq int(10) NOT NULL DEFAULT 0 ,
	userType VARCHAR(20)  NULL DEFAULT NULL,
	userNameKo VARCHAR(100)  NULL DEFAULT NULL,
	userNameEn VARCHAR(100) NULL DEFAULT NULL,
	userCreditId VARCHAR(100) NULL DEFAULT NULL,
	hospitalName VARCHAR(100) NULL DEFAULT NULL,
	hospitalAddr VARCHAR(255) NULL DEFAULT NULL,
	phoneNo VARCHAR(50) NULL DEFAULT NULL,
	reg_date DATETIME  NULL DEFAULT NULL,
	deleteYn VARCHAR(1)  NOT NULL default 'N'
);

create table t_svma_user(
	credit_id VARCHAR(20) PRIMARY KEY NOT NULL,
	user_name VARCHAR(20) NOT NULL,
	user_type VARCHAR(20) NULL,
	user_type_desc VARCHAR(100) NULL,
	reg_date DATETIME  NULL
);

