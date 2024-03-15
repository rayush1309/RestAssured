package coreJava;

public class secondLargestNumber {
    public static void main(String[] args) {

        int[] arr = {2, 4, 5, 9, 8, 3, 7};
        int fl = Integer.MIN_VALUE;
        int sl = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > fl) {
                sl = fl;
                fl = arr[i];
            } else if (arr[i] > sl && fl!=arr[i]) {
                sl = arr[i];
            }
        }

        System.out.println("Second Largest Number: " + sl);
    }
}
