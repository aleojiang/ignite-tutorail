package pjiang.tutorial.ignite;

import java.util.Arrays;

/**
 * Created by: patrick.jiang@activenetwork.com
 * Created on:  10:34 2018/10/19.
 */
public enum MyType {
    A("a"),
    B("b"),
    C("c");
    
    private String value;
    
    MyType(String value) {
        this.value = value;
    }
    
    public static MyType fromString(String text) {
        return Arrays.stream(MyType.values())
                .filter(v -> v.getValue().equals(text))
                .findAny()
                .map(v -> MyType.valueOf(v.name()))
                .orElse(MyType.A);
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
}
