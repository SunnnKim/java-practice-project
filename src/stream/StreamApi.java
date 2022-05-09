package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApi {
    // Stream API
    /*
        sequence of elements supporting sequential and parallel aggregate operations
        functional in nature : 스트림이 처리하는 소스를 변경하지 않음!!!
        연속된 데이터를 처리하는 operation들의 모음
        자체가 데이터나 컬렉션이 아님
        데이터 내 처리를 담당함
        스트림으로 처리하는 데이터는 오직 한번만 처리한다.
        무제한일수도 있음 (short Circuit)
        중개 오퍼레이션은 근본적으로 lazy
        손쉽게 병렬처리
     */

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("k");
        names.add("kim");
        names.add("kimsun");
        names.add("kimsunju");

        // 1. map을 사용해서 전달하는 문자열을 upperCase로
        Stream<String> stringStream = names.stream().map(String::toUpperCase);
//        names.stream().map(s -> s.toUpperCase());
        names.forEach(System.out::println);

        // 무제한으로 데이터 처리 가능함
        // 스트림을 받아 스트림으로 처리가능, 쪼개서 데이터 처리 가능

        // 중개 오퍼레이션 : 근본적으로 lazy
        /**
         * * Stream pipe line
         * - 0 또는 다수의 중개 오퍼레이션과 한개의 종료 오퍼레이션으로 구성한다.
         * - 스트림의 데이터 소스는 오직 터미널 오퍼레이션을 실행할 때에만 처리한다.
         *
         *
         * 1. 중개형 오퍼레이터
         * - stream 리턴
         * - Stateless / Stateful 오퍼레이션으로 더 상세하게 구분할 수 잇음
         * - lazy 하다 : 의미는 좀더 찾아봐야할듯?
         * - filter, map, limit, skip, sort 등
         *
         * 2. 종료형 오퍼레이션
         * - 스트림을 리턴하지 않음
         * - 특정 컬렉션이나 타입을 리턴
         * - collect , allMatch, forEach, min, max....
         *
         */
        System.out.println("==================");
        names.stream().map(s->{
            System.out.println(s); // 실행안됨
            return s.toUpperCase();
        });
        System.out.println("==================");
        List<String> collect = names.stream().map(s->{
            System.out.println(s); //
            return s.toUpperCase();
        }).collect(Collectors.toList());

        // 병렬처리
        // parallelStream 는 병렬처리를 함 (다른 스레드)
        // 병렬처리가 다 좋은 거는 아님 (멀티스레드)
        // 스레드를 만들어서 처리하는 비용이 들기 때문에 오히려 한 스레드에서 처리하는거보다 오래걸리고 비용이 많이 들수도있음
        // 데이터가 방대하고 큰경우는 병렬처리가 좋지만 일반적인 경우는 stream 처리하는 것도 괜찮다

        names.parallelStream().map(String::toUpperCase);
        // 병렬처리 확인
        List<String> lists = names.parallelStream().map(s->{
            System.out.println(s + "  " + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());
        lists.forEach(System.out::println);
        // 일반스트림
        List<String> lists2 = names.stream().map(s->{
            System.out.println(s + "  " + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());
        lists2.forEach(System.out::println);

        /**
         * parallelStream ::::
         * --------------------
         * kimsunju  ForkJoinPool.commonPool-worker-5
         * kim  ForkJoinPool.commonPool-worker-3
         * kimsun  main
         * k  ForkJoinPool.commonPool-worker-7
         *
         *
         * stream()
         * -------------------
         * k  main
         * kim  main
         * kimsun  main
         * kimsunju  main
         */
        // 병렬처리와 일반스트림의 성능은 직접 처리해보면서 확인하는 것이 좋다@

    }
}
