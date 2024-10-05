package DTO;

public class Publishers {
     private String publisherId;
     private String publisherName;

     public Publishers(String publisherId, String publisherName) {
          this.publisherId = publisherId;
          this.publisherName = publisherName;
     }

     public String getPublisherId () {
          return this.publisherId;
     }

     public String getPublisherName () {
          return this.publisherName;
     }

     public void setPublisherId (String publisherId) {
          this.publisherId = publisherId;
     }

     public void setPublisherName (String publisherName) {
          this.publisherName = publisherName;
     }
}
