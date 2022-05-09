package functionalInterface;

import java.util.function.*;

public class Foo {

    public static void main(String[] args) {
        // 2. 함수형 인터페이스의 구현
        // JAVA8 이전의 구현방법
        RunSomthing runSomthing = new RunSomthing() {
            // 익명내부클래스
            @Override
            public void doIt() {
                System.out.println("runSomthing functionalInterface");
            }
        };
        // Java8 이후
        // functionalInterface.Ramda 형태로 변환
        RunSomthing runSomthing1 = () -> System.out.println("runSomthing1 functionalInterface");
        // 2줄이상
        RunSomthing runSomthing2 = () -> {
            System.out.println("runSomthing2 functionalInterface");

            System.out.println("runSomthing2 functionalInterface.Ramda");
        };

        // 실행
        runSomthing.doIt();
        runSomthing1.doIt();
        runSomthing2.doIt();

        runSomthing.printDefault(); // default
        RunSomthing.printName(); // static

        // 람다의 사용
        /**
         * 람다표현식 :
         * 마치 함수를 사용한 것 같아 보이시만, 실제로는 특수한 형태의 Object를 구현한 것이라고 할 수 있음
         * 함수형인터페이스를 inline 형태로 구현한 것이지만, 함수처럼 보이기 때문에
         * 이러한 특수한 형태를 리턴하는 게 가능함
         * First Class Object로 사용할 수 있음
         * 고차함수 : 함수가 함수를 리턴 할 수 있음
         * 함수형 프로그래밍을 할 때 주의 : 외부에서 값을 참고하는 경우, 동일한 결과가 나올 수 있도록 인터페이스를 짜는 것이 중요함
         */
        // 기본제공

        // 2. function
        //
        // 기본제공 함수형인터페이스
        // 2.1 기본제공하는 인터페이스를 클래스에서 상속받아 사용한다
        Plus10 plus10 = new Plus10();
        plus10.apply(10);

        // 2.2 직접 구현한다
        Function<Integer, Integer> plus20 = (i) -> i+20;

        System.out.println(plus10.apply(1));
        System.out.println(plus20.apply(1));

        // Function<T, R>
        // 미리 있는 함수들 조합
        // 인자 T를 받아서 R을 리턴
        Function<Integer, Integer> iPlus1 = (i) -> i + 1;
        Function<Integer, Integer> iMultiply10 = (i) -> i * 10;

        // Function > apply
        System.out.println( iPlus1.apply(1)); // iPlus 함수에 1 할당 :: 2

        // Function > compose
        Function<Integer, Integer> multiply10AndPlus1 = iPlus1.compose(iMultiply10);
        System.out.println( "111 "+multiply10AndPlus1.apply(2) ); //
        System.out.println( iPlus1.compose(iMultiply10).apply(2) ); // 21
        // multiply10AndPlus1.apply(2) ==  iPlus1.compose(iMultiply10).apply(2)

        // Function > andThen
        iPlus1.andThen(iMultiply10).apply(10);// (10+1) * 10 == 110
        System.out.println( iPlus1.andThen(iMultiply10).apply(10));

        // BiFunction<T, U, R>
        // 두개의 값 T, U를 받아서 R을 리턴
        BiFunction<Integer, Integer, Integer> hi = (i, j) -> i+j;

        // Consumer<T>
        // T를 받고 void (return 불가)
        Consumer<String> printT = ( name ) -> {
            System.out.println("hello " + name + "world");
        };
        Consumer<String> printT2 = ( name ) -> System.out.println(name);


        Function<String, String> afterPrint = (name) -> name;
        printT.accept("RAMDA");
        printT.andThen(printT2).accept("RAMDA");

        // Supplier<T>
        // 받아올 값의 타입을 정의
        Supplier<Integer> get10 = () -> 10; // 무조건 10 리턴

        // Predicate<T>
        // 인자값을 받아서 true/false 리턴
        // 같은 매개변수 타입으로 and, or 조건 가능
        Predicate<String> startsWithS = (str) -> str.startsWith("s");
        Predicate<Integer> isEven = (i) -> i % 2 == 0;
        Predicate<Integer> isOver10 = (i) -> i > 10 ;

        System.out.println( startsWithS.test("hello") ) ; // false
        System.out.println( isEven.or(isOver10).test(11) ); // true
        System.out.println( isEven.and(isOver10).test(11) ); // false


        // UnaryOperator
        // Function<T, R>의 특수한 형태로, 입력값 하나를 받아서 동일한 타입을 리턴하는 함수 인터페이스
        // Function 상속받았기 때문에 Function 내부 함수들 사용가능
        // 입력, 출력값의 타입이 같은 경우 사용
        UnaryOperator<Integer> uPlus10 = (i) -> i + 10;
        UnaryOperator<Integer> uMultiply3 = (i) -> i * 3;
        System.out.println(uPlus10.apply(10));
        System.out.println(uPlus10.andThen(uMultiply3).apply(10));

        // BinaryOperator<T>
        // BiFunction 의 특수한 형태, 출력값 2개, 리턴값이 모두 같은 자료형인 경우 사용
        BinaryOperator<Integer> biPlus = (i, j) ->  i + j + 10;

        System.out.println(biPlus.apply(1,2));


        // 자바가 제공하는 주요 함수형 인터페이스들은 많이 있으니 웬만하면 찾아보고 할 수 있음
        // 굳이 새로 함수형인터페이스 만들지 않고도 기본제공기능으로 할 수 있음












    }
}
