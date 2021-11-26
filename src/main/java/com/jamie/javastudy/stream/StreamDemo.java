package com.jamie.javastudy.stream;

import java.util.function.Consumer;

/**
 * @PackageName: com.jamie.study.stream
 * @ClassName: stream
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/6 11:45 上午
 */
public class StreamDemo {

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }
    public static boolean isHeavyApple(Apple apple) {
        return apple.getHeavy() > 10;
    }


    public static void main(String[] args) {

        /*List<Integer> list = Arrays.asList(1, 2, 3, 4);

        list.stream().mapToInt(v -> v * v).forEach(System.out::println);
*/
        Consumer<Integer> consumer1 = x -> System.out.println("first x = " + x);
        Consumer<Integer> consumer2 = x -> {
            System.out.println("second x = " + x);
            throw new NullPointerException("throw exception test");
        };

        Consumer<Integer> consumer3 = x -> System.out.println("third x = " + x);

        consumer1.andThen(consumer3).andThen(consumer2).accept(1);

        /*System.out.println(DateUtil.format(new Date(), "yyyyMMddHHmm"));
        System.out.println(DateUtil.format(new Date(), "yyyyMMddHH"));
        System.out.println(DateUtil.format(new Date(), "yyyyMMdd"));
*/
        /*String[] testCase = {"hello", "best"};
        List<String> strings = Arrays.asList(testCase).stream().filter(v -> v.equals("hello")).collect(Collectors.toList());
        List<String> strings2 = Arrays.asList(testCase).parallelStream().filter(v -> v.equals("hello")).collect(Collectors.toList());

        Thread t =  new Thread(() -> {
            System.out.println("new thread");
        });

        System.out.println(t.getName());
        System.out.println(strings);*/
   /*     Integer[] testCase = {1,3,10,5,7};
        List<Integer> caseList = Arrays.asList(testCase);

        String[] testCase2 = {"xxxx","ssss","c"};
        List<String> caseList2 = Arrays.asList(testCase2);


        Collections.sort(caseList, (Integer e1, Integer e2) -> e2.compareTo(e1));
        System.out.println(caseList);
        Collections.sort(caseList, (Integer e1, Integer e2) -> {
            if (e1 > e2) {
                return 1;
            } else if (e1.equals(e2)){
                return 0;
            }else {
                return -1;
            }
        });

        System.out.println(caseList);




        ///

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer s) {
                System.out.println("hahah" + s);
            }
        };
        Consumer<Integer> consumer1 = (s) -> System.out.println("enenn" + s);

        caseList.stream().forEach(consumer);

        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Random().nextInt();
            }
        };

        System.out.println(supplier.get());
        Supplier supplier2 = () -> new Random().nextInt();
        System.out.println(supplier2.get());

        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer > 5;
            }
        };

        caseList.stream().filter(predicate).collect(Collectors.toList());

        System.out.println(caseList.toString());


        Function<String, Integer> function = new Function<String, Integer>() {

            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };
        Stream<Integer> stream1 = caseList2.stream().map(function);
        System.out.println(stream1.collect(Collectors.toList()));
*/


    }









}
