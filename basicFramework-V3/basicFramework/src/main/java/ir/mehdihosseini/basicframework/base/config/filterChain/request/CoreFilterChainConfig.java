package ir.mehdihosseini.basicframework.base.config.filterChain.request;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("coreFilter")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CoreFilterChainConfig implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        System.out.println("FilterChainConfig.doFilter");

        if (HttpMethod.OPTIONS.name().equals(request.getMethod()) || HttpMethod.HEAD.name().equals(request.getMethod()) ||
                HttpMethod.PATCH.name().equals(request.getMethod()) || HttpMethod.TRACE.name().equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        } else {
            filterChain.doFilter(request, response);
        }
        LocaleContextHolder.getLocale();

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
