import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Represents a date.
 * 
 * @author  Roberto Castillejo Embid.
 * @version 1.0.
 */
public class Date 
{
	private LocalDateTime now;	    //Stores the current date.
	private String dayOfWeek;		//Stores the day of the week.
	private String day;				//Stores the day of the month.
	private String monthOfYear;		//Stores the month of the year.
	private String year;			//Stores the year.
	private String hour;			//Stores the hour.
	private String minutes;			//Stores the minutes.
	private String seconds;		    //Stores the seconds.
	
	
	/**
	 * Default Date Constructor.
	 */
	public Date()
	{
		now = LocalDateTime.now();
		DayOfWeek dayW = now.getDayOfWeek();
		Month monthY = now.getMonth();			
		dayOfWeek = dayW.getDisplayName(TextStyle.SHORT, Locale.getDefault());
		day = formatInputValue(now.getDayOfMonth());
		monthOfYear = monthY.getDisplayName(TextStyle.SHORT, Locale.getDefault());
		year = Integer.toString(now.getYear());
		hour = formatInputValue(now.getHour());
		minutes = formatInputValue(now.getMinute());
		seconds = formatInputValue(now.getSecond());
	}
	
	/**
	 * Returns a LocalDateTime object with the current date.
	 * 
	 * @return 	A LocalDateTime object with the current date.
	 */
	public LocalDateTime getNow()
	{
		return now;
	}
	
	/**
	 * Returns the day of the week.
	 * 
	 * @return	The day of the week.
	 */
	public String getDayOfWeek()
	{
		return dayOfWeek;
	}
	
	/**
	 * Returns the day of the month.
	 * 
	 * @return	The day of the month.
	 */
	public String getDay()
	{
		return day;
	}
	
	/**
	 * Returns the month of the year.
	 * 
	 * @return	the month of the year.
	 */
	public String getMonthOfYear()
	{
		return monthOfYear;
	}
	
	/**
	 * Returns the year.
	 * 
	 * @return	The year.
	 */
	public String getYear()
	{
		return year;
	}
	
	/**
	 * Returns the hour.
	 * 
	 * @return	The hour.
	 */
	public String getHour()
	{
		return hour;
	}
	
	/**
	 * Returns the minutes.
	 * 
	 * @return	The minutes.
	 */
	public String getMinutes()
	{
		return minutes;
	}
	
	/**
	 * Returns the seconds.
	 * 
	 * @return	The seconds.
	 */
	public String getSeconds()
	{
		return seconds;
	}
	
	/**
	 * Returns the current date, changing it to another passed by year parameters.
	 * 
	 * @param now		The new date.
	 */
	public void setNow(LocalDateTime now)
	{
		this.now = now;
	}
	
	/**
	 * Change the day of the week to another. A date is passed as a parameter.
	 * 
	 * @param now		The new date.
	 */
	public void setDayOfWeek(LocalDateTime now)
	{
		DayOfWeek dayW = now.getDayOfWeek();
		dayOfWeek = dayW.getDisplayName(TextStyle.SHORT, Locale.getDefault());
	}
	
	/**
	 * Change the day of the month to another. A date is passed as a parameter..
	 * 
	 * @param now		The new date.
	 */
	public void setDay(LocalDateTime now)
	{
		day = formatInputValue(now.getDayOfMonth());
	}
	
	/**
	 * Change the month of the year to another. A date is passed as a parameter.
	 * 
	 * @param now		The new date.
	 */
	public void setMonthOfYear(LocalDateTime now)
	{
		Month monthY = now.getMonth();
		monthOfYear = monthY.getDisplayName(TextStyle.SHORT, Locale.getDefault());
	}
	
	/**
	 * Change the year to another. A date is passed as a parameter.
	 * 
	 * @param ahora		The new date.
	 */
	public void setYear(LocalDateTime now)
	{
		year = Integer.toString(now.getYear());
	}
	
	/**
	 * Change the time to another. A date is passed as a parameter.
	 * 
	 * @param now		The new date.
	 */
	public void setHour(LocalDateTime now)
	{
		hour = formatInputValue(now.getHour());
	}
	
