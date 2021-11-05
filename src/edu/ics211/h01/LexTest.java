/**
 * 
 */

package edu.ics211.h01;

import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author esb 2021/08/24
 *
 */
class LexTest {

  private final String shortTest = "public static void main() { };";
  private final String[] shortExpected = { "public", "static", "void", "main", "(", ")", "{", "}",
      ";" };

  // source code of Lexer.java
  private final String fileContents = "/**\n" + " * \n" + " */\n" + "package edu.ics211.h01;\n"
      + "\n" + "import java.io.InputStream;\n" + "\n" + "/**\n" + " * @author esb 2021/08/24\n"
      + " *\n" + " */\n" + "public interface Lexer {\n" + "  String[] lexicalTokens(String in);\n"
      + "  String[] lexicalTokens(InputStream in);\n" + "}";

  private final String[] expectedResult = { "package", "edu", ".", "ics211", ".", "h01", ";",
      "import", "java", ".", "io", ".", "InputStream", ";", "public", "interface", "Lexer", "{",
      "String", "[", "]", "lexicalTokens", "(", "String", "in", ")", ";", "String", "[", "]",
      "lexicalTokens", "(", "InputStream", "in", ")", ";", "}" };

  private final String fileName = "Lexer.java";

  private InputStream fileInput;

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    try {
      System.out.println("looking for file in " + new java.io.File("Lexer.java").getAbsolutePath());
    } catch (Exception ex){
      System.out.println("got exception " + ex);
      System.exit(1);
    }
    try {
      fileInput = new java.io.FileInputStream(fileName);
    } catch (java.io.FileNotFoundException ex) {
      fail("file " + fileName + " not found");
    }

  }

  @Test
  void test() {
    Lexer lexer = new Lex();
    // short test
    String[] shortLexed = lexer.lexicalTokens(shortTest);
    assertArrayEquals(shortLexed, shortExpected);
    // long test including comments
    String[] lexed = lexer.lexicalTokens(fileContents);
    assertArrayEquals(lexed, expectedResult);
    // file test
    String[] lexedFile = lexer.lexicalTokens(fileInput);
    assertArrayEquals(lexedFile, expectedResult);
  }

}