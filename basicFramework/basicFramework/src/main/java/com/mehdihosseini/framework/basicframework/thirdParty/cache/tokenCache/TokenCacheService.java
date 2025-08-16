package com.mehdihosseini.framework.basicframework.thirdParty.cache.tokenCache;

import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.structure.ExternalOrganizationInfoStructure;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.token.ExternalTokenDto;

import java.util.Map;

/**
 * =============================================================================================
 * Token Cache Service
 * سرویس کش توکن‌های سازمان‌های بیرونی
 * =============================================================================================
 *
 * @Description :
 * <p> En : This interface defines the contract for managing the in-memory token cache
 * of external organizations. It provides functionality to store, retrieve, clear,
 * and list all tokens used in external integrations.
 * </p>
 * <p> Fa : این اینترفیس مسئول تعریف عملیات اصلی مربوط به کش (Cache) توکن‌های سازمان‌های بیرونی است.
 * از این سرویس برای ذخیره‌سازی، بازیابی، حذف و مدیریت کامل توکن‌های موجود در حافظه موقت
 * استفاده می‌شود که در تعاملات بین‌سیستمی کاربرد دارد.
 * <p>
 * کاربردهای معمول (Use-Cases):
 * - کاهش بار دسترسی به دیتابیس با نگهداری توکن‌ها در حافظه
 * - پاسخ‌دهی سریع به درخواست‌هایی که نیاز به توکن دارند
 * - پاکسازی و ریست کش در زمان تغییرات سیستمی یا امنیتی
 */
public interface TokenCacheService {
    public void saveOrUpdateToken(String extOrgName, ExternalTokenDto tokenInfo);
    public <E extends ExternalOrganizationInfoStructure>void saveOrUpdateToken(E extOrgName, ExternalTokenDto tokenInfo);

    public ExternalTokenDto getToken(String extOrgName);
    public <E extends ExternalOrganizationInfoStructure>ExternalTokenDto getToken(E extOrgName);

    public void clearDataTokens();

    public Map<String, ExternalTokenDto> getAll();
}
