package ir.mehdihosseini.basicframework.utils;

import java.text.MessageFormat;
import java.util.List;

public final class TextUtils {
    private TextUtils() {
    }

    /**
     * @param text The text for convert to capitalize.
     * @EN : The first letter of each word should be uppercase and the rest should be lowercase.
     * @FA : حرف اول هر کلمه بزرگ و بقیه حروف کوچک باشند
     * @example : "salamManMehdi hosseini hastam" to "SalamManMehdi Hosseini Hastam"
     */
    public static String toCapitalize(String text) {
        if (text == null || text.isEmpty()) return text;
        String[] words = splitWords(text);
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            result.append(capitalizeWord(word)).append(" ");
        }

        return result.toString().trim(); // حذف فاصله اضافی انتهایی
    }

    /**
     * @param text The text for convert to camel case.
     * @EN Connect the words and start each new word with a capital letter.
     * @FA : کلمات به هم متصل شوند و هر کلمه جدید با حرف بزرگ شروع شود فقط اولین کلمه تماما با حروف کوچک است.
     */
    public static String toCamelCase(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        // رشته را به کلمات جدا شده با فاصله یا کاراکترهای خاص تقسیم کنید
        String[] words = text.split("[\\s_\\-]+");
        StringBuilder camelCaseString = new StringBuilder();

        // اولین کلمه را با حروف کوچک به نتیجه اضافه کنید
        camelCaseString.append(words[0].toLowerCase());

        // بقیه کلمات را به صورت Capitalize به رشته اضافه کنید
        for (int i = 1; i < words.length; i++) {
            camelCaseString.append(capitalizeWord(words[i]));
        }

        return camelCaseString.toString();
    }

//    public static String toSnakeCase(String text) {
//        if (text == null || text.isEmpty()) return text;
//        String[] words = splitWords(text);
//        return String.join("_", words).toLowerCase();
//    }

    /**
     * @param nameEntityClass The name entity class or name model.
     * @EN Words are separated by "_" and all letters are lowercase.
     * @FA : کلمات با "_" جدا شوند و همه حروف کوچک شوند.
     * @use : for name table
     * @example : "salamManMehdiHosseiniHastam" to "salam_Man_Mehdi_Hosseini_Hastam"
     */
    public static String toSnakeCase(String nameEntityClass) {
        return nameEntityClass.replaceAll("([A-Z])([A-Z][a-z])", "$1_$2")
                .replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }

    /**
     * @param word The text entered to method.
     * @EN An auxiliary method for capitalizing a word: the first letter is capitalized, the rest are small.
     * @FA : متد کمکی برای Capitalizing یک کلمه: حرف اول بزرگ، باقی کوچک.
     */
    private static String capitalizeWord(String word) {
        return (word == null || word.isEmpty()) ? null : word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    /**
     * @param text The text entered method
     * @EN Helper method to break string into words
     * @FA متد کمکی برای شکستن رشته به کلمات
     */
    private static String[] splitWords(String text) {
        return text.trim().split("\\s+");
    }

    /**
     * EN : Removing a text from the beginning of a string and returning the continuation of that string
     * FA : حذف یک متن از ابتدا یک رشته و برگرداندن ادامه آن رشته
     *
     * @example : "basicSourceCode\src\main\java\com\basicSourceCode" with "src\main\java" to "com\basicSourceCode"
     */
    public static String removeBeforeAndIncludingKeyword(String input, String keyword) {
        int index = input.indexOf(keyword); // پیدا کردن مکان کلمه کلیدی
        if (index != -1) {
            // محاسبه موقعیت پایان کلمه کلیدی
            int endOfKeyword = index + keyword.length();
            return input.substring(endOfKeyword).trim(); // بازگرداندن رشته از بعد کلمه کلیدی
        }
        return input; // اگر کلمه کلیدی وجود ندارد، رشته اصلی را برگردان
    }

    /**
     * EN : Remove before a word or a text and return the continuation of that text
     * FA : حذف قبل از یک کلمه یا یک متن و برگرداندن ادامه آن متن
     *
     * @example : "basicSourceCode\src\main\java\com\basicSourceCode" with "src\" to "src\main\java\com\basicSourceCode"
     */
    public static String removeTextBeforeKeyword(String input, String keyword) {
        int index = input.indexOf(keyword); // پیدا کردن مکان کلمه کلیدی
        if (index != -1) {
            // محاسبه موقعیت پایان کلمه کلیدی
            return input.substring(index).trim(); // بازگرداندن رشته از بعد کلمه کلیدی
        }
        return input; // اگر کلمه کلیدی وجود ندارد، رشته اصلی را برگردان
    }


    /**
     * @param camelCaseText entered camel case text
     *                      EN : Conversion to CamelCase with the difference that a space is created between uppercase and lowercase letters
     *                      FA : تبدیل به CamelCase با این تفاوت که بین حروف کوچک و بزرگ یک فاصله ایجاد میشود
     * @example : "salamManMehdiHosseiniHastam" to "salam Man Mehdi Hosseini Hastam"
     */
    public static String toSplitCamelCase(String camelCaseText) {
        // استفاده از regex برای جدا کردن حروف بزرگ
        return (camelCaseText == null || camelCaseText.isEmpty()) ? null : camelCaseText.replaceAll("([a-z])([A-Z])", "$1 $2")
                .replaceAll("([A-Z])([A-Z][a-z])", "$1 $2");
    }

    public static class DynamicMessageFormatter {
        private DynamicMessageFormatter() {
        }

        /**
         * @Description <p> فرمت کردن لیست به عنوان متن </p>
         */
        public static String formatListTemplate(String template, String listPlaceholder, List<String> items) {
            String listAsText = items == null || items.isEmpty()
                    ? "هیچ موردی یافت نشد"
                    : String.join(", ", items);

            return template.replace(listPlaceholder, listAsText);
        }

        /**
         * @Description <p>فرمت کردن پیام با MessageFormat و اضافه کردن آرگومان‌ها</p>
         */
        public static String formatWithDynamicArgs(String template, Object... args) {
            try {
                return MessageFormat.format(template, args);
            } catch (IllegalArgumentException e) {
                return "Formatting error: " + e.getMessage();
            }
        }

        /**
         * @Description <p>ساخت پیام داینامیک با لیست و آرگومان‌ها</p>
         */
        public static String createMessage(String template, List<String> items, String listPlaceholder, Object... args) {
            String withList = formatListTemplate(template, listPlaceholder, items);
            return formatWithDynamicArgs(withList, args);
        }
    }
}
