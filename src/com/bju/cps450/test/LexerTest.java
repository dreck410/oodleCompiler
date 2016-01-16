package com.bju.cps450.test;

import java.io.*;

import com.bju.cps450.node.*;
import junit.framework.TestCase;

import com.bju.cps450.lexer.Lexer;
import com.bju.cps450.lexer.LexerException;

public class LexerTest extends TestCase {
	Lexer lex;

	public void testSuccessfulScan() throws IOException, LexerException {
		Lexer lexer = new Lexer(new PushbackReader(new BufferedReader(new FileReader("lexertest.ood"))));
		assertNextToken(lexer, TString.class);
		assertNextToken(lexer, TThen.class);
		assertNextToken(lexer, TWhile.class);
		assertNextToken(lexer, EOF.class);
	}

	public void assertNextToken(Lexer lexer, Class tokenExpected) throws IOException, LexerException {
		Token t = lexer.next();
		System.err.println(t);
		if(t.getClass().equals(tokenExpected)) {
			return;
		} else {
			fail();
		}
	}
}
