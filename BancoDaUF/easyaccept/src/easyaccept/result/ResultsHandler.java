package easyaccept.result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * This is the results handler. The handler storages information about all tests scripts results.
 * 
 * @author Gustavo Farias
 * @author Alvaro Magnum
 * @author Magno Jefferson
 */
public class ResultsHandler {

	
	private Map<String, ScriptResultsManager> allTests;
	
	/**
	 * The ResultHandler constructor.
	 */
	public ResultsHandler(){
		this.allTests = new HashMap<String, ScriptResultsManager>();
	}
	
	/**
	 * Adds a test Result to the results handler.
	 * 
	 * @param file 
	 * 				the name of the file that contains the tests.
	 * @param scriptResultManager 
	 * 				The manager of a specified test script results.
	 */
	public void addResult(String file, ScriptResultsManager scriptResultManager){
		this.allTests.put(file, scriptResultManager);
	}
	
	/**
	 * Returns the number of not passed tests of a specified test script.	
	 * 
	 * @param file 
	 * 				the name of the file that contains the executed acceptance tests.
	 * 
	 * @return The number of not passed tests or -1 if there is no test file with the specified test script name.
	 */
	public int getScriptNumberOfNotPassedTests(String file) {
		if(allTests.containsKey(file)){
			return this.allTests.get(file).getNumberOfErrors();
		}
		return -1;
	}
	
	/**
	 * Returns the number of passed tests of a specified test script.
	 * 
	 * @param file 
	 * 				the name of the file that contains the executed acceptance tests.
	 * 
	 * @return The number of passed tests or -1 if there is no test file with the specified name.
	 */
	public int getScriptNumberOfPassedTests(String file) {
		if(allTests.containsKey(file)){
			return this.allTests.get(file).getNumberOfPassedTests();
		}
		return -1;
	}

	/**
	 * This method returns a summarized result of a specified test script.
	 * Summarized result contains: 	Each tested script's name.
	 * 								Number of passed tests.
	 * 								Number of not passed tests.
	 * 							
	 * @param file 
	 * 				the name of the file that contains the executed acceptance tests.
	 * 
	 * @return A summarized result of specified test script execution or null 
	 * if there is no script with the specified name.
	 */
	public String getScriptSummarizedResults(String file) {
		
		if(allTests.containsKey(file)){
			String summarizedOut = "Test file: " + file + " | Passed Tests: " + + getScriptNumberOfPassedTests(file) +
			" | Not Passed Tests: " + getScriptNumberOfNotPassedTests(file) + "\n\n";

			return summarizedOut;
			
		}else{
			return null;
		}
		
		
	}
	
	/**
	 * This method returns a summarized result of all executed tests.
	 * Summarized result contains: 	Each tested script's name.
	 * 								Number of passed tests.
	 * 								Number of not passed tests.
	 * 							
	 * @return A summarized result of all executed tests.
	 */
	public String getSummarizedResults() {
		
		String summarizedOut = "";
		ArrayList<String> keys = setToArrayList(allTests.keySet());
		
		Collections.sort(keys);
		
		for (int i = 0; i < keys.size(); i++) {
			String file = keys.get(i);
			summarizedOut += "Test file: " + file + " | Passed Tests: " + getScriptNumberOfPassedTests(file) +
			" | Not Passed Tests: " + getScriptNumberOfNotPassedTests(file) + "\n\n";
		}

		return summarizedOut;
	}
	
	private ArrayList<String> setToArrayList(Set<String> keySet) {
		ArrayList<String> a = new ArrayList<String>();
		
		for (String string : allTests.keySet()) {
			a.add(string);
		}
		Collections.sort(a);
		
		return a;
	}

	/**
	 * This method returns the test result of a specified script line or null if there
	 * is no test file with the specified name.
	 * The test script line result is a Result object.
	 * 
	 * @param file 
	 * 				name of the test file.
	 * @param line 
	 * 				the test script line which the acceptance test is located.
	 * 
	 * @return The test result of the specified script line or null if the test script has not the specified line.
	 */
	public Result getLineResult(String file, int line) {
		if(allTests.containsKey(file)){
			return this.allTests.get(file).getLineResult(line);
		}
		return null;
	}
	
