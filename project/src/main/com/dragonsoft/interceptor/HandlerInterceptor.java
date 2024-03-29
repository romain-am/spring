package main.com.dragonsoft.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class HandlerInterceptor extends HandlerInterceptorAdapter {
	

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

	}

	@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response,Object handler, Exception ex) 
					throws Exception {
		
		if (ex != null){
			ex.printStackTrace();
		}
	}
}