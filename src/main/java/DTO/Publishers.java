package DTO;

public class Publishers {
    private String publisherID,publisherName;

    //constructor
    public Publishers(){}

    public Publishers(String publisherID, String publisherName) {
        this.publisherID = publisherID;
        this.publisherName = publisherName;
    }

    //getter
    public String getPublisherID() {
        return publisherID;
    }
    
    public String getPublisherName() {
        return publisherName;
    }

    //setter
    public void setPublisherID(String publisherID) {
        this.publisherID = publisherID;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
}
