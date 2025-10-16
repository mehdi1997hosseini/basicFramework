package ir.mehdihosseini.basicframework.base.config.filterChain.localizationContext.tenant;

import org.springframework.core.NamedThreadLocal;

public class BasicTenantContext {

    private static final ThreadLocal<String> tenantHolder = new NamedThreadLocal<>("_Tenant_Name");

    public static void setTenant(String tenantID) {
        tenantHolder.set(tenantID);
    }

    public static String getTenant() {
        return tenantHolder.get();
    }

    public static void clearTenant() {
        tenantHolder.remove();
    }

}
