package emporium;

import java.util.HashMap;
import java.util.HashSet;

class MultiMap<K, V> {
    private HashMap<K, HashSet<V>> map = new HashMap<>();
    public void put(K key, V value) {
        if(map.get(key) == null)
            map.put(key, new HashSet<V>());
        map.get(key).add(value);
    }
    public Object[] get(K key) {
        if(map.get(key) == null) return new Object[0];
        return map.get(key).toArray();
    }
}
