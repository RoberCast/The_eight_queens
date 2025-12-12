import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This represents the program's output file manager. The output files are the program trace and the program's output.
 * 
 * @author  Roberto Castillejo Embid.
 * @version 1.0.
 */
public class OutputManager extends IOManager
{
	private boolean isTxtFile;			//Checks if a file has the .txt extension
	private boolean isInformed;	        //Saves whether the user has been informed about the graphic output file.
	
	/**
	 * Default constructor for OutputManager.
	 */
	public OutputManager()
	{
		super();
		isTxtFile = false;
		isInformed = false;
	}
	
	/**
	 * Constructor for OutputManager in which it is passed as a parameter whether a file has the .txt extension and 
	 * whether the user has been informed about the graphic output file.
	 * 
	 * @param isTxtFile			True if the file has the .txt extension, false otherwise.
	 * @param isInformed	    True if the user has been informed about the graphic output file, false otherwise.
	 */
	public OutputManager(boolean isTxtFile, boolean isInformed)
	{
		super();
		this.isTxtFile = isTxtFile;
		this.isInformed = isInformed;
	}
	
	
	/**
	 * Returns whether the input file has the .txt extension
	 * 
	 * @return		True if it has a .txt extension, false otherwise.
	 */
	public boolean isTxtFile()
	{
		return isTxtFile;
	}
	
	/**
	 * Returns whether the user has already been informed that the graphical output has been successfully generated.
	 * 
	 * @return		True if the user has already been informed, false otherwise.
	 */
	public boolean isInformed()
	{
		return isInformed;
	}
	
	/**
	 * Change if an output file has the .txt extension to another value passed as a parameter.
	 * 
	 * @param isTxtFile	True if it has the .txt extension, false otherwise.
	 */
	public void setTxt(boolean isTxtFile)
	{
		this.isTxtFile = isTxtFile;
	}
	
	/**
	 * Change if a user has been informed that the program's graphical output has been successful.
	 * 
	 * @param isInformed		True if the user has been informed, false otherwise.
	 */
	public void setInformado(boolean isInformed)
	{
		this.isInformed = isInformed;
	}
	
	/**
	 * Generates a file containing the problem trace or its solution. The parameter passing is as follows:
	 * 
	 * Output file: The filename and an ArrayList<String> containing the problem result are passed. The 
	 * parameters esTraza, solución, and tablero must be null.
	 * 
	 * Graphic output file: It takes the file name, a String with the solution to write, and an array 
	 * representing a board. ValuesToWrite has a null value and Trace has a false value.
	 * 
	 * Trace file: The file parameter must be null, solution must be null, and board must be null. The 
	 * remaining parameters are an ArrayList<String> with the trace content and isTrace = true.
	 * 
	 * @param file				The name of the output file.
	 * @param valuesToWrite		The result of the problem.
	 * @param isTrace			True if it is the trace file or false otherwise.
	 * @param solution			A String with the solution to the problem.
	 * @param board				A matrix that represents a board with the solution to the problem.
	 */
	public void writeFile(String file, ArrayList<String> valuesToWrite, boolean isTrace, String solution, int[][] board)
	{
		Date date = new Date();
		String dateAndHour = date.insertTraceName();
		String traceName = dateAndHour + ".txt";
		File afile = null;
		FileWriter out = null;
		PrintWriter pw = null;
		
		if(isTrace){
			afile = new File(traceName);
		}
		else{
			afile = new File(file);
		}
		
		try{
			if(isTrace){   //Code for trace output.
				out = new FileWriter(afile);
				pw = new PrintWriter(out);
				//Write the result to the file.
				for(int i = 0; i < valuesToWrite.size(); i++){
					pw.println(valuesToWrite.get(i));
				}
				//It shows the user that the typing was successful.
				String successTrace = "Information: Trace file successfully generated.";
				writeInLog(successTrace, true);
			}
			else{    //Code for the output file with the solution to the problem.
				if(file.contains(".txt")){ //It is a .txt file
					isTxtFile = true;
					if(!afile.exists() && solution == null){
						out = new FileWriter(afile);
						pw = new PrintWriter(out);
						//Write the result to the file.
						for(int i = 0; i < valuesToWrite.size(); i++){
							pw.println(valuesToWrite.get(i));
						}
						//It shows the user that the writing process was successful.
						String exito = "Information: File " + "\"" + file + "\"" + " successfully generated.";
						writeInLog(exito, true);
					}
					else if(solution != null){
						out = new FileWriter(afile, true);
						pw = new PrintWriter(out);
						
						pw.println();
						pw.println(solution);
						pw.println();
						if(board != null){	//If the problem has a solution.
							drawBoard(pw, board);
						}
						//It shows the user that the typing was successful.
						if(!isInformed){
							String success = "Information: File " + "\"" + file + "\"" + " successfully generated.";
							writeInLog(success, true);
							isInformed = true;
						}
					}
					else{ //The output file exists.
						String existFile = "Error: The file " + "\"" + file + "\"" + " already exists.";
						writeInLog(existFile, true);
						System.exit(-1);
					}
				}
				else{
					if(!isInformed){
						String noTxt = "Error: The output file must be a text file with the .txt extension";
						writeInLog(noTxt, true);
						isInformed = true;
					}
				}
			}
		}
		catch(Exception e){
			if(isTrace){
				String traceProblem = "Error: There was a problem generating the trace file.";
				writeInLog(traceProblem, true);
				System.exit(-1);
			}
			else{
				String outputProblem = "Error: There was a problem generating the output file: " + file;
				writeInLog(outputProblem, true);
				System.exit(-1);
			}
		}
		finally{
			try{
				//It ensure the file is closed
				if(out != null){
					out.close();
				}
			}
			catch(Exception e){
				if(isTrace){
					String closingTraceErr = "Error closing the trace file: There was a problem closing the trace file."; 
					writeInLog(closingTraceErr, true);
					System.exit(-1);
				}
				else{
					String closingOutputErr = "Error: There was a problem closing the output file: " + file;
					writeInLog(closingOutputErr, true);
					System.exit(-1);
				}
			}
		}
	}
	
