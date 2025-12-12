import java.util.ArrayList;

/**
 * Class that represents a manager for the eight queens problem generalized to n queens 
 * and an n x n board.
 * 
 * @author  Roberto Castillejo Embid.
 * @version 1.0.
 */
public class ProblemManager 
{
	private int[] solution;				    //Save the solution to the queen problem.
	private ArrayList<String> output;	    //Save the solution to the problem in console or file output format.
	private int[][] graphicOutput;		    //Save the solution to the problem in a graphical output format.
	private int n;						    //Save the board size and the number of queens.
	private String fileName;		        //Save the output file name.
	private boolean isTrace;			    //It records whether or not it is a trace output.
	private ArrayList<String> trace;	    //Save the application trace.
	private boolean isGraphic;			    //Save whether the output is graphical or not.
	private OutputManager outputManager;	//Save an output manager.
	private InputManager inputManager;		//Save an input manager.
			
	/**
	 * Default constructor for ProblemManager.
	 */
	public ProblemManager()
	{
		n = 0;
		solution = null;
		output = null;
		graphicOutput = null;
		fileName = null;
		isTrace = false;
		trace = null;
		isGraphic = false;
		outputManager = null;
		inputManager = new InputManager();
	}
	
	/**
	 * Constructor for ProblemManager in which the number of queens and the size of the
	 * board are passed as parameters, an output file name, whether it is the printout 
	 * of the trace and whether it is a graphic output file.
	 * 
	 * @param n 				The size of the board and the number of queens.
	 * @param fileName		    The name of the output file.
	 * @param isTrace			True if it is the trace, false otherwise.
	 * @param isGraphic			True if it is the graphical output of the problem.
	 */
	public ProblemManager(int n, String fileName, boolean isTrace, boolean isGraphic)
	{
		outputManager = new OutputManager();
		inputManager = new InputManager();
		
		try{
			this.n = n;
			checkLimit(n);		//Check if the dimensions of the board and number of queens exceed n = 13.
			this.fileName = fileName;
			solution = new int[n];
			this.isTrace = isTrace;
			if(isTrace){
				trace = new ArrayList<String>();
			}
			else{
				trace = null;
			}
			this.isGraphic = isGraphic;
			if(isGraphic){
				executeGraphicOutput();
			}
			else{
				output = new ArrayList<String>();
			}
		}
		catch(Exception e){
			String negativeErr = "Error: The value for n cannot be a negative number.";
			outputManager.writeInLog(negativeErr, true);
			printHelp(false);
			
			System.exit(-1);
		}
	}
	
	/**
	 * Returns an array with the position number in each square of the board for a solution. 
	 * Index 0 indicates column a, index 1 column b, and so on up to n - 1.
	 * 
	 * @return	An array with the position number in each square of the board for a solution.
	 */
	public int[] getSolution()
	{
		return solution;
	}
	
	/**
	 * Returns an ArrayList<String> containing all solutions for a given n. These solutions are 
	 * stored as Strings in the array. Each position in the ArrayList<String> represents a solution; 
	 * therefore, its length is the number of solutions.
	 * 
	 * @return	An ArrayList<String> with all the solutions for a given n.
	 */
	public ArrayList<String> getOutput()
	{
		return output;
	}
	
	/**
	 * Returns an array containing a solution to the problem. This array represents a chessboard and 
	 * contains the positions of the queens on it.
	 * 
	 * @return	A matrix with a solution to the problem.
	 */
	public int[][] getGraphicOutput()
	{
		return graphicOutput;
	}
	
	/**
	 * Returns the size of the board, which is equal to the number of queens.
	 * 
	 * @return	The size of the board, which is equal to the number of queens.
	 */
	public int getN()
	{
		return n;
	}
	
	/**
	 * Returns the name of the output file. 
	 * 
	 * @return	The name of the output file.
	 */
	public String getFileName()
	{
		return fileName;
	}
	
	/**
	 * Returns whether or not trace printing is required.
	 * 
	 * @return	True if trace printing is required, false otherwise.
	 */
	public boolean isTrace()
	{
		return isTrace;
	}
	
	/**
	 * Returns an ArrayList<String> containing the positions accepted and rejected by the problem. 
	 * Each index in the list represents a set of positions that may or may not be valid.
	 * 
	 * @return	An ArrayList<String> containing the positions accepted and not accepted by the problem.
	 */
	public ArrayList<String> getTrace()
	{
		return trace;
	}
	
	/**
	 * Returns whether printing the graphical solution is required or not.
	 * 
	 * @return	True if graphic printing is required, false otherwise.
	 */
	public boolean isGraphic()
	{
		return isGraphic;
	}
	
	/**
	 * Returns an OutputManager object.
	 * 
	 * @return	An OutputManager object.
	 */
	public OutputManager getOutputManager()
	{
		return outputManager;
	}
	
