package DataStructures.Arrays.src;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayNumberExamples {

    public static void main(String[] args){

       List<Integer> list = Arrays.asList(1,7,8,9,5,2,36,4,78,222,24,9);

        // Exercise 1 : Given a list of numbers, return the sum of all numbers.
        sumOfAllNum(list);
        sumOfMapValues(); ///IMP
        createMapFromList(); //Create a map from list - VERY IMP

        // Exercise 2 : Given a list of numbers, return the average of all numbers
        averageOfAllNumbers(list);

        // Exercise 3 : Given a list of numbers, square them and filter the numbers which are greater 100 and then find the average of them
        getNumbersSquareAndFilterAndAverage(list);

        // Exercise 4 : Given a list of numbers, return the even and odd numbers separately
        getNumbersEvenAndOddSeperately(list);

        // Exercise 5 : Given a list of numbers, find out all the numbers starting with 2
        getNumbersStartingWith2(list);

        // Exercise 6 : Given a list of numbers, print the duplicate numbers
        getDuplicateNumbers(list);

        // Exercise 7 : Given a list of numbers, print the maximum and minimum values
        getMaxAndMinValueInList(list);


    }

    private static void getMaxAndMinValueInList(List<Integer> list) {
        int max = list.stream().max(Comparator.comparing(i->Integer.valueOf(i))).get();
        int min = list.stream().min(Comparator.comparing(i->Integer.valueOf(i))).get();

        System.out.println("max: "+max+"min: "+min);


    }

    private static void createMapFromList() {
        //Create a map from list - VERY IMP
        List<Integer> list1 = Arrays.asList(1,7,8,9,5,2,36,4,78,222,24);
        Map<Object,Object> m1 = list1.stream().collect(Collectors.toMap(value->value, value->value.toString()));
        System.out.println("map: "+m1);

    }

    private static void getDuplicateNumbers(List<Integer> list) {

        //method 1 - Collections.frequency(list, num)>1
        Set<Integer> duplicateNum = list.stream().filter(num->Collections.frequency(list, num)>1).collect(Collectors.toSet());
        System.out.println("duplicateNum: "+duplicateNum);

        //Method 2 : IMP
       Set<Integer> duplicateNum1 = list.stream()
                .collect(Collectors.groupingBy(Function.identity()
                        , Collectors.counting()))    // create a map {1=1, 2=1, 3=2, 4=2, 5=1, 7=1, 9=2}
                .entrySet().stream()                 // Map -> Stream
                .filter(m -> m.getValue() > 1)       // if map value > 1, duplicate element
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        System.out.println("duplicateNum1: "+duplicateNum1);

        //Method 3
        Set<Integer> items = new HashSet<>();
        Set<Integer> duplicateNum2 = list.stream()
                .filter(n -> !items.add(n)) // Set.add() returns false if the element was already in the set.
                .collect(Collectors.toSet());

        System.out.println("duplicateNum2: "+duplicateNum2);
    }

    private static void getNumbersStartingWith2(List<Integer> list) {
        List<Integer> startWithTwo =  list.stream().filter(i->i.toString().startsWith("2")).collect(Collectors.toList());
        System.out.println("startWithTwo: "+startWithTwo);

        //Method 2
        List<Integer> startWithTwo1 = list.stream().map(num->String.valueOf(num)).filter(num->num.startsWith("2")).map(i->Integer.valueOf(i)).collect(Collectors.toList());
        System.out.println("startWithTwo1: "+startWithTwo1);
    }

    private static void getNumbersEvenAndOddSeperately(List<Integer> list) {
        List<Integer> even =  list.stream().filter(i->i%2 ==0).collect(Collectors.toList());
        List<Integer> odd =  list.stream().filter(i->i%2 !=0).collect(Collectors.toList());

        System.out.println("even: "+even+"odd: "+odd);
    }

    private static void getNumbersSquareAndFilterAndAverage(List<Integer> list) {

        OptionalDouble result = list.stream().map(i->i*i)
                .filter(num->num>100).mapToDouble(d->d).average();
        System.out.println("result: "+ result);
    }

    private static void averageOfAllNumbers(List<Integer> list) {
        //Collectors.averagingInt(d->d)
        Double avg = list.stream().collect(Collectors.averagingInt(Integer::intValue));
        Double avg4 = list.stream().collect(Collectors.averagingInt(d->d));
        System.out.println("avg: "+ avg);

        //mapToDouble(Integer::intValue).average()
        //mapToDouble(d->d).average();
        OptionalDouble avg1 = list.stream().mapToDouble(Integer::intValue).average();
        OptionalDouble avg2 = list.stream().mapToDouble(d->d).average();
        System.out.println("avg1: "+ avg1 + "avg2: "+ avg2);




    }

    private static void sumOfMapValues() {

        // sum of values of a Map<Object, Integer> data structure
        Map<Object, Integer> map = new HashMap<>();
        map.put("Delhi",1);
        map.put("Bangalore", 2);

        Integer sum5 = map.values()
                .stream()
                .mapToInt(Integer::valueOf)
                .sum();
        System.out.println("Sum of values of Map : "+ sum5);

        // Incomplete - sum of list of values for each key in map
        Map<String, List<Integer>> map2 = new HashMap<>();
        List<Integer> l1 = Arrays.asList(1,7,8,9,5);
        List<Integer> l2 = Arrays.asList(3,6,8,0,2);
        map2.put("Delhi", l1);
        map2.put("Bangalore", l2);
        // map2.values().stream().mapToInt()
       // map2.entrySet().stream().mapToInt(Map.Entry::getValue).sum();
       // System.out.println();




    }

    private static void sumOfAllNum(List<Integer> list) {

        // use summingInt(Integer::valueOf)
        Integer sum1 = list.stream().collect(Collectors.summingInt(Integer::valueOf));
        System.out.println("sum1 = "+sum1);

        //use reduce((a,b)->a+b) or reduce(0, Integer::sum)
        Optional<Integer> sum2 = list.stream().reduce((a, b)-> a+b);
        Integer sum3 = list.stream().reduce(0, Integer::sum);

        //use IntStream.sum() --> mapToInt()
        // use mapToInt(Integer::valueOf) or mapToInt(Integer::intValue)
        Integer sum4 = list.stream()
                .mapToInt(Integer::intValue)
                .sum();



    }


}
