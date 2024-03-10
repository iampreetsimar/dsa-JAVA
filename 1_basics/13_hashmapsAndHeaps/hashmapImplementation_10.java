// 1. You are required to complete the code of our Hashmap class. 
// Implement the functions to achieve what is explained in the theoretical discussion.
// 2. Input and Output is managed for you.

// Sample Input
// put India 135
// put Aus 5
// put Canada 3
// display
// get China
// remove Aus
// get Aus
// containsKey US
// put US 10
// put UK 20
// remove US
// containsKey US
// put Pak 80
// put China 200
// display
// put Utopia 0
// display
// put Nigeria 3
// display
// put India 138
// display
// put Sweden 1
// display
// put finland 20
// display
// quit

// Sample Output
// Display Begins
// Bucket0 .
// Bucket1 .
// Bucket2 Canada@3 .
// Bucket3 India@135 Aus@5 .
// Display Ends
// null
// 5
// null
// false
// 10
// false
// Display Begins
// Bucket0 .
// Bucket1 .
// Bucket2 Canada@3 UK@20 Pak@80 .
// Bucket3 India@135 China@200 .
// Display Ends
// Display Begins
// Bucket0 Utopia@0 .
// Bucket1 .
// Bucket2 Canada@3 UK@20 Pak@80 .
// Bucket3 India@135 China@200 .
// Display Ends
// Display Begins
// Bucket0 Utopia@0 .
// Bucket1 .
// Bucket2 Canada@3 UK@20 Pak@80 .
// Bucket3 India@135 China@200 Nigeria@3 .
// Display Ends
// Display Begins
// Bucket0 Utopia@0 .
// Bucket1 .
// Bucket2 Canada@3 UK@20 Pak@80 .
// Bucket3 India@138 China@200 Nigeria@3 .
// Display Ends
// Display Begins
// Bucket0 Utopia@0 Sweden@1 .
// Bucket1 .
// Bucket2 Canada@3 UK@20 Pak@80 .
// Bucket3 India@138 China@200 Nigeria@3 .
// Display Ends
// Display Begins
// Bucket0 Utopia@0 .
// Bucket1 .
// Bucket2 Pak@80 finland@20 .
// Bucket3 .
// Bucket4 Sweden@1 .
// Bucket5 .
// Bucket6 Canada@3 UK@20 .
// Bucket7 India@138 China@200 Nigeria@3 .
// Display Ends

import java.io.*;
import java.util.*;

