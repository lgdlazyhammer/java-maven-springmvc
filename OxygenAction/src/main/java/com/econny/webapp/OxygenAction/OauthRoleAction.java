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
import com.econny.webapp.OxygenEntity.OauthRoleEntity;
import com.econny.webapp.OxygenService.impl.OauthRoleServiceImpl;

@Controller
@RequestMapping("/oauthRoleService")
public class OauthRoleAction {

	@Autowired
	OauthRoleServiceImpl oauthRoleServiceImpl;

	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST })
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String userId, @RequestParam(required = true) String description)
			throws IOException {

		OauthRoleEntity role = new OauthRoleEntity();
		role.setUserId(userId);
		role.setDescription(description);

		try {
			oauthRoleServiceImpl.save(role);
		} catch (Exception e) {
			return new ApiResultEntity(true, e, 500, "");
		}

		return new ApiResultEntity(true, role, 200, "");
	}

	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST })
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String id, @RequestParam(required = true) String userId,
			@RequestParam(required = true) String description) throws IOException {

		OauthRoleEntity role = new OauthRoleEntity();
		role.setUserId(userId);
		role.setDescription(description);

		try {
			oauthRoleServiceImpl.update(role);
		} catch (Exception e) {
			return new ApiResultEntity(true, e, 500, "");
		}

		return new ApiResultEntity(true, role, 200, "");
	}

	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST })
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object update(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String id) throws IOException {

		OauthRoleEntity role = new OauthRoleEntity();
		role.setId(id);

		try {
			oauthRoleServiceImpl.delete(role);
		} catch (Exception e) {
			return new ApiResultEntity(true, e, 500, "");
		}

		return new ApiResultEntity(true, role, 200, "");
	}

	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST })
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public Object findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {

		List<OauthRoleEntity> list = null;

		try {
			list = oauthRoleServiceImpl.findList(new OauthRoleEntity());
		} catch (Exception e) {
			return new ApiResultEntity(true, e, 500, "");
		}

		return new ApiResultEntity(true, list, 200, "");
	}
}
