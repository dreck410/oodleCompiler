package com.bju.cps450;

/**
 * Created by daniel on 1/16/16.
 */
import com.bju.cps450.lexer.Lexer;
import com.bju.cps450.lexer.LexerException;
import com.bju.cps450.node.*;

import java.io.Console;
import java.io.IOException;
import java.io.PushbackReader;

public class OodleLexer extends Lexer {
    private String FileName;

    public OodleLexer(@SuppressWarnings("hiding") PushbackReader in, String fileName) {
        super(in);
        this.FileName = fileName;

    }

    protected void filter() throws LexerException, IOException {
        String tokenText = this.getTokenType();
//        System.out.println(this.FileName + ":" + this.getTokenLineInfo() + ":" + tokenText);

        if (tokenText != "ignore"){
            System.out.println(this.FileName + ":" + this.getTokenLineInfo() + ":" + tokenText);
        }
    }

    protected String getTokenType(){
        String output = "";
        output = this.token instanceof TIgnore ? "ignore" : output;
        output = this.token instanceof TIdentifier ? "Identifier:" + this.token.getText() : output;
        output = this.token instanceof TNewLine ? "CR" : output;
        output = this.token instanceof TDot ? "'.'" : output;
        output = this.token instanceof TColon ? "':'" : output;
        output = this.token instanceof TOparen ? "'('" : output;
        output = this.token instanceof TCparen ? "')'" : output;
        output = this.token instanceof TIllegal ? "Unrecognized Char:" + this.token.getText() : output;
        output = this.token instanceof TStringLit ? "String Lit:" + this.token.getText() : output;
        if (output != ""){
            return output;
        }

        output = "Keyword:" + this.token.getText();


        return output;
    }

    protected String getTokenLineInfo(){
        Integer line = this.token.getLine();
        Integer column = this.token.getPos();
        return line + "," + column;
    }
}
