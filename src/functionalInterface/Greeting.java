package functionalInterface;

public class Greeting {

    // 메소드 레퍼런스용 예제 클래스
    public String name;

    public Greeting(){
    }

    public Greeting(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String hello(String name){
        return "hello" + name;
    }

    public static String hi(String name) {
        return "hi " + name;
    }
}
