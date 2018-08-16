package Map;

public class BSTMap<K extends Comparable<K>, V> implements Map<K, V>{
    private class Node {
        public K key;
        public V value;
        public Node left, right;

        public Node (K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }
}
