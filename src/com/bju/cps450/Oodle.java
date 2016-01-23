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
import com.bju.cps450.parser.Parser;
import com.bju.cps450.parser.ParserException;
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
		Options options = new Options();
    	//command line options
		CmdLineParser.Option help = parser.addBooleanOption('?', "help");
		CmdLineParser.Option d = parser.addBooleanOption('d',"ds");
		CmdLineParser.Option s = parser.addBooleanOption('s', "ds");


		//parse command line arguments
		parser.parse(args);
		
		//set applicable values from options class
		options.setDs(((Boolean)parser.getOptionValue(d, false) && (Boolean)parser.getOptionValue(s, false)) );

		if ((Boolean)parser.getOptionValue(help, false)) {
			printHelp();
			return;
		}
		options.setFiles(parser.getRemainingArgs());

		if(parser.getRemainingArgs().length > 0) {
			SuperFile reader = null;
			try {
				reader = new SuperFile(options.getFileNames());
				OodleLexer lexer = new OodleLexer(reader, options.getDs());
				Token token = lexer.next();


				while(!(token.getClass().equals(EOF.class)
                        || token == null)){
					token = lexer.next();

				}
				if (lexer.hasError){
					System.exit(1);
				}
				// NO ERRORS! YAY time to check syntax
				OodleParser oodleParser = new OodleParser(new OodleLexer(reader, options.getDs()), reader);
				oodleParser.parse();
				if(oodleParser.NumberOfErrors > 0 ){
					System.out.println(oodleParser.NumberOfErrors + " errors found");
					System.exit(1);
				}

			}catch(IOException e){
				return;
			}
		} else {
			throw new IOException("no files to lex");
		}
    }
}

