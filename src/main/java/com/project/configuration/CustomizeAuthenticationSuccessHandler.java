package com.project.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// set our response to OK status
		response.setStatus(HttpServletResponse.SC_OK);

		boolean admin = false;
		boolean patient = false;
		boolean doctor = false;

		System.out.println("AT onAuthenticationSuccess(...) function!");

		for (GrantedAuthority auth : authentication.getAuthorities()) {
			if ("ROLE_ADMIN".equals(auth.getAuthority())) {
				admin = true;
			} else if ("ROLE_PATIENT".equals(auth.getAuthority())) {
				patient= true;
			} else if ("ROLE_DOCTOR".equals(auth.getAuthority())) {
				doctor = true;
			} else {

			}
		}

		if (admin) {
			System.out.println("user is admin");
			response.sendRedirect("/admin/index");
		} else if (patient) {
			System.out.println("user is patient");
			response.sendRedirect("/patient/index");
		} else if (doctor) {
			System.out.println("user is doctor");
			response.sendRedirect("/doctor/index");
		} else {
			System.out.println("user is anonymous");
			response.sendRedirect("/403");
		}
	}
}
