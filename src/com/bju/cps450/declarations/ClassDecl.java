package com.bju.cps450.declarations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 3/6/16.
 */
public class ClassDecl extends AbstractDeclaration {
    private String Name;
    private List<MethodDecl> methods;
    private List<VarDecl> variables;


    public ClassDecl(){
        methods = new ArrayList<MethodDecl>();
        variables = new ArrayList<VarDecl>();
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<MethodDecl> getMethods() {
        return methods;
    }

    public void setMethods(List<MethodDecl> methods) {
        this.methods = methods;
    }

    public List<VarDecl> getVariables() {
        return variables;
    }

    public void setVariables(List<VarDecl> variables) {
        this.variables = variables;
    }

    public AbstractDeclaration lookupVariables(String name){
        for(int i = 0; i < getVariables().size(); ++i){
            if(getVariables().get(i).getName().equals(name)){
                return getVariables().get(i);
            }
        }
        for(int i = 0; i < getMethods().size(); ++i){
            if(getMethods().get(i).getName().equals(name)){
                return getMethods().get(i);
            }
        }
        return null;
    }
}
