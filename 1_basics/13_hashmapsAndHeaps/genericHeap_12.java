// Implement a Generic Heap using Comparable and Comparator intrfaces.

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class genericHeap_12 {
    public static void main(String[] args) throws Exception {
        Student[] students = {
            new Student(10, "Ajay", 2),
            new Student(1, "Mukesh", 5),
            new Student(25, "Simar", 1),
            new Student(5, "Nitin",  3),
            new Student(39, "Pankaj", 4)
        };

        PriorityQueue<Student> pq = new PriorityQueue<>(students);
        PriorityQueue<Student> pqRev = new PriorityQueue<>(students, Collections.reverseOrder());

        PriorityQueue<Student> pqRank = new PriorityQueue<>(students, new StudentRankComparator());
        PriorityQueue<Student> pqRankRev = new PriorityQueue<>(students, 
                                               Collections.reverseOrder(new StudentRankComparator()));

        PriorityQueue<Student> pqName = new PriorityQueue<>(students, new StudentNameComparator());
        PriorityQueue<Student> pqNameRev = new PriorityQueue<>(students, 
                                               Collections.reverseOrder(new StudentNameComparator()));

        pq.display();   // roll num ascending
        pqRev.display();    // roll num descending

        pqRank.display();   // rank ascending
        pqRankRev.display();    // rank descending

        pqName.display();   // name ascending
        pqNameRev.display();    // name descending
    }  
    
    public static class Student implements Comparable<Student> {
        int rollNum;
        String name;
        int rank;

        Student(int rollNum, String name, int rank) {
            this.rollNum = rollNum;
            this.name = name;
            this.rank = rank;
        }

        public int compareTo(Student other) {       // default priority set on 'rollNum' in ascending order
            return this.rollNum - other.rollNum;  // Collections.reverseOrder() changes to : other.Num - this.rollNum
        }

        public String toString() {  // to print object using print(object)
            return this.rollNum + ": Roll # | " + this.name + ": Name |  " + this.rank + ": Rank |";
        }
    }

    static class StudentNameComparator implements Comparator<Student> {
        public int compare(Student s1, Student s2) {    // for priority set on 'name' in ascending order
            return s1.name.compareTo(s2.name);     
        }
    }


    static class StudentRankComparator implements Comparator<Student> {
        public int compare(Student s1, Student s2) {    // for priority set on 'rank' in ascending order
            return s1.rank - s2.rank;
        }
    }

    public static class PriorityQueue<T> {      // <T> for Generics - data type finalized on initialization
        ArrayList<T> data;
        Comparator<T> comp;

        PriorityQueue() {
            data = new ArrayList<>();
            comp = null;
        }

        PriorityQueue(T[] input) {
            this();
            constructHeap(input);
        }

        PriorityQueue(Comparator<T> comp) {
            this();
            this.comp = comp;
        }
        
        PriorityQueue(T[] input, Comparator<T> comp) {
            this(comp);
            constructHeap(input); 
        }

        private void constructHeap(T[] input) {     // use downHeapify() to construct PQ in O(N)
            for(T val : input) {
                data.add(val);
            }

            for(int i = (data.size() / 2) - 1; i >= 0; i--) {
                downHeapify(i);
            }
        }

        // uses result from compareTo()/compare() to set priority
        private boolean isHigherPriority(int childIdx, int parentIdx) {
            if (comp == null) {     
                Comparable childVal = (Comparable)data.get(childIdx);
                Comparable parentVal = (Comparable)data.get(parentIdx);

                if(childVal.compareTo(parentVal) < 0)   // childVal -> this object and parentVal -> other object
                    return true;
                else 
                    return false;
            } else {
                T childVal = data.get(childIdx);
                T parentVal = data.get(parentIdx);
                
                if(comp.compare(childVal, parentVal) < 0)   // childVal -> object1 and parentVal -> object2
                    return true;
                else    
                    return false;
            }
        }
        
        public int size() {
            return data.size();
        }

        private void swap(int idx1, int idx2) {
            T val1 = data.get(idx1);
            T val2 = data.get(idx2);
            data.set(idx1, val2);
            data.set(idx2, val1);
        }
        
        public T peek() {
            if(data.size() == 0) {
                System.out.println("Underflow");
                return null;
            }
            return data.get(0);
        }
        
        public void add(T val) {
            data.add(val);
            upHeapify(data.size() - 1);
        }

        private void upHeapify(int childIdx) {
            if(childIdx == 0)
                return;

            int parentIdx = (childIdx - 1) / 2;
            if(isHigherPriority(childIdx, parentIdx)) { 
                swap(childIdx, parentIdx);
                upHeapify(parentIdx);
            }
        }

        public T remove() {
            if(data.size() == 0) {
                System.out.println("Underflow");
                return null;
            }

            swap(0, data.size() - 1);
            T val = data.remove(data.size() - 1);
            downHeapify(0);
            return val;
        }

        private void downHeapify(int parentIdx) {
            int lcIdx = (2 * parentIdx) + 1;
            int rcIdx = (2 * parentIdx) + 2;
            int higherPriorityIdx = parentIdx;

            if(lcIdx < data.size() && isHigherPriority(lcIdx, higherPriorityIdx)) {
                higherPriorityIdx = lcIdx;
            }

            if(rcIdx < data.size() && isHigherPriority(rcIdx, higherPriorityIdx)) {
                higherPriorityIdx = rcIdx;
            }

            if (higherPriorityIdx != parentIdx) {
                swap(higherPriorityIdx, parentIdx);
                downHeapify(higherPriorityIdx);
            }
        }
    
        public void display() {
            System.out.println("~~~~~ PQ Start ~~~~");
            while(data.size() > 0) {
                System.out.println(remove());
            }
            System.out.println("~~~~~ PQ End ~~~~");
        }
    }
}
