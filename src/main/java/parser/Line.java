package parser;

import java.util.Scanner;
import java.util.regex.Pattern;

import util.helper;

/**
 * Line is an immutable storage for the parsed CSV, multiple lines make up a document
 * @author jwillson
 *
 */
public class Line {
	/**
	 * The original day pattern, time, and duration
	 */
	protected int oday, otime, oduration;
	/**
	 * An array that is the split row of the csv
	 */
	protected String[] line;

	/**
	 * Empty Constructor, only use is for Blocker object for time tables
	 */
	public Line()
	{
	}
	
	/**
	 * Constructor that takes the given row of a csv as a String, then splits it to each column piece. Fixes shift issue in class lines with empty first columns
	 * @param input - a row from the Document as a String
	 */
	public Line(String input)
	{
		line = new String[Constants.COL_COUNT];
		Scanner parser = new Scanner(input);
		//Fancy regex to ignore , within " " to handle fields containing ,
		parser.useDelimiter(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
		int i = 1;
		//Check for class line and shift
		if(parser.hasNext())
		{
			line[0]=parser.next();
			line[0]=line[0].replace("\"", "");
			//Set this as a class line if it has purely numeric content in CLSS ID, and shift
			if(line[0].matches("[0-9]+"))
			{
				line[1]=line[0];
				line[0]="";
				i++;
			}
		}
		while(parser.hasNext())
		{
			line[i]=parser.next();
			line[i]=line[i].replace("\"", "");
			i++;
		}
		for (int j = 0; j<Constants.COL_COUNT; j++) {
			if(line[j]==null)
				line[j]="";
		}
		parser.close();
	}
	
	/**
	 * Converts the Meeting Pattern field from a String to a multipiece int representation of the meeting pattern
	 */
	protected void parseOriginalDayTime()
	{
		
		if(line[Constants.MEET_PATT].contains("Not") || line[Constants.MEET_PATT].contains("Online"))
		{
			otime = -1;
			oduration = -1;
			oday = -1;
			return;
		}
		//Split off day into [0]
		String[] dayPiece = line[Constants.MEET_PATT].split(" ", 2);
		//Split off time into [0] and [1]
		String[] timePieces = dayPiece[1].split("-",2);
		//Set time and duration using parsed time
		otime = helper.parseTime(timePieces[0]);
		oduration = helper.parseTime(timePieces[1]) - otime;
		//Set day using parsed day
		oday = helper.parseDays(dayPiece[0]);
	}
	
	/**
	 * Converts the original day pattern into an array of days
	 * @return an array containing the numeric value of days this course is on
	 */
	protected int[] getOriginalDays()
	{
		if(this.oday <= 4)
			return new int[] {oday};
		else if (oday == 5)
			return new int[] {Constants.M, Constants.W};
		else if (oday == 6)
			return new int[] {Constants.T, Constants.T_TH};
		else
			return new int[] {};
	}
	
	
	/**
	 * Converts the String array of a row back into CSV format as a single String
	 * @return a csv formated row
	 */
	protected String backupOutput() {
		String ret = "";
		for (int i =0; i<line.length; i++)
		{
			ret = ret.concat(line[i] + ",");
		}
		return ret;
	}
	
	/**
	 * Standard output format for debugging
	 */
	@Override
	public String toString() {
		String ret = "";
		for (int i = 0; i<line.length-1;i++) {
			ret = ret.concat(line[i] + " ");
		}
		ret = ret.concat(line[line.length-1]);
		return ret;
	}
}