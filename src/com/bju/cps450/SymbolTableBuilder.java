package com.bju.cps450;

import com.bju.cps450.analysis.DepthFirstAdapter;
import com.bju.cps450.application.Application;
import com.bju.cps450.application.Type;
import com.bju.cps450.declarations.ClassDecl;
import com.bju.cps450.declarations.MethodDecl;
import com.bju.cps450.node.*;

import java.util.HashMap;
import java.util.LinkedList;

import static com.bju.cps450.Globals.symbolTable;

/**
 * Created by daniel on 3/6/16.
 */
public class SymbolTableBuilder extends DepthFirstAdapter {

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



    public SymbolTableBuilder(SuperFile file){
        this.file = file;
    }

    private Type getArrayDimType(Type t, LinkedList<PExpression> expressions){
        for(int i = 0; i < expressions.size(); ++i){
            t = new Type(t.getName() + "[]");
        }
        return t;
    }


    // Begin  statements

    @Override
    public void inStart(Start node) {
        try {
            symbolTable.beginScope();
        } catch (Exception e) {
            reportError(e.getMessage());
        }
    }

    @Override
    public void inAClassDecl(AClassDecl node) {
        lastToken = node.getStart();
        try{
            currentClass = symbolTable.addClassDecl(node.getStart().getText());
        } catch( Exception e){
            reportError(e.getMessage());
        }

        try {
            symbolTable.beginScope();
        } catch (Exception e) {
            reportError(e.getMessage());

        }
    }

    @Override
    public void outAClassDecl(AClassDecl node) {
        try {
            symbolTable.endScope();
        } catch (Exception e) {
            reportError(e.getMessage());
        }
        currentClass = null;
    }

    @Override
    public void inAMethodDecl(AMethodDecl node) {
        lastToken = node.getName();
        try{
            currentMethod = symbolTable.addMethodDecl(node.getName().getText());
        }catch(Exception e){
            reportError(e.getMessage());
        }

        try{
            symbolTable.beginScope();
        } catch(Exception e){
            reportError(e.getMessage());
        }
    }



    @Override
    public void outAMethodDecl(AMethodDecl node) {
        try {
            symbolTable.endScope();
        } catch (Exception e) {
            reportError(e.getMessage());
        }
        currentMethod = null;
    }


    @Override
    public void inAVarDecl(AVarDecl node) {
        lastToken = node.getIdentifier();

    }

    @Override
    public void outAVarDecl(AVarDecl node) {
        Type t = Application.getNodeProperties(node.getType()).getType();
        try{
            symbolTable.addVarDecl(node.getIdentifier().getText());

        } catch (Exception e){
            reportError(e.getMessage());
        }
    }


    @Override
    public void outAIntType(AIntType node) {
        Type t = Type.oodleInt;
        t = getArrayDimType(t, node.getExpression());
        Application.getNodeProperties(node).setType(t);
    }



    @Override
    public void outAStringType(AStringType node) {
        Type t = Type.oodleString;
        t = getArrayDimType(t, node.getExpression());
        Application.getNodeProperties(node).setType(t);
    }

    @Override
    public void outABooleanType(ABooleanType node) {
        Type t = Type.oodleBoolean;
        t = getArrayDimType(t, node.getExpression());
        Application.getNodeProperties(node).setType(t);
    }



    @Override
    public void outACustomType(ACustomType node) {
        Type t = new Type(node.getIdentifier().getText());
        t = getArrayDimType(t, node.getExpression());
        Application.getNodeProperties(node).setType(t);

    }

    @Override
    public void inAAssignmentStatement(AAssignmentStatement node) {


    }

    @Override
    public void outAAssignmentStatement(AAssignmentStatement node) {


    }





}