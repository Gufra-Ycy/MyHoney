package com.gufra.Builder;

public class MyBuilder {
    private String a;
    private String b;
    public MyBuilder(){

    }

    public MyBuilder setA(String a){
        this.a = a;
        return this;
    }

    public MyBuilder setB(String b){
        this.b = b;
        return this;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }
}
