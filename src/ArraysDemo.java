
package DataStructures.Arrays.src;

import java.util.*;
import java.util.stream.Collectors;

public class ArraysDemo {


    public  static void main(String[] args){


        //Create an Int Array : below 3 ways
        //Method1
        int arr1[] = new int[]{10,2,7,9,1};

        //Method 2
        int[] arr2 = {1,2,3,4,5};

        //Method 3
        int arr[] = new int[5];
        arr[0]=1;
        arr[1]=(2);
        arr[2]=(3);
        arr[3]=(4);
        arr[4]=(5);



        // Array to List
        arrayToList(arr);


        //sort
        java.util.Arrays.sort(arr1);
        //System.out.println("Sorted List " + java.util.Arrays.toString(java.util.Arrays.asList(arr1).get(0)));
        //reverse an array - java 8
        java.util.Arrays.stream(arr1).boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
        // without streams
        //Collections class works with wrapper classes only (not with primitive data type -int)
        //convert int --> Integer
        Integer[] reverseList = new Integer[arr1.length];
        int k=0;
        for(int i : arr1){
            reverseList[k++] = Integer.valueOf(i);
        }
        java.util.Arrays.sort(reverseList, Collections.reverseOrder());
      //  System.out.println("Reversed Sorted List " + java.util.Arrays.toString(reverseList));


        //java 8
        // arr[int] --> Integer(arr) ---> filter even Integers ---> map(divide each element by 2) ---> collect as List
        List<Integer> list1 = java.util.Arrays.stream(arr).boxed().filter(p -> p %2 ==0 ).map(i -> i/2 ).collect(Collectors.toList());
         //System.out.println("Java 8 Convert To List " +list1);


        //binary search
        int result = java.util.Arrays.binarySearch(arr, 10);    // returns index or (-(insertion point)-1)
        //System.out.println("Binary Search () " +result);

        int arr4[] = new int[]{10,2,7,9,1,10,3,4,7,2,4};
       // List<Integer> list2 = java.util.Arrays.stream(arr4).boxed().sorted().map(i->i*2).collect(Collectors.toList());
        List<Integer> list2 = java.util.Arrays.stream(arr4).boxed().map(i->i*2).collect(Collectors.toList());
        //list2.stream().collect(Collectors::groupingBy);

       // System.out.println("Java 8 Convert To List " +list2);






    }

    private static void arrayToList(int[] arr) {
        //Method 1
        List<int []> list = java.util.Arrays.asList(arr);
        String arrlistAsString = java.util.Arrays.toString(list.get(0));


        // Method 2 : IMP
        // when arr[] --> List<>
        List<Integer> listArr1 = Arrays.stream(arr).boxed().toList();
        System.out.println("output1"+ listArr1);


        //Method 3
        List<Integer> listInt = Arrays.asList(1,2,3);
        List<String> listString = Arrays.asList("A", "B");

        //Method 4
        List<Integer> listArr = Arrays.stream(arr)
                .mapToObj(Integer::valueOf)
                .collect(Collectors.toList());
        //System.out.println("listArr"+ listArr);


    }

}

