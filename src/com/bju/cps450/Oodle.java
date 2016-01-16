/* Oodle.java
 * Author: Ethan McGee
 * Date: 2014-01-23
 * 
 * Purpose: Main class for Oodle compiler project
 */

package com.bju.cps450;
import com.bju.cps450.lexer.Lexer;
import com.bju.cps450.node.EOF;
import com.bju.cps450.node.Token;
import jargs.gnu.CmdLineParser;
import jargs.gnu.CmdLineParser.IllegalOptionValueException;
import jargs.gnu.CmdLineParser.UnknownOptionException;

import java.io.*;
import java.util.Objects;

import com.bju.cps450.lexer.LexerException;

public class Oodle
{
	/* printHelp
	 * Arguments:
	 * 
	 * Purpose: Writes a help statement to standard out
	 */
	public static void printHelp() {
		System.out.println("Oodle Compiler");
		System.out.println("v 0.1");
		System.out.println("Author: <name>");
		System.out.println("");
		System.out.println("Usage:");
		System.out.println(" java -jar oodle.jar [options] [files]");
	}
	
	/* main
	 * Arguments:
	 *  @args - the list of command line arguments
	 * Purpose: main execution function for compiler
	 */
    public static void main(String[] args) throws IOException, IllegalOptionValueException, UnknownOptionException, LexerException {
    	CmdLineParser parser = new CmdLineParser();
    	//command line options
		CmdLineParser.Option help = parser.addBooleanOption('?', "help");
		CmdLineParser.Option ds = parser.addDoubleOption("ds");
		//parse command line arguments
		Boolean printTokens = false;

		parser.parse(args);
		
		//set applicable values from options class
		if ((Boolean)parser.getOptionValue(help, false)) {
			printHelp();
			return;
		}

		if((Boolean)parser.getOptionValue(ds, false)){
			printTokens = true;
		}

		if(parser.getRemainingArgs().length > 0) {
			String file = parser.getRemainingArgs()[0];
			PushbackReader reader = new PushbackReader(new InputStreamReader(new FileInputStream(file)));
			OodleLexer lexer = new OodleLexer(reader,file);
			//lexer.filter();
			Token token = lexer.next();
			while(!Objects.equals(token.getText(), "")){
				//System.out.print(token.getText());
				token = lexer.next();
			}

		} else {
			throw new IOException("no files to lex");
		}
    }
}

