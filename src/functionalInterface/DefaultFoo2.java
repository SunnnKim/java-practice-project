package functionalInterface;

public class DefaultFoo2 implements Foo2{

    String name;
    //

    public DefaultFoo2(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println(this.name);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
