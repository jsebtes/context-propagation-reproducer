package org.acme.context;

public class MyContext {

    private static final ThreadLocal<String> myValue = new ThreadLocal<>();

    public static void setMyValue(String value) {
        myValue.set(value);
    }

    public static String getMyValue() {
        return myValue.get();
    }

    public static void clear() {
        myValue.remove();
    }

}
