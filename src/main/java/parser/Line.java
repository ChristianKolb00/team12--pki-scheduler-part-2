package parser;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Line {
	protected boolean type;//Is this a class line(true) or filler line(false)?
	protected int oday, otime, oduration;
	protected String[] line;
	protected int aggEnrollOriginal;

	public Line(String input)
	{
		//Set this a non-class line
		type = false;
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
				type=true;
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
			System.out.println(j + ": " + line[j] + "\n");
		}
		parser.close();
		//Set this a class line if it has purely numeric content in CLSS ID(this excludes header line)
		if(line[1].contains("[a-zA-Z]+") == false )
			type=true;
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