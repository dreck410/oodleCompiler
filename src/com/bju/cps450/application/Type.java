package com.bju.cps450.application;

/**
 * Created by daniel on 3/4/16.
 */
public class Type {

    // built in types
    public static Type oodleInt = new Type("int");
    public static Type oodleString = new Type("string");
    public static Type oodleArray = new Type("array");
    public static Type oodleBoolean = new Type("boolean");


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
