package BUS;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Validate {
     // return null when input from user have any error or not in option table
     protected static int parseChooseHandler (String userChoose,  int totalOptions) {
          try {
               int parseChoose =  Integer.parseInt(userChoose);
               if ((parseChoose > 0) && (parseChoose <= totalOptions))
                    return parseChoose;
               else {
                    System.out.print("\033\143");
                    System.out.println("your option is not in options table!");
                    return 0;
               }
                    
          } catch (Exception err) {
               System.out.print("\033\143");
               System.out.println("error input please try again: " + "\n" + err);
               return 0;
          }
     }

     // converted format for input date from user
     protected static LocalDate formatInputDate (String inputDate) {
          DateTimeFormatter checkFormat = DateTimeFormatter.ofPattern("dd/MM/YYYY");
          try {
               LocalDate userInputDate = LocalDate.parse(inputDate, checkFormat); 
               return userInputDate;

          } catch (Exception e) {
               System.out.print("\033\1443");
               System.out.println("your date input is wrong!");
               return null;
          }
     }

     protected static Integer isNumber (String inputNumber) {
          try {
               Integer tempInt = Integer.parseInt(inputNumber);
               return tempInt;
          } catch (Exception err) {
               System.out.println("your input is wrong format! please try again!");
               return null;
          }
     }

     protected static boolean validatePrice (Object inputPrice) {
          if (inputPrice instanceof BigDecimal)
               return true;
          else {
               System.out.println("your input is not a price! please try again!");
               return false;
          }
     }

     protected static boolean validateId (String inputId) {
          return true;
     }

     protected static boolean validateTypeOfBook (String inputType) {
          
          return true;
     }
}
