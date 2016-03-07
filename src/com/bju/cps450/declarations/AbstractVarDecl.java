package com.bju.cps450.declarations;

/**
 * Created by daniel on 3/6/16.
 */
public class AbstractVarDecl extends AbstractDeclaration {
    protected String name;
    protected MethodDecl methodOwner;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public MethodDecl getMethodOwner(){
        return methodOwner;
    }

    public void setMethodOwner(MethodDecl m){
        this.methodOwner = m;
    }

}
