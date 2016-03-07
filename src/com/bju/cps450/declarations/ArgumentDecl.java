package com.bju.cps450.declarations;

/**
 * Created by daniel on 3/6/16.
 */
public class ArgumentDecl extends AbstractVarDecl {
    private MethodDecl MethodOwner;
    private String Name;
    public MethodDecl getClassOwner(){
        return MethodOwner;
    }

    public void setMethodOwner(MethodDecl MethodOwner){
        this.MethodOwner = MethodOwner;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