	/**
	 * Returns an InputManager object.
	 * 
	 * @return	An InputManager object.
	 */
	public InputManager getInputManager()
	{
		return inputManager;
	}
	
	/**
	 * Replace one solution with another solution passed as a parameter.
	 * 
	 * @param solution	A solution to the problem.
	 */
	public void getSolution(int[] solution)
	{
		this.solution = solution;
	}
	
	/**
	 * Replace one list of solutions to the problem with another list passed as a parameter.
	 * 
	 * @param output	A list of solutions.
	 */
	public void getOutput(ArrayList<String> output)
	{
		this.output = output;
	}
	
	/**
	 * Change a graphical solution to one passed by a parameter.
	 * 
	 * @param newGraphicalSol	A graphical solution.
	 */
	public void setGraphicalOutput(int[][] newGraphicalSol)
	{
		graphicOutput = newGraphicalSol;
	}
	
	/**
	 * Change the problem size and the number of queens to another value passed as a parameter.
	 * 
	 * @param n		A value for the size of the board and the number of queens.
	 */
	public void setN(int n)
	{
		this.n = n;
	}
	
	/**
	 * Change the name of the output file to another name passed as a parameter.
	 * 
	 * @param fileName		A name for the output file.
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	
	/**
	 * Change the predicate used to determine if the trace printout is needed. 
	 * True if needed, false otherwise.
	 * 	
	 * @param isTrace	True if the trace is needed, false otherwise.
	 */
	public void setIsTrace(boolean isTrace)
	{
		this.isTrace = isTrace;
	}
	
	/**
	 * Change the list where accepted and unaccepted positions are stored, i.e., 
	 * the tracking list, to another tracking list passed as a parameter.
	 * 
	 * @param trace		A list that contains a trace of the problem.
	 */
	public void setTrace(ArrayList<String> trace)
	{
		this.trace = trace;
	}
	
	/**
	 * Change the predicate used to determine if printing the graphic output file 
	 * is needed. True if needed, false otherwise.
	 * 
	 * @param isGraphic		True if graphical output is needed, false otherwise.
	 */
	public void setIsGraphic(boolean isGraphic)
	{
		this.isGraphic = isGraphic;
	}
	
	/**
	 * Change the Output Manager to another Output Manager passed as a parameter.
	 * 
	 * @param outputManager	An outputManager object.
	 */
	public void setOutputManager(OutputManager outputManager)
	{
		this.outputManager = outputManager;
	}
	
	/**
	 * Change the Input Manager to another Input Manager passed as a parameter.
	 * 
	 * @param inputManager	An inputManager object.
	 */
	public void setInputManager(InputManager inputManager)
	{
		this.inputManager = inputManager;
	}
	
	/**
	 * Displays the solution to the problem through standard output or generates 
	 * an output file with the solution.
	 */
	public void showOutput()
	{
		//Only values ​​greater than 0 are valid for n.
		if(n == 0){
			String zeroErr = "Error: The value for n must be greater than 0.";
			outputManager.writeInLog(zeroErr, true);
			printHelp(false);
		}
		else{
			queens(solution, n, 0);
			
			if(output.size() == 0){ //The output ArrayList does not contain solutions.
				String noSolution = "The problem for n = " + n + " has no solution.";
				if(fileName == null){
					System.out.println();
					System.out.println(noSolution);
					String output = "The solution is displayed via standard output.";
					outputManager.writeInLog(output, false);
				}
				else{
					output.add(noSolution);
					outputManager.writeFile(fileName, output, false, null, null);
					if(fileName.contains(".txt")){
						String output = "The solution is shown per file.";
						outputManager.writeInLog(output, false);
					}
					else{
						printHelp(false);
					}
				}
			}
			else{
				if(fileName == null){
					System.out.println();
					for(int i = 0; i < output.size(); i++){
						System.out.println(output.get(i));
					}
					String output = "The solution is displayed via standard output.";
					outputManager.writeInLog(output, false);
				}
				else{
					outputManager.writeFile(fileName, output, false, null, null);
					if(fileName.contains(".txt")){
						String output = "The solution is shown per file.";
						outputManager.writeInLog(output, false);	
					}
					else{
						printHelp(false);
					}
				}
			}
		}
	}
	
	/**
	 * Generates a trace file with the positions accepted and rejected 
	 * by the algorithm that solves the problem.
	 */
	public void writeTraceFile()
	{
		if(n > 0){
			outputManager.writeFile(null, trace, true, null, null);
		}
	}
	
