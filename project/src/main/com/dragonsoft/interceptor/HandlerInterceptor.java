package main.com.dragonsoft.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class HandlerInterceptor extends HandlerInterceptorAdapter {
	private static final Logger LOG
    = LogManager.getLogger();
	

	@Override
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler) throws Exception {

		return true;
	}

	@Override
	public void postHandle(
			HttpServletRequest request, 
			HttpServletResponse response,
			Object handler, 
			ModelAndView modelAndView) throws Exception {
		//Implement logic

	}

	@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response,Object handler, Exception ex) 
					throws Exception {
		
		if (ex != null){
			LOG.warn(ex.getMessage());
		}
	}
}