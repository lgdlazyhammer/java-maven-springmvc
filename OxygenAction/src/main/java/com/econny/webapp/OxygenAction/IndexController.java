package com.econny.webapp.OxygenAction;

//http://blog.csdn.net/wyc_cs/article/details/6679722 jsonparser example
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.econny.webapp.OxygenEntity.OauthUserEntity;
import com.econny.webapp.OxygenService.impl.OauthUserServiceImpl;
import com.econny.webapp.OxygenService.impl.OauthUserServiceImplTwo;
import com.econny.webapp.OxygenService.inter.OauthUserService;

import sun.misc.BASE64Encoder;

@Controller
@RequestMapping("/")
public class IndexController {

	public OauthUserService userService = new OauthUserServiceImpl();

	@Autowired(required = true)
	public OauthUserServiceImplTwo oauthUserServiceImplTwo;

	@Autowired(required = true)
	public OauthUserServiceImpl oauthUserServiceImpl;

	// 获取以当前类为参数的日志对象
	// private static Log log = LogFactory.getLog(MainApp.class);
	// Logger instance named "MyApp".
	private static final Logger logger = LogManager.getLogger(IndexController.class);
	
	@RequestMapping("/")
	public ModelAndView index() {
		return new ModelAndView("/plants/index");
	}
	@RequestMapping("/aboutus")
	public ModelAndView aboutUs() {
		return new ModelAndView("/plants/aboutus");
	}
	@RequestMapping("/plants")
	public ModelAndView plants() {
		return new ModelAndView("/plants/plants");
	}
	@RequestMapping("/progress")
	public ModelAndView progress() {
		return new ModelAndView("/plants/progress");
	}
	@RequestMapping("/init")
	public ModelAndView initService() {
		return new ModelAndView("/init_service");
	}
	
	@RequestMapping("/tree")
	public ModelAndView tree() {
		return new ModelAndView("/tree");
	}

	@RequestMapping("/treeNode")
	public ModelAndView treeNode(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String id) {
		return new ModelAndView("/tree_node", "nodeId", id);
	}

	@RequestMapping("/indexGenKey")
	public ModelAndView indexGenKey() {

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

		OauthUserEntity user = userService.getUserById();
		return new ModelAndView("index", "user", user);
	}

	@RequestMapping("/users")
	@ResponseBody
	public Object users() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", 1);
		map.put("end", 10);
		List<OauthUserEntity> users = oauthUserServiceImplTwo.qryUserByPage(map);
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

		OauthUserEntity user = oauthUserServiceImplTwo.getUserById();
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
			oauthUserServiceImplTwo.insertUser(map);
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
		oauthUserServiceImplTwo.insertUserBatch(map2);
	}
}
