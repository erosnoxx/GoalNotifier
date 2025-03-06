package com.erosnox.seeurun.infrastructure.config.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class SecurityMatchers {
    public static RequestMatcher[] getPublicEndpoints() {
        return new RequestMatcher[] {
                new AntPathRequestMatcher("/swagger-ui/**"),
                new AntPathRequestMatcher("/v3/api-docs/**"),
                new AntPathRequestMatcher("/auth/**")
        };
    }

    public static RequestMatcher[] getUserEndpoints() {
        return new RequestMatcher[] {
                new AntPathRequestMatcher("/users/**")
        };
    }
}
