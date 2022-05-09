package functionalInterface;

import java.util.function.*;

public class Ramda {
    public static void main(String[] args) {

        // functionalInterface.Ramda
        UnaryOperator<Integer> plus5 = (i) -> {
            // {} 안에 바디 작성
            return i + 5;
        };
        // 한줄인 경우 {} 없이 작성가능
        // return 예약어도 생략가능
        UnaryOperator<Integer> plus10 = (i) -> i + 10;
        System.out.println( plus10.apply(10) );

        // Supplier : 인자없고 리턴값만
        Supplier<Integer> get10 = () -> {
            return 10;
        };

        // Bifunction
        BiFunction<Integer, String, String> process = (number, str) -> {
            return number + " :: " + str;
        };

        // 인자의 타입은 생략 가능, 컴파일러가 추론(infer)하지만 명시할 수도 있다. (Integer one, Integer two)
        BinaryOperator<Integer> test1 = (Integer a, Integer b) -> a + b ;
        BinaryOperator<Integer> test2 = (a, b) -> a + b ;

        Ramda ramda = new Ramda();
        ramda.run();


    }
    private void run(){
        // 변수 캡처 (Variable Capture)
        /*

        람다에서 람다를 감싸고 있는 영역의 로컬 배리어블이 있다고 가정해보면 (baseNumber)
        펑션안에서 로컬변수를 참조한다고 가정하면
        이때 이 변수가 캡쳐가 된다.
        변수 캡쳐라는 기능은 이전에서도 익명 클래스, 내부 클래스에서도 사용됨
        자바 8 이전에는 final 이 붙어있어야만 익명클래스, 내부 클래스에서 사용할 수 있었지만
        스코프 밖에있는 변수를 사용하는 경우, 참조가 가능하지만 final 을 사용했어야했음
        java 8 부터는 final 을 생략할 수 있는 경우가 있는데, 사실상 final 인 경우!

        만약 스코프 내 변수가 effective final 이 아닌 경우는 람다에서 참조가 불가능하다.




        */
        // 1. 로컬변수 캡쳐
        // final이거나 effective final 인 경우에만 참조할 수 있다.
        // 그렇지 않을 경우 concurrency 문제가 생길 수 있어서 컴파일가 방지한다.

        // 2. effective final
        // 이것도 역시 자바 8부터 지원하는 기능으로 “사실상" final인 변수.
        // final 키워드 사용하지 않은 변수를 익명 클래스 구현체 또는 람다에서 참조할 수 있다.

        // 3. 익명 클래스 구현체와 달리 ‘쉐도윙’하지 않는다.
        // 익명 클래스는 새로 스콥을 만들지만, 람다는 람다를 감싸고 있는 스콥과 같다.

        /*
            쉐도잉 ?
            한 클래스 속 다양한 스코프 상에서 같은 이름의 로컬변수가 존재하는 경우,
            가장 안쪽의 변수로 바깥쪽 스코프의 변수 값이 가려지게 되는 것을 쉐도잉이라고 함 (Nested Class)
            쉐도잉이 가능한 경우는 다음과 같다.
            1. 로컬클래스 속 지역변수
            2. 익명클래스 속 지역변수

            람다는 쉐도잉이 안된다. 람다는 람다를 감싸는 스코프과 같기때문에 쉐도잉이 일어나지 않는다
            아래 예제를 보자.

         */


        // 로컬변수
        // 사실상 final 변수이기 때문에 어디서도 변경할 수 없으므로 final 키워드를 생략할 수 있음 (2. effective final)
        int baseNumber = 10; // local variable
        // baseNumber++; // 사실상 final 변수이므로 사용 불가



        // 로컬클래스
        class LocalClass{
            void printBaseNumber(){
                // printBaseNumber 스코프
                int baseNumber = 5; // 쉐도잉 가능
                System.out.println(baseNumber);
            }
        }

        // 익명클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                // accept 스코프
                int baseNumber = 7; // 쉐도잉 가능
                System.out.println(baseNumber);
            }
        };

        // 람다
        IntConsumer printInt = (i) -> {
            // 쉐도잉 불가
//            int baseNumber = 9; // 오류
            // printInt 의 스코프는 run() 메소드의 스코프와 같기 때문에 쉐도잉이 안됨
            // 동일한 이름의 변수를 정의하는 것이 불가능하다

            System.out.println(i + baseNumber);
        };

        LocalClass l = new LocalClass();
        l.printBaseNumber();

        integerConsumer.accept(0);

        printInt.accept(3);

        // baseNumber++; // 이 코드는 사용불가함 (컴파일 에러)
        // 람다에서는 final, effective final 만 참조가 가능하기 때문에
        // 변수의 값이 변경되는 경우는 사용이 불가능하다
        // err Msg : Variable used in lamda expression should be final or effective final

    }
}
