package parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Document {
	private String file;
	protected ArrayList<Line> docLines;//First index correlates to index of files
	
	
	public Document(String path) {
		docLines = new ArrayList<Line>();
		String line = "";
		//Make a line object for every line in the document into a 2d array
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while ((line = br.readLine())!= null) {
				//To handle the \n in some comment fields
				if (!line.endsWith("\"") && !line.endsWith(",")) {
					line = line.concat("\n" + br.readLine());
				}
				docLines.add(new Line(line));
			}
			br.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(@SuppressWarnings("hiding") IOException e) {
			e.printStackTrace();
		}
		file = path;
	}
	
	@Override
	public String toString() {
		String ret = "";
		for(int i = 0; i<docLines.size(); i++) {
			ret = ret.concat(docLines.get(i).toString() + "\n");
		}
		return ret;
	}
	
	protected ArrayList<Line> getLines(){
		return docLines;
	}
}