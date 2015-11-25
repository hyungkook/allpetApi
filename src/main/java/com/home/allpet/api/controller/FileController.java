package com.home.allpet.api.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.home.allpet.api.model.FileVo;
import com.home.allpet.api.model.UploadedFile;

@RestController
@RequestMapping("/v1")
public class FileController {
	
	
	//단일파일업로드
		@RequestMapping(value = "/photoUploads", method = RequestMethod.POST)
		public void photoUploads(HttpServletRequest request,HttpServletResponse response, @RequestParam MultipartFile upload) throws IOException{
		    String attach_path = "resource" + File.separator + "photo_upload" + File.separator;
		    PrintWriter printWriter = null;
		    String filename = "";
		    String CKEditorFuncNum = "";
		    if (upload != null) {
		        filename = upload.getOriginalFilename();
		        CKEditorFuncNum = request.getParameter("CKEditorFuncNum");
		        try {
		          //파일 확장자
					String filename_ext = filename.substring(filename.lastIndexOf(".")+1);
					//확장자를소문자로 변경
					filename_ext = filename_ext.toLowerCase();
					//파일 기본경로
					String dftFilePath = request.getSession().getServletContext().getRealPath("/");
					//파일 기본경로 _ 상세경로
					String filePath = dftFilePath + "resource" + File.separator + "photo_upload" + File.separator;
					File file = new File(filePath);
					if(!file.exists()) {
						file.mkdirs();
					}
					String realFileNm = "";
					SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
					String today= formatter.format(new java.util.Date());
					realFileNm = today+UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
					String rlFileNm = filePath + realFileNm;
					///////////////// 서버에 파일쓰기 ///////////////// 
					OutputStream os=new FileOutputStream(rlFileNm);
					byte[] bytes = upload.getBytes();
					os.write(bytes);
					os.flush();
					os.close();
					///////////////// 서버에 파일쓰기 /////////////////
					StringBuffer bf = request.getRequestURL();
					int lastIndex = bf.indexOf("/v1/photoUpload");
					String serverUrl = bf.substring(0,lastIndex);
					
					String domain = request.getHeader("referer");//request.getRequestURL().toString();
					
					domain = domain.replace("http://", "");
					domain = domain.substring(0, domain.indexOf("/"));
					
					String sFileInfo = serverUrl + File.separator + attach_path +realFileNm;
					printWriter = response.getWriter();
		            printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
		                    + CKEditorFuncNum
		                    + ",'"
		                    + sFileInfo
		                    + "','이미지를 업로드 하였습니다.'"
		                    + ")</script>");
		            printWriter.flush();
		        } catch (IOException e) {
		            e.printStackTrace();
		        } finally {
		            if (printWriter != null) {
					    printWriter.close();
					}
		        }
		    }
		    
		}
	//단일파일업로드
	@RequestMapping(value = "/photoUpload", method = RequestMethod.POST)
	public String photoUpload(HttpServletRequest request, FileVo vo){
		String callback = vo.getCallback();
		String callback_func = vo.getCallback_func();
		String file_result = "";
		try {
			if(vo.getFiledata() != null && vo.getFiledata().getOriginalFilename() != null && !vo.getFiledata().getOriginalFilename().equals("")){
				//파일이 존재하면
				String original_name = vo.getFiledata().getOriginalFilename();
				String ext = original_name.substring(original_name.lastIndexOf(".")+1);
				//파일 기본경로
				String defaultPath = request.getSession().getServletContext().getRealPath("/");
				//파일 기본경로 _ 상세경로
				String path = defaultPath + "resource" + File.separator + "photo_upload" + File.separator;
				File file = new File(path);
				System.out.println("path:"+path);
				//디렉토리 존재하지 않을경우 디렉토리 생성
				if(!file.exists()) {
					file.mkdirs();
				}
				//서버에 업로드 할 파일명(한글문제로 인해 원본파일은 올리지 않는것이 좋음)
				String realname = UUID.randomUUID().toString() + "." + ext;
				///////////// 서버에 파일쓰기 ///////////////// 
				vo.getFiledata().transferTo(new File(path+realname));
				file_result += "&bNewLine=true&sFileName="+original_name+"&sFileURL=/resource/photo_upload/"+realname;
			} else {
				file_result += "&errstr=error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:" + callback + "?callback_func="+callback_func+file_result;
	}
	
	//다중파일업로드
	@RequestMapping(value = "/multiplePhotoUpload", method = RequestMethod.POST)
	public void multiplePhotoUpload(HttpServletRequest request, HttpServletResponse response){
		try {
			//파일정보
			String sFileInfo = "";
			//파일명을 받는다 - 일반 원본파일명
			String filename = request.getHeader("file-name");
			//파일 확장자
			String filename_ext = filename.substring(filename.lastIndexOf(".")+1);
			//확장자를소문자로 변경
			filename_ext = filename_ext.toLowerCase();
			//파일 기본경로
			String dftFilePath = request.getSession().getServletContext().getRealPath("/");
			//파일 기본경로 _ 상세경로
			String filePath = dftFilePath + "resource" + File.separator + "photo_upload" + File.separator;
			File file = new File(filePath);
			if(!file.exists()) {
				file.mkdirs();
			}
			String realFileNm = "";
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String today= formatter.format(new java.util.Date());
			realFileNm = today+UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
			String rlFileNm = filePath + realFileNm;
			///////////////// 서버에 파일쓰기 ///////////////// 
			InputStream is = request.getInputStream();
			OutputStream os=new FileOutputStream(rlFileNm);
			int numRead;
			byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
			while((numRead = is.read(b,0,b.length)) != -1){
				os.write(b,0,numRead);
			}
			if(is != null) {
				is.close();
			}
			os.flush();
			os.close();
			///////////////// 서버에 파일쓰기 /////////////////
			// 정보 출력
			sFileInfo += "&bNewLine=true";
			// img 태그의 title 속성을 원본파일명으로 적용시켜주기 위함
			sFileInfo += "&sFileName="+ filename;;
			StringBuffer bf = request.getRequestURL();
			int lastIndex = bf.indexOf("/v1/multiplePhotoUpload");
			String serverUrl = bf.substring(0,lastIndex);
			
			String domain = request.getHeader("referer");//request.getRequestURL().toString();
			
			domain = domain.replace("http://", "");
			domain = domain.substring(0, domain.indexOf("/"));
			
			sFileInfo += "&sFileURL=" + serverUrl + File.separator + "resource"+ File.separator +"photo_upload" + File.separator +realFileNm;
			PrintWriter print = response.getWriter();
			print.print(sFileInfo);
			print.flush();
			print.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	   public @ResponseBody String upload(MultipartHttpServletRequest request, HttpServletResponse response) {                 
		
		String saveDirectory = "/resource/";
	     //0. notice, we have used MultipartHttpServletRequest
	 
	     //1. get the files from the request object
	     Iterator<String> itr =  request.getFileNames();
	 
	     MultipartFile mpf = request.getFile(itr.next());
	     
	     UploadedFile ufile =  new UploadedFile();
	     try {
	                //just temporary save file info into ufile
	        ufile.length = mpf.getBytes().length;
	        ufile.bytes= mpf.getBytes();
	        ufile.type = mpf.getContentType();
	        ufile.name = mpf.getOriginalFilename();
	        File file = new File(saveDirectory + ufile.name);
	        if( !file.getParentFile().exists() ){
	        	file.getParentFile().mkdirs();
	        }
	        mpf.transferTo(file);
	        return file.getAbsolutePath().toString();
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	     return null;
	  }

}
