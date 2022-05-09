package functionalInterface;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;

public class  InterfaceMethod {
    public static void main(String[] args) {
        DefaultFoo2 foo = new DefaultFoo2("default foo");
        foo.printName(); // 일반 메소드
        foo.printNameUpperCase(); // 디폴트메소드

        // 2. java8 새로 추가된 메소드
        List<String> name = new ArrayList<>();
        name.add("sunju");
        name.add("sunnn");
        name.add("kim");
        name.add("foo");

        // iterable
        // 1.1 foreach
        name.forEach(System.out::println);

        // 1.2 spliterator
        Spliterator<String> spliterator = name.spliterator();
        Spliterator<String> spliterator1 = spliterator.trySplit();
        // tryAdvence : Function 받아서 있는 경우에 리턴 (iterator)
        // split 기능이 있음
        System.out.println("=====================");
        while(spliterator.tryAdvance(System.out::println));
        System.out.println("=====================");
        while(spliterator1.tryAdvance(System.out::println));

        // parallel 하게 처리할 때 사용
        // stream 기반이 spliterator 이 있음

        // 2. stream
        System.out.println("=====================");
        long k = name.stream()
                .map(String::toUpperCase)
                .filter(s-> s.startsWith("K"))
                .count();

        System.out.println(k);

        // 3. removeIf
        name.removeIf(s -> s.startsWith("k"));
        name.forEach(System.out::println);


        // Comparator
        // 정렬할 떄 주로 사용. functional interface
        // 정순
        name.sort(String::compareToIgnoreCase);
         name.forEach(System.out::println);
        // 역순
        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        name.sort(compareToIgnoreCase.reversed().thenComparing(String::isEmpty));
        name.forEach(System.out::println);
        // static method
        // 3.1 nullFirst / nullLast

        // 자바 8에서 비 침투적인 상속성 인터페이스로 코드를 짜는게 편함
        // 예시 : webMvcConfigurer 이거를 구현한 어뎁터 클래스는 deprecation됨
        // webMvcConfigurer 인터페이스에 디폴트를 제공하기 떄문임

    }
}
/**
 *
 */