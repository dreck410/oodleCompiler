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
    private Integer displayTokens;
    private SuperFile superFile;
    public Integer NumberOfErrors = 0;
    public boolean hasError = false;

    /**
     * printOut is
     * 0 quiet
     * 1 print out errors
     * or 2 verbose
     * @param in
     * @param printOut
     */
    public OodleLexer(@SuppressWarnings("hiding") SuperFile in, Integer printOut) {
        super(in.getReader());
        this.superFile = in;
        this.displayTokens = printOut;


    }

    /**
     * prints the info about a token
     * @throws LexerException
     * @throws IOException
     */
    protected void filter() throws LexerException, IOException {
        String tokenText = this.getTokenText();

        if (!Objects.equals(tokenText, "ignored token")
                && this.displayTokens > 1
                    || ( this.displayTokens !=0
                        && (this.token instanceof TIllegal
                        || this.token instanceof TUnterminatedString
                        || this.token instanceof TIllegalString))){

            System.out.println(this.getTokenLineInfo() + ": " + tokenText);
        }
    }

    /**
     * checks the type of a token
     * maybe can be replaced with a switch?
     * @return String of a token
     */
    protected String getTokenText(){
        if (this.token instanceof TNewLine){
            return "cr";
        }

        //ignore
        if(isIgnore(this.token)){
            return "ignored token";
        }

        //Operators
        if(isOperator(this.token)){
            return "operator: " + this.token.getText();

        }

        //Symbols
        if (isSymbol(this.token)) {
            return "'" + this.token.getText() + "'";
        }

        //Errors
        if (this.token instanceof TIllegal){
            this.NumberOfErrors++;
            this.hasError= true;
            return "unrecognized char: " + this.token.getText();
        }

        if (this.token instanceof TIllegalString){
            this.NumberOfErrors++;

            this.hasError = true;
            return "illegal String: " + this.token.getText();
        }

        if (this.token instanceof TUnterminatedString){
            this.NumberOfErrors++;

            this.hasError = true;
            return "unterminated string: " + this.token.getText();
        }

        //identifier
        if (this.token instanceof TIdentifier){
            return "identifier: " + this.token.getText();
        }

        //special
        if (this.token instanceof TStringLit){
            return "string lit: " + this.token.getText();
        }
        if (this.token instanceof TInteger){
            return "integer: " + this.token.getText();
        }

        return "keyword: " + this.token.getText();
    }

    /**
     * Checks to see if a token is an error
     * @param input
     * @return
     */
    public boolean isError(Token input) {
        return (input instanceof TIllegal
                || input instanceof TIllegalString
                || input instanceof TUnterminatedString
        );
    }

    /**
     * Returns true if the token is a symbol
     * false if not
     * @param input
     * @return
     */
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

    /**
     * get's the token's original file and location
     * from the subfiles.
     * will change currentFile if we have moved out of the file
     *
     * @return
     */
    protected String getTokenLineInfo(){
        if (CurrentFile == null || !CurrentFile.InRange(this.token.getLine()) ){
            CurrentFile = this.superFile.getFileByLine(this.token.getLine());
            // got a new file print out a separator line
            System.out.println("\n"+CurrentFile.Name);
        }

        Integer column = this.token.getPos();
        return CurrentFile.Name + ":" + CurrentFile.getOffset(this.token.getLine()) + "," + column;
    }
}
