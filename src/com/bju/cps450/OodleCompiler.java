package com.bju.cps450;

import com.bju.cps450.application.SymbolTable;
import com.bju.cps450.lexer.LexerException;
import com.bju.cps450.node.EOF;
import com.bju.cps450.node.Start;
import com.bju.cps450.node.Token;

import java.io.IOException;

/**
 * Created by daniel on 1/23/16.
 */
public class OodleCompiler {
    OodleLexer oodleLexer;
    OodleParser oodleParser;
    SemanticChecker oodleSemanticChecker;
    SymbolTableBuilder symbolTableBuilder;
    SuperFile superFile;

    public Boolean hasErrors = false;

    public OodleCompiler(SuperFile in, Integer printOut){
        superFile = in;
        oodleLexer = new OodleLexer(superFile, printOut);
        oodleParser = new OodleParser(new OodleLexer(superFile,0), superFile);
        oodleSemanticChecker = new SemanticChecker(superFile);
        symbolTableBuilder = new SymbolTableBuilder(superFile);

    }

    /**
     * Does a lexical analysis of the file till EOF or the token is null
     * @return the number of errors
     */
    public Integer Lex(){
        try {
            Token token = oodleLexer.next();
            while(!(token.getClass().equals(EOF.class)
                    || token == null)){
                token = oodleLexer.next();
            }
        } catch (LexerException e) {
            hasErrors = true;
            return oodleLexer.NumberOfErrors + 1;
        } catch (IOException e) {
            hasErrors = true;

            return oodleLexer.NumberOfErrors + 1;
        }
        if(oodleLexer.NumberOfErrors > 0){
            hasErrors = true;
        }
        return oodleLexer.NumberOfErrors;

    }

    /**
     * does a parse of the file to ensure it is syntactically valid
     * @return the number of errors found
     */
    public Start Parse(){
        try {
            Start n = oodleParser.parse();
            if (oodleParser.NumberOfErrors > 0){
                hasErrors = true;
            }
            return n;
        } catch (LexerException e) {
            return null;
        } catch (IOException e) {
            return null;

        }


    }

    public void Compile(){
        System.out.println("\nLexing:");
        // does a lexical analysis of the file.
        Lex();
        System.out.println("\nParsing:");
        // parses the file

        Start node = Parse();

        if(!hasErrors){
            System.out.println("\nBuilding the Table");
            node.apply(symbolTableBuilder);
            if (symbolTableBuilder.NumberOfErrors > 0){
                hasErrors = true;
            }
        }

        if(!hasErrors) {
            System.out.println("\nChecking Semantics");
            node.apply(oodleSemanticChecker);
        }


        System.out.println((new StringBuilder()
                .append("\n")
                .append(oodleLexer.NumberOfErrors + oodleParser.NumberOfErrors + oodleSemanticChecker.NumberOfErrors + oodleSemanticChecker.NumberOfErrors)
                .toString()) + " error(s) found");
        if(node == null
                || oodleLexer.NumberOfErrors > 0
                || oodleParser.NumberOfErrors > 0
                || symbolTableBuilder.NumberOfErrors > 0
                || oodleSemanticChecker.NumberOfErrors > 0){
            System.exit(1);
        }




    }


}
