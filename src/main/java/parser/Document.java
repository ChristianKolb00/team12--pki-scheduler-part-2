package parser;

import java.io.BufferedReader;
import java.sql.Timestamp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
		boolean proceed = true;
		proceed = outputBackup();
		if(!proceed)
		{
			System.out.println("Rename failure");
			return proceed;
		}
		proceed = outputNew();
		if(!proceed)
		{
			System.out.println("Creation failure");
			return proceed;
		}
		proceed = outputChanges();
		return proceed;
	}
	
	/**
	 * Outputs changes to file to change log file, creates change log if it does not exist
	 * @return true if log appended successfully
	 */
	private boolean outputChanges()
	{
		File sourceFile = new File(file);
		File changeLog = new File(sourceFile.getParent()+"changelog.txt");
		try {
			if(!changeLog.exists())
				changeLog.createNewFile();
			FileWriter outting = new FileWriter(changeLog,true);
			for(int i = 0; i < docLines.size(); i++)
			{
				if(docLines.get(i) instanceof Course)
				{
					if(((Course)docLines.get(i)).changed)
					{
						outting.write(docLines.get(i).outputOriginal());
						outting.write("Changed to:\n");
						outting.write(docLines.get(i).output());
					}
				}
			}
			outting.close();
		}
		catch(Exception e){
			System.out.println(e);
			return false;
		}
		
		return true;
	}
	
	/**
	 * Creates a backup file using the original Line storage
	 * @return true if rename is successful
	 */
	private boolean outputBackup()
	{
		/*
		File origFile = new File(file);
		File backupFile = new File(file.substring(0,file.length()-4) + "_backup_" + Constants.backupTime + file.substring(file.length()-4));
		return origFile.renameTo(backupFile);Fix this it does not work for some reason
		*/
		//Workaround
		File backupFile = new File(file.substring(0,file.length()-4) + "_backup_" + Constants.backupTime + file.substring(file.length()-4));
		try
		{
			backupFile.createNewFile();
			FileWriter outting = new FileWriter(backupFile,false);
			for(int i = 0; i < docLines.size(); i++)
			{
					outting.write(docLines.get(i).outputOriginal());
			}
			outting.close();
		}
		catch(Exception e){
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	/**
	 * Overwrites original file with the new schedule
	 * @return true if the write is successful
	 */
	private boolean outputNew()
	{
		File origFile = new File(file);
		try
		{
			FileWriter outting = new FileWriter(origFile,false);
			for (int i = 0; i < docLines.size(); i++)
				outting.write(docLines.get(i).output());
			outting.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
			return false;
		}
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