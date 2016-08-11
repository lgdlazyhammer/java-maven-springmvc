package com.econny.webapp.OxygenAction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.econny.webapp.OxygenEntity.UserEntity;
import com.econny.webapp.OxygenService.impl.UserServiceImpl;
import com.econny.webapp.OxygenService.inter.UserService;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	public UserService userService = new UserServiceImpl();

	// 获取以当前类为参数的日志对象
	// private static Log log = LogFactory.getLog(MainApp.class);
	// Logger instance named "MyApp".
	private static final Logger logger = LogManager.getLogger(IndexController.class);

	@RequestMapping("/index")
	public ModelAndView index() {

		logger.trace("Entering application.");
		logger.info("Didn't do it info.");
		logger.warn("Didn't do it warn.");
		logger.error("Didn't do it.");
		logger.trace("Exiting application.");

		UserEntity user = userService.getUserById();
		return new ModelAndView("index", "user", user);
	}
}
