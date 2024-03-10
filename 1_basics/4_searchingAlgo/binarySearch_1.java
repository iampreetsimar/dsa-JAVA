public class binarySearch_1 {
    public static void main(String[] args) {
        
        int[] arr = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

        int low = 0;
        int high = arr.length - 1;

        int element = 25;

        while(low <= high) {
            int mid = (low + high) / 2;

            if(element > arr[mid]) {
                low = mid + 1;
            } else if(element < arr[mid]) {
                high = mid - 1;
            } else {
                System.out.println("Element at idx: " + mid);
                return;
            }
        }

        System.err.println("Element not found.");
    }
}
