package paradigmproject2;

import java.util.ArrayList;
import java.util.HashMap;
public class Markandsweep {
   // public static gc mar =new gc("/home/bassant/heap.csv", "/home/bassant/roots.txt" , "/home/bassant/pointers.csv");
    public ArrayList<object> heapArray; //heap.csv
    private HashMap<Integer, object> heapHashMap;
    private ArrayList<Integer> roots;

    private Markandsweep(ArrayList<object> heapArray, HashMap<Integer, object> heapHashMap, ArrayList<Integer> roots){
        this.heapArray = heapArray;
        this.heapHashMap =heapHashMap;
        this.roots=roots;
    }
    private void markobject ( object object ){
        if( object.isMarked() ) return;
        object.setMarked(true);
        for( object child : object.getChildren() ) markobject(child);
    }
    public void mark (){
        for( int rootId : roots ){
            markobject(heapHashMap.get( rootId ));
        }
    }
    public ArrayList<object> markandsweep(){
        mark();
        sweep();
        return heapArray;
    }
    private void sweep(){
        int p = 0;
        for ( int i = 0 ; i < heapArray.size() ; i++ ){
            object node = heapArray.get(i);
            if( node.isMarked() ){
                node.setMarked(false);
            }
            else{
                heapArray.remove(node);
                i--;
            }
        }
    }
    public static void main(String[] args) {
        gc garbagecollector =new gc("/home/bassant/heap.csv", "/home/bassant/roots.txt" , "/home/bassant/pointers.csv");// to get input
        var sweeping=new Markandsweep(garbagecollector.heapArray,garbagecollector.heapHashMap,garbagecollector.roots);
        garbagecollector.storeHeap(sweeping.markandsweep() , "/home/bassant/IdeaProjects/Garbage_collector/mark andsweepheap.csv");
    }

}
