package com.econny.webapp.OxygenAction;

//http://blog.csdn.net/wyc_cs/article/details/6679722 jsonparser example
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.econny.webapp.OxygenEntity.UserEntity;
import com.econny.webapp.OxygenService.impl.UserServiceImpl;
import com.econny.webapp.OxygenService.inter.UserService;

import sun.misc.BASE64Encoder;

@Controller
@RequestMapping("/index")
public class IndexController {

	public UserService userService = new UserServiceImpl();

	@Autowired(required = true)
	public UserService userServiceImplTwo;

	@Autowired(required = true)
	public UserService userServiceImplThree;

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

		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("generate_key");
			byte[] data = new String("").getBytes();
			md5.update(data);
			String token = (new BASE64Encoder()).encodeBuffer(md5.digest());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		UserEntity user = userService.getUserById();
		return new ModelAndView("index", "user", user);
	}

	@RequestMapping("/users")
	@ResponseBody
	public Object users() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", 1);
		map.put("end", 10);
		List<UserEntity> users = userServiceImplTwo.qryUserByPage(map);
		return users;
	}

	@RequestMapping("/insertusers")
	@ResponseBody
	public Object insertUsers() {

		insertSomeUser(20);
		return null;
	}
	
	@RequestMapping("/insertusersbatch")
	@ResponseBody
	public Object insertUsersBatch() {

		batchInsertUser(10);
		return null;
	}

	@RequestMapping("/json")
	@ResponseBody
	public Object json() {

		UserEntity user = userServiceImplTwo.getUserById();
		System.out.println("this is the autowired user: " + user.getName());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", "success");
		return result;
	}

	public void insertSomeUser(Integer number) {
		for (int i=0;i<number;i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name",RandomStringUtils.random(6, 20, 110, true, true));
			map.put("password", RandomStringUtils.random(8, 20, 110, true, true));
			//map.put("name", UUID.randomUUID().toString());
			//map.put("password", UUID.randomUUID().toString());
			System.out.println("the parsed parameters for insert user: " + map.toString());
			userServiceImplTwo.insertUser(map);
		}
	}
	
	public void batchInsertUser(Integer number) {

		Map<String, Object> map2 = new HashMap<String, Object>();
		List<Map> list = new ArrayList();
		for (int i=0;i<number;i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name",RandomStringUtils.random(6, 20, 110, true, true));
			map.put("password", RandomStringUtils.random(8, 20, 110, true, true));
			//map.put("name", UUID.randomUUID().toString());
			//map.put("password", UUID.randomUUID().toString());
			System.out.println("the parsed parameters for insert user: " + map.toString());
			list.add(map);		
		}
		map2.put("list",list);
		userServiceImplTwo.insertUserBatch(map2);
	}
}