	/**
	 * This method returns a map with all test results of the specified test script.
	 * null is returned if there is no test file with the specified name.
	 * Each script line result is a Result object.
	 * 							
	 * @param file 
	 * 				the name of the test script.
	 * 
	 * @return A map with all the test results of the specified test script  or null if there
	 * is no test file with the specified name.
	 */
	public Map<Integer, Result> getScriptResults(String file) {
		if(allTests.containsKey(file)){
			return this.allTests.get(file).getResults();
		}
		return null;
	}
	
	/**
	 * This method returns the complete results of all executed tests.
	 * Complete result contains: 	Each tested script's name.
	 * 								Passed tests.
	 * 								Not passed tests.		
	 * 								Failures (if they occur).
	 * 								Failures lines.
	 * 								System Messages.
	 * 				
	 * @return The complete results of all executed tests.
	 */
	public String getCompleteResults() {
		
		String compResult = "";
		ArrayList<String> keys = setToArrayList(allTests.keySet());
		Collections.sort(keys);
		
		for (String file : keys) {
			compResult += getScriptCompleteResults(file);
			
			compResult += "======================== \n\n";
		}
		return compResult;
	}
	
	
	/**
	 * This method returns the complete results of the specified test file.
	 * Complete result contains: 	Each tested script's name.
	 * 								Passed tests.
	 * 								Not passed tests.		
	 * 								Failures (if they occur).
	 * 								Failures lines.
	 * 								System Messages.
	 * 
	 * @param file 
	 * 				the name of the acceptance test script.
	 * 
	 * @return The complete results of the specified test file or null if there
	 * is no test file with the specified name.
	 */
	public String getScriptCompleteResults(String file) {
		if(allTests.containsKey(file)){
			return getScriptSummarizedResults(file) + getScriptFailures(file);
		}else{
			return null;
		}
		
	}
	
	/**
	 * Returns the failures of the specified test script file (if they occur).	
	 * 
	 * @param file 
	 * 				the name of the file that contains the executed acceptance tests.
	 * 
	 * @return The failures of the executed test script file (if they occur). 
	 * 		   The empty string "" is returned if no failures have been occurred.
			   null is returned if there is no test file with the specified name.
	 */
	public String getScriptFailures(String file) {
		
		if(!allTests.containsKey(file)){
			return null;
		}
		
		String failures = "";
		String originalMessage = "";
		
		if(getScriptNumberOfNotPassedTests(file) != 0){
			failures += "FAILURES:\n";
			for (Result oneResult : allTests.get(file).getResults().values()) {
				if(oneResult.hasError()){
					originalMessage = oneResult.getErrorMessage() + "\n";
				
					//The original failure string is at EasyAcceptException class
					failures += "   " +"At line "+ oneResult.getLine()+":"+ getCustomizedMessage(originalMessage);
				}
			}
		}
	
		return failures;
	}
	
	private String getCustomizedMessage(String originalMessage) {
		String myMessage = "";
		//myMessage = originalMessage.replaceFirst(":", " ");
		int indice = originalMessage.indexOf(":");
		
		myMessage = originalMessage.substring(indice+1,originalMessage.length());
		
		return myMessage;
	}

	/**
	 * Returns the total number of tests.	
	 * 
	 * @return The total number of tests.
	 */
	public int getTotalNumberOfTests() {
		int numberOfTests = 0;
		for (String file : allTests.keySet()) {
			numberOfTests += allTests.get(file).getNumOfTests();
		}		
		return numberOfTests;
	}
	
	/**
	 * Returns the total number of tests of a specified test script.	
	 * 
	 * @return The total number of tests of the specified test script or -1 if there is no test file with the specified test script name.
	 */
	public int getScriptTotalNumberOfTests(String file) {
		if(!allTests.containsKey(file)){
			return -1;
		}
		return allTests.get(file).getNumOfTests();
	}
	
	/**
	 * Returns the total number of passed tests.
	 * 
	 * @return The number of passed tests of all executed test scripts.
	 */
	public int getTotalNumberOfPassedTests() {
		int passedTests = 0;
		
		
		for (ScriptResultsManager srm : this.allTests.values()) {
			passedTests += srm.getNumberOfPassedTests();
		}
		
		return passedTests;
	}
	
	/**
	 * Returns the total number of not passed tests.
	 * 
	 * @return The number of not passed tests of all executed test scripts.
	 */
	public int getTotalNumberOfNotPassedTests() {
		
		int totalErrors = 0;
		for (ScriptResultsManager srm : this.allTests.values()) {
			totalErrors += srm.getNumberOfErrors();
		}
		return totalErrors;
	}
	
}
