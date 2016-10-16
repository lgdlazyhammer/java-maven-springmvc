package com.econny.webapp.OxygenAction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.econny.webapp.OxygenEntity.ApiResultEntity;
import com.econny.webapp.OxygenEntity.UploadFileEntity;
import com.econny.webapp.OxygenEnum.FileSecureLevel;

@Controller
@RequestMapping("/commonService")
public class CommonService {
	
	/*
	 * 文件保存位置可选 保密性高文件保存在数据库 --Level 0 保密性低文件保存在文件夹里--Level 1
	 */
	/*
	 * @CrossOrigin(origin = { "http://site1.com", "http://site2.com" },
	 * allowedHeaders = { "header1", "header2" }, - exposedHeaders = {
	 * "header3", "header4" }, method = RequestMethod.DELETE, maxAge = 123,
	 * allowCredentials = "false")
	 */
	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST })
	@RequestMapping(value = "/fileUploadSingle", method = RequestMethod.POST)
	@ResponseBody
	public Object fileUploadSingle(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = true) MultipartFile file_data,
			@RequestParam(required = true) Integer secureLevel) throws IOException {
		// 获取文件暂存和保存路径
		Properties prop = new Properties();
		InputStream in = request.getSession().getServletContext()
				.getResourceAsStream("/WEB-INF/fileService.properties");
		prop.load(in);
		in.close();

		System.out.println("secure level: " + secureLevel);
		// 如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解
		// 如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解
		// 并且上传多个文件时，前台表单中的所有<input
		// type="file"/>的name都应该是myfiles，否则参数里的myfiles无法获取到所有上传的文件
		MultipartFile myfile = file_data;
		if (myfile.isEmpty()) {
			return new ApiResultEntity(false, "", 400, "no file uploaded");
		} else {
			
			//generate the database save id
			UploadFileEntity uploadFileEntity = new UploadFileEntity();
			//set file original name -- aaa.txt
			uploadFileEntity .setFileName(myfile.getOriginalFilename());
			//set file size -- 3233
			uploadFileEntity .setFileSize(String.valueOf(myfile.getSize()));
			//set file type -- image/png
			uploadFileEntity .setFileContentType(myfile.getContentType());
			//set secure level
			uploadFileEntity.setSecureLevel(secureLevel);
			// 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
			String realPath = request.getSession().getServletContext()
					.getRealPath("/WEB-INF/" + prop.getProperty("filePathSave"));
			
			if(secureLevel == FileSecureLevel.LevelOne.getCode()){
				//do nothing
			}else if(secureLevel == FileSecureLevel.LevelTwo.getCode()){
				// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
				FileUtils.copyFileToDirectory(multipartToFile(myfile),
						new File(realPath, uploadFileEntity.getId()));

				return new ApiResultEntity(true, uploadFileEntity.getId(), 200, "");
			}
		};
		
		return new ApiResultEntity(false, "", 500, "");
	}

	/*
	 * 文件保存位置可选 保密性高文件保存在数据库 --Level 0 保密性低文件保存在文件夹里--Level 1
	 */
	/*
	 * @CrossOrigin(origin = { "http://site1.com", "http://site2.com" },
	 * allowedHeaders = { "header1", "header2" }, - exposedHeaders = {
	 * "header3", "header4" }, method = RequestMethod.DELETE, maxAge = 123,
	 * allowCredentials = "false")
	 */
	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST })
	@RequestMapping(value = "/fileUploadMulti", method = RequestMethod.POST)
	@ResponseBody
	public Object fileUploadMulti(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile[] myfiles,
			@RequestParam String secureLevel) throws IOException {
		// 获取文件暂存和保存路径
		Properties prop = new Properties();
		InputStream in = request.getSession().getServletContext()
				.getResourceAsStream("/WEB-INF/fileService.properties");
		prop.load(in);
		Iterator<String> it = prop.stringPropertyNames().iterator();
		while (it.hasNext()) {
			String key = it.next();
			System.out.println(key + ":" + prop.getProperty(key));
		}
		in.close();

		System.out.println("secure level: " + secureLevel);
		// 如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解
		// 如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解
		// 并且上传多个文件时，前台表单中的所有<input
		// type="file"/>的name都应该是myfiles，否则参数里的myfiles无法获取到所有上传的文件
		for (MultipartFile myfile : myfiles) {
			if (myfile.isEmpty()) {
				System.out.println("文件未上传");
			} else {
				System.out.println("文件长度: " + myfile.getSize());
				System.out.println("文件类型: " + myfile.getContentType());
				System.out.println("文件名称: " + myfile.getName());
				System.out.println("文件原名: " + myfile.getOriginalFilename());
				System.out.println("========================================");
				// 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
				String realPath = request.getSession().getServletContext()
						.getRealPath("/WEB-INF/" + prop.getProperty("filePathSave"));
				// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
				FileUtils.copyDirectoryToDirectory(multipartToFile(myfile),
						new File(realPath, myfile.getOriginalFilename()));
			}
		}
		return "ok";
	}

	// parse a multipart File to File
	public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
		File convFile = new File(multipart.getOriginalFilename());
		multipart.transferTo(convFile);
		return convFile;
	}

}