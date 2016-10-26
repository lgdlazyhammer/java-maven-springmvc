package com.econny.webapp.OxygenAction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/clientService")
public class ClientAction {
	
	@RequestMapping(value = "/fileUploadSingle", method = RequestMethod.POST)
	public RedirectView fileUploadSingle(){
		//redirect is a get request
		//最后的参数为false代表以post方式提交请求 
		return new RedirectView("http://localhost:8090/OxygenWeb/commonService/fileUploadSingle", true, false, false);
	}

}
