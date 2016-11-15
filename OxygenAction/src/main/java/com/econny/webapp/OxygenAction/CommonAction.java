package com.econny.webapp.OxygenAction;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.econny.webapp.OxygenService.impl.UploadFileServiceImpl;

@Controller
@RequestMapping("/commonService")
public class CommonAction {

	@Autowired
	UploadFileServiceImpl uploadFileServiceImpl;

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
	public Object fileUploadSingle(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) MultipartFile file_data, @RequestParam(required = true) Integer secureLevel)
			throws IOException {
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

			// generate the database save id
			UploadFileEntity uploadFileEntity = new UploadFileEntity();
			// set file original name -- aaa.txt
			uploadFileEntity.setFileName(myfile.getOriginalFilename());
			// set file size -- 3233
			uploadFileEntity.setFileSize(myfile.getSize());
			// set file type -- image/png
			uploadFileEntity.setFileContentType(myfile.getContentType());
			// set secure level
			uploadFileEntity.setSecureLevel(secureLevel);
			// 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
			String realPath = request.getSession().getServletContext()
					.getRealPath("/WEB-INF/" + prop.getProperty("filePathSave"));
			
			if (!realPath.endsWith(java.io.File.separator)) {
				realPath += java.io.File.separator;
		        }

			String realPathStatic = request.getSession().getServletContext()
					.getRealPath("/WEB-INF/" + prop.getProperty("filePathStatic"));
			
			if (!realPathStatic.endsWith(java.io.File.separator)) {
				realPathStatic += java.io.File.separator;
			        }

			if (secureLevel == FileSecureLevel.LevelOne.getCode()) {
				// 保存2进制文件内容
				uploadFileEntity.setFileContent(myfile.getBytes());
				uploadFileServiceImpl.insert(uploadFileEntity);

				return new ApiResultEntity(true, uploadFileEntity.getId(), 200, "");
				// do nothing
			} else if (secureLevel == FileSecureLevel.LevelTwo.getCode()) {
				// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
				File tempFile = multipartToFile(myfile);
				FileUtils.copyFileToDirectory(tempFile, new File(realPath, uploadFileEntity.getId()));
				//delete the temporary file or it leave a file in root path
				tempFile.delete();

				uploadFileServiceImpl.insert(uploadFileEntity);

				return new ApiResultEntity(true, uploadFileEntity.getId(), 200, "");
			} else if (secureLevel == FileSecureLevel.LevelThree.getCode()) {
				// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
				// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
				File tempFile = multipartToFile(myfile);
				FileUtils.copyFileToDirectory(tempFile,
						new File(realPathStatic, uploadFileEntity.getId()));
				//delete the temporary file or it leave a file in root path
				tempFile.delete();
				uploadFileServiceImpl.insert(uploadFileEntity);

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
	public Object fileUploadMulti(HttpServletRequest request, HttpServletResponse response,
			@RequestParam MultipartFile[] myfiles, @RequestParam Integer secureLevel) throws IOException {
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

				// generate the database save id
				UploadFileEntity uploadFileEntity = new UploadFileEntity();
				// set file original name -- aaa.txt
				uploadFileEntity.setFileName(myfile.getOriginalFilename());
				// set file size -- 3233
				uploadFileEntity.setFileSize(myfile.getSize());
				// set file type -- image/png
				uploadFileEntity.setFileContentType(myfile.getContentType());
				// set secure level
				uploadFileEntity.setSecureLevel(secureLevel);
				// 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
				String realPath = request.getSession().getServletContext()
						.getRealPath("/WEB-INF/" + prop.getProperty("filePathSave"));
				
				if (!realPath.endsWith(java.io.File.separator)) {
					realPath += java.io.File.separator;
			        }

				String realPathStatic = request.getSession().getServletContext()
						.getRealPath("/WEB-INF/" + prop.getProperty("filePathStatic"));
				
				if (!realPathStatic.endsWith(java.io.File.separator)) {
					realPathStatic += java.io.File.separator;
				        }

				if (secureLevel == FileSecureLevel.LevelOne.getCode()) {
					// 保存2进制文件内容
					uploadFileEntity.setFileContent(myfile.getBytes());
					uploadFileServiceImpl.insert(uploadFileEntity);

					return new ApiResultEntity(true, uploadFileEntity.getId(), 200, "");
					// do nothing
				} else if (secureLevel == FileSecureLevel.LevelTwo.getCode()) {
					// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
					File tempFile = multipartToFile(myfile);
					FileUtils.copyFileToDirectory(tempFile, new File(realPath, uploadFileEntity.getId()));
					//delete the temporary file or it leave a file in root path
					tempFile.delete();

					uploadFileServiceImpl.insert(uploadFileEntity);

					return new ApiResultEntity(true, uploadFileEntity.getId(), 200, "");
				} else if (secureLevel == FileSecureLevel.LevelThree.getCode()) {
					// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
					File tempFile = multipartToFile(myfile);
					FileUtils.copyFileToDirectory(tempFile,
							new File(realPathStatic, uploadFileEntity.getId()));
					tempFile.delete();
					uploadFileServiceImpl.insert(uploadFileEntity);

					return new ApiResultEntity(true, uploadFileEntity.getId(), 200, "");
				}
			}
		}
		
		return new ApiResultEntity(false, "", 500, "");
	}

	/*
	 * file download
	 */
	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET })
	@RequestMapping(value = "/fileDownloadSingle", method = RequestMethod.GET)
	@ResponseBody
	public Object fileDownloadSingleGET(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String id) throws IOException {
		// 获取文件暂存和保存路径
		Properties prop = new Properties();
		InputStream in = request.getSession().getServletContext()
				.getResourceAsStream("/WEB-INF/fileService.properties");
		prop.load(in);
		in.close();

		UploadFileEntity uploadFileEntityQry = new UploadFileEntity();
		uploadFileEntityQry.setId(id);

		UploadFileEntity uploadFileEntityRes = uploadFileServiceImpl.getById(uploadFileEntityQry);

		if (uploadFileEntityRes.getSecureLevel().equals(FileSecureLevel.LevelOne.getCode())
				|| uploadFileEntityRes.getSecureLevel().equals(FileSecureLevel.LevelTwo.getCode())) {
			// return the file bytes
			makeDownloadResponse(response, request.getSession().getServletContext(), prop, uploadFileEntityRes);
			return null;
		} else if (uploadFileEntityRes.getSecureLevel().equals(FileSecureLevel.LevelThree.getCode())) {
			uploadFileEntityRes.setRefUrl(prop.getProperty("filePathStatic") + "/" + uploadFileEntityRes.getId()
			+ "/" + uploadFileEntityRes.getFileName());
			// return the static url
			return new ApiResultEntity(true, uploadFileEntityRes , 200, "");
		}

		return null;
	}

	/*
	 * file download
	 */
	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST })
	@RequestMapping(value = "/fileDownloadSingle", method = RequestMethod.POST)
	@ResponseBody
	public Object fileDownloadSinglePost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String id) throws IOException {
		// 获取文件暂存和保存路径
		Properties prop = new Properties();
		InputStream in = request.getSession().getServletContext()
				.getResourceAsStream("/WEB-INF/fileService.properties");
		prop.load(in);
		in.close();

		UploadFileEntity uploadFileEntityQry = new UploadFileEntity();
		uploadFileEntityQry.setId(id);

		UploadFileEntity uploadFileEntityRes = uploadFileServiceImpl.getById(uploadFileEntityQry);

		if (uploadFileEntityRes.getSecureLevel().equals(FileSecureLevel.LevelOne.getCode())
				|| uploadFileEntityRes.getSecureLevel().equals(FileSecureLevel.LevelTwo.getCode())) {
			// return the file bytes
			makeDownloadResponse(response, request.getSession().getServletContext(), prop, uploadFileEntityRes);
			return null;
		} else if (uploadFileEntityRes.getSecureLevel().equals(FileSecureLevel.LevelThree.getCode())) {
			
			uploadFileEntityRes.setRefUrl(prop.getProperty("filePathStatic") + "/" + uploadFileEntityRes.getId()
			+ "/" + uploadFileEntityRes.getFileName());
			// return the static url
			return new ApiResultEntity(true, uploadFileEntityRes , 200, "");
		}

		return null;

	}

	// parse a multipart File to File
	public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
		File convFile = new File(multipart.getOriginalFilename());
		multipart.transferTo(convFile);
		return convFile;
	}

	//made download file response
	public void makeDownloadResponse(HttpServletResponse response, ServletContext servletContext, Properties prop,
			UploadFileEntity uploadFileEntity) throws IOException {
		// 获取网站部署路径(通过ServletContext对象)，用于确定下载文件位置，从而实现下载
		String path = servletContext.getRealPath("/WEB-INF/" + prop.getProperty("filePathSave"));
		
		if (!path.endsWith(java.io.File.separator)) {
			path += java.io.File.separator;
		        }

		// 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
		response.setContentType("multipart/form-data");
		// 2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
		response.setHeader("Content-Disposition", "attachment;fileName=" + uploadFileEntity.getFileName());
		ServletOutputStream out;

		if (uploadFileEntity.getSecureLevel() == FileSecureLevel.LevelOne.getCode()) {

			InputStream inputStream = new ByteArrayInputStream(uploadFileEntity.getFileContent());
			BufferedInputStream in = new BufferedInputStream(inputStream);

			// 3.通过response获取ServletOutputStream对象(out)
			out = response.getOutputStream();

			int b = 0;
			byte[] buffer = new byte[1024];
			/*while (b != -1) {
				b = in.read(buffer);
				// 4.写到输出流(out)中
				out.write(buffer, 0, b);
			}*/
			while ((b = in.read(buffer)) != -1) {
				// 4.写到输出流(out)中
				out.write(buffer, 0, b);
			}
			in.close();
			out.close();
			out.flush();

		} else if (uploadFileEntity.getSecureLevel() == FileSecureLevel.LevelTwo.getCode()) {

			// 通过文件路径获得File对象(假如此路径中有一个download.pdf文件)
			File file = new File(path + uploadFileEntity.getId() + "/" + uploadFileEntity.getFileName());

			FileInputStream inputStream = new FileInputStream(file);
			BufferedInputStream in = new BufferedInputStream(inputStream);

			// 3.通过response获取ServletOutputStream对象(out)
			out = response.getOutputStream();

			int b = 0;
			byte[] buffer = new byte[1024];
			while ((b = in.read(buffer)) != -1) {
				// 4.写到输出流(out)中
				out.write(buffer, 0, b);
			}
			in.close();
			out.close();
			out.flush();
		}
	}

	/*
	 * file delete
	 */
	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST })
	@RequestMapping(value = "/fileDeleteSinglePost", method = RequestMethod.POST)
	@ResponseBody
	public Object fileDeleteSinglePost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String id) throws IOException {

		UploadFileEntity uploadFileEntity = new UploadFileEntity();
		uploadFileEntity.setId(id);
		uploadFileServiceImpl.delete(uploadFileEntity, "", "");
		return new ApiResultEntity(true, uploadFileEntity , 200, "");

	}

}
