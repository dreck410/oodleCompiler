package com.bju.cps450;

import com.bju.cps450.analysis.DepthFirstAdapter;
import com.bju.cps450.application.Application;
import com.bju.cps450.application.SymbolTable;
import com.bju.cps450.application.Type;
import com.bju.cps450.declarations.*;
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


    private Type getType(Node node){
        AbstractDeclaration decl = null;
        if(currentMethod != null) {
            decl = currentMethod.lookupVariables(node.toString());
            if (decl != null) {
                return decl.getType();
            }
        }
        decl = currentClass.lookupVariables(node.toString());
        if(decl != null){
            return decl.getType();
        }
        return Type.Error;
    }

    private Type getNodeType(Node node){
        Type t = getType(node);
        if (t == Type.Error){
            t = Application.getNodeProperties(node).getType();
        }
        return t;
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
        lastToken = node.getName();
        currentMethod = (MethodDecl) currentClass.lookupMethods(node.getName());
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
        Type lhsType = getType(node.getIdentifier());

        if (Objects.equals(lhsType, Type.oodleNull)){
            // still no?
            reportError("Variable '" + node.getIdentifier().getText() + "' does not exist in current scope");

        }else {

            Type rhsType = getNodeType(node.getValue());

            if (!Objects.equals(lhsType, rhsType)) {
                reportError("Tried to assign object of type '" + rhsType + "' to object of type '" + lhsType + "'");
            }
        }
    }



    @Override
    public void outAMethodCallExpression(AMethodCallExpression node) {
        Type t = getNodeType(node);
        if(Objects.equals(currentClass.lookupMethods(node.getMethod()), null)){
            reportError("Method '"+node.getMethod().getText()+"' does not exist in current context");
        }

    }

    @Override
    public void outACallStatement(ACallStatement node) {
        Type t = getNodeType(node);
        MethodDecl m = (MethodDecl) currentClass.lookupMethods(node.getMethod());
        if(Objects.equals(m, null)){
            reportError("Method '"+node.getMethod().getText()+"' does not exist in current context");
        }
        else{
            for (int i = 0; i < m.getArguments().size(); ++i) {
                Type argType = getNodeType(node.getArgs().get(i));
                Type paramType = m.getArguments().get(i).getType();
                if (!Objects.equals(paramType, argType)) {
                    reportError("Method '" + m.getName() + "' does not take a '" + argType + "'.");

                }
            }
        }


    }

    @Override
    public void outAIfStatement(AIfStatement node) {
        super.outAIfStatement(node);
    }

    @Override
    public void outALoopStatement(ALoopStatement node) {
        super.outALoopStatement(node);
    }

    @Override
    public void outAGtExpression(AGtExpression node) {

        Type lhsType = getNodeType(node.getLhs());


        Type rhsType = getNodeType(node.getRhs());
        if(!Objects.equals(lhsType, rhsType)){
            reportError("Comparing objects of different type " + lhsType +", and " + rhsType);
        }

        Application.getNodeProperties(node).setType(Type.oodleBoolean);

    }

    @Override
    public void outAGteqExpression(AGteqExpression node) {
        super.outAGteqExpression(node);
    }

    @Override
    public void outAPlusExpression(APlusExpression node) {
        Type lhsType = getNodeType(node.getLhs());

        Type rhsType = getNodeType(node.getRhs());

        if(!Objects.equals(lhsType, Type.oodleInt)
                || !Objects.equals(rhsType, Type.oodleInt)){
            reportError("Problem in plus expression added '" + lhsType + "' with '" + rhsType + "'.");
            Application.getNodeProperties(node).setType(Type.Error);

        }else {
            Application.getNodeProperties(node).setType(Type.oodleInt);
        }
    }

    @Override
    public void outAMinusExpression(AMinusExpression node) {
        Type lhsType = getNodeType(node.getLhs());

        Type rhsType = getNodeType(node.getRhs());

        if(!Objects.equals(lhsType, Type.oodleInt)
                || !Objects.equals(rhsType, Type.oodleInt)){
            reportError("Non integer value found in minus expression");
            Application.getNodeProperties(node).setType(Type.Error);

        }else {
            Application.getNodeProperties(node).setType(Type.oodleInt);
        }
    }

    @Override
    public void outAMultiplyExpression(AMultiplyExpression node) {
        Type lhsType = getNodeType(node.getLhs());

        Type rhsType = getNodeType(node.getRhs());

        if(!Objects.equals(lhsType, Type.oodleInt)
                || !Objects.equals(rhsType, Type.oodleInt)){
            reportError("Non integer value found in mult expression");
            Application.getNodeProperties(node).setType(Type.Error);

        }else {
            Application.getNodeProperties(node).setType(Type.oodleInt);
        }
    }

    @Override
    public void outADivideExpression(ADivideExpression node) {
        Type lhsType = getNodeType(node.getLhs());

        Type rhsType = getNodeType(node.getRhs());

        if(!Objects.equals(lhsType, Type.oodleInt)
                || !Objects.equals(rhsType, Type.oodleInt)){
            reportError("Non integer value found in division expression");
            Application.getNodeProperties(node).setType(Type.Error);

        }else {
            Application.getNodeProperties(node).setType(Type.oodleInt);
        }
    }

    @Override
    public void outAConcatExpression(AConcatExpression node) {
        super.outAConcatExpression(node);
    }

    @Override
    public void outANotExpression(ANotExpression node) {
        Type t = getNodeType(node.getExpression());
        if (t != Type.oodleBoolean){
            reportError("'" + node.toString() + "' is not a boolean");
            Application.getNodeProperties(node).setType(Type.Error);

        }else{
            Application.getNodeProperties(node).setType(Type.oodleBoolean);

        }
    }

    @Override
    public void outAAndExpression(AAndExpression node) {
        Type lhsType = getNodeType(node.getLhs());
        Type rhsType = getNodeType(node.getRhs());

        if(lhsType != Type.oodleBoolean && rhsType != Type.oodleBoolean){
            reportError("Non bool type detected in AND statement");
            Application.getNodeProperties(node).setType(Type.Error);
        }else{
            Application.getNodeProperties(node).setType(Type.oodleBoolean);
        }
    }

    @Override
    public void outANegExpression(ANegExpression node) {
        super.outANegExpression(node);
    }

    @Override
    public void outAPosExpression(APosExpression node) {
        super.outAPosExpression(node);
    }

    @Override
    public void outANewExpression(ANewExpression node) {
        super.outANewExpression(node);
    }

    @Override
    public void outAIntExpression(AIntExpression node) {
        Application.getNodeProperties(node).setType(Type.oodleInt);
    }

    @Override
    public void outAStringLitExpression(AStringLitExpression node) {

        Application.getNodeProperties(node).setType(Type.oodleString);
        reportError("String is not supported");
    }

    @Override
    public void outATrueExpression(ATrueExpression node) {
        Application.getNodeProperties(node).setType(Type.oodleBoolean);

    }

    @Override
    public void outAFalseExpression(AFalseExpression node) {
        Application.getNodeProperties(node).setType(Type.oodleBoolean);
    }


}
