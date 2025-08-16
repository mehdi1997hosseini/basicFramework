package com.mehdihosseini.framework.basicframework.thirdParty.cache.authConfigCache;

import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.structure.ExternalOrganizationInfoStructure;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.ExternalOrganizationAuthServiceEntity;

import java.util.List;
import java.util.Map;

/**
 * =============================================================================================
 * External Organization Cache Service
 * سرویس کش توکن سازمان‌های بیرونی
 * =============================================================================================
 *
 * @Description :
 * This interface is responsible for managing the in-memory cache layer for external organization tokens.
 * It enables fast access, synchronization, and control over token data used in integration scenarios.
 * <p>
 * این اینترفیس مسئول مدیریت لایه کش حافظه‌ای (Cache) برای توکن‌های سازمان‌های بیرونی است.
 * با استفاده از اینترفیس، دسترسی سریع، هماهنگی، و کنترل مؤثر روی اطلاعات توکن‌های مرتبط با سیستم‌های بیرونی
 * امکان‌پذیر می‌گردد.
 * <p>
 * ⚙️ کاربرد:
 * زمانی که بخواهیم اطلاعات توکن‌ها برای سازمان‌های بیرونی به‌صورت لحظه‌ای در حافظه نگه داشته شوند (برای جلوگیری از
 * دسترسی مکرر به دیتابیس یا منابع کند خارجی)، از این سرویس استفاده می‌کنیم.
 * <p>
 * Typical Use-Cases:
 * - Reducing DB load by caching token data
 * - Synchronizing token info on-demand
 * - Refreshing or evicting token data for external systems
 */
public interface ExternalOrganizationAuthCatchService {
    // CRUD
    Boolean saveOrUpdate(ExternalOrganizationAuthServiceEntity externalOrganizationAuthServiceEntity);
    Boolean isExternalOrganizationExist(String extOrgName);
    <E extends ExternalOrganizationInfoStructure>Boolean isExternalOrganizationExist(E extOrgName);
    Map<String, ExternalOrganizationAuthServiceEntity> findAllExternalOrganization();
    ExternalOrganizationAuthServiceEntity findExternalOrganizationByExtOrgName(String extOrgName);
    <E extends ExternalOrganizationInfoStructure>ExternalOrganizationAuthServiceEntity findExternalOrganizationByExtOrgName(E extOrgName);


    /** ------ operation ------ */
    Boolean refreshExternalOrganizationByEntity(ExternalOrganizationAuthServiceEntity externalOrganizationAuthServiceEntity);
    Boolean removeExternalOrganizationFromCatch(ExternalOrganizationAuthServiceEntity externalOrganizationAuthServiceEntity);
    void refreshAllCatch(List<ExternalOrganizationAuthServiceEntity> findAllExtOrg);

}
