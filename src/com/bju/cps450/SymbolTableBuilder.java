package com.bju.cps450;

import com.bju.cps450.analysis.DepthFirstAdapter;
import com.bju.cps450.application.Application;
import com.bju.cps450.application.Type;
import com.bju.cps450.declarations.ClassDecl;
import com.bju.cps450.declarations.MethodDecl;
import com.bju.cps450.node.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

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
            t = new Type(t.getType() + "[]");
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
        if(!Objects.equals(node.getStart().getText(), node.getEnd().getText())){
            reportError("Class name '" + node.getStart().getText() + "' and end tag do not match.");
        }
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
            currentMethod = symbolTable.addMethodDecl(node.getName().getText(), node.getType());
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
        if(!Objects.equals(node.getName().getText(), node.getEnd().getText())){
            reportError("Method name '"+node.getName().getText()+"' and end tag '" + node.getEnd().getText() + "' do not match");
        }
        if(!Objects.equals(node.getType(), null)){
            Type t = new Type(node.getType().toString().trim());
            Application.getNodeProperties(node).setType(t);
            currentMethod.setType(t);
            currentMethod.lookupVariables(currentMethod.getName()).setType(t);
        }
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
        if(node.getExpression() == null && node.getType() == null){
            reportError("No type specified");
        }else {
            if (node.getExpression() != null && currentMethod == null) {
                reportError("Unsupported Feature");
                t = Application.getNodeProperties(node.getExpression()).getType();

            }
            try {
                symbolTable.addVarDecl(node.getIdentifier().getText(), t);

            } catch (Exception e) {
                reportError(e.getMessage());
            }
        }
    }

    @Override
    public void outAArg(AArg node) {
        Type t = Application.getNodeProperties(node.getType()).getType();
        try{
            symbolTable.addArgumentDecl(node.getIdentifier().getText(), t);

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
    public void outAClassInherits(AClassInherits node) {
        Type t = new Type(node.getIdentifier().getText());
        Application.getNodeProperties(node).setType(t);

        reportError("Class inheritance is not supported");
    }

    @Override
    public void outAIntExpression(AIntExpression node) {
        Type t = Type.oodleInt;

        Application.getNodeProperties(node).setType(t);
    }

    @Override
    public void outAStringLitExpression(AStringLitExpression node) {
        Type t = Type.oodleString;
        Application.getNodeProperties(node).setType(t);
    }

    @Override
    public void outATrueExpression(ATrueExpression node) {
        Type t = Type.oodleBoolean;
        Application.getNodeProperties(node).setType(t);    }

    @Override
    public void outAFalseExpression(AFalseExpression node) {
        Type t = Type.oodleBoolean;
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
