package com.bju.cps450.application;

/**
 * Created by daniel on 3/4/16.
 */
public class Type {

    // built in types
    public static Type OodleInteger = new Type("int");
    public static Type OodleString = new Type("string");
    public static Type OodleArray = new Type("array");
    public static Type OodleBoolean = new Type("boolean");


    private String name;

    public Type(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Type type = (Type) o;
        return name.equals(type.name);
    }

}