public class hashmapImplementation_10 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> map = new HashMap<>();
    
        String str = br.readLine();
        while (str.equals("quit") == false) {
            if (str.startsWith("put")) {
                String[] parts = str.split(" ");
                String key = parts[1];
                Integer val = Integer.parseInt(parts[2]);
                map.put(key, val);
            } else if (str.startsWith("get")) {
                String[] parts = str.split(" ");
                String key = parts[1];
                System.out.println(map.get(key));
            } else if (str.startsWith("containsKey")) {
                String[] parts = str.split(" ");
                String key = parts[1];
                System.out.println(map.containsKey(key));
            } else if (str.startsWith("remove")) {
                String[] parts = str.split(" ");
                String key = parts[1];
                System.out.println(map.remove(key));
            } else if (str.startsWith("size")) {
                System.out.println(map.size());
            } else if (str.startsWith("keySet")) {
                System.out.println(map.keySet());
            } else if (str.startsWith("display")) {
                map.display();
            }
            str = br.readLine();
        }
    }

    // use Generics: K - key datatype, V - value datatype -> sets datatype when map is initialized
    public static class HashMap<K, V> {
        private class HMNode {      
            K key;
            V value;

            HMNode(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private int size;   // n - represents total items in map
        private LinkedList<HMNode>[] buckets;       // N - each bucket has a LL
        private double thresholdValue = 2.0;

        private void initBuckets(int N) {
            buckets = new LinkedList[N];
            for(int i = 0; i < buckets.length; i++) {
                buckets[i] = new LinkedList<>();    // initialize a LL at each bucket idx
            }
        }

        HashMap() {
            initBuckets(4);     // initialize buckets size : 4
            size = 0;       // current items in map : 0
        }

        public int size() {     // returns total items in map : n -> TC : O(1)
            return size;
        }

        private int getHash(K key) {    // TC: O(1)
            int hash = key.hashCode();      // returns a unique hash for key: can be +ve, -ve, 0 or > array.length
            return Math.abs(hash) % buckets.length;     // returns bucket idx as % is done w.r.t buckets.length
        }

        private int findNodeByKey(K key, int bucketIdx) {       // TC: O(loadFactor)
            LinkedList<HMNode> list = buckets[bucketIdx];   // traverse LL at bucket idx
            for(int i = 0; i < list.size(); i++) {
                HMNode node = list.get(i);
                if(key.equals(node.key)) {      // if key found in LL -> return node's idx in LL
                    return i;
                }
            }
            return -1;      // if key not in LL -> return -1
        }
    
        public V get(K key) {   // TC: O(loadFactor)
            int bucketIdx = getHash(key);
            int keyIdx = findNodeByKey(key, bucketIdx);

            if(keyIdx == -1) {   // if key not in LL -> return null
                return null;
            }

            // return value of node at key Idx in LL at bucket Idx
            return buckets[bucketIdx].get(keyIdx).value;
        }
        
        public boolean containsKey(K key) {     // TC: O(loadFactor)
            int bucketIdx = getHash(key);
            int keyIdx = findNodeByKey(key, bucketIdx);

            if(keyIdx == -1)    // if key not in LL -> return false
                return false;

            return true;    // key present in LL -> return true
        }

        public V remove(K key) {    // TC: O(loadFactor)
            int bucketIdx = getHash(key);
            int keyIdx = findNodeByKey(key, bucketIdx);

            if(keyIdx == -1)    // if key not in LL -> return null
                return null;

            HMNode node = buckets[bucketIdx].remove(keyIdx);    // key present -> remove node from LL
            size--;     // decrement size of map by 1
            return node.value;      // return deleted node's value
        }
        
        public void put(K key, V value) {   // TC: O(loadFactor) -> when rehashing occurs, take more time
            int bucketIdx = getHash(key);
            int keyIdx = findNodeByKey(key, bucketIdx);

            if(keyIdx != -1) {  // key already present in LL -> UPDATE node's value
                HMNode node = buckets[bucketIdx].get(keyIdx);
                node.value = value;
            } else {    // key not present in LL -> INSERT key:value to end of LL -> increment size by 1
                HMNode node = new HMNode(key, value);
                buckets[bucketIdx].add(node);
                size++;
            }

            double loadFactor = (size * (1.0)) / buckets.length;   // datatype should be double to include decimals
            if(loadFactor > thresholdValue) {       // loadFactor > threshold -> rehashing
                rehash();
            } 
        }

        private void rehash() {     // expensive operation, but doesn't occur everytime
            LinkedList<HMNode>[] old = buckets;     // buckets array becomes old array
            size = 0;       // set size of map to 0
            initBuckets(buckets.length * 2);    // initialized new buckets array of twice the size of old

            for(LinkedList<HMNode> list : old) {    // traverse each bucket
                for(HMNode node : list) {       // traverse each node in LL at a bucket
                    put(node.key, node.value);      // put() {key:value} of node to new buckets array
                }   // puts() changes the bucket idx for a node based on new buckets array length
            }
        }

        public ArrayList<K> keySet() {  // TC: O(size)
            ArrayList<K> keyList = new ArrayList<>();

            for(LinkedList<HMNode> list : buckets) {    // traverse over buckets array
                for(HMNode node : list) {   // traverse each LL node in a bucket
                    keyList.add(node.key);  // add each node's key to arraylist
                }
            }

            return keyList;
        }

        public void display() {  // TC: O(size)
            System.out.println("Display Begins");
            for (int bi = 0; bi < buckets.length; bi++) {
                System.out.print("Bucket" + bi + " ");
                for (HMNode node : buckets[bi]) {
                    System.out.print( node.key + "@" + node.value + " ");
                }
                System.out.println(".");
            }
            System.out.println("Display Ends");
        }
    }
}
