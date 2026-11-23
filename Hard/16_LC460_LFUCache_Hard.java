
import java.util.*;

class LFUCache {
    private static class Node {
        int key, value, frequency;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.frequency = 1;
        }
    }

    private final int capacity;
    private int minFrequency;

    private final Map<Integer, Node> cache = new HashMap<>();
    private final Map<Integer, LinkedHashSet<Integer>> freqMap =
            new HashMap<>();

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        Node node = cache.get(key);
        updateFrequency(node);

        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }

        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            updateFrequency(node);
            return;
        }

        if (cache.size() == capacity) {
            LinkedHashSet<Integer> keys =
                    freqMap.get(minFrequency);

            int removeKey = keys.iterator().next();
            keys.remove(removeKey);
            cache.remove(removeKey);
        }

        Node newNode = new Node(key, value);
        cache.put(key, newNode);

        freqMap.computeIfAbsent(1, k -> new LinkedHashSet<>())
               .add(key);

        minFrequency = 1;
    }

    private void updateFrequency(Node node) {
        int oldFrequency = node.frequency;

        LinkedHashSet<Integer> oldSet =
                freqMap.get(oldFrequency);

        oldSet.remove(node.key);

        if (oldSet.isEmpty()) {
            freqMap.remove(oldFrequency);

            if (minFrequency == oldFrequency) {
                minFrequency++;
            }
        }

        node.frequency++;

        freqMap.computeIfAbsent(node.frequency,
                k -> new LinkedHashSet<>()).add(node.key);
    }
}
