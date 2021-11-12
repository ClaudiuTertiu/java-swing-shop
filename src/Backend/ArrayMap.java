package Backend;

import java.util.*;

public class ArrayMap<K,V> extends AbstractMap<K, V> {
    private ArrayList<ArrayMapEntry<K,V>> arr;

    public ArrayMap() {
        arr = new ArrayList<>();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entrySet = new HashSet<>(arr);
        return entrySet;
    }

    public int size() {
        return arr.size();
    }

    public V put(K key, V value) {
        ArrayMapEntry<K,V> newArr= new ArrayMapEntry<>(key,value);
        if(arr == null)
            arr = new ArrayList<>();
        if(arr.contains(newArr))
            return null;
        arr.add(newArr);
        return value;
    }

    public V get(Object key) {
        Iterator itr = arr.iterator();
        while(itr.hasNext()) {
            ArrayMapEntry<K,V> entry = (ArrayMapEntry<K,V>) itr.next();
            if(entry.getKey().equals(key))
                return entry.getValue();
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        Iterator<ArrayMapEntry<K,V>> i = arr.iterator();
        while (i.hasNext()) {
            ArrayMapEntry<K,V> e = i.next();
            str.append("\n" + e.key + ": " + e.value);
        }
        return str.toString();
    }

    public class ArrayMapEntry<K,V> implements Map.Entry<K,V> {

        private K key;
        private V value;

        public ArrayMapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }


        public K getKey() {
            return key;
        }

        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public String toString() {
            return key + ": " + value;
        }

        public boolean equals(Object o) {
            ArrayMapEntry<K,V> arrayMapEntry = (ArrayMapEntry) o;
            if(value.equals(arrayMapEntry.value) && key.equals(arrayMapEntry.key) )
                return true;
            return false;
        }

        public int hashCode() {
            return value.hashCode();
        }
    }
}
