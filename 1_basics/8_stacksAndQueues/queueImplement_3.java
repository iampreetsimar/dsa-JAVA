// 1. You are required to complete the code of our CustomQueue class. The class should mimic the behaviour of a 
//    Queue and implement FIFO semantic.
// 2. Here is the list of functions that you are supposed to complete
//      2.1. add -> Should accept new data if there is space available in the underlying 
//      array or print "Queue overflow" otherwise.
//      2.2. remove -> Should remove and return value according to FIFO, if available or 
//      print "Queue underflow" otherwise and return -1.
//      2.3. peek -> Should return value according to FIFO, if available or print "Queue 
//      underflow" otherwise and return -1.
//      2.4. size -> Should return the number of elements available in the queue.
//      2.5. display -> Should print the elements of queue in FIFO manner (space- 
//      separated) ending with a line-break.
// 3. Input and Output is managed for you.

// Sample Input
// 5
// add 10
// display
// add 20
// display
// add 30
// display
// add 40
// display
// add 50
// display
// add 60
// display
// peek
// remove
// display
// peek
// remove
// display
// peek
// remove
// display
// peek
// remove
// display
// peek
// remove
// display
// peek
// remove
// quit

// Sample Output
// 10 
// 10 20 
// 10 20 30 
// 10 20 30 40 
// 10 20 30 40 50 
// Queue overflow
// 10 20 30 40 50 
// 10
// 10
// 20 30 40 50 
// 20
// 20
// 30 40 50 
// 30
// 30
// 40 50 
// 40
// 40
// 50 
// 50
// 50
// 
// Queue underflow
// Queue underflow

import java.io.*;

public class queueImplement_3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        CustomQueue qu = new CustomQueue(n);

        String str = br.readLine();
        while(str.equals("quit") == false){
            if(str.startsWith("add")){
                int val = Integer.parseInt(str.split(" ")[1]);
                qu.add(val);
            } else if(str.startsWith("remove")){
                int val = qu.remove();
                if(val != -1){
                    System.out.println(val);
                }
            } else if(str.startsWith("peek")){
                int val = qu.peek();
                if(val != -1){
                    System.out.println(val);
                }
            } else if(str.startsWith("size")){
                System.out.println(qu.size());
            } else if(str.startsWith("display")){
                qu.display();
            }

            str = br.readLine();
        }
    }

    public static class CustomQueue {
        int[] data;
        int front;
        int size;

        public CustomQueue(int cap) {
            data = new int[cap];
            front = 0;
            size = 0;
        }

        int size() {
            // write ur code here
             
            // return size data member - tells number of elements in queue
            return size;
        }

        void display() {
            // write ur code here

            // print all elements of queue starting from front
            for(int i = 0; i < size; i++) {
                System.out.print(data[(front + i) % data.length] + " ");
            }
            System.out.println();
        }

        void add(int val) {
            // write ur code here

            // if size == data.length -> queue is full
            if(size == data.length) {
                System.out.println("Queue Overflow");
                return;
            }

            // get rear idx where val must be added
            int idx = (front + size) % data.length;
            data[idx] = val;
            size++;     // increment size by 1
        }

        int remove() {
            // write ur code here

            // if size == 0 -> queue is empty
            if(size == 0) {
                System.out.println("Queue Underflow");
                return - 1;
            }

            // item to be removed from front
            int val = data[front];
            
            // update value of front
            front = (front + 1) % data.length;
            // decrement size by 1
            size--;

            return val;
        }

        int peek() {
            // write ur code here

            // if size == 0 -> queue is empty
            if(size == 0) {
                System.out.println("Queue Underflow");
                return -1;
            }

            // return element from front
            return data[front];
        }
    }
}
