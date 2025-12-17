package ir.mehdihosseini.basicframework.base.config.filterChain.core;

import ir.mehdihosseini.basicframework.base.config.filterChain.rateLimit.RateLimitFilterConfig;
import ir.mehdihosseini.basicframework.base.config.properties.ManagerPropertiesConfig;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component("coreFilter")
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class AbstractCoreFilterChainConfig implements Filter {

    private final ManagerPropertiesConfig propertiesConfig;

    public AbstractCoreFilterChainConfig(ManagerPropertiesConfig propertiesConfig) {
        this.propertiesConfig = propertiesConfig;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse
            , FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        log.info("FilterChainConfig.doFilter");
        if (HttpMethod.OPTIONS.name().equals(request.getMethod()) || HttpMethod.HEAD.name().equals(request.getMethod()) ||
                HttpMethod.PATCH.name().equals(request.getMethod()) || HttpMethod.TRACE.name().equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        } else {
            filterChain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Bean
    @ConditionalOnProperty(prefix = "manager.rate-limit", name = "is-enable", havingValue = "true")
    public FilterRegistrationBean<?> rateLimitFilter() {
        FilterRegistrationBean<RateLimitFilterConfig> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new RateLimitFilterConfig(propertiesConfig.getRateLimit().getMaxNum(),
                propertiesConfig.getRateLimit().getMaxPerSecond()));

        registrationBean.setUrlPatterns(List.of("/app/*"));
        return registrationBean;
    }

}
