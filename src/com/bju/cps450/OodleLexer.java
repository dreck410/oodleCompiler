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
    public boolean hasError = false;

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
        if(isIgnore(this.token)){
            output = "ignored token";
            return output;

        }

        //Operators
        if(isOperator(this.token)){
            output = "operator: " + this.token.getText();
            return output;

        }


        //Symbols
        if (isSymbol(this.token)) {
            output = "'" + this.token.getText() + "'";
            return output;
        }

        //Errors
        if(isError(this.token)){
            output = this.token instanceof TIllegal ? "unrecognized char: " + this.token.getText() : output;
            output = this.token instanceof TIllegalString ? "illegal String: " + this.token.getText() : output;
            output = this.token instanceof TUnterminatedString ? "unterminated string: " + this.token.getText() : output;
            this.hasError = true;
            return output;
        }

        //identifier
        output = this.token instanceof TIdentifier ? "identifier: " + this.token.getText() : output;

        //special
        output = this.token instanceof TNewLine ? "cr" : output;
        output = this.token instanceof TStringLit ? "string lit: " + this.token.getText() : output;
        output = this.token instanceof TInteger ? "integer: " + this.token.getText() : output;





        if (!Objects.equals(output, "")){
            return output;
        }

        output = "keyword: " + this.token.getText();


        return output;
    }

    private boolean isError(Token input) {
        return (this.token instanceof TIllegal
                || this.token instanceof TIllegalString
                || this.token instanceof TUnterminatedString
        );
    }

    private boolean isSymbol(Token input) {
        return( input instanceof TDot
                || input instanceof TColon
                || input instanceof TOparen
                || input instanceof TCparen
                || input instanceof TObrace
                || input instanceof TCbrace
                || input instanceof TSemicolon
                || input instanceof TComma
                || input instanceof TAssignment
        );
    }

    private boolean isOperator(Token input) {
        return ( input instanceof TStringConcat
                || input instanceof TPlus
                || input instanceof TMinus
                || input instanceof TDivide
                || input instanceof TMultiply
                || input instanceof TGt
                || input instanceof TGteq
                || input instanceof TEq);
    }

    private Boolean isIgnore(Token input) {
        return(input instanceof TSpace
                || input instanceof TComment
                || input instanceof TTab
                || input instanceof TLineContinue
                || input instanceof EOF );

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
