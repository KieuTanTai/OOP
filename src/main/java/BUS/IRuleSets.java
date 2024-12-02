package BUS;

public interface IRuleSets {
     void add();

     void add(Object objectId);

     int find(String id); // return index

     void search();

     void search(String id);

     void remove();

     void remove(String id);

     void edit();

     void edit(String id);
}