	/**
	 * Prints the application's help. This is passed as a parameter if the 
	 * printing is due to a syntax error.
	 * 
	 * @param isSyntaxErr		True if it is a syntax error, false otherwise.
	 */
	public void printHelp(boolean isSyntaxErr)
	{
		inputManager.printHelp(isSyntaxErr);
	}
	
	
	/**
	 * Find all solutions to the eight queens problem generalized to n queens on an n x n board.
	 * 
	 * @param s		A vector of integers, solution to the problem.
	 * @param n		A positive integer that is the size of the board.
	 * @param k		A positive integer that is the queen to be placed.
	 */
	private void queens(int[] s, int n, int k)
	{
		s[k] = 0;
		
		while(s[k] < n){
			s[k] = s[k] + 1;
			if(completeable(s, k)){
				if(k == n - 1){
					write(s);
				}
				else{
					queens(s, n, k + 1);
				}
			}
			//If trace == true, insert the rejected positions into an ArrayList<String> trace.
			if(isTrace && k == n -1 && s[k] > 0 && n > 1){
				writeRejected(s);
			}
		}
	}
	
	/**
	 * Check if an extension of the previous partial solution is completable, that is, if it has no 
	 * queens in the same row or on the same diagonal.
	 * 
	 * @param s		A vector of integers.
	 * @param k		A positive integer.
	 * @return		True if it is completable, false otherwise.
	 */
	private boolean completeable(int[] s, int k)
	{
		for(int i = 0; i < k; i++){
			if(s[i] == s[k] || (Math.abs(s[i] - s[k]) == Math.abs(i - k))){
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Process the solution to the problem.
	 * 
	 * @param s		A vector of integers is the solution to the problem.
	 */
	private void write(int[] s)
	{
		String solution = buildSolution(s);
		
		//If isTrace == true, insert the accepted positions into the ArrayList<String> trace.
		if(isTrace){
			trace.add("Position accepted: |============================================> " + solution);
		}
		
		//Graphic output mode.
		if(isGraphic){
			//Initialize the matrix that contains the board
			graphicOutput = new int[n][n];
			for(int i = 0; i < s.length; i++){
				int aux = s[i];
				graphicOutput[aux - 1][i] = 1;
			}
			String aux = "Solution: " + solution;
			solution = aux;
			showGraphicalOutput(solution);
		}
		else{
			//Standard output mode / output file
			output.add(output.size() + 1 + ": " + solution);
		}
	}
	
	/**
	 * Displays the solutions to the problem graphically in an output file.
	 * 
	 * @param text	 A solution to the problem.
	 */
	private void showGraphicalOutput(String text)
	{
		if(n == 2 || n == 3){ 	//There is no solution.
			outputManager.writeFile(fileName, null, false, text, null);
		}
		else{
			outputManager.writeFile(fileName, null, false, text, graphicOutput);
		}
	}
	
	
	/**
	 * Generate a String with the correct positions corresponding to a solution to the problem.
	 * 
	 * @param s		A vector of integers is the solution to the problem.
	 * @return		A String with the correct positions corresponding to a solution to the problem.
	 */
	private String buildSolution(int[] s)
	{
		Character letter = 'a';				//It corresponds to the first column of the board.
		int reference = letter.charValue(); //Save the letter value.
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < s.length; i++){
			sb.append(letter.toString() + s[i] + " ");
			reference++;
			letter = new Character((char) reference);
		}
		
		return sb.toString();
	}
	
	/**
	 * Add unaccepted positions to the ArrayList<String> of the trace.
	 * 
	 * @param s		An array of positive integers.
	 */
	private void writeRejected(int[] s)
	{
		Character letter = 'a';				//It corresponds to the first column of the board.
		int reference = letter.charValue(); //Save the letter value.
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < s.length; i++){
			sb.append(letter.toString() + s[i] + " ");
			reference++;
			letter = new Character((char) reference);
		}
		
		trace.add("Rejected position: " + sb);
	}
	
	/**
	 * Check if a graphic file exists.
	 * 
	 * @param file	The file name.
	 */
	private void checkGraphicalFile(String file)
	{
		outputManager.checkGraphicExistence(file);
	}
	
	/**
	 * Check if the limit for board size and number of queens has been exceeded, and if so, 
	 * inform the user, log the error, and display the help.
	 * 
	 * @param n		The size of the board and the number of queens.
	 */
	private void checkLimit(int n)
	{
		//It is limited to n < 14 to avoid very large text files and times.
		if(n >= 14){
			String limit = "Error: The board size and the number of queens cannot be greater than n = 13.";
			inputManager.writeInLog(limit, true);
			inputManager.printHelp(false);
			System.exit(-1);
		}
	}
	
	/**
	 * Generates an output file with the graphical solution to the problem.
	 */
	private void executeGraphicOutput()
	{
		if(n == 0){
			String zeroErr = "Error: The value for n must be greater than 0.";
			outputManager.writeInLog(zeroErr, true);
			printHelp(false);
			System.exit(-1);
		}
		checkGraphicalFile(fileName);	//Check for the existence of the graphic output file.				
		if(n == 2 || n == 3){
			String solution = "The problem for n = " + n + " has no solution.";
			showGraphicalOutput(solution);
		}
		graphicOutput = null;
		queens(solution, n, 0);
		if(!(fileName.contains(".txt"))){
			printHelp(false);
		}
	}
}
