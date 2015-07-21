package com.home.allpet.api.model;

import org.springframework.web.multipart.MultipartFile;

public class FileVo {
	private MultipartFile Filedata;
	private String callback;
	private String callback_func;
	private String filename;
    private String CKEditorFuncNum;
    
	public MultipartFile getFiledata() {
		return Filedata;
	}
	public void setFiledata(MultipartFile filedata) {
		Filedata = filedata;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public String getCallback_func() {
		return callback_func;
	}
	public void setCallback_func(String callback_func) {
		this.callback_func = callback_func;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getCKEditorFuncNum() {
		return CKEditorFuncNum;
	}
	public void setCKEditorFuncNum(String cKEditorFuncNum) {
		CKEditorFuncNum = cKEditorFuncNum;
	}
	
}
