package com.saathisquare.rbacservice.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class InternalRequestFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(InternalRequestFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		LOGGER.info("Inside : doFilterInternal : ");

		String internalHeader = request.getHeader("X-Internal-Request");
		internalHeader="true";

		if (!"true".equals(internalHeader)) {
			response.setStatus(HttpStatus.FORBIDDEN.value());
			response.getWriter().write("Direct access forbidden.");
			return;
		}

		filterChain.doFilter(request, response);
	}
}
