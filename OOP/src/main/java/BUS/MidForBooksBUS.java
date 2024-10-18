package BUS;
import DTO.MidForBooks;

public class MidForBooksBUS {
     MidForBooks[] midList;
     private int index;

     // constructors
     public MidForBooksBUS () {
          this.index = 0;
          midList = new MidForBooks[0];
     }

     public MidForBooksBUS (int index, MidForBooks[] midList) {
          this.index = index;
          this.midList = midList;
     }

     public MidForBooksBUS (MidForBooksBUS MidFB) {
          this.index = MidFB.index;
          this.midList = MidFB.midList;
     }

     // getter / setter
     public int getQuantity () {
          return this.index;
     }

     public MidForBooks[] midList () {
          return this.midList;
     }

     public void setQuantity (int index) {
          this.index = index;
     }

     public void setMidList (MidForBooks[] midList) {
          this.midList = midList;
     }

     // add remove find ....
}
