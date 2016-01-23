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

    public OodleCompiler(SuperFile in, boolean printOut){
        superFile = in;
        oodleLexer = new OodleLexer(superFile, printOut);
        oodleParser = new OodleParser(new OodleLexer(superFile,false), superFile);

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
        if(Lex() > 0){
            System.out.println(oodleLexer.NumberOfErrors + " lexical error(s) found");
            System.exit(1);
        }
        if(Parse() > 0){
            System.out.println(oodleParser.NumberOfErrors + " parse error(s) found");
            System.exit(1);
        }



    }


}
