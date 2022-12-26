package myVisitor;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import iteration.Iterator;
import iteration.MyArrayList;
import results.Results;
import results.SpellCheckResults;
import strategy.CaseInSensitiveStrategy;
import strategy.CaseSensitiveStrategy;
import strategy.CheckStrategy;
import util.ProcessUSToUKFile;

public class SpellCheckVisitor implements Visitor {

	private Results spellCheckResults;
	private String strategy;
	private CheckStrategy Check_in;
	private CheckStrategy Check_sens;
	private Iterator it;
	private ProcessUSToUKFile pif;
	private List<String> result;

	public SpellCheckVisitor(String fileName, Results spellCheckResultsIn, String stg, FileWriter fw) {
		strategy = stg;
		spellCheckResults = spellCheckResultsIn;
		pif = new ProcessUSToUKFile(fileName, fw);

	}

	@Override
	public void visit(MyArrayList myArray) {
		result = new ArrayList<>();
		it = myArray.getIterator();
		if (strategy == "casesensitive") {
			Check_sens = new CaseSensitiveStrategy();
			result = Check_sens.performSpellCheck(myArray, pif.getUSUKWords_caseSensitive());
			((SpellCheckResults) spellCheckResults).setCaseSensitive(result);
		} else {
			Check_in = new CaseInSensitiveStrategy();
			result = Check_in.performSpellCheck(myArray, pif.getUSUKWords_caseInSensitive());
			((SpellCheckResults) spellCheckResults).setCaseInSensitive(result);
		}
	}

}
