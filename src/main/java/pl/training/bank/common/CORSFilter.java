package pl.training.bank.common;

import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.springframework.http.HttpHeaders.*;

public class CORSFilter implements Filter {

    private static final String ALL = "*";
    private static final String MAX_AGE = "3600";
    private static final String ALLOWED_HEADERS = "Origin, X-Requested-With, Content-Type, Accept, Authorization";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        setResponseHeaders(httpResponse);
        if (isOptionsRequest(request)) {
            httpResponse.setStatus(SC_OK);
        } else {
            chain.doFilter(request, response);
        }
    }

    private void setResponseHeaders(HttpServletResponse response) {
        response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, ALL);
        response.setHeader(ACCESS_CONTROL_ALLOW_METHODS, ALL);
        response.setHeader(ACCESS_CONTROL_MAX_AGE, MAX_AGE);
        response.setHeader(ACCESS_CONTROL_ALLOW_HEADERS, ALLOWED_HEADERS);
    }

    private boolean isOptionsRequest(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        return HttpMethod.OPTIONS.name().equalsIgnoreCase(httpRequest.getMethod());
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }

}
