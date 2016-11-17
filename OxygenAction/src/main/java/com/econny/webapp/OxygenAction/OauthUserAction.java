package com.econny.webapp.OxygenAction;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.econny.webapp.OxygenEntity.ApiResultEntity;
import com.econny.webapp.OxygenEntity.OauthUserEntity;
import com.econny.webapp.OxygenEntity.OauthUserSessionEntity;
import com.econny.webapp.OxygenEnum.ServicePermission;
import com.econny.webapp.OxygenService.impl.OauthUserServiceImpl;
import com.econny.webapp.OxygenService.impl.OauthUserSessionServiceImpl;

@Controller
@RequestMapping("/oauthUserService")
public class OauthUserAction {

	@Autowired
	OauthUserServiceImpl oauthUserServiceImpl;
	
	@Autowired
	OauthUserSessionServiceImpl oauthUserSessionServiceImpl;

	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST })
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String name, @RequestParam(required = true) String password,
			@RequestParam(required = true) String gender, @RequestParam(required = true) String phoneNumber,
			@RequestParam(required = true) String email, @RequestParam(required = true) String description)
			throws IOException {

		OauthUserEntity user = new OauthUserEntity();
		user.setName(name);
		user.setPassword(password);
		user.setGender(gender);
		user.setPhoneNumber(phoneNumber);
		user.setEmail(email);
		user.setDescription(description);

		try {
			oauthUserServiceImpl.save(user);
		} catch (Exception e) {
			return new ApiResultEntity(true, e, 500, "");
		}

		return new ApiResultEntity(true, user, 200, "");
	}

	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST })
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String id, @RequestParam(required = true) String name,
			@RequestParam(required = true) String password, @RequestParam(required = true) String gender,
			@RequestParam(required = true) String phoneNumber, @RequestParam(required = true) String email,
			@RequestParam(required = true) String description) throws IOException {

		OauthUserEntity user = new OauthUserEntity();
		user.setId(id);
		user.setName(name);
		user.setPassword(password);
		user.setGender(gender);
		user.setPhoneNumber(phoneNumber);
		user.setEmail(email);
		user.setDescription(description);

		try {
			oauthUserServiceImpl.update(user);
		} catch (Exception e) {
			return new ApiResultEntity(true, e, 500, "");
		}

		return new ApiResultEntity(true, user, 200, "");
	}

	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST })
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object update(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String id) throws IOException {

		OauthUserEntity user = new OauthUserEntity();
		user.setId(id);

		try {
			oauthUserServiceImpl.delete(user);
		} catch (Exception e) {
			return new ApiResultEntity(true, e, 500, "");
		}

		return new ApiResultEntity(true, user, 200, "");
	}

	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST })
	@RequestMapping(value = "/checkAuthorization", method = RequestMethod.POST)
	@ResponseBody
	public Object checkAuthorization(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String name, @RequestParam(required = true) String password) throws IOException {

		OauthUserEntity user = new OauthUserEntity();
		user.setName(name);
		user.setPassword(password);
		//check file service
		user.setServiceType(ServicePermission.FileServicePermission.getPermission());
		
		Integer count = oauthUserServiceImpl.checkUserPermission(user);

		return new ApiResultEntity(true, count, 200, "");
	}

	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST })
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Object login(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String name, @RequestParam(required = true) String password) throws IOException {

		OauthUserEntity user = new OauthUserEntity();
		user.setName(name);
		user.setPassword(password);
		List<OauthUserEntity> userResult = oauthUserServiceImpl.findList(user);
		if(userResult.size()>0){
			OauthUserSessionEntity oauthUserSessionEntity = new OauthUserSessionEntity();
			oauthUserSessionEntity.setId(userResult.get(0).getId());
			oauthUserSessionEntity.setUser(userResult.get(0));
			oauthUserSessionServiceImpl.save(oauthUserSessionEntity);
			
			return new ApiResultEntity(true, userResult.get(0), 200, "");
		}

		return new ApiResultEntity(false, null, 404, "");
	}
	
	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST })
	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
	@ResponseBody
	public Object checkLogin(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String id) throws IOException {

			OauthUserSessionEntity oauthUserSessionEntity = oauthUserSessionServiceImpl.read(id);
			
			return new ApiResultEntity(true, oauthUserSessionEntity, 200, "");
	}

}
