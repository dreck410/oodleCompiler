package com.bju.cps450;

import com.bju.cps450.analysis.DepthFirstAdapter;
import com.bju.cps450.application.Application;
import com.bju.cps450.application.SymbolTable;
import com.bju.cps450.application.Type;
import com.bju.cps450.declarations.AbstractDeclaration;
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


    private Type getType(Node node){
        AbstractDeclaration decl = Globals.symbolTable.getCurrentMethodDecl().lookupVariables(node.toString());
        if(decl != null){
            return decl.getType();
        }
        decl = Globals.symbolTable.getCurrentClassDecl().lookupVariables(node.toString());
        if(decl != null){
            return decl.getType();
        }
        return null;
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
        Type lhsType = getType(node.getIdentifier());

        if (Objects.equals(lhsType, null)){
            // still no?
            reportError("Variable '" + node.getIdentifier().getText() + "' does not exist in current scope");
        }

        Type rhsType = getType(node.getValue());
        if(rhsType == null){
            rhsType =Application.getNodeProperties(node.getValue()).getType();
        }

        if(!Objects.equals(lhsType, rhsType)){
            reportError("Tried to assign object of type '"+ rhsType.getType() + "' to object of type '"+ lhsType.getType() +"'");
        }

    }


    @Override
    public void outAMinusExpression(AMinusExpression node) {
        Type lhsType = getType(node.getLhs());
        if (lhsType == null){
            // still null probably a literal
            lhsType = Application.getNodeProperties(node.getLhs()).getType();

        }
        Type rhsType = getType(node.getRhs());
        if (rhsType == null){
            // still null probably a literal
            rhsType = Application.getNodeProperties(node.getRhs()).getType();

        }
        if(!Objects.equals(lhsType, Type.oodleInt)
                || !Objects.equals(rhsType, Type.oodleInt)){
            reportError("Non integer value found in minus expression");
        }
        Application.getNodeProperties(node).setType(Type.oodleInt);
    }
}
