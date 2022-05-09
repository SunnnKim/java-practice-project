package functionalInterface;

import java.util.function.Function;

public class Plus10 implements Function<Integer, Integer> {


    // 2.1 자바 기본제공 함수형인터페이스

    @Override
    public Integer apply(Integer integer) {
        return integer + 10;
    }

}
