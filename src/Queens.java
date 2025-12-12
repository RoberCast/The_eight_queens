/**
 * Main class of the program.
 * 
 * @author  Roberto Castillejo Embid.
 * @version 1.0.
 */
public class Queens 
{
	private ProblemManager problemMan;		//Save a Problem Manager.
	
	/**
	 * Default constructor for Queens.
	 */
	public Queens()
	{
		problemMan = null;
	}
	
	/**
	 * Run the application according to the set of chosen parameters.
	 * 
	 * @param args	The application execution arguments.
	 */
	static public void main(String[] args)
	{
		Queens queens = new Queens();
		queens.runQueens(args);
	}
	
	/**
	 * Returns a ProblemManager object.
	 * 
	 * @return	A ProblemManager object.
	 */
	public ProblemManager getProblemManager()
	{
		return problemMan;
	}
	
	/**
	 * Change a ProblemManager object to another one passed by parameter.
	 * 
	 * @param newProblemMan	A ProblemManager object.
	 */
	public void setProblemManager(ProblemManager newProblemMan)
	{
		problemMan = newProblemMan;
	}
	
	/**
	 * Run the application according to the selected parameters.
	 * 
	 * Run options:
	 * Case 1: [-h]							Displays the help.
	 * Case 2: n							Displays the solution to the problem via standard output.
	 * Case 3: [-t] n						It displays the solution to the problem via standard output and generates the trace file.
	 * Case 4: n [output.txt]				Generates an output file with the solution.
	 * Case 5: [-g] n [output.txt]			Generates an output file with the graphical solution.
	 * Case 6: [-t] n [output.txt]			Generates an output file with the solution and another with the trace.
	 * Case 7: [-t] [-g] n [output.txt]		Generates an output file with the graphical solution and another with the trace.
	 * 
	 * @param v		A String array with the execution arguments.
	 */
	public void runQueens(String[] v)
	{
		try{
			switch(v.length){
				case 1:
					manageCases1y2(v);
					break;
				case 2:
					manageCases3y4(v);
					break;
				case 3:
					manageCases5y6(v);
					break;
				case 4:
					manageCase7(v);
					break;
				default:
					manageInvalidSyntax();
					break;
			}
		}
		catch(Exception e){
			manageInvalidSyntax();
		}
	}
	
	
	/**
	 * Manage cases 1 and 2 of the program.
	 * Case 1:	Diplays the help. 
	 * Case 2:	Displays the solution to the problem via standard output.
	 * 
	 * @param v		A String array with the execution arguments.
	 */
	private void manageCases1y2(String[] v)
	{
		//Case 1.
		if(v[0].equals("-h")){
			problemMan = new ProblemManager();
			problemMan.printHelp(false);
		}
		//Case 2.
		else{
			int aux = Integer.parseInt(v[0]);
			problemMan = new ProblemManager(aux, null, false, false);
			problemMan.showOutput();
		}
	}
	
	/**
	 * Manage cases 3 and 4 of the program.
	 * Case 3: 	It displays the solution to the problem via standard output and generates the file with the trace.
	 * Case 4:  Generate an output file with the solution.
	 * 
	 * @param v		A String array with the execution arguments.
	 */
	private void manageCases3y4(String[] v)
	{
		//Case 3.
		if(v[0].equals("-t")){
			int aux = Integer.parseInt(v[1]);
			problemMan = new ProblemManager(aux, null, true, false);
			problemMan.showOutput();
			problemMan.writeTraceFile();
		}
		//Case 4.
		else if(v[1] != null){
			int aux = Integer.parseInt(v[0]);
			problemMan = new ProblemManager(aux, v[1], false, false);
			problemMan.showOutput();
		}
		else{
			manageInvalidSyntax();
		}
	}
	
	/**
	 * Manage cases 5 and 6 of the program.
	 * Case 5:	Generate an output file with the graphical solution.
	 * Case 6:  Generate an output file with the solution and another with the trace.
	 * 
	 * @param v		A String array with the execution arguments.
	 */
	private void manageCases5y6(String[] v)
	{
		//Case 5.
		if(v[0].equals("-g")){
			int aux = Integer.parseInt(v[1]);
			problemMan = new ProblemManager(aux, v[2], false, true);
		}
		//Case 6.
		else if(v[0].equals("-t")){
			int aux = Integer.parseInt(v[1]);
			problemMan = new ProblemManager(aux, v[2], true, false);
			problemMan.showOutput();
			if(v[2].contains(".txt")){
				problemMan.writeTraceFile();
			}
		}
		else{
			manageInvalidSyntax();
		}
	}
	
	/**
	 * Manage case 7 of the application: Generate a file with the graphical solution and 
	 * another with the trace.
	 * 
	 * @param v		A String array with the execution arguments.
	 */
	private void manageCase7(String[] v)
	{
		if(v[0].equals("-t") && v[1].equals("-g")){
			int aux = Integer.parseInt(v[2]);
			problemMan = new ProblemManager(aux, v[3], true, true);
			if(v[3].contains(".txt")){
				problemMan.writeTraceFile();
			}
		}
		else{
			manageInvalidSyntax();
		}
	}
	
	/**
	 * Handles syntax not accepted by the application.
	 */
	private void manageInvalidSyntax()
	{
		problemMan = new ProblemManager();
		problemMan.printHelp(true);
	}
}
