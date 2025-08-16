package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService;

import com.mehdihosseini.framework.basicframework.service.entity.BasicEntityService;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.dto.ExternalOrganizationAuthServiceDto;
import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.structure.ExternalOrganizationInfoStructure;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.dto.SaveOrUpdateExternalOrganizationAuthServiceDto;

import java.util.List;

/**
 * =============================================================================================
 * External Organization Auth Service
 * سرویس مدیریت توکن‌های سازمان‌های بیرونی
 * =============================================================================================
 *
 * @Description :
 * This interface provides operations for managing external organizations and their associated access tokens.
 * It extends the BasicService for basic CRUD functionality and adds specialized methods for token lifecycle,
 * caching, and integration control of external systems.
 * این اینترفیس عملیات مربوط به مدیریت سازمان‌های بیرونی و توکن‌های دسترسی مربوط به آن‌ها را ارائه می‌دهد.
 * این اینترفیس از BasicService ارث‌بری می‌کند تا عملیات پایه (CRUD) را شامل شود و همچنین متدهای خاصی برای
 * کنترل چرخه‌ی حیات توکن، کش‌کردن داده‌ها، و تعامل دستی با سیستم‌های بیرونی فراهم می‌سازد.
 * کاربرد:
 * اینترفیس زمانی کاربرد دارد که سامانه با سازمان‌های بیرونی (مانند بیمه، بانک، سرویس دولتی و...) در تعامل باشد
 * و لازم باشد که اطلاعات دسترسی یا احراز هویت آن‌ها (مانند توکن) را ذخیره، به‌روزرسانی، کش یا قطع موقت کرد.
 */
public interface ExternalOrganizationAuthService extends BasicEntityService<ExternalOrganizationAuthServiceEntity, String> {
    ExternalOrganizationAuthServiceDto save(SaveOrUpdateExternalOrganizationAuthServiceDto externalOrganization);
    ExternalOrganizationAuthServiceDto update(SaveOrUpdateExternalOrganizationAuthServiceDto externalOrganization);
    List<ExternalOrganizationAuthServiceDto> findAllToDto();

    <E extends ExternalOrganizationInfoStructure> ExternalOrganizationAuthServiceEntity findExternalOrganizationByOrgName(E externalOrganizationInfo);

}
