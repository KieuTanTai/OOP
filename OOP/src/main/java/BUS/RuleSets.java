package BUS;

public interface RuleSets {
     public abstract void add(Object objectId);
     public abstract int find(String id); //return index
     public abstract void search(String id);
     public abstract void remove(String id);
     public abstract void edit(String id);
}
