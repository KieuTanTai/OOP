package BUS;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

import DTO.StaTypes;
import DTO.Stationary;
import util.Validate;

public class StationaryBUS implements IRuleSets {
     private Stationary[] staList;
     private int count;
     private static final Scanner input = new Scanner(System.in);
     
     //constructors
     public StationaryBUS() {}
     
     public StationaryBUS(Stationary[] staList, int count) {
          this.staList = staList;
          this.count = count;
     }


     // getters / setters
     public Stationary[] getStaList() {
          return staList;
     }

     public void setStaList(Stationary[] staList) {
          this.staList = staList;
     }

     public int getCount() {
          return count;
     }

     public void setCount(int count) {
          this.count = count;
     }

     public static Scanner getInput() {
          return input;
     }

     // all others methods like: add remove edit find show....
     // find methods (DONE)
     // strict find 
     @Override
     public int find (String inputValue) {
          for (int i = 0; i < staList.length; i++)
               if (staList[i].getProductID().equals(inputValue) || staList[i].getProductName().equals(inputValue))
                    return i;
          return -1;
     }

     // relative find
     // return list index of products that have contains specific string
     public Stationary[] relativeFind (String name) {
          int count = 0;
          Stationary[] staArray = new Stationary[0];
         for (Stationary stationary : staArray)
             if (stationary.getProductName().contains(name)) {
                 staArray = Arrays.copyOf(staArray, staArray.length + 1);
                 staArray[count] = stationary;
                 count++;
             }
          if (count == 0)
               return null;
          return staArray;
     }
     
     // add method
     @Override
     public void add(Object stationary) {
          if (stationary instanceof Stationary) {
               staList = Arrays.copyOf(staList, staList.length + 1);
               staList[count] = (Stationary) stationary;
               count++;
          }
          else 
               System.out.println("your new stationary have something not like stationary!");
          
     }

     // search methods
     @Override
     public void search (String inputValue) {
          int index = find(inputValue);
          if (index == -1) {
               System.out.println("your stationary is not found!");
               return;
          }
          String toStringHandler = composeUsingFormatter(staList[index]);
          System.out.printf("stationary's id / name is : %s\nstationary detail : \n%s", inputValue, toStringHandler);
     } 

     // relative search
     public void relativeSearch (String name) {
          Stationary[] indexList = relativeFind(name);
          if (indexList == null) {
               System.out.println("not found any stationary!");
               return;
          }
         for (Stationary stationary : indexList)
             System.out.printf("stationary's id : %s\ndetail : %s\n", stationary.getProductID(), composeUsingFormatter(stationary));
     }

     // advanced search
     public void advancedSearch () {

     }

     // remove method (DONE)
     @Override
     public void remove(String id) {
          int index = find(id);
          if (index == -1) {
               System.out.println("your stationary is not found !");
               return;
          }
          for (int i = index; i < staList.length - 1; i++) 
               staList[i] = staList[i + 1];
          staList = Arrays.copyOf(staList, staList.length - 1);
          count --;
          
     }

     // edit methods
     @Override
     public void edit(String id) {
          int index = find(id);
          if (index == -1) {
               System.out.println("your stationary is not exist !");
               return;
          }
          String newName;
          do {
               System.out.print("enter a new name for this stationary: ");
               newName = input.nextLine();
          }while (Validate.checkName(newName));
          staList[index].setProductName(newName);    
     }

     // edit release date
     public void edit (String id, LocalDate newDate) {
          int index = find(id);
          if (index == -1) {
               System.out.println("your book is not exist !");
               return;
          }
          staList[index].setReleaseDate(newDate);    
     }

     // edit price
     public void edit (String id, BigDecimal newPrice) {
          int index = find(id);
          if (index == -1) {
               System.out.println("your book is not exist !");
               return;
          }
          staList[index].setProductPrice(newPrice);
     }

     // edit quantity
     public void edit (String id, int newQuantity) {
          int index = find(id);
          if (index == -1) {
               System.out.println("your book is not exist !");
               return;
          }
          staList[index].setQuantity(newQuantity);
     }

     // edit type 
     public void edit (String id, StaTypes newType) {
          int index = find(id);
          if (index == -1) {
               System.out.println("your book is not exist !");
               return;
          }
          staList[index].setType(newType);
     }

     // edit brand
     public void edit (String id, String brand) {
          int index = find(id);
          if (index == -1) {
               System.out.println("your book is not exist !");
               return;
          }
          staList[index].setBrand(brand);;
     }

     // remove methods (DONE)
     @Override
     public void remove() {
          Menu.removeHandler();
     }
     
     @Override
     public void remove(String id) {
          int index = find(id);
          if (index == -1) {
               System.out.println("your stationary is not found !");
               return;
          }
          for (int i = index; i < staList.length - 1; i++) 
               staList[i] = staList[i + 1];
          staList = Arrays.copyOf(staList, staList.length - 1);
          count --;
          
     }

     // some other methods
     private String composeUsingFormatter (Stationary stationary) {
          return String.format(" stationary id: %s\n type: %s\n brand: %s\n material: %s\n source: %s\n", 
          stationary.getStationaryID(), stationary.getTypeName(), stationary.getBrand(), stationary.getMaterial(), stationary.getSource());
     }

     @Override
     public void add() {
          // TODO Auto-generated method stub
          throw new UnsupportedOperationException("Unimplemented method 'add'");
     }

     @Override
     public int find() {
          // TODO Auto-generated method stub
          throw new UnsupportedOperationException("Unimplemented method 'find'");
     }

     @Override
     public void search() {
          // TODO Auto-generated method stub
          throw new UnsupportedOperationException("Unimplemented method 'search'");
     }

     @Override
     public void remove() {
          // TODO Auto-generated method stub
          throw new UnsupportedOperationException("Unimplemented method 'remove'");
     }

     @Override
     public void edit() {
          // TODO Auto-generated method stub
          throw new UnsupportedOperationException("Unimplemented method 'edit'");
     }
}
