package com.econny.webapp.OxygenAction;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import com.econny.webapp.OxygenEntity.ApiResultEntity;

@Controller
@RequestMapping("/clientService")
public class ClientAction {
	
	@RequestMapping(value = "/fileUploadSingle", method = RequestMethod.POST)
	public RedirectView fileUploadSingle(){
		//redirect is a get request
		//最后的参数为false代表以post方式提交请求 
		return new RedirectView("http://localhost:8090/OxygenWeb/commonService/fileUploadSingle", true, false, false);
	}
	
	@RequestMapping(value = "/testCrossDomain", method = RequestMethod.POST)
	public void testCrossDomain(){
		//redirect is a get request
		RestTemplate restTemplate = new RestTemplate();
		Map<String ,Object> urlVariables = new HashMap<String ,Object>(); 
		urlVariables.put("name", "peter"); 
		urlVariables.put("password", "123456"); 
		ApiResultEntity quote = restTemplate.postForObject("http://localhost:8090/oauthUserService/login", HttpMethod.POST, ApiResultEntity.class, urlVariables);
	}

	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET })
	@RequestMapping(value = "/backendCrossDomainHost", method = RequestMethod.GET)
	@ResponseBody
	public Object backendCrossDomanHost(){
		//redirect is a get request
		Map<String ,Object> urlVariables = new HashMap<String ,Object>(); 
		urlVariables.put("name", "peter"); 
		urlVariables.put("password", "123456"); 
		return new ApiResultEntity(true, urlVariables, 200, "");
	}
	
	@RequestMapping(value = "/testCrossDomainGet", method = RequestMethod.GET)
	@ResponseBody
	public Object testCrossDomainGet(){
		//redirect is a get request
		RestTemplate restTemplate = new RestTemplate();
		Map<String ,Object> urlVariables = new HashMap<String ,Object>(); 
		urlVariables.put("name", "peter"); 
		urlVariables.put("password", "123456"); 
		ApiResultEntity quote = restTemplate.getForObject("http://localhost:8090/OxygenWeb/clientService/backendCrossDomainHost", ApiResultEntity.class);
		return quote;
		/*MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("name", "peter");
		map.add("password", "123456");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		final HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map ,
		        headers);

		try {
		    RestTemplate restTemplate = new RestTemplate();
		    org.springframework.http.ResponseEntity<String> responseEntity = (org.springframework.http.ResponseEntity) restTemplate.exchange(
		            "http://localhost:8090/oauthUserService/login", HttpMethod.POST, entity,
		            String.class);

		    if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
		        String temp = responseEntity.getBody();
		        System.out.println("jsonObject " + temp);
		    }
		} catch (final HttpClientErrorException | HttpServerErrorException e) {
			e.printStackTrace();
		    throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getResponseBodyAsString());
		}*/
	}
	
	@RequestMapping(value = "/testCrossDomainGetLogin", method = RequestMethod.GET)
	@ResponseBody
	public Object testCrossDomainGetLogin(){
		
		/*RestTemplate restTemplate = new RestTemplate();
		Map<String ,Object> uriVariables = new HashMap<String ,Object>(); 
		uriVariables.put("name", "peter"); 
		uriVariables.put("password", "123456"); 
		ResponseEntity<ApiResultEntity> responseEntity = restTemplate.postForEntity("http://localhost:8090/OxygenWeb/oauthUserService/login", HttpMethod.POST, ApiResultEntity.class, uriVariables);
		return responseEntity.getBody();*/
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("name", "peter");
		map.add("password", "123456");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		final HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map ,
		        headers);

		try {
		    RestTemplate restTemplate = new RestTemplate();
		    ResponseEntity<String> responseEntity = restTemplate.exchange(
		            "http://localhost:8090/OxygenWeb/oauthUserService/login", HttpMethod.POST, entity,
		            String.class);

		    if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
		        String temp = responseEntity.getBody();
		        System.out.println("jsonObject " + temp);		        
		        return temp;
		    }
		    
		    if (responseEntity.getStatusCode() == HttpStatus.OK) {
		        String temp = responseEntity.getBody();
		        System.out.println("jsonObject " + temp);		        
		        return temp;
		    }
		} catch (final HttpClientErrorException | HttpServerErrorException e) {
		    throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getResponseBodyAsString());
		}
		
		return "";
	}

}
