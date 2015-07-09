package com.home.allpet.api.model;

import java.util.Date;

public class BoardVo {

	Long boardSeq;				// 게시물 고유번호
	String username;			// 등록자 이름
	String passwd;				// 비밀번호
	String email;					// 이메일
	String title;					// 제목
	String content;				// 내용
	Date regDate;				// 등록일
	int rcount;					// 조회수
	int masterSeq;				// 원본 고유번호
	int replyNum;				// 답변글 순차번호
	int step;						// 답변글 깊이
	String boardSsid;			// ssid
	String boardType;			// 게시판 타입
	
	public Long getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(Long boardSeq) {
		this.boardSeq = boardSeq;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getRcount() {
		return rcount;
	}
	public void setRcount(int rcount) {
		this.rcount = rcount;
	}
	public int getMasterSeq() {
		return masterSeq;
	}
	public void setMasterSeq(int masterSeq) {
		this.masterSeq = masterSeq;
	}
	public int getReplyNum() {
		return replyNum;
	}
	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public String getBoardSsid() {
		return boardSsid;
	}
	public void setBoardSsid(String boardSsid) {
		this.boardSsid = boardSsid;
	}
	public String getBoardType() {
		return boardType;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
	
	
}
