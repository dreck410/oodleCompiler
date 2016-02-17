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
		// makes it possible to specify -ds
		CmdLineParser.Option d = parser.addBooleanOption('d',"ds");
		CmdLineParser.Option s = parser.addBooleanOption('s', "ds");


		//parse command line arguments
		parser.parse(args);
		
		//set applicable values from options class
		// ds is only set if both d and s are present
		options.setDs(((Boolean)parser.getOptionValue(d, false) && (Boolean)parser.getOptionValue(s, false)) );

		if ((Boolean)parser.getOptionValue(help, false)) {
			printHelp();
			return;
		}
		options.setFiles(parser.getRemainingArgs());

		if(parser.getRemainingArgs().length > 0) {
			SuperFile reader = null;
			try {
                // takes the input files and combines them into 1 super file object
				reader = new SuperFile(options.getFileNames());
				// creates a new compiler with the given source code
                OodleCompiler oodleCompiler = new OodleCompiler(reader, options.getDs());

				oodleCompiler.Compile();


			}catch(IOException e){
				return;
			}
		} else {
			throw new IOException("no files to lex");
		}
    }
}