	/**
	 * Check if a graphical output file exists and if so, return an error message and update the application's .log file.
	 * 
	 * @param fileName		The name of the graphic file.
	 */
	public void checkGraphicExistence(String fileName)
	{
		File file = new File(fileName);
		if(file.exists()){
			String fileExists = "Error: The graphic file " + "\"" + fileName + "\"" + " already exists.";
			writeInLog(fileExists, true);
			System.exit(-1);
		}
	}
	
	/**
	 * Draw the board with the queens placed in their position according to the solution to the problem.
	 * 
	 * @param pw		A PrintWriter object.
	 * @param board	    A matrix that represents the board.
	 */
	private void drawBoard(PrintWriter pw, int[][] board)
	{
		//Draw the chessboard with the queens in place.
		for(int i = board.length; i > 0; i--){
			drawDottedLine(pw, board);
			pw.println();
			pw.print(i);
			if(i < 10){
				pw.printf("%4s", "  |");
			}
			else{
				pw.printf("%3s", "  |");
			}
			for(int j = 0; j < board.length; j++){
				if(board[i-1][j] == 1){
					pw.print("  R  |");
				}
				else{
					if(i % 2 == 0){	//It is an even row
						if(j % 2 == 0){ //It is an even column
							pw.print("  *  |");
						}
						else{
							pw.print("     |");
						}
					}
					else{
						if(j % 2 == 0){  //It is an even column
							pw.print("     |");
						}
						else{
							pw.print("  *  |");
						}
					}
				}
			}
			pw.println();
		}
		//Draw the last horizontal line and the line of letters.
		drawDottedLine(pw, board);
		pw.println();
		drawLettersLine(pw, board);
		
	}
	
	/**
	 * Draw the horizontal lines of the board.
	 * 
	 * @param pw		A PrintWriter object.
	 * @param board	A matrix that represents the board.
	 */
	private void drawDottedLine(PrintWriter pw, int[][] board)
	{
		pw.printf("%4s", " ");
		for(int k = 0; k < 6 * board.length + 1; k++){
			pw.print("-");
		}
	}
	
	/**
	 * Draw the line of letters that represents the columns on the board.
	 * 
	 * @param pw		A PrintWriter object.
	 * @param board	    A matrix that represents the board.
	 */
	private void drawLettersLine(PrintWriter pw, int[][] board)
	{
		Character letter = 'a';				 //It corresponds to the first column of the board.
		int reference = letter.charValue(); //Save the letter value.
				
		pw.printf("%2s", " ");
		for(int i = 0; i < board.length; i++){
			pw.printf("%6c", letter);
			reference++;
			letter = new Character((char) reference);
		}
		pw.println();
	}
}
