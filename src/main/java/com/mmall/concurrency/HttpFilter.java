package com.mmall.concurrency;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.mmall.concurrency.example.threadlocal.RequestHolder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		log.info("do filter,{}, {}",Thread.currentThread().getId(),req.getServletPath());
		RequestHolder.add(Thread.currentThread().getId());
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}

}
