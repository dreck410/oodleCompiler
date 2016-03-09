package com.bju.cps450.application;


import com.bju.cps450.declarations.*;
import com.bju.cps450.node.PType;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by daniel on 3/4/16.
 */
//TODO: flesh this out!!!
public class SymbolTable {
    private Stack<List<AbstractDeclaration>> symbolTableStack = new Stack<>();
    private int currentScope;
    private ClassDecl currentClassDecl;
    private MethodDecl currentMethodDecl;

    public MethodDecl getCurrentMethodDecl() {
        return currentMethodDecl;
    }

    public ClassDecl getCurrentClassDecl() {
        return currentClassDecl;
    }



    public <T extends AbstractDeclaration> T lookup(String name, Class<T> clazz){
        return lookup(name, false, clazz);
    }


    /**
     * finds a symbol in the table if it's there. returns null if not found
     * @param name name of symbol to find
     * @param allScopes if you want to look in all of the scopes
     * @param clazz the class type of the symbol
     * @param <T>
     * @return
     */
    private <T extends AbstractDeclaration> T lookup(String name, boolean allScopes, Class<T> clazz) {
        for (int i = symbolTableStack.size() - 1; i >= 0; --i) {
            for (int j = 0; j < symbolTableStack.get(i).size(); ++j) {
                if (symbolTableStack.get(i).get(j).getClass().equals(clazz)) {
                    // they're the same Type
                    AbstractDeclaration decl = symbolTableStack.get(i).get(j);
                    if (clazz.equals(ClassDecl.class)) {
                        // they're both classes!

                        if (((ClassDecl) decl).getType().getName().equals(name)) {
                            // they have the same name
                            // found it!!
                            return (T) decl;

                        }
                    } else if (clazz.equals(MethodDecl.class)) {
                        // they're both methods!
                        if (((MethodDecl) decl).getType().getName().equals(name)) {
                            // they have the same name!
                            return (T) decl;
                        }
                    } else if (clazz.equals(VarDecl.class)) {
                        if (((VarDecl) decl).getType().getName().equals(name)) {
                            return (T) decl;
                        } // if Var Decl
                    }// if
                }// if same class
            } // for j
            if(!allScopes){
                // quit early
                return null;
            }
        } // for i
        // never found it looked everywhere
        return null;
    } // end lookup


    public void beginScope() throws Exception{
        symbolTableStack.push(new ArrayList<AbstractDeclaration>());
        switch (symbolTableStack.size()){
            case 1:
                // Globals??
                currentScope = 1;

                break;
            case 2:
                // class
                currentScope = 2;

                break;
            case 3:
                // Methods
                currentScope = 3;
                break;
            default:
                // uhhh NO
                throw new Exception("Too many scopes");


        }

    }


    public void push(String name, AbstractDeclaration decl){
        //TODO: what does this mean?

    }

    private void addDeclToSymbolTable(AbstractDeclaration decl){
        symbolTableStack.get(symbolTableStack.size() - 1).add(decl);
    }

    public void addArgumentDecl(String name, Type t) throws Exception {
        if(lookup(name, ArgumentDecl.class) == null){
            if(symbolTableStack.size() == 3){
                // good! we are where we can create args
            }
        }
    }

    public MethodDecl addMethodDecl(String name, PType type) throws Exception {
        if(lookup(name, MethodDecl.class) == null){
            if(symbolTableStack.size() == 2){
                // has to be in class
                MethodDecl decl = new MethodDecl();
                decl.setName(name);
                decl.setType(new Type(type.toString()));
                addDeclToSymbolTable(decl);
                currentMethodDecl = decl;
                return decl;
            } else{
                throw new Exception("Can only define methods inside of classes");
            }
        } else{
            throw new Exception("Method '"+ name + "' already defined in this scope");
        }
    }


    public VarDecl addVarDecl(String name, PType type) throws Exception{
        if (lookup(name, VarDecl.class) == null) {
            if (symbolTableStack.size() != 1) {
                // has to be not in global
                VarDecl decl = new VarDecl();
                decl.setName(name);
                decl.setType(new Type(type.toString()));
                addDeclToSymbolTable(decl);
                return decl;
            } else {
                throw new Exception("Can't define variables at the global scope");
            }

        } else{
            throw new Exception("Variable '"+ name +"' already defined in this scope");
        }
    }

    public ClassDecl addClassDecl(String name) throws Exception{
        // no two classes of the same name
        if(lookup(name, ClassDecl.class) == null) {
            // check the scope
            if (symbolTableStack.size() == 1) {
                ClassDecl decl = new ClassDecl();
                decl.setName(name);
                decl.setType(new Type(name));
                addDeclToSymbolTable(decl);
                currentClassDecl = decl;
                return decl;
            } else {
                throw new Exception("can only add classes at global level");
            }
        } else {
            throw new Exception("A class with that name '" + name + "' already exists.");
        }
    }

    public void endScope() throws Exception{
        int i = symbolTableStack.size() - 1;
        currentScope = i;

        switch (i) {
            case 1:
                // Globals??
                for(int j = 0; j < symbolTableStack.get(i).size(); ++j){
                    if (symbolTableStack.get(i).get(j) instanceof VarDecl){
                        ((VarDecl) symbolTableStack.get(i).get(j)).setClassOwner(currentClassDecl);
                        currentClassDecl.getVariables().add(((VarDecl) symbolTableStack.get(i).get(j)));
                    }
                    if (symbolTableStack.get(i).get(j) instanceof MethodDecl){
                        ((MethodDecl) symbolTableStack.get(i).get(j)).setClassOwner(currentClassDecl);
                        currentClassDecl.getMethods().add(((MethodDecl) symbolTableStack.get(i).get(j)));
                    }
                }

                break;
            case 2:
                // class
                // every item in this scope
                for (int j = 0; j < symbolTableStack.get(i).size(); ++j) {
                    if (symbolTableStack.get(i).get(j) instanceof ArgumentDecl) {
                        ((ArgumentDecl) symbolTableStack.get(i).get(j)).setMethodOwner(currentMethodDecl);
                        currentMethodDecl.getArguments().add(((ArgumentDecl) symbolTableStack.get(i).get(j)));
                    }
                    if (symbolTableStack.get(i).get(j) instanceof VarDecl){
                        ((VarDecl) symbolTableStack.get(i).get(j)).setMethodOwner(currentMethodDecl);
                        currentMethodDecl.getVariables().add(((VarDecl) symbolTableStack.get(i).get(j)));
                    }
                }
                break;
            case 3:
                // Methods
                // never should be here?
                throw new Exception("How did we get 4 things in the stack!");


            default:
                // uhhh NO
                throw new Exception("Attempted to pop empty symbol table");


        }



        symbolTableStack.pop();
    }



}
