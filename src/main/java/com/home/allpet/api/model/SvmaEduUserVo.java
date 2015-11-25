package com.home.allpet.api.model;

import java.sql.Date;

public class SvmaEduUserVo {
	
	public static final String USER_TYPE_MEMBER = "01";
	public static final String AGE_CODE_NONMEMBER = "02";
	
	Long boardSeq;						// 게시물 고유번호
	String eduTitle;
	Date eduDate;
	Long parentBoardSeq;				// 교육 넘버
	
	String userType;
	String userNameKo;
	String userNameEn;
	String userCreditId;
	String hospitalName;
	String hospitalAddr;
	String phoneNo;
	String userTypeDesc;
	public Long getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(Long boardSeq) {
		this.boardSeq = boardSeq;
	}
	public String getEduTitle() {
		return eduTitle;
	}
	public void setEduTitle(String eduTitle) {
		this.eduTitle = eduTitle;
	}
	public Date getEduDate() {
		return eduDate;
	}
	public void setEduDate(Date eduDate) {
		this.eduDate = eduDate;
	}
	public Long getParentBoardSeq() {
		return parentBoardSeq;
	}
	public void setParentBoardSeq(Long parentBoardSeq) {
		this.parentBoardSeq = parentBoardSeq;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserNameKo() {
		return userNameKo;
	}
	public void setUserNameKo(String userNameKo) {
		this.userNameKo = userNameKo;
	}
	public String getUserNameEn() {
		return userNameEn;
	}
	public void setUserNameEn(String userNameEn) {
		this.userNameEn = userNameEn;
	}
	public String getUserCreditId() {
		return userCreditId;
	}
	public void setUserCreditId(String userCreditId) {
		this.userCreditId = userCreditId;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getHospitalAddr() {
		return hospitalAddr;
	}
	public void setHospitalAddr(String hospitalAddr) {
		this.hospitalAddr = hospitalAddr;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getUserTypeDesc() {
		return userTypeDesc;
	}
	public void setUserTypeDesc(String userTypeDesc) {
		this.userTypeDesc = userTypeDesc;
	}
	
	
}
