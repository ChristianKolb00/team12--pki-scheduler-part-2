package parser;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Line is an immutable storage for the parsed CSV, multiple lines make up a document
 * @author jwillson
 *
 */
public class Line {
	protected int oday, otime, oduration;
	protected String[] line;
	protected int aggEnrollOriginal;

	
	public Line()
	{
	}
	
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