package driver;

import java.io.File;
import java.io.FileWriter;

import iteration.MyArrayList;
import myVisitor.SpellCheckVisitor;
import myVisitor.TopKMostFreqVisitor;
import myVisitor.Visitor;
import results.Results;
import results.SpellCheckResults;
import results.TopKFreqWordsResults;
import util.FileProcessor;
import util.MyLogger;
import validator.ValidatorUtil;

/***
 * @author
 * saikrishna kovuru, kishorekumar basam
 */
public class Driver {
  private static void runAnalysis(FileProcessor fileProcessor, Visitor... visitors) {
    MyArrayList myArrayList = new MyArrayList.Builder().withFileProcessor(fileProcessor).build();

    for (Visitor visitor : visitors) {
      myArrayList.accept(visitor);
    }
  }

  private static void saveResults(Results... analysisResults) {
    for (Results results : analysisResults) {
      results.writeToFile();
    }
  }

  public static void main(String[] args) {

    final int REQUIRED_NUMBER_OF_ARGS = 6;
    if ((args.length != REQUIRED_NUMBER_OF_ARGS) || (args[0].equals("${inputFilename}"))
        || (args[1].equals("${acceptableWordsFilename}")) || (args[2].equals("${k}"))
        || (args[3].equals("${topKOutputFilename}")) || (args[4].equals("${spellCheckOutputFilename}"))
        || (args[5].equals("${errorLog}"))) {

      System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.",
          REQUIRED_NUMBER_OF_ARGS);
      System.exit(0);
    }
    FileWriter errorLog = null;
    if (new File(args[0]).exists() && new File(args[1]).exists() && args[2].matches("\\d+")) {

      File input1 = new File(args[0]);
      File input2 = new File(args[2]);
      try {
        if (input1.length() != 0 || input2.length() != 0) {

          new ValidatorUtil(args);
          MyLogger.writeMessage("Let's get started with this assignment!", null);

          errorLog = new FileWriter(args[5]);

          FileProcessor fileProcessor = new FileProcessor(args[0], errorLog);
          Results topKFreqWordsResults = new TopKFreqWordsResults(args[3]);
          Visitor topKMostFreqAnalyzer = new TopKMostFreqVisitor(args[2], topKFreqWordsResults);

          Results spellCheckResults = new SpellCheckResults(args[4]);
          Visitor caseSensitiveSpellCheck = new SpellCheckVisitor(args[1], spellCheckResults, "casesensitive",
              errorLog);
          Visitor nonCaseSensitiveSpellCheck = new SpellCheckVisitor(args[1], spellCheckResults, "caseinsensitive",
              errorLog);

          runAnalysis(fileProcessor, topKMostFreqAnalyzer, caseSensitiveSpellCheck, nonCaseSensitiveSpellCheck);

          saveResults(topKFreqWordsResults, spellCheckResults);

          MyLogger.writeMessage("done!", null);
        } else {
          errorLog = new FileWriter("errorLog.txt");
          errorLog.write("input files cannot be empty");
          MyLogger.writeMessage("input files cannot be empty!", null);
          errorLog.flush();
          errorLog.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
      }
    } else {
      MyLogger.writeMessage("either the input files are absent/the value of kth most frequent digit is an integer",
          null);
    }
  }
}