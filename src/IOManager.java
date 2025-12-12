/**
 * It represents an input/output manager.
 * 
 * @author  Roberto Castillejo Embid.
 * @version 1.0.
 */

public abstract class IOManager 
{
	private Logger log;		//Stores a Logger object.
	
	/**
	 * Default constructor for IOManager
	 */
	protected IOManager()
	{
		log = new Logger();
	}
	
	/**
	 * Constructor for GestorES in which a Logger object is passed as a parameter.
	 * 
	 * @param log	A Logger object.
	 */
	protected IOManager(Logger log)
	{
		this.log = log;
	}
	
	/**
	 * Returns a Logger object.
	 * 
	 * @return		A Logger object.
	 */
	public Logger getLog()
	{
		return log;
	}
	
	/**
	 * Replace a Logger object with another passed as a parameter.
	 * 
	 * @param nLog		A Logger object.
	 */
	public void setLog(Logger nLog)
	{
		log = nLog;
	}
	
	/**
	 * It displays a text passed as a parameter to the console and also adds it to the 
	 * application's .log file, which is responsible for recording all events that have 
	 * occurred within the application. If the output parameter is false, only the text 
	 * is written to the application's .log file.
	 * 
	 * @param text 	    The text that should be displayed on the console and written to the .log file
	 * @param stOutput	True if the text is to be displayed on standard output, false otherwise.
	 */
	public void writeInLog(String text, boolean stOutput)
	{
		if(stOutput){
			System.out.println();
			System.out.println(text);
		}
		log.writeInLog(text);
	}
}