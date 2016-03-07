package com.bju.cps450.declarations;

/**
 * Created by daniel on 3/6/16.
 */
public class VarDecl extends AbstractVarDecl{
    private ClassDecl classOwner;

    public ClassDecl getClassOwner(){
        return classOwner;
    }

    public void setClassOwner(ClassDecl classOwner){
        this.classOwner = classOwner;
    }
}
