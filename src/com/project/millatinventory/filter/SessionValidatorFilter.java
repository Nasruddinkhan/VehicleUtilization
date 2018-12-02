package com.project.millatinventory.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class SessionValidatorFilter implements Filter{
	
final static Logger logger = Logger.getLogger(SessionValidatorFilter.class);
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request= (HttpServletRequest)arg0;
		HttpSession session = request.getSession();
		
		Object attribute = session.getAttribute("User");
		if(null != attribute ){
			chain.doFilter(arg0, arg1);	
		}else
		{
			logger.info("Session Expired");
			request.getRequestDispatcher("/sessionExpired.jsp").forward(arg0, arg1);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}
}
