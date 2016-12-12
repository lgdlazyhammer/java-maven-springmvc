package com.econny.webapp.OxygenAspect;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.econny.webapp.OxygenEntity.OauthUserSessionEntity;
import com.econny.webapp.OxygenService.impl.OauthUserSessionServiceImpl;

//声明这是一个组件
@Component
//声明这是一个切面Bean
@Aspect
public class LoginCheckAspect {
	
	@Autowired
	OauthUserSessionServiceImpl oauthUserSessionServiceImpl;
	
	@Before(value = "execution(* com.econny.webapp.OxygenAction.EconnyTreeAction.getByNodeId())")
    public void beforeMethod(JoinPoint point) throws ServletException {
        System.out.println("------test aop before");
        Object[] args = point.getArgs();
        HttpServletRequest request = (HttpServletRequest) args[0];
        Cookie[] cookies = request.getCookies();
        
		Integer count = 0;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("oxygenId".equals(cookie.getName())) {
                	count = 1;
                	if(StringUtils.isEmpty(cookie.getValue())){
						throw new ServletException();
                	}else{
                		//it seems that springmvc does not inject this service in filter
                		OauthUserSessionEntity oauthUserSessionEntity = oauthUserSessionServiceImpl.read(cookie.getValue());
                		if(StringUtils.isEmpty(oauthUserSessionEntity.getUser().getName())){
                			throw new ServletException();
                		}
                	}
                }
            }
            
            if(count == 0){
            	throw new ServletException();
            }
        }else{
        	throw new ServletException();
        }
    };
 
	//声明切入点  
	   /*@Pointcut("execution(* com.econny.webapp.OxygenAction.EconnyTreeAction.getByNodeId(..)) && args(request)")  
	   public void login(HttpServletRequest request){
		      System.out.println(" User " + "login check");  
	   };  
	  
	   @Before(value="login(*)")  
	   public void logBefore(HttpServletRequest request) throws ServletException{  
		   
		   Cookie[] cookies = request.getCookies();
			Integer count = 0;
	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	                if ("oxygenId".equals(cookie.getName())) {
	                	count = 1;
	                	if(StringUtils.isEmpty(cookie.getValue())){
							throw new ServletException();
	                	}else{
	                		//it seems that springmvc does not inject this service in filter
	                		OauthUserSessionEntity oauthUserSessionEntity = oauthUserSessionServiceImpl.read(cookie.getValue());
	                		if(StringUtils.isEmpty(oauthUserSessionEntity.getUser().getName())){
	                			throw new ServletException();
	                		}
	                	}
	                    cookie.setValue("");
	                    cookie.setMaxAge(0);
	                    httpReponse.addCookie(cookie);
	                }
	            }
	            
	            if(count == 0){
	            	throw new ServletException();
	            }
	        }else{
	        	throw new ServletException();
	        }
	   }  
	   @AfterReturning(value="login(*)")  
	   public void logSuc(String username){  
	      System.out.println("[Logger] User " + username + "login successfully");  
	   }  
	   @AfterThrowing(pointcut="login(*)",throwing="e")  
	   public void logFailure(RuntimeException e){  
	      System.out.println("[Logger] Exception:" + e.getMessage());  
	   }*/
}
