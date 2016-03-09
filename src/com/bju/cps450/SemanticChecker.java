package com.bju.cps450;

import com.bju.cps450.analysis.DepthFirstAdapter;
import com.bju.cps450.application.Application;
import com.bju.cps450.application.SymbolTable;
import com.bju.cps450.application.Type;
import com.bju.cps450.declarations.ClassDecl;
import com.bju.cps450.declarations.MethodDecl;
import com.bju.cps450.declarations.VarDecl;
import com.bju.cps450.node.AAssignmentStatement;
import com.bju.cps450.node.AVarDecl;
import com.bju.cps450.node.*;
import sun.org.mozilla.javascript.tools.shell.Global;

import java.lang.instrument.ClassDefinition;
import java.util.HashMap;
import java.util.Objects;

import static com.bju.cps450.Globals.*;

/**
 * Created by daniel on 3/5/16.
 */
public class SemanticChecker extends DepthFirstAdapter {

    private Token lastToken;
    private SuperFile file;

    private ClassDecl currentClass;
    private MethodDecl currentMethod;

    public int NumberOfErrors = 0;

    private HashMap<Node, HashMap<String, Object>> attributeGrammarMap
            = new HashMap<Node, HashMap<String, Object>>();


    private void reportError(String error){
        NumberOfErrors += 1;
        if(lastToken != null){
            SubFile subFile = this.file.getFileByLine(lastToken.getLine());
            String output = new StringBuilder()
                    .append(subFile.Name)
                    .append(": at (")
                    .append(this.file.getFileLineByToken(lastToken))
                    .append(",")
                    .append(lastToken.getPos())
                    .append(") ")
                    .append(error)
                    .toString();
            System.out.println(output);
        }else{
            System.out.println(error);
        }

    }



    public SemanticChecker(SuperFile file){
        this.file = file;
    }


    // Begin IN statements


    @Override
    public void inAClassDecl(AClassDecl node) {
        lastToken = node.getStart();
        currentClass = Globals.symbolTable.lookup(node.getStart().getText(), ClassDecl.class);
    }


    @Override
    public void outAClassDecl(AClassDecl node) {
        currentClass = null;
    }


    @Override
    public void inAMethodDecl(AMethodDecl node) {
        currentMethod = Globals.symbolTable.lookup(node.getName().getText(), MethodDecl.class);
    }

    @Override
    public void outAMethodDecl(AMethodDecl node) {
        currentMethod = null;
    }

    @Override
    public void inAVarDecl(AVarDecl node) {
        lastToken = node.getIdentifier();

    }


    @Override
    public void outAVarDecl(AVarDecl node) {

    }

    @Override
    public void inAAssignmentStatement(AAssignmentStatement node) {
        lastToken = node.getIdentifier();

    }


    @Override
    public void outAAssignmentStatement(AAssignmentStatement node) {

        if(node.getIndex().size() > 0 && !Objects.equals(node.getIndex().getClass(), Type.oodleInt.getClass())){
            reportError("Index must be of type Int");
        }
        VarDecl var = Globals.symbolTable.lookup(node.getIdentifier().getText(), VarDecl.class);
        if(!Objects.equals(node.getClass(), node.getValue().getClass())){
            reportError("Tried to assign object of class '"+ node.getValue().getClass() + "' to object of type '"+ node.getClass()+"'");
        }

    }




}
