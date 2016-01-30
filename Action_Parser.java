//package config;

//ANTLR library
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import java.io.*;
import org.antlr.v4.runtime.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.File;

public class Action_Parser {

	public static boolean parser(String inp, String suc, String inv, String fName) throws Exception{
		
			BufferedReader br = new BufferedReader(new FileReader(inp));
			PrintWriter success = new PrintWriter(new FileWriter(suc));
			PrintWriter invalid = new PrintWriter(new FileWriter(inv));
			
			String s = null;
			System.out.println("in Action_parser.java");
			while((s=br.readLine())!=null)
			{
				if(s.contains("in:<") || s.contains("in:Hotspot"))
				{
					ANTLRInputStream input = new ANTLRInputStream(s);
					sysLexer lexer = new sysLexer(input);
					CommonTokenStream tokens = new CommonTokenStream(lexer);
					//String parserFile = gram  + "Parser" ;
					//parserFile parser = new parserFile(tokens);
					sysParser parser = new sysParser(tokens);
					ParseTree tree = parser.r();
					EvalVisitor visitor = new EvalVisitor();
					success.println(visitor.visit(tree));
				}
				else
				{
					invalid.println(s);
					//System.out.println("You are in invalid state");
				}
			}
			invalid.flush();
			invalid.close();
			success.flush();
			success.close();
			br.close();

			return true;			
	}
	
}
