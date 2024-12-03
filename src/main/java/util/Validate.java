package util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Pattern;

public class Validate {
     // private static final Scanner input = new Scanner(System.in);
     private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

     // check quantity (DONE)
     public static boolean checkQuantity(int quantity) {
          return quantity > 0;
     }

     public static boolean checkValidRange(int min, int max) {
          return min >= 0 && max >= 0 && min <= max;
     }

     // format type of string price
     public static String formatPrice(BigDecimal price) {
          try {
               NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.of("vi", "VN"));
               return formatter.format(price);
          } catch (Exception err) {
               System.out.println("your input is not correct!\n" + err.getMessage());
               return "";
          }

     }

     // check valid price discount
     public static BigDecimal executePrice(BigDecimal totalPrice, BigDecimal maxDiscount, BigDecimal discount) {
          BigDecimal result = totalPrice.multiply(discount);
          return result.compareTo(maxDiscount) > 0 ? maxDiscount : result;
     }

     public static BigDecimal isLargestDiscount (BigDecimal[] discounts) {
          int length = discounts.length;
          for (int i = 0; i < length - 1; i++) {
               int temp = i;
               for (int j = i + 1; j < length; j++) {
                    if (discounts[j].compareTo(discounts[i]) > 0)
                         temp = j;
               }
               if (temp != i) {
                    BigDecimal tempPrice = discounts[i];
                    discounts[i] = discounts[temp];
                    discounts[temp] = tempPrice; 
               }
          }
          return discounts[0];
     }

     // check if input null or not (DONE)
     public static boolean requiredNotNull(Object input) {
          try {
               return input != null;
          } catch (Exception e) {
               System.out.println("something went wrong!" + e.getMessage());
               return false;
          }
     }

     // check duplicate (DONE)
     public static boolean hasDuplicates(String[] options) {
          for (int i = 0; i < options.length - 1; i++)
               for (int j = i + 1; j < options.length; j++)
                    if (options[i].equals(options[j]))
                         return true;
          return false;
     }

     // converted format for input date from user (DONE)
     public static LocalDate isCorrectDate(String date) {
          try {
               String regex = "(^0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4}$)";
               Pattern pattern = Pattern.compile(regex);
               // check if input is matches with regex or not
               if (!pattern.matcher(date).matches())
                    throw new Exception("your date is not correct format!");

               String[] splitDate = date.split("-");
               LocalDate convertDate = LocalDate.parse(date, formatter);
               LocalDate nowDate = LocalDate.now();

               if ((convertDate.getMonthValue() == 2) && (Integer.parseInt(splitDate[0]) > convertDate.getDayOfMonth()))
                    throw new Exception("invalid day of this month!");

               if (convertDate.isAfter(nowDate) || (convertDate.getYear() < 1900))
                    throw new Exception("invalid date ! Are you a time traveler ?");

               return convertDate;
          } catch (Exception err) {
               System.out.printf(
                         "%s Please try again!\nformat date is \"dd-mm-yyyy\" like \"12-12-2022\"with min date is \"01-01-1900\"\n",
                         err.getMessage());
               return null;
          }
     }

     public static boolean isValidRangeDate(LocalDate start, LocalDate end) {
          return !start.isAfter(end);
     }

     // return null when input from user have any error or not in option table (DONE)
     public static int parseChooseHandler(String userChoose, int totalOptions) {
          try {
               int parseChoose = Integer.parseInt(userChoose);
               if ((parseChoose > 0) && (parseChoose <= totalOptions))
                    return parseChoose;
               else {
                    // System.out.print("\033\143");
                    System.out.println("your option is not in options table!");
                    return -1;
               }

          } catch (Exception err) {
               // System.out.print("\033\143");
               System.out.printf("error input \n%s\nplease try again! \n", err.getMessage());
               return -1;
          }
     }

     // (DONE)
     public static Integer isNumber(String inputNumber) {
          try {
               return Integer.parseInt(inputNumber);
          } catch (Exception err) {
               System.out.printf("%s is wrong format! please try again!\n", err.getMessage());
               return -1;
          }
     }

     // (DONE)
     public static BigDecimal isBigDecimal(String value) {
          try {
               return new BigDecimal(value);
          } catch (Exception err) {
               System.out.println("your input is not correct!\n" + err.getMessage());
               return null;
          }
     }

     // (DONE)
     public static boolean validateID(String inputId) {
          String regex = "^(?=[0-9_-]{8}$)[^%+\\/#'::\":]+$";
          Pattern pattern = Pattern.compile(regex);
          return pattern.matcher(inputId).matches();
     }

     // (CONTINUE)
     public static boolean validatePhone(String phoneString) {
          String regex = "^[0-9]{10}$";
          Pattern pattern = Pattern.compile(regex);
          return pattern.matcher(phoneString).matches();
     }

     // (CONTINUE)
     public static boolean validateEmail(String email) {
          String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
          Pattern pattern = Pattern.compile(regex);
          return pattern.matcher(email).matches();
     }

     // (DONE)
     public static boolean checkAddress(String address) {
          String regex = "^[\\p{L}\\p{N}\\s,./]*$";
          Pattern pattern = Pattern.compile(regex);
          return pattern.matcher(address).matches();
     }

     // (DONE)
     public static boolean checkName(String inputName) {
          String regex = "^[\\p{L}\\p{M}0-9][\\p{L}\\p{M}0-9 '\\-&^()$_!@#%*`\\[\\]]{0,48}$";
          Pattern pattern = Pattern.compile(regex);
          if (inputName.length() < 2)
               return false;
          return pattern.matcher(inputName).matches();
     }

     // (DONE)
     public static boolean checkHumanName(String inputName) {
          String regex = "^[\\p{M}\\p{L}][\\p{M}\\p{L} '\\-]{0,48}[\\p{M}\\p{L}]$";
          Pattern pattern = Pattern.compile(regex);
          if (inputName.length() < 1)
               return false;
          return pattern.matcher(inputName).matches();
     }

     // (DONE)
     public static boolean checkPackagingSize(String packagingSize) {
          String regex = "^\\d+(\\.\\d+)?\\s+x\\s+\\d+(\\.\\d+)?\\s+cm$";
          Pattern pattern = Pattern.compile(regex);
          return pattern.matcher(packagingSize).matches();
     }
}
