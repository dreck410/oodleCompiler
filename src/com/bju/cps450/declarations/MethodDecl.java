package com.bju.cps450.declarations;

import com.bju.cps450.application.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 3/6/16.
 */
public class MethodDecl extends AbstractDeclaration {
    private String name;
    private List<ArgumentDecl> arguments;
    private List<VarDecl> variables;
    private ClassDecl classOwner;


    public MethodDecl(){
        arguments = new ArrayList<ArgumentDecl>();
        variables = new ArrayList<VarDecl>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ArgumentDecl> getArguments() {
        return arguments;
    }

    public void setArguments(List<ArgumentDecl> arguments) {
        this.arguments = arguments;
    }

    public List<VarDecl> getVariables() {
        return variables;
    }

    public void setVariables(List<VarDecl> variables) {
        this.variables = variables;
    }

    public ClassDecl getClassOwner() {
        return classOwner;
    }

    public void setClassOwner(ClassDecl classOwner) {
        this.classOwner = classOwner;
    }

    public AbstractDeclaration lookupVariables(String name){
        name = name.trim();
        for(int i = 0; i < getVariables().size(); ++i){
            if(getVariables().get(i).getName().equals(name)){
                return getVariables().get(i);
            }
        }
        for(int i = 0; i < getArguments().size(); ++i){
            if(getArguments().get(i).getName().equals(name)){
                return getArguments().get(i);
            }
        }
        return getClassOwner().lookupVariables(name);
    }

}
