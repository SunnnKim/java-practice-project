package functionalInterface;

// 1. 함수형인터페이스의 사용
// FunctionalInterface : 자바기본제공 어노테이션, 함수형인터페이스
// 함수형인터페이스를 더 견고하게 사용할 수 있게됨
@FunctionalInterface
public interface RunSomthing {

    // 1.1 추상메소드
    void doIt(); // 추상메소드 : 함수형인터페이스에서는 추상메소드 1개만 쓸 수 있다.
//    void doItAgain(); // 이미 추상메소드가 존재하므로 주석 해제시 컴파일 오류가 뜬다.

    // 1.2 그 외 정의된 메소드의 사용
    // static, public, default 등 정의된 메소드는 사용가능하다
    // 인터페이스임에도 불구하고 다양한 형태의 메소드를 구현가능할 수 있음!
    static void printName(){
        System.out.println("this is static Method");
    };
    default void printDefault(){
        System.out.println("this is Method");
    };

}

