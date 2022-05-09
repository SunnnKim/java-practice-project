package functionalInterface;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MethodEx {
    // 메소드 레퍼런스
    // 람다표현식을 구현할 떄 쓰는 방법
    //
    public static void main(String[] args) {

        // 예제 1
        // 문자열을 받아서 문자열을 리턴하는 함수
        // 1.1 람다를 사용하기
        UnaryOperator<String> hi = (str) -> "hi " + str;

        // 1.2 같은 기능을 하는 functionalInterface.Greeting 클래스의 hi 라는 메소드를 호출하기
        // :: 의 의미 : hi2 의 구현체로 functionalInterface.Greeting 클래스의 hi 스태틱 함수를 쓰겠다는 의미
        // 메소드 레퍼런스 사용법 ::
        UnaryOperator<String> hi2 = Greeting::hi;


        // 특정한 인스턴스의 메소드를 사용하는 경우
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;

        System.out.println(hello.apply(""));
        // 생성자 참조하는 경우
        // 입력값이 없는데 리턴값은 있는 경우의 함수형인터페이스 사용
        // 생성자는 클래스 타입이 리턴값이기 때문에 레퍼런스로 만들경우는 new로 만듦
        Supplier<Greeting> greetingConstructor = Greeting::new;
        // but, 이 자체로 실제 생성자가 만들어지는 것은 아니고 호출해주어야 함
        greetingConstructor.get();

        // 마찬가지로 다른 함수들도 참조만 한 것이지, 실제 호출을 위해서는 불러와야함
        hi2.apply("sunju");
        hello.apply("lullu");
        greetingConstructor.get().hello("kiki");


        System.out.println( hi2.apply("sunju") );
        System.out.println( hello.apply("lullu") );
        System.out.println( greetingConstructor.get());

        // 매개변수 있는 생성자 참조하는 경우
        // 매개변수 있고, 출력값이 있는 경우의 인터페이스 사용
        Function<String, Greeting> greetingConstructor2 = Greeting::new;

        // greetingConstructor와 greetingConstructor2 는 같은 레퍼런스를 참조하는 것 같아 보이지만
        // 다른 생성자를 참조하고 있음
        // 메소드 레퍼런스만 참조하는 경우, 인자값이 있는 생성자인지 확인해야함

        Greeting g = greetingConstructor.get();
        Greeting sunju = greetingConstructor2.apply("sunju");
        System.out.println(g.getName());
        System.out.println(sunju.getName());


        // 임의 객체의 인스턴스 메소드 참조하는 방법 (불특정다수)
        // 특정 타입의 불특정다수의 인스턴스 메소드?
        String[] names = {"Sunju", "sunju", "kim"};

        // Comparator : Java 8 이후엔 functional interface가 됨
        // sort 함수는
        Arrays.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });
        // 람다식
        Arrays.sort(names, (o1, o2)-> 0);


        // 임의의 객체의 인스턴스 메소드를 참조하는 경우는
        // 타입::인스턴스메소드
        // 형식으로 사용한다
        Arrays.sort(names, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(names));
        // 위 예제의 경우, String 타입의 임의의 인스턴스에 compareToIgnoreCase를 적용




    }
}
