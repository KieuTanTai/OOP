package BUS;

public interface RuleSets {
     void add(Object objectId);
     int find(String id); //return index
     void search(String id);
     void remove(String id);
     void edit(String id);
}
