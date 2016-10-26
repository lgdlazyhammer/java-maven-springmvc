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
import com.econny.webapp.OxygenEntity.OauthServiceEntity;
import com.econny.webapp.OxygenService.impl.OauthServiceServiceImpl;

@Controller
@RequestMapping("/oauthServiceService")
public class OauthServiceAction {

	@Autowired
	OauthServiceServiceImpl oauthServiceServiceImpl;

	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST })
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String roleId, @RequestParam(required = true) String description)
			throws IOException {

		OauthServiceEntity service = new OauthServiceEntity();
		service.setRoleId(roleId);
		service.setDescription(description);

		try {
			oauthServiceServiceImpl.save(service);
		} catch (Exception e) {
			return new ApiResultEntity(true, e, 500, "");
		}

		return new ApiResultEntity(true, service, 200, "");
	}

	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST })
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String id, @RequestParam(required = true) String roleId,
			@RequestParam(required = true) String description) throws IOException {

		OauthServiceEntity service = new OauthServiceEntity();
		service.setRoleId(roleId);
		service.setDescription(description);

		try {
			oauthServiceServiceImpl.update(service);
		} catch (Exception e) {
			return new ApiResultEntity(true, e, 500, "");
		}

		return new ApiResultEntity(true, service, 200, "");
	}

	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST })
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object update(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String id) throws IOException {

		OauthServiceEntity service = new OauthServiceEntity();
		service.setId(id);

		try {
			oauthServiceServiceImpl.delete(service);
		} catch (Exception e) {
			return new ApiResultEntity(true, e, 500, "");
		}

		return new ApiResultEntity(true, service, 200, "");
	}

	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST })
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	@ResponseBody
	public Object findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {

		List<OauthServiceEntity> list = null;

		try {
			list = oauthServiceServiceImpl.findList(new OauthServiceEntity());
		} catch (Exception e) {
			return new ApiResultEntity(true, e, 500, "");
		}

		return new ApiResultEntity(true, list, 200, "");
	}
}
