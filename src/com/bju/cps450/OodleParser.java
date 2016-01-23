package com.bju.cps450;

import com.bju.cps450.lexer.Lexer;
import com.bju.cps450.lexer.LexerException;
import com.bju.cps450.node.EOF;
import com.bju.cps450.node.Start;
import com.bju.cps450.parser.Parser;
import com.bju.cps450.parser.ParserException;

import java.io.IOException;

/**
 * Created by daniel on 1/22/16.
 */
public class OodleParser extends Parser {
    public Integer NumberOfErrors = 0;
    public SuperFile superFile;
    public  OodleLexer oodleLexer;
    public OodleParser(@SuppressWarnings("hiding") OodleLexer lexer, SuperFile superFileIn) {
        super(lexer);
        oodleLexer = lexer;
        this.superFile = superFileIn;
    }

    public Start parse() throws LexerException, IOException {
        try {
            Start start;
            start = super.parse();
            System.out.println(NumberOfErrors + " errors found");
            return start;
        } catch (ParserException e) {
            if(e.getToken().getClass().equals(EOF.class)){
                return null;
            }
            NumberOfErrors++;
            SubFile file = this.superFile.getFileByLine(e.getToken().getLine());
            String output = new StringBuilder()
                    .append(file.Name)
                    .append(": ")
                    .append(file.getOffset(e.getToken().getLine()))
                    .append(",")
                    .append(e.getToken().getPos())
                    .append(": got ")
                    .append(this.oodleLexer.getTokenText())
                    .append(" but ")
                    .append(e.getMessage())
//                    .append(" after ")
//                    .append(this.nodeList.get(0))

                    .toString();
            System.out.println(output);
           // this.oodleLexer.next();
            //this.parse();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (LexerException e) {
            e.printStackTrace();
        }
        return null;
    }

}
