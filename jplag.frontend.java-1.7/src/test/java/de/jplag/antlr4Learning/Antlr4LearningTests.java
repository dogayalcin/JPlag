package de.jplag.antlr4Learning;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import de.jplag.java17.JplagJava7Listener;
import de.jplag.java17.grammar.Java7Lexer;
import de.jplag.java17.grammar.Java7Parser;

public class Antlr4LearningTests {
	private static File srcTestResources;

	@BeforeClass
	public static void getPaths() {
		srcTestResources = new File(System.getProperty("user.dir"), "src/test/resources");
	}

	@Test
	public void testVisitor() throws IOException {
		BufferedInputStream fis = new BufferedInputStream(new FileInputStream(new File(srcTestResources, "ExceptionOne.java")));
		CharStream input = CharStreams.fromStream(fis);

		// create a lexer that feeds off of input CharStream
		Java7Lexer lexer = new Java7Lexer(input);

		// create a buffer of tokens pulled from the lexer
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		Java7Parser j7p = new Java7Parser(tokens);
		j7p.addParseListener(new JplagJava7Listener(new de.jplag.java17.Parser()));

		j7p.compilationUnit();
	}

	@Ignore
	public void howToUseAntlr4() throws IOException {
		FileInputStream fis = new FileInputStream(new File(srcTestResources, "Java7FeatureTest.java"));
		// create a CharStream that reads from file input stream
		CharStream input = CharStreams.fromStream(fis);

		// create a lexer that feeds off of input CharStream
		Java7Lexer lexer = new Java7Lexer(input);

		// create a buffer of tokens pulled from the lexer
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		// create a parser that feeds off the tokens buffer
		Java7Parser parser = new Java7Parser(tokens);

		ParseTree tree = parser.compilationUnit(); // begin parsing at compilationUnit rule
		System.out.println(tree.toStringTree(parser)); // print LISP-style tree

	}

	@Ignore
	public void howToUseAntlr4Tokens() throws IOException {
		FileInputStream fis = new FileInputStream(new File(srcTestResources, "Java7FeatureTest.java"));
		// create a CharStream that reads from file input stream
		CharStream input = CharStreams.fromStream(fis);

		// create a lexer that feeds off of input CharStream
		Java7Lexer lexer = new Java7Lexer(input);

		for (Token t : lexer.getAllTokens()) {
			System.out.println(lexer.getTokenNames()[t.getType()]);
		}
	}

}
