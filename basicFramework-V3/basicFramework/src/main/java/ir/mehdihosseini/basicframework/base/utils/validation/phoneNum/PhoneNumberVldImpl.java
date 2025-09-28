package ir.mehdihosseini.basicframework.base.utils.validation.phoneNum;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberVldImpl implements ConstraintValidator<PhoneNumber, String> {
    private PhoneTypeVld phoneTypeVld;
    private String message;

    @Override
    public void initialize(PhoneNumber phone) {
        this.phoneTypeVld = phone.phoneType();
        this.message = phone.message();
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        boolean isValid = false;

        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }

        isValid = switch (phoneTypeVld) {
            case MOBILE -> checkMobileNumber(phoneNumber);
            case TELEPHONE -> checkTelephoneNumber(phoneNumber);
        };

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();

        return isValid;
    }

    public boolean checkMobileNumber(String mobileNumber) {
        String regex = "^09\\d{9}$"; // الگوی شماره موبایل ایران
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mobileNumber);
        if (!matcher.matches()) {
            this.message = "THE ENTERED MOBILE NUMBER IS NOT VALID.";
            return false;
        }
        return true;
    }

    public boolean checkTelephoneNumber(String homeNumber) {
        String regex = "^\\d{8}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(homeNumber);
        if (!matcher.matches()) {
            this.message = "THE ENTERED TELEPHONE NUMBER IS NOT VALID.";
            return false;
        }
        return true;
    }


}
