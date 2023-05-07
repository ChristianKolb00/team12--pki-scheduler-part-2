package parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Document is a single csv file composed of Line objects with an immutable order for outputting
 * @author jwillson
 *
 */
public class Document {
	/**
	 * The file path to the file this Document represents
	 */
	private String file;
	/**
	 * All the rows that compose this CSV
	 */
	protected ArrayList<Line> docLines;//First index correlates to index of files
	
	/**
	 * Initialized the Document, using a BufferedReader to parse the file into separate lines. Each line is then checked for type, and then
	 * used to create a Line or Course object.
	 * @param path - the filepath to the source file
	 * @param rooms - an array of all valid Rooms
	 */
	public Document(String path, Room[] rooms) {
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
				//Is a course, this breaks due to Sheets
				if(line.startsWith(",\"") && Character.isDigit(line.charAt(2)))
					docLines.add(new Course(line,rooms));
				//Else is filler
				else
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
	
	/**
	 * Final call that triggers document to output change log, backup copy, and new file.
	 * @return true if successful
	 */
	public boolean output()
	{
		return outputChanges() && outputBackup() && outputNew();
		
	}
	
	//TODO: Implement
	private boolean outputChanges()
	{
		return true;
	}
	
	private boolean outputBackup()
	{
		return true;
	}
	
	private boolean outputNew()
	{
		return true;
	}
	
	/**
	 * Standard output format for debugging
	 */
	@Override
	public String toString() {
		String ret = "";
		for(int i = 0; i<docLines.size(); i++) {
			ret = ret.concat(docLines.get(i).toString() + "\n\n");
		}
		return ret;
	}
}