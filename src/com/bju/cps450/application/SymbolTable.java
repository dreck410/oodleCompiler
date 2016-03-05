package com.bju.cps450.application;


import com.bju.cps450.declarations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by daniel on 3/4/16.
 */
//TODO: flesh this out!!!
public class SymbolTable {
    private Stack<List<AbstractDeclaration>> symbolTableStack = new Stack<>();

    public void beginScope(){
        symbolTableStack.push(new ArrayList<AbstractDeclaration>());


    }

    public void endScope(){
        int i = symbolTableStack.size() - 1;




        symbolTableStack.pop();
    }

}
