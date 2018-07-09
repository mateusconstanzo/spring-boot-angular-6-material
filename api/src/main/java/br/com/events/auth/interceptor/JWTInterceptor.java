package br.com.events.auth.interceptor;

import br.com.events.auth.SecurityConstants;
import br.com.events.auth.SecurityHolder;
import br.com.events.auth.exception.ForbiddenException;
import br.com.events.auth.exception.UnauthorizedException;
import br.com.events.auth.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class JWTInterceptor extends HandlerInterceptorAdapter {

    private static final String EVENT_URI = "/event";

    @Autowired
    private JWTService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!request.getRequestURI().contains(EVENT_URI) || request.getMethod().contains("OPTIONS")) {
            return true;
        }

        Optional<String> header = getBearerToken(request);

        if (header.isPresent()) {

            Optional<String> user = jwtService.valid(header.get());

            if (user.isPresent()) {

                SecurityHolder.setUserId(user.get());

                return true;

            }

            throw new UnauthorizedException("Unauthorized: Format is Authorization: Bearer [token]");

        }

        throw new ForbiddenException("Forbidden: No Authorization header was found.");
    }

    private Optional<String> getBearerToken(HttpServletRequest request) {

        String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);

        if (authHeader != null && authHeader.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            return Optional.of(authHeader.substring(SecurityConstants.TOKEN_PREFIX.length()));
        }

        return Optional.empty();
    }

}
