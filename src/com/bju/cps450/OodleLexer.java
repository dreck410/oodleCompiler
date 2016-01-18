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
import java.util.Objects;

public class OodleLexer extends Lexer {
    private String FileName;
    private SubFile CurrentFile = null;
    private Integer CurrentLine;
    private Boolean displayTokens;
    private SuperFile superFile;

    public OodleLexer(@SuppressWarnings("hiding") SuperFile in, Boolean printOut) {
        super(in.getReader());
        this.superFile = in;
        this.displayTokens = printOut;

    }

    protected void filter() throws LexerException, IOException {
        String tokenText = this.getTokenText();

        if (tokenText != "ignored token"
                && this.displayTokens
                || this.token instanceof TIllegal
                || this.token instanceof TUnterminatedString
                || this.token instanceof TIllegalString){

            System.out.println(this.getTokenLineInfo() + ": " + tokenText);
        }
    }

    /**
     * checks the type of a token
     * maybe can be replaced with a switch?
     * @return String of a token
     */
    protected String getTokenText(){
        String output = "";

        //ignore
        output = this.token instanceof TSpace ? "ignored token" : output;
        output = this.token instanceof TComment ? "ignored token" : output;
        output = this.token instanceof TTab ? "ignored token" : output;
        output = this.token instanceof TLineContinue ? "ignored token" : output;
        output = this.token instanceof EOF ? "ignored token" : output;

        //identifier
        output = this.token instanceof TIdentifier ? "identifier: " + this.token.getText() : output;

        //special
        output = this.token instanceof TNewLine ? "cr" : output;
        output = this.token instanceof TStringLit ? "string lit: " + this.token.getText() : output;
        output = this.token instanceof TInteger ? "integer: " + this.token.getText() : output;

        //Operators
        output = this.token instanceof TStringConcat ? "operator: " + this.token.getText() : output;
        output = this.token instanceof TPlus ? "operator: " + this.token.getText() : output;
        output = this.token instanceof TMinus ? "operator: " + this.token.getText() : output;
        output = this.token instanceof TDivide ? "operator: " + this.token.getText() : output;
        output = this.token instanceof TMultiply ? "operator: " + this.token.getText() : output;
        output = this.token instanceof TGt ? "operator: " + this.token.getText() : output;
        output = this.token instanceof TGteq ? "operator: " + this.token.getText() : output;
        output = this.token instanceof TEq ? "operator: " + this.token.getText() : output;

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
        output = this.token instanceof TIllegal ? "unrecognized char: " + this.token.getText() : output;
        output = this.token instanceof TIllegalString ? "illegal String: " + this.token.getText() : output;
        output = this.token instanceof TUnterminatedString ? "unterminated string: " + this.token.getText() : output;


        if (!Objects.equals(output, "")){
            return output;
        }

        output = "keyword: " + this.token.getText();


        return output;
    }


    protected String getTokenLineInfo(){
        if (CurrentFile == null || !CurrentFile.InRange(this.token.getLine()) ){
            CurrentFile = this.superFile.getFileByLine(this.token.getLine());
            // got a new file print out a separator line
            System.out.println(CurrentFile.Name);
        }

        Integer column = this.token.getPos();
        return CurrentFile.Name + ":" + CurrentFile.getOffset(this.token.getLine()) + "," + column;
    }
}
