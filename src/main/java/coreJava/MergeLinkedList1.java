package coreJava;

import java.util.ArrayList;
import java.util.List;

public class MergeLinkedList1 {
    public static void main(String[] args) {
        List<List<Integer>> list=new ArrayList<>();
        list.add(List.of(1,2,3));
        list.add(List.of(2,3,4,6));
        list.add(List.of(4,5,9,6));
        System.out.println(list);
        sortList(list);
        //printing after returning element into the list.
        List<Integer> ans=sortList(list);
        System.out.println(ans);

    }
    //adding all element in the list
    public static List<Integer>sortList(List<List<Integer>> list){
        List<Integer>list1= new ArrayList<>();
       for (List<Integer> ll:list){
           list1.addAll(ll);
       }
       //sorting logic: bubble sort
        for (int i = 0; i < list1.size()-1; i++) {
            for (int j = i+1; j <list1.size()-i-1 ; j++) {
                if (list1.get(j)>list1.get(j+1)){
                    int temp=list1.get(j);
                    list1.set(j,list1.get(j+1));
                    list1.set(j+1,temp);
                }

            }

        }

        //returning sorted list
        return list1;

    }

}
