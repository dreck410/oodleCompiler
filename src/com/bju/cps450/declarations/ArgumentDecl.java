package com.bju.cps450.declarations;

/**
 * Created by daniel on 3/6/16.
 */
public class ArgumentDecl extends AbstractVarDecl {

    private String Name;

    public void setMethodOwner(MethodDecl MethodOwner){
        this.methodOwner = MethodOwner;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
