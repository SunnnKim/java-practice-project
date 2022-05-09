package functionalInterface;

public interface Bar extends Foo2{
    // Foo2의 기본메소드를 상속받고 싶지 않은 경우, 다시 추상화 가능
    void printNameUpperCase();


}
