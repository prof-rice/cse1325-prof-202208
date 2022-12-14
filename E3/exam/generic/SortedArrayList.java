import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator; // a. ½ for dashed box and b. ½ for E in right place

public class SortedArrayList <E extends Comparable<? super E>> { // ½ extends=implements + ½ super
    private ArrayList<E> list = new ArrayList<>(); // c. ½ left and right of =
    public boolean add(E e) { // d. 
        list.add(e);            // 1  list, add, and e
        Collections.sort(list); // 1½ Collections, sort, and list
        return true;            //  ½
    }
    public E get(int index) {
        return list.get(index);
    }
    Iterator<E> iterator() {
        return list.iterator(); // e. return, list, iterator, and ()
    }
    
    public static void main(String[] args) {
        SortedArrayList<String> strings = new SortedArrayList<>();
        for(String arg : args) strings.add(arg);
        
        Iterator it = strings.iterator(); // ½ left and right of =
        while(it.hasNext()) System.out.println(it.next()); // while, (hasNext), it, next()
    }
}
