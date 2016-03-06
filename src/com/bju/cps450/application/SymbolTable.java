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

    public void beginScope() throws Exception{
        symbolTableStack.push(new ArrayList<AbstractDeclaration>());
        switch (symbolTableStack.size()){
            case 1:
                // Globals??

                break;
            case 2:
                // class
                break;
            case 3:
                // Methods
                break;
            default:
                // uhhh NO
                throw new Exception("Too many scopes");


        }

    }

    private void addDeclToSymbolTable(AbstractDeclaration decl){
        symbolTableStack.get(symbolTableStack.size() - 1).add(decl);
    }

    public void endScope(){
        int i = symbolTableStack.size() - 1;




        symbolTableStack.pop();
    }



}
