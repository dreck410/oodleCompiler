package com.bju.cps450.declarations;

import com.bju.cps450.application.Type;

/**
 * Created by daniel on 3/4/16.
 */
public class AbstractDeclaration {

    protected Type type;

    public Type getType(){
        return type;
    }

    public void setType(Type type){
        this.type = type;
    }
}
