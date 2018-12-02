package com.project.millatinventory.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class ExceptionHandler implements Filter{
	
static final Logger logger= Logger.getLogger(ExceptionHandler.class);
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request= (HttpServletRequest)arg0;
		
		
		try{
			chain.doFilter(arg0, arg1);	
		}catch (Throwable throwable){
			StringWriter stringWriter= new StringWriter();
			PrintWriter printWriter= new PrintWriter(stringWriter);
			throwable.printStackTrace(printWriter);
			String stackTraceAsString= stringWriter.toString(); 

			logger.error("Error Occurred "+ stackTraceAsString);
			request.getRequestDispatcher("/error.jsp").forward(arg0, arg1);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}
}
