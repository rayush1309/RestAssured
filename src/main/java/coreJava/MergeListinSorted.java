package coreJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeListinSorted {
    public static void main(String[] args) {


        List<List<Integer>> ll = new ArrayList<>();
        ll.add(Arrays.asList(1, 3, 4));
        ll.add(Arrays.asList(3, 5, 6));


        List<Integer> sortedList = sortArray(ll);

        for (int i = 0; i < sortedList.size(); i++) {
            System.out.println(sortedList.get(i));
        }
    }

    public static List<Integer> sortArray(List<List<Integer>> list) {
        List<Integer> list2 = new ArrayList<>();

        for (List<Integer> sublist : list) {
            list2.addAll(sublist);
        }

        // Use bubble sort
        for (int i = 0; i < list2.size() - 1; i++) {
            for (int j = 0; j < list2.size() - i - 1; j++) {
                if (list2.get(j) > list2.get(j + 1)) {
                    // swap temp and arr[i]
                    int temp = list2.get(j);
                    list2.set(j, list2.get(j + 1));
                    list2.set(j + 1, temp);
                }
            }
        }

        return list2;

    }

    private static List<Integer> arrayToList(int[] array) {
        List<Integer> result = new ArrayList<>();
        for (int value : array) {
            result.add(value);
        }

        return result;
    }
}
