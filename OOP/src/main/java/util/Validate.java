package util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Validate {
     // private static final Scanner input = new Scanner(System.in);
     private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

     // check quantity (DONE)
     public static boolean checkQuantity(int quantity) {
          return quantity > 0;
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
          String regex = "^(?=[a-zA-Z0-9_-]{10}$)[^%+\\\\/#'::\":]+$";
          Pattern pattern = Pattern.compile(regex);
          return pattern.matcher(inputId).matches();
     }

     // (CONTINUE)
     public static boolean validateTypeOfBook(String inputType) {

          return true;
     }

     // (DONE)
     public static boolean checkName(String inputName) {
          String regex = "^[\\p{L}\\p{M}0-9][\\p{L}\\p{M}0-9 '\\-&^()$_!@#%*`\\[\\]]{0,48}$";
          Pattern pattern = Pattern.compile(regex);
          if (inputName.length() < 3)
               return false;
          return pattern.matcher(inputName).matches();
     }

     // (DONE)
     public static boolean checkHumanName(String inputName) {
          String regex = "^[\\p{M}\\p{L}][\\p{M}\\p{L} '\\-]{0,48}[\\p{M}\\p{L}]$";
          Pattern pattern = Pattern.compile(regex);
          if (inputName.length() < 3)
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
