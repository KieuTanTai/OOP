package util;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Validate {
     private static final Scanner input = new Scanner(System.in);

     public static boolean checkQuantity (int quantity) {
         return quantity > 0;
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

     // converted format for input date from user (LACK VALIDATE DATE)
     public static LocalDate formatInputDate (String inputDate) {
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
          try {
              return LocalDate.parse(inputDate, formatter);
          } catch (Exception err) {
               // System.out.print("\033\1443");
               System.out.println("your date input is wrong!" + err);
               return null;
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
          String regex = "^[A-Za-zÀ-ÖØ-öø-ÿ0-9'\\-\\s]{1,50}$";
          Pattern pattern = Pattern.compile(regex);
         return pattern.matcher(inputName).matches();
     }

     public static boolean checkHumanName (String inputName) {
          String regex = "^[A-Za-zÀ-ÖØ-öø-ÿ'\\-\\s]{1,50}$";
          Pattern pattern = Pattern.compile(regex);
         return pattern.matcher(inputName).matches();
     }

     public static boolean checkPackagingSize (String packagingSize) {
          String regex = "^\\d+(\\.\\d+)?\\s*x\\s*\\d+(\\.\\d+)?\\s*cm$";
          Pattern pattern = Pattern.compile(regex);
          return pattern.matcher(packagingSize).matches();
     }
}
