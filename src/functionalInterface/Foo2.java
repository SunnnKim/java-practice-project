package functionalInterface;

import java.util.Locale;

public interface Foo2 {

    void printName();

    // @implSpec 자바독 태그 사용해서 어떤 기능인지 명시해두는 것이 좋음
    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase());
    };
    // 여러개 둘 수 있음
    default void hello() {
    };

    String getName();

    static void printAnything(){

    }
}
/*
    기본 메소드가 제대로 동작하는지 확인하기 위해서는
    문서화를 통해 기본 구현체의 기능과 역할을 반드시 명시하는 것이 좋다.
    자바8에서는 @implSpec 이라는 자바독 어노테이션을 제공하므로
    반드시 기능에 대한 정의를 해놓도록 하자

    또한 기본 메소드는 구현체에서 재정의가 가능하다

    * Object 에서 제공하는 메소드들은 기본메소드로 선언할 수 있지만 재정의 할 수 없다!
    (toString() 과 같은 함수..)

    * 기본메소드를 각각 가지고 있는 두개의 인터페이스를 모두 상속받는 클래스는 컴파일에러가 난다
    충돌하는 default method 해결을 위해서는 반드시 오버라이딩으로 재정의 해줘야한다

* */
