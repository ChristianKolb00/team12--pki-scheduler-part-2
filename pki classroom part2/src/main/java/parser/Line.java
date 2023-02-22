package parser;

import java.util.Scanner;

public class Line {
	//Each line is size 38
	boolean type;//Is this a class line(true) or filler line(false)?
	String[] line;

	public Line(String input)
	{
		//Set this a non-class line
		type = false;
		line = new String[38];
		Scanner parser = new Scanner(input);
		//Fancy regex to ignore , within " " to handle fields containing ,
		parser.useDelimiter(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
		int i = 0;
		while(parser.hasNext())
		{
			line[i]=parser.next();
			i++;
		}
		parser.close();
		//Set this a class line if it has purely numeric content in CLSS ID(this excludes header line)
		if(line[1].contains("[a-zA-Z]+") == false )
			type=true;
	}
}
