package com.econny.webapp.OxygenFilters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.econny.webapp.OxygenEntity.OauthUserSessionEntity;
import com.econny.webapp.OxygenService.impl.OauthUserSessionServiceImpl;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {
	
	@Autowired
	OauthUserSessionServiceImpl oauthUserSessionServiceImpl;
    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		// HttpServletResponse httpReponse = (HttpServletResponse) response;
		Cookie[] cookies = httpRequest.getCookies();
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
                    /*cookie.setValue("");
                    cookie.setMaxAge(0);
                    httpReponse.addCookie(cookie);*/
                }
            }
            
            if(count == 0){
            	throw new ServletException();
            }
        }else{
        	throw new ServletException();
        }
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
