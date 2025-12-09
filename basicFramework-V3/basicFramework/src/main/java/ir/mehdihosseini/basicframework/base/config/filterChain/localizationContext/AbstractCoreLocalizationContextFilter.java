package ir.mehdihosseini.basicframework.base.config.filterChain.localizationContext;

import ir.mehdihosseini.basicframework.base.config.filterChain.localizationContext.tenant.BasicTenantContext;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public abstract class AbstractCoreLocalizationContextFilter implements Filter  {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {

            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String tenantHeader = request.getHeader("X-Tenant-ID");
            if (tenantHeader == null || tenantHeader.isEmpty()) {
                BasicTenantContext.setTenant("TESTER_SYSTEM");
            } else {
                BasicTenantContext.setTenant(tenantHeader);
            }

            filterChain.doFilter(servletRequest, servletResponse);

        } finally {
            BasicTenantContext.clearTenant();
        }
    }

}
