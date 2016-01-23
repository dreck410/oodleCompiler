package com.bju.cps450.test;

import java.io.*;
import java.util.ArrayList;

import com.bju.cps450.OodleLexer;
import com.bju.cps450.SuperFile;
import com.bju.cps450.node.*;
import junit.framework.TestCase;

import com.bju.cps450.lexer.Lexer;
import com.bju.cps450.lexer.LexerException;

public class LexerTest extends TestCase {
	OodleLexer lexer;

	public void testSuccessfulScan() throws IOException, LexerException {
		String[] files = {"lexertest.ood"};
		SuperFile file = new SuperFile(files);
		lexer = new OodleLexer(file, 1);
		assertNextToken(lexer, TNewLine.class);
		assertNextToken(lexer, TClass.class);
		assertNextToken(lexer, TIdentifier.class);
		assertNextToken(lexer, TIs.class);
		assertNextToken(lexer, TNewLine.class);
		assertNextToken(lexer, TNewLine.class);
		assertNextToken(lexer, TIdentifier.class);
		assertNextToken(lexer, TColon.class);
		assertNextToken(lexer, TString.class);
		assertNextToken(lexer, TAssignment.class);
		assertNextToken(lexer, TIllegalString.class);
		assertNextToken(lexer, TNewLine.class);
		assertNextToken(lexer, TIllegal.class);
		assertNextToken(lexer, TAssignment.class);
		assertNextToken(lexer, TInteger.class);
		assertNextToken(lexer, TNewLine.class);
		assertNextToken(lexer, TLogicalAnd.class);
		assertNextToken(lexer, TLogicalNot.class);

	}

	// Checks the next token is the assumed token
	// Ignores space and comments and will skip them
	public void assertNextToken(Lexer lexer, Class tokenExpected) throws IOException, LexerException {
		Token t = lexer.next();
		System.err.println(t);
		while(t.getClass().equals(TComment.class)
				|| t.getClass().equals(TSpace.class)
				|| t.getClass().equals(TTab.class)
				|| t.getClass().equals(TLineContinue.class))
		{
			t = lexer.next();
		}

		if(t.getClass().equals(tokenExpected)) {
			return;
		} else {
			fail();
		}
	}
}
