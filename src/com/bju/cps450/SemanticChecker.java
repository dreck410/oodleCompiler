package com.bju.cps450;

import com.bju.cps450.analysis.DepthFirstAdapter;
import com.bju.cps450.node.AAssignmentStatement;
import com.bju.cps450.node.AVarDecl;
import com.bju.cps450.node.*;

import java.lang.instrument.ClassDefinition;

/**
 * Created by daniel on 3/5/16.
 */
public class SemanticChecker extends DepthFirstAdapter {

    private Token lastToken;
    private SuperFile file;

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
        }
    }

    public int NumberOfErrors = 0;


    public SemanticChecker(SuperFile file){
        this.file = file;
    }
    // Begin IN statements
    @Override
    public void inAAssignmentStatement(AAssignmentStatement node) {
        System.out.println("in an assignment");
    }

    @Override
    public void inStart(Start node) {
        try {
            Globals.symbolTable.beginScope();
        } catch (Exception e) {
            reportError(e.getMessage());
        }
    }

    @Override
    public void inAVarDecl(AVarDecl node) {
        System.out.println("in a var decl");

    }


    // Begin out statements

    @Override
    public void outAAssignmentStatement(AAssignmentStatement node) {
        System.out.println("out an assignment");
    }

    @Override
    public void outAVarDecl(AVarDecl node) {
        System.out.println("out a var decl");
    }

}
