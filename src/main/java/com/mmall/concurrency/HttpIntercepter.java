package com.mmall.concurrency;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mmall.concurrency.example.threadlocal.RequestHolder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpIntercepter extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("HttpIntercepter.preHandle()");
		return true;
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		RequestHolder.remove();
		log.info("HttpIntercepter.afterCompletion()");
		return;
	}
	
	
}
