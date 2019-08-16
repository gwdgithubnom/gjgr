package org.ose.javase.design.pattern;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Validator {
    public static void main(String[] args) {
        Map<FieldEnum, String> form1Input = new HashMap<FieldEnum, String>();
        form1Input.put(FieldEnum.NAME, null);
        form1Input.put(FieldEnum.RATE, "111.33");
        System.out.println(ValidatorUtils.validateForm1(form1Input));

        Map<FieldEnum, String> form2Input = new HashMap<FieldEnum, String>();
        form2Input.put(FieldEnum.NAME, "12345678910");
        form2Input.put(FieldEnum.RATE, "111.33");
        System.out.println(ValidatorUtils.validateForm2(form2Input));
    }
}

class ValidatorUtils {
    private static final FieldEnum[] FORM1_FIELDS = { FieldEnum.NAME, FieldEnum.RATE };
    private static final FieldEnum[] FORM2_FIELDS = { FieldEnum.NAME };

    public static Map<FieldEnum, String> validateForm1(Map<FieldEnum, String> input) {
        Map<FieldEnum, String> result = new HashMap<FieldEnum, String>();

        validate(input, FORM1_FIELDS, result);

        return result;
    }

    public static Map<FieldEnum, String> validateForm2(Map<FieldEnum, String> input) {
        Map<FieldEnum, String> result = new HashMap<FieldEnum, String>();

        validate(input, FORM2_FIELDS, result);

        return result;
    }

    private static void validate(Map<FieldEnum, String> input, FieldEnum[] fields,
                                 Map<FieldEnum, String> result) {
        for (FieldEnum field : fields) {
            String value = input.get(field);
            ValidatorInterf[] validators = ValidatorFactory.getValidators(field);
            if (validators != null) {
                for (ValidatorInterf validator : validators) {
                    String info = validator.validate(value);
                    if (info != null) {
                        result.put(field, info);
                        break;
                    }
                }
            }
        }
    }
}

class ValidatorFactory {
    private static final Map<FieldEnum, ValidatorInterf[]> VALIDATORS = new HashMap<FieldEnum, ValidatorInterf[]>();

    static {
        VALIDATORS.put(FieldEnum.NAME, new ValidatorInterf[] {
            new NotNullValidator("Please enter your name"),
            new MaxLengthValidator("Length of the name should not exceed 10 characters", 10) });

        VALIDATORS.put(FieldEnum.RATE, new ValidatorInterf[] {
            new NotNullValidator("Please enter the rate"),
            new RegularValidator("Please enter valid number", "^\\d\\d?\\.?\\d?\\d?$") });
    }

    public static ValidatorInterf[] getValidators(FieldEnum field) {
        return VALIDATORS.get(field);
    }

    private static final class NotNullValidator implements ValidatorInterf {
        private final String msg;

        public NotNullValidator(String msg) {
            this.msg = msg;
        }

        @Override
        public String validate(String value) {
            return isBlank(value) ? msg : null;
        }

        private boolean isBlank(String str) {
            int length;

            if ((str == null) || ((length = str.length()) == 0)) {
                return true;
            }

            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    private static final class MaxLengthValidator implements ValidatorInterf {
        private final String msg;
        private final int    length;

        public MaxLengthValidator(String msg, int size) {
            this.msg = msg;
            this.length = size;
        }

        @Override
        public String validate(String value) {
            if (value != null && value.length() > length) {
                return msg;
            }
            return null;
        }
    }

    private static final class RegularValidator implements ValidatorInterf {
        private final String  msg;
        private final Pattern pattern;

        public RegularValidator(String msg, String format) {
            this.msg = msg;
            this.pattern = Pattern.compile(format);
        }

        @Override
        public String validate(String value) {
            return pattern.matcher(value).matches() ? null : msg;
        }
    }
}

interface ValidatorInterf {
    /**
     * @param value
     * @return null if validation succeeds, error message if fails
     */
    String validate(String value);
}

enum FieldEnum {
    NAME("name"), RATE("rate");

    private String value;

    FieldEnum(String value) {
        this.value = value;
    }
}
