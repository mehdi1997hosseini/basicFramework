package ir.mehdihosseini.basicframework.utils.validation.nin;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NINVldImpl.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NIN {
    String message() default "interface national code validation";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    NIN_TypeVld NIN_type();

}
