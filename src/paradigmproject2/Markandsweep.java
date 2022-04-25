package paradigmproject2;

import java.util.ArrayList;
import java.util.HashMap;
public class Markandsweep {
    public static gc mar =new gc("/home/bassant/heap.csv", "/home/bassant/roots.txt" , "/home/bassant/pointers.csv");
    public static ArrayList<object> markandsweep(){
        mar.mark();
        sweep();
        return mar.heapArray;
    }
    private static void sweep(){
        int p = 0;
        for ( int i = 0 ; i < mar.heapArray.size() ; i++ ){
            object node = mar.heapArray.get(i);
            if( node.isMarked() ){
                node.setMarked(false);
            }
            else{
                mar.heapArray.remove(node);
                i--;
            }
        }
    }
    public static void main(String[] args) {

       // gc garbagecollector =new gc("/home/bassant/heap.csv", "/home/bassant/roots.txt" , "/home/bassant/pointers.csv");
        mar.storeHeap(markandsweep() , "/home/bassant/Downloads/mark andsweepheap.csv");

    }

}
