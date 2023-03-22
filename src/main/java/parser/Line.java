package parser;

import java.util.Scanner;

public class Line {
	protected boolean type;//Is this a class line(true) or filler line(false)?
	protected String[] line;
	protected String[] webOriginal;
	protected int aggEnrollOriginal;

	public Line(String input)
	{
		//Set this a non-class line
		type = false;
		line = new String[Constants.COL_COUNT];
		Scanner parser = new Scanner(input);
		//Fancy regex to ignore , within " " to handle fields containing ,
		parser.useDelimiter(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
		int i = 0;
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