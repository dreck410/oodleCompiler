package com.bju.cps450;

import com.bju.cps450.lexer.LexerException;
import com.bju.cps450.node.EOF;
import com.bju.cps450.node.Token;

import java.io.IOException;

/**
 * Created by daniel on 1/23/16.
 */
public class OodleCompiler {
    OodleLexer oodleLexer;
    OodleParser oodleParser;
    SuperFile superFile;

    public OodleCompiler(SuperFile in, Integer printOut){
        superFile = in;
        oodleLexer = new OodleLexer(superFile, printOut);
        oodleParser = new OodleParser(new OodleLexer(superFile,0), superFile);

    }

    public Integer Lex(){
        try {
            Token token = oodleLexer.next();
            while(!(token.getClass().equals(EOF.class)
                    || token == null)){
                token = oodleLexer.next();
            }
        } catch (LexerException e) {
            return oodleLexer.NumberOfErrors + 1;
        } catch (IOException e) {
            return oodleLexer.NumberOfErrors + 1;
        }
        return oodleLexer.NumberOfErrors;

    }

    public Integer Parse(){
        try {
            oodleParser.parse();
        } catch (LexerException e) {
            return oodleParser.NumberOfErrors + 1;
        } catch (IOException e) {
            return oodleParser.NumberOfErrors + 1;

        }
        return oodleParser.NumberOfErrors;

    }

    public void Compile(){
        System.out.println("\nLexing:");
        Lex();
        System.out.println("\nParsing:");
        Parse();
        if(oodleLexer.NumberOfErrors > 0 || oodleParser.NumberOfErrors > 0){
            System.out.println((new StringBuilder()
                    .append("\n")
                    .append(oodleLexer.NumberOfErrors + oodleParser.NumberOfErrors)
                    .toString()) + " error(s) found");
            System.exit(1);
        }


    }


}
