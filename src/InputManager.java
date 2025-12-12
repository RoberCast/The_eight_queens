import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class represents the program's input file manager. The input file will be the application's 
 * help, which consists of a .txt file.
 * 
 * @author  Roberto Castillejo Embid.
 * @version 1.0.
 */
public class InputManager extends IOManager
{
	private String helpFile;		//Save the path to the help file.
	
	/**
	 * Default constructor of InputManager.
	 */
	public InputManager()
	{
		super();
		helpFile = "help/help.txt";
	}
	
	/**
	 * ManagerEntrada constructor in which the path of a help file is passed as a parameter.
	 * 
	 * @param helpFile	The path to a help file.
	 */
	public InputManager(String helpFile)
	{
		super();
		this.helpFile = helpFile;
	}
		
	/**
	 * Returns the path to the help file.
	 * 
	 * @return		The path to the help file.
	 */
	public String getHelpFile()
	{
		return helpFile;
	}
	
	/**
	 * Change the path of the help file to another one passed by parameter.
	 * 
	 * @param newPath		The new path to the help file.
	 */
	public void setHelpFile(String newPath)
	{
		helpFile = newPath;
	}

	/**
	 * Print the application help.
	 * 
	 * @param syntaxError		True if it is a syntax error, false otherwise.
	 */
	public void printHelp(boolean syntaxError)
	{
		if(syntaxError){
			String errSintaxis = "Error: The syntax is incorrect.";
			writeInLog(errSintaxis, true);
		}
		
		readFile(helpFile);
	}
	
	
	/**
	 * Input file reader. The file path is passed to it as a parameter.
	 * 
	 * @param file	The input file path.
	 */
	private void readFile(String file)
	{		
		BufferedReader bfr = null;
		
		try{
			InputStream is = getClass().getResourceAsStream(file);
			bfr = new BufferedReader(new InputStreamReader(is));

			String line = bfr.readLine();
			
			//Read the file.
			while(line != null){
				System.out.println(line);
				//It update the line in the file.
				line = bfr.readLine();
			}
		}
		catch(IOException e){
			String fileError = "Error: There was a problem reading the file " + helpFile + ".";
			writeInLog(fileError, true);
		}
		finally{
			//Close the file.
			try{
				if(bfr != null){
					bfr.close();
				}
			}catch(IOException e){
				String closeError = "Error: There was a problem closing the file " + helpFile + ".";
				writeInLog(closeError, true);
			}
		}
	}
}
