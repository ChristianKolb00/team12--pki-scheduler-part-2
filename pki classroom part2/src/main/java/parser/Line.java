package parser;

import java.util.Scanner;

public class Line {
	//Each line is size 38
	boolean type;//Is this a class line(true) or filler line(false)?
	String[] line;

	public Line(String input)
	{
		line = new String[38];
		Scanner parser = new Scanner(input);
		//Fancy regex to ignore , within " " to handle fields containing ,
		parser.useDelimiter(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
		int i = 0;//Count for if line is class or filler
		while(parser.hasNext())
		{
			line[i]=parser.next();
			//Special case for comma used in prof name
			if (i==15)
			{
				line[i] = line[i].concat(parser.next());
			}
			//Case of last field having multiple commas because professors are terrorists
			while(i==38 && parser.hasNext())
			{
				line[i] = line[i].concat(parser.next());
			}
			i++;
		}
		parser.close();
		if(i==38)
			type=true;
	}
}
