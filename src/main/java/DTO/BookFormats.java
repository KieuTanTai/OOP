package DTO;

public class BookFormats {
     private String formatID;
     private String formatName;

     public BookFormats() {
     }

     public BookFormats(String formatID, String formatName) {
          this.formatID = formatID;
          this.formatName = formatName;
     }

     public String getFormatID() {
          return this.formatID;
     }

     public String getFormatName() {
          return this.formatName;
     }

     public void setFormatID(String formatID) {
          this.formatID = formatID;
     }

     public void setFormatName(String formatName) {
          this.formatName = formatName;
     }
}
