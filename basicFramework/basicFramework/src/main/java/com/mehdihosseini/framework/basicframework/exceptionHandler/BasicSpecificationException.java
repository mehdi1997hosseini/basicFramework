package com.mehdihosseini.framework.basicframework.exceptionHandler;

/**
 * -----------------------------------------------------------------------------
 * <p>English:</p>
 * BasicSpecificationException defines a common contract for application error types.
 * Classes (especially Enums) implementing this interface must provide:
 * - getErrorCode(): returns a code to identify the type of error
 * - getMessage(): returns the error message (can be key-based or localized)
 * <p>
 * This interface is useful for defining structured, type-safe error handling across the app.
 * -----------------------------------------------------------------------------
 * <p> فارسی:</p>
 * اینترفیس BasicSpecificationException یک قرارداد مشترک برای انواع خطا در برنامه تعریف می‌کند.
 * کلاس‌هایی که اینترفیس را پیاده‌سازی می‌کنند (مخصوصاً Enumها) باید متدهای زیر را پیاده‌سازی کنند:
 * - getErrorCode(): بازگرداندن کد یکتا برای شناسایی نوع خطا
 * - getMessage(): بازگرداندن پیام خطا (می‌تواند کلید متنی یا پیام محلی‌شده باشد)
 * <p>
 * اینترفیس فوق برای مدیریت ساختاریافته و نوع‌محور خطاها در کل برنامه بسیار مناسب است.
 * -----------------------------------------------------------------------------
 */
public interface BasicSpecificationException {
    /**
     * -----------------------------------------------------------------------------
     * <p>English:</p>
     * Returns the error code associated with the exception type.
     * -----------------------------------------------------------------------------
     * <p>فارسی:</p>
     * کد خطای مرتبط با نوع استثنا را باز می‌گرداند.
     * -----------------------------------------------------------------------------
     */
    String getErrorCode();
    /**
     * -----------------------------------------------------------------------------
     * <p>English:</p>
     * Returns the message or message key describing the error.
     * -----------------------------------------------------------------------------
     * <p>فارسی:</p>
     * پیام یا کلید پیام مربوط به نوع خطا را باز می‌گرداند.
     * -----------------------------------------------------------------------------
     */
    String getMessage();

}
