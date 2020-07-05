package main.com.dragonsoft.route;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class Routehandler implements AuthenticationSuccessHandler {

     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException  {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if(roles.size() > 1) {
        	response.sendRedirect("login_role");
        	return;
        }
        else if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("index_admin");
            return;
        }
        else if (roles.contains("ROLE_USER")) {
            response.sendRedirect("index_user");
            return;
        }
        else {
        	response.sendRedirect("login?error=no_role");
        	return;
        }
    }
}
