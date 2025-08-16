package com.mehdihosseini.framework.basicframework.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.ResourcePropertySource;

/**
 * -----------------------------------------------------------------------------
 * <p>English:</p>
 * FrameworkPropertyPostProcessor is a Spring EnvironmentPostProcessor that loads
 * a default property file named "framework-defaults.properties" from the classpath.
 * It adds this file to the environment with low priority so that user-defined
 * properties can override the default values.
 * <p>
 * Use case:
 * This is useful in framework-based setups where default configurations should be
 * applied unless explicitly overridden by the application developer.
 * <p>
 * Notes:
 * - The property file is optional; if it's not found, the system logs the error and continues.
 * - The order is set to LOWEST_PRECEDENCE to ensure it's applied after all other sources.
 * -----------------------------------------------------------------------------
 * <p>فارسی:</p>
 * این کلاس یک EnvironmentPostProcessor در Spring است که فایل "framework-defaults.properties"
 * را از مسیر کلاس‌پث بارگذاری می‌کند و آن را به محیط تنظیمات اضافه می‌نماید.
 * اولویت اضافه‌سازی آن پایین است تا مقادیر تعریف‌شده توسط کاربر بتوانند آن را override کنند.
 *
 * <p>کاربرد:</p>
 * مناسب برای ساختارهای فریم‌ورکی است که تنظیمات پیش‌فرض ارائه می‌دهند، مگر اینکه توسط برنامه‌نویس
 * به‌صورت مشخص مقادیر جدیدی تعریف شود.
 *
 * <p> نکات:</p>
 * - بارگذاری این فایل اختیاری است؛ در صورت عدم وجود، تنها یک پیام خطا در خروجی چاپ می‌شود و ادامه می‌دهد.
 * - اولویت LOWEST_PRECEDENCE تضمین می‌کند که بعد از سایر منابع پیکربندی اجرا شود.
 * -----------------------------------------------------------------------------
 */
public final class FrameworkPropertyPostProcessor implements EnvironmentPostProcessor, Ordered {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        try {
            PropertySource<?> propertySource =
                    new ResourcePropertySource(new ClassPathResource("framework-defaults.properties"));
            environment.getPropertySources().addLast(propertySource); // اولویت کمتر = قابل override
        } catch (Exception ex) {
            System.err.println("Failed to load framework-defaults.properties: " + ex.getMessage());
        }
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
