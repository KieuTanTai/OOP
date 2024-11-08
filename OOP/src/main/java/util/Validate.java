package util;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Validate {
     private static final Scanner input = new Scanner(System.in);
     private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
               
     // check quantity
     public static boolean checkQuantity (int quantity) {
         return quantity > 0;
     }

     public static boolean checkLeapYear (int year) {
          if (((year % 4 == 0) && (year % 100 != 0)) || year % 400 == 0)
               return true;
          return false;
     }

     // converted format for input date from user
     public static LocalDate isCorrectDate (String date) {
          try {
               String regex = "(^0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4}$)";
               Pattern pattern = Pattern.compile(regex);
               // check if input is matches with regex or not
               if (!pattern.matcher(date).matches())
                    throw new Exception("your date is not correct format!");

               // validate if it larger than 29 on February (leap year) or larger than 28 (normal year)
               String[] splitDate = date.split("-");

               if (Integer.parseInt(splitDate[0]) > 29)
                    throw new Exception("invalid day of this month!");

               if (!checkLeapYear(Integer.parseInt(splitDate[2])) && (Integer.parseInt(splitDate[0]) == 29))
                    throw new Exception("invalid day of this month!");
               
               LocalDate convertDate = LocalDate.parse(date, formatter);
               String nowDate = formatter.format(LocalDateTime.now());
               LocalDate convertNowDate = LocalDate.parse(nowDate, formatter);
               
               if (convertDate.compareTo(convertNowDate) > 0)
                    throw new Exception("invalid date ! Are you come from future ?");
               return convertDate;

          } catch (Exception err) {
               System.out.printf("%s Please try again!\nformat date is \"dd-mm-yyyy\" like \"12-12-2022\"\n", err.getMessage());
               return null;
          }
     }

     // return null when input from user have any error or not in option table
     public static int parseChooseHandler (String userChoose,  int totalOptions) {
          try {
               int parseChoose =  Integer.parseInt(userChoose);
               if ((parseChoose > 0) && (parseChoose <= totalOptions))
                    return parseChoose;
               else {
                    // System.out.print("\033\143");
                    System.out.println("your option is not in options table!");
                    return -1;
               }
                    
          } catch (Exception err) {
               // System.out.print("\033\143");
               System.out.println("error input please try again: " + "\n" + err);
               return -1;
          }
     }


     public static Integer isNumber (String inputNumber) {
          try {
              return Integer.parseInt(inputNumber);
          } catch (Exception err) {
               System.out.println("your input is wrong format! please try again!");
               return -1;
          }
     }

     public static BigDecimal isBigDecimal (String value) {
          try {
               return new BigDecimal(value);
          } catch (Exception err) {
               System.out.println("your input is not correct!\n" + err);
               return null;
          }
     }

     public static boolean validateID (String inputId) {
          String regex = "^[a-zA-Z0-9_-](?!.*::)[^%+\\\\/#'\"]+$";
          Pattern pattern = Pattern.compile(regex);
          return !pattern.matcher(inputId).matches();
     }

     public static boolean validateTypeOfBook (String inputType) {
          
          return true;
     }

     public static boolean checkName (String inputName) {
          String regex = "^[\\p{L}\\p{M}0-9][\\p{L}\\p{M}0-9 '\\-&^()$]{0,48}[\\p{M}\\p{L}0-9]$";;
          Pattern pattern = Pattern.compile(regex);
          if (inputName.length() < 3)
               return false;
          return pattern.matcher(inputName).matches();
     }

     // (DONE)
     public static boolean checkHumanName (String inputName) {
          String regex = "^[\\p{M}\\p{L}][\\p{M}\\p{L} '\\-]{0,48}[\\p{M}\\p{L}]$";
          Pattern pattern = Pattern.compile(regex);
          if (inputName.length() < 3)
               return false;
          return pattern.matcher(inputName).matches();
     }

     public static boolean checkPackagingSize (String packagingSize) {
          String regex = "^\\d+(\\.\\d+)?\\s*x\\s*\\d+(\\.\\d+)?\\s*cm$";
          Pattern pattern = Pattern.compile(regex);
          return pattern.matcher(packagingSize).matches();
     }
}
