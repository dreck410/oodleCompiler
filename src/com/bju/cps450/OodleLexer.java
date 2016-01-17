package com.bju.cps450;

/**
 * Created by daniel on 1/16/16.
 */
import com.bju.cps450.lexer.Lexer;
import com.bju.cps450.lexer.LexerException;
import com.bju.cps450.node.*;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.Console;
import java.io.IOException;
import java.io.PushbackReader;

public class OodleLexer extends Lexer {
    private String FileName;
    private Boolean displayTokens;
    private SuperFile superFile;

    public OodleLexer(@SuppressWarnings("hiding") SuperFile in, Boolean printOut) {
        super(in.getReader());
        this.superFile = in;
        this.displayTokens = printOut;

    }

    protected void filter() throws LexerException, IOException {
        String tokenText = this.getTokenText();

        if ((tokenText != "ignore" && this.displayTokens)
                || this.token instanceof TIllegal
                || this.token instanceof TUnterminatedString
                || this.token instanceof TIllegalString){

            System.out.println(this.getTokenLineInfo() + ":" + tokenText);
        }
    }

    /**
     *
     *
     * @return String of a token
     */
    protected String getTokenText(){
        String output = "";

        //ignore
        output = this.token instanceof TIgnore ? "ignore" : output;
        output = this.token instanceof EOF ? "ignore" : output;

        //identifier
        output = this.token instanceof TIdentifier ? "identifier:" + this.token.getText() : output;

        //special
        output = this.token instanceof TNewLine ? "cr" : output;
        output = this.token instanceof TStringLit ? "string lit: " + this.token.getText() : output;

        //Operators
        output = this.token instanceof TStringConcat ? "Operator: " + this.token.getText() : output;
        output = this.token instanceof TPlus ? "Operator: " + this.token.getText() : output;
        output = this.token instanceof TMinus ? "Operator: " + this.token.getText() : output;
        output = this.token instanceof TDivide ? "Operator: " + this.token.getText() : output;
        output = this.token instanceof TMultiply ? "Operator: " + this.token.getText() : output;
        output = this.token instanceof TGt ? "Operator: " + this.token.getText() : output;
        output = this.token instanceof TGteq ? "Operator: " + this.token.getText() : output;
        output = this.token instanceof TEq ? "Operator: " + this.token.getText() : output;

        //Symbols
        output = this.token instanceof TDot ? "'.'" : output;
        output = this.token instanceof TColon ? "':'" : output;
        output = this.token instanceof TOparen ? "'('" : output;
        output = this.token instanceof TCparen ? "')'" : output;
        output = this.token instanceof TObrace ? "'['" : output;
        output = this.token instanceof TCbrace ? "']'" : output;
        output = this.token instanceof TSemicolon ? "';'" : output;
        output = this.token instanceof TComma ? "','" : output;
        output = this.token instanceof TAssignment ? "':='" : output;

        //Errors
        output = this.token instanceof TIllegal ? "unrecognized char:" + this.token.getText() : output;
        output = this.token instanceof TIllegalString ? "illegal String:" + this.token.getText() : output;
        output = this.token instanceof TUnterminatedString ? "unterminated string: " + this.token.getText() : output;


        if (output != ""){
            return output;
        }

        output = "Keyword:" + this.token.getText();


        return output;
    }

    protected String getTokenLineInfo(){
        Integer line = this.token.getLine();
        SubFile file = this.superFile.getFileByLine(line);

        Integer column = this.token.getPos();
        return file.Name + ":" + (line - file.StartLine+1) + "," + column;
    }
}
