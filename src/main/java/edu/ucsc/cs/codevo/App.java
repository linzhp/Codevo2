package edu.ucsc.cs.codevo;

import java.io.File;
import java.io.IOException;

import org.eclipse.jdt.internal.compiler.CompilationResult;
import org.eclipse.jdt.internal.compiler.DefaultErrorHandlingPolicies;
import org.eclipse.jdt.internal.compiler.ast.CompilationUnitDeclaration;
import org.eclipse.jdt.internal.compiler.batch.CompilationUnit;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;
import org.eclipse.jdt.internal.compiler.impl.CompilerOptions;
import org.eclipse.jdt.internal.compiler.parser.Parser;
import org.eclipse.jdt.internal.compiler.problem.DefaultProblemFactory;
import org.eclipse.jdt.internal.compiler.problem.ProblemReporter;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	try {
			CompilationUnitDeclaration cu = parse("resources/App.java");
			cu.cleanUp();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static CompilationUnitDeclaration parse(String fileName) 
    		throws IOException {
    	String content = Files.toString(new File(fileName), Charsets.UTF_8);
    	CompilerOptions options = new CompilerOptions();
    	options.complianceLevel = 
    			options.sourceLevel = 
    			options.targetJDK = 
    			ClassFileConstants.JDK1_8;
    	Parser parser = new Parser(
    			new ProblemReporter(
    					DefaultErrorHandlingPolicies.proceedWithAllProblems(), 
    					options, new DefaultProblemFactory()
    			),
    			false);
    	CompilationUnit cu = 
    			new CompilationUnit(content.toCharArray(), fileName, null);
    	CompilationResult compilationResult = 
    			new CompilationResult(cu, 0, 0, options.maxProblemsPerUnit);
    	return parser.parse(cu, compilationResult);
    }
}
