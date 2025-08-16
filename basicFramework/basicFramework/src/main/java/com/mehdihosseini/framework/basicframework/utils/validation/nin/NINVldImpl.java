package com.mehdihosseini.framework.basicframework.utils.validation.nin;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NINVldImpl implements ConstraintValidator<NIN, String> {

    private final static String isNotValidSSN[] = new String[]{
            "0000000000",
            "1111111111",
            "2222222222",
            "3333333333",
            "4444444444",
            "5555555555",
            "6666666666",
            "7777777777",
            "8888888888",
            "9999999999",
    };
    private NIN_TypeVld nINType;
    private String message;

    @Override
    public void initialize(NIN NIN) {
        this.nINType = NIN.NIN_type();
        this.message = NIN.message();
    }

    @Override
    public boolean isValid(String nin, ConstraintValidatorContext context) {
        boolean isValid = false;

        if (nin == null || nin.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
            return false;
        }

        switch (nINType) {
            case SSN_IR:
                isValid = checkSSN_IR(nin);
                break;
            case SSN_COMPANY:
                isValid = check_SSN_company(nin);
                break;
            case PASSPORT_NO:
                isValid = check_PASSPORT_NO(nin);
                break;
            case FIDA:
                isValid = check_FIDA(nin);
                break;
        }

        return isValid;
    }

    public boolean checkSSN_IR(String ssn) {
        //check length
        if (ssn.length() != 10)
            return false;

        char[] breakNationalCode = ssn.toCharArray();
        int length = breakNationalCode.length;
        int result = 0;
        int numberIndex = 0;
        for (int start = 0; start < length - 1; start++) {
            numberIndex = length - start;
            result += (Character.getNumericValue(breakNationalCode[start]) * numberIndex);
        }
        int numberTotal = result % 11;
        if (numberTotal > 2) {
            int number = 11 - numberTotal;
            return number == Character.getNumericValue(breakNationalCode[9]);
        } else return numberTotal == Character.getNumericValue(breakNationalCode[9]);
    }

    public boolean check_SSN_company(String ssn) {
        //check length
        if (ssn.length() != 11 || !ssn.matches("\\d+"))
            return false;

        char[] breakNationalCode = ssn.toCharArray();
        int numberCheck = breakNationalCode[ssn.length() - 1];

        int numberPlus = breakNationalCode[ssn.length() - 2] + 2;
        int[] numberTax = {29, 27, 23, 19, 17, 29, 27, 23, 19, 17};

        int total = 0;

        for (int start = 0; start <= breakNationalCode.length - 2; start++) {
            total += (Character.getNumericValue(breakNationalCode[start]) + numberPlus) * numberTax[start];
        }
        total = total % 11;
        if (total == 10) {
            total = 0;
        }

        return total == numberCheck;
    }

    public boolean check_PASSPORT_NO(String passportNO) {
        return false;
    }

    private boolean check_FIDA(String fidaCode) {
        return false;
    }


}
