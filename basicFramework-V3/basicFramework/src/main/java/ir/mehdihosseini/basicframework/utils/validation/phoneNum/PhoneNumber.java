package ir.mehdihosseini.basicframework.utils.validation.phoneNum;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneNumberVldImpl.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {
    String message() default "the entered phoneNumber is not valid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    PhoneTypeVld phoneType() default PhoneTypeVld.MOBILE;
}