	/**
	 * Change the minutes to another number. A date is passed as a parameter.
	 * 
	 * @param now		The new date.
	 */
	public void setMinutes(LocalDateTime now)
	{
		minutes = formatInputValue(now.getMinute());
	}
	
	/**
	 * Change the seconds to another value. A date is passed as a parameter.
	 * 
	 * @param now		The new date.
	 */
	public void setSeconds(LocalDateTime now)
	{
		seconds = formatInputValue(now.getSecond());
	}
	
	/**
	 * Returns the current date in the specified format. An example of the format would be: "Mon Dec 12 2016 22:17:43".
	 * 
	 * @return		The current date in a specific format.
	 */
	public String insertDate()
	{
		return dayOfWeek + " " +  monthOfYear + " " + day + " " + year + " " + hour + ":" + minutes + ":" + seconds;
	}
	
	/**
	 * Returns the current date as the name for the application trace file. An example of the format would be: "mon_dec_12_2016 22_17_43" trace file.
	 * 
	 * @return		The current date in a format specific to the trace.
	 */
	public String insertTraceName()
	{
		return dayOfWeek + "_" + monthOfYear + "_" + day + "_" + year + " " + hour + "_" + minutes + "_" + seconds;
	}
	
	/**
	 * Returns a hashCode value for Date.
	 * 
	 * @return 	A hashCode value for Date.
	 */
	@Override
	public int hashCode()
	{
		return 31 * ((now == null) ? 0 : now.hashCode()) + 31 * ((dayOfWeek == null) ? 0 : dayOfWeek.hashCode()) + 
				31 * ((day == null) ? 0 : day.hashCode()) + 31 * ((monthOfYear == null) ? 0 : monthOfYear.hashCode()) +
				31 * ((year == null) ? 0 : year.hashCode()) + 31 * ((hour == null) ? 0 : hour.hashCode()) + 
				31 * ((minutes == null) ? 0 : minutes.hashCode()) + 31 *((seconds == null) ? 0 : seconds.hashCode());
	}
	
	/**
	 * Returns true if two Date objects are equal.
	 * 
	 * @param o 	A Date object.
	 * @return 		True if both objects are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object o)
	{
		if(o == this){
			return true;
		}
		
		if(o == null)
		{
			return false;
		}
		
		if(!(o instanceof Date)){
			return false;
		}
		else{
			Date f = (Date) o;
			return f.now.equals(now) && f.dayOfWeek.equals(dayOfWeek) && f.day.equals(day) && f.monthOfYear.equals(monthOfYear) &&
					f.year.equals(year) && f.hour.equals(hour) && f.minutes.equals(minutes) && f.seconds.equals(seconds);
		}
	}
	
	/**
	 * Returns a String representation of Date.
	 * 
	 * @return 	A String representation of Date.
	 */
	@Override
	public String toString()
	{
		StringBuffer buff = new StringBuffer();
		
		buff.append("Date: [");
		buff.append(now);
		buff.append(", ");
		buff.append(dayOfWeek);
		buff.append(", ");
		buff.append(monthOfYear);
		buff.append(", ");
		buff.append(day);
		buff.append(", ");
		buff.append(year);
		buff.append(", ");
		buff.append(hour);
		buff.append(", ");
		buff.append(minutes);
		buff.append(", ");
		buff.append(seconds);
		buff.append("]");
		
		return buff.toString();	
	}
	
	/**
	 * Formats an integer value corresponding to hours, minutes, or seconds, and returns that formatted value as a String.
	 * 
	 * @param value		An integer value. The value is hours, minutes, or seconds.
	 * @return			A String with the formatted value.
	 */
	private String formatInputValue(int value)
	{
		StringBuilder sb = new StringBuilder();
		
		//If the value only has one digit, a 0 is added in front of it.
		if(value < 10){
			sb.append(0);
		}
		
		sb.append(value);
		
		return sb.toString();
	}
}
