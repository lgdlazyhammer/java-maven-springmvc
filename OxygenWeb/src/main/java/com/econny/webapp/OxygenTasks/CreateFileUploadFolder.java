package com.econny.webapp.OxygenTasks;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

@Component("CreateFileUploadFolderTask")
public class CreateFileUploadFolder implements ServletContextAware {

	private ServletContext servletContext;

	public void CreateFileUploadFolder() {

		// 获取文件暂存和保存路径
		Properties prop = new Properties();
		InputStream in = servletContext.getResourceAsStream("/WEB-INF/fileService.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<String> it = prop.stringPropertyNames().iterator();
		while (it.hasNext()) {
			String key = it.next();
		}
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String realPath = servletContext.getRealPath("/WEB-INF/" + prop.getProperty("filePathSave"));
		// create the folder -- this should be done when the project first
		// started
		new File(realPath).mkdirs();
		
		System.out.println("create file upload folder task.");
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		this.servletContext = arg0;
	}
}
