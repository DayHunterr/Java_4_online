package ua.com.alevel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        // Create stream

//        List<Integer> integers = Arrays.asList(1, 5, 9);
//        Stream<Integer> integerStream1 = integers.stream(); //1
//
//        Stream<Integer> integerStream2 = Stream.of(1, 5, 9); //2
//
//        IntStream intStream = IntStream.of(1, 5, 9); //3
//
//        Stream<Integer> integerStream3 = Arrays.stream(new Integer[]{1, 5, 9}); //4
//
//        Stream<Integer> integerStream4 = Stream.<Integer>builder().add(1).add(5).add(9).build(); //5

//        Path path = Paths.get("home.txt");
//        Stream<String> lines = Files.lines(path); //6

//        int[] ints = [1 ... 100];

//        IntStream intStream1 = IntStream.range(1, 101);
//        Stream<Integer> intStream1 = Stream.of(1, 9, 10);
//        intStream1.forEach(value -> System.out.println("value = " + value));

//        List<Integer> integers1 = intStream1
//                .filter(value -> value % 2 == 0)
//                .filter(value -> value > 30 && value < 60)
//                .boxed()
//                .toList();
//        integers1.forEach(value -> System.out.println("value = " + value));
//        intStream1.forEach(value -> System.out.println("value = " + value));

//        Stream<Integer> integerStream = Stream.generate(() -> 100);
//        List<Integer> ret = IntStream.rangeClosed(0, 100).boxed().collect(Collectors.toList());
//        ret.forEach(value -> System.out.println("value = " + value));

//        List<Integer> randList = new Random().ints(100, 0, 10000)
//                .boxed().collect(Collectors.toList());

//        randList.forEach(value -> System.out.println("value = " + value));

//        System.out.println();

//        List<Integer> integers = randList
//                .stream()
//                .sorted()
//                .skip(30)
//                .filter(value -> value % 2 == 0)
//                .toList();
//        integers.forEach(value -> System.out.println("value = " + value));

//        Stream<Integer> integerStream = Stream.of(1,1,1,1 ,3, 9, 4, 5, 90, 12, 12, 27);
//        Stream<Integer> integerStream2 = Stream.of(1,1,1,1 ,3, 9, 4, 5, 90, 12, 12, 27);

//        long start = System.nanoTime();
//        List<Integer> integers1 = integerStream
//                .sorted()
//                .filter(value -> value % 3 == 0)
//                .distinct()
//                .toList();
//        long end = System.nanoTime() - start;
//        System.out.println("end bad = " + end);
//
//        start = System.nanoTime();
//        List<Integer> integers2 = integerStream2
//                .distinct()
//                .filter(value -> value % 3 == 0)
//                .sorted()
//                .toList();
//        end = System.nanoTime() - start;
//        System.out.println("end super = " + end);
//        integers1.forEach(v -> System.out.println("v = " + v));

//        List<String> stringStream = integerStream
//                .map(v -> v.toString())
//                .toList();
//        stringStream.forEach(v -> System.out.println("v = " + v));

//        Stream<Integer> randList = new Random()
//                .ints(200, 0, 10000)
//                .boxed();

//        List<Integer> collect = randList.map(v -> v * 2).toList();

//        Long count = randList.count();
//        System.out.println("count = " + count);
//        Integer max = randList.max(Comparator.naturalOrder()).get();
//        System.out.println("max = " + max);
//        Integer min = randList.min(Comparator.naturalOrder()).get();
//        System.out.println("min = " + min);
//        Integer first = randList.findFirst().get();
//        System.out.println("first = " + first);
//        Integer any = randList.findAny().get();
//        System.out.println("any = " + any);

//        Stream<Integer> integerStream2 = Stream.of(1,1,1,1 ,3, 9, 4, 5, 90, 12, 12, 27);

//        boolean allMatch = integerStream2.allMatch(integer -> integer == 1);
//        System.out.println("allMatch = " + allMatch);
//        boolean noneMatch = integerStream2.noneMatch(integer -> integer == 1);
//        System.out.println("noneMatch = " + noneMatch);
//        boolean anyMatch = integerStream2.anyMatch(integer -> integer == 1);
//        System.out.println("anyMatch = " + anyMatch);

//        Integer sum = integerStream2.reduce(0, (a, b) -> a + b);
//        Integer sum = integerStream2.mapToInt(value -> value.intValue()).sum();
//        Integer sum = integerStream2.collect(Collectors.summingInt(value -> value.intValue()));
//        Integer sum = integerStream2.mapToInt(Integer::intValue).sum();

//        System.out.println("sum = " + sum);

//        new DoubleDot().run();
        new Converter().run();
    }
}
