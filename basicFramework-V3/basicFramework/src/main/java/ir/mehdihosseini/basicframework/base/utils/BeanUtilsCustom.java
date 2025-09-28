package ir.mehdihosseini.basicframework.base.utils;

import ir.mehdihosseini.basicframework.base.entity.BasicEntity;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;

public final class BeanUtilsCustom extends BeanUtils {
    private BeanUtilsCustom() {
    }

    public static <S extends BasicEntity<ID>, B, ID> void copyDeepProperties(S source, S target, Class<B> stopClass) {
        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();

        while (sourceClass != null && !sourceClass.equals(stopClass)) {
            Field[] fields = sourceClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Field targetField = targetClass.getDeclaredField(field.getName());
                    targetField.setAccessible(true);
                    targetField.set(target, field.get(source));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    // اگر فیلد موجود نیست یا مشکلی در دسترسی وجود دارد، آن را نادیده بگیرید
                }
            }
            sourceClass = sourceClass.getSuperclass();
        }
    }

    public static <S, T> void updateEntityDynamically(S source, T target) {
        if (source == null || target == null) {
            return;
        }

        Field[] sourceFields = source.getClass().getDeclaredFields();

        for (Field sourceField : sourceFields) {
            sourceField.setAccessible(true);

            try {
                Object sourceValue = sourceField.get(source);
                if (sourceValue == null) {
                    continue; // مقدار null نادیده گرفته می‌شود
                }

                String fieldName = sourceField.getName();

                try {
                    Field targetField = target.getClass().getDeclaredField(fieldName);
                    targetField.setAccessible(true);

                    Class<?> targetType = targetField.getType();

                    // پشتیبانی از Enum
                    if (targetType.isEnum() && sourceValue instanceof String) {
                        String strValue = (String) sourceValue;

                        Object enumValue = null;
                        for (Object constant : targetType.getEnumConstants()) {
                            if (((Enum<?>) constant).name().equalsIgnoreCase(strValue)) {
                                enumValue = constant;
                                break;
                            }
                        }

                        if (enumValue == null) {
                            throw new IllegalArgumentException("Invalid enum value for field: " + fieldName);
                        }

                        targetField.set(target, enumValue);
                    }
                    // اگر نوع یکسان بود یا قابل انتساب بود
                    else if (targetType.isAssignableFrom(sourceValue.getClass())) {
                        targetField.set(target, sourceValue);
                    }

                } catch (NoSuchFieldException e) {
                    // اگر فیلدی با نام مشابه در تارگت پیدا نشد، صرف نظر می‌کنیم
                    throw new IllegalArgumentException("error when converted : " + e.getMessage());
                }

            } catch (IllegalAccessException e) {
                // دسترسی غیرمجاز - صرف نظر
                throw new IllegalArgumentException("error when read fields in class BeanUtilsCustom by message error : " + e.getMessage());
            }
        }
    }

}
