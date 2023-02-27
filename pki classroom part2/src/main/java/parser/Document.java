package parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Document {
	private String[] files;
	private Line[][] docLines;//First index correlates to index of files
	private Course[] courses;
	
	
	public Document(String[] paths) {
		for(int i = 0; i < paths.length; i++) {
			String path = paths[i];
			String line = "";
			//Make a line object for every line in the document into a 2d array
			try {
				BufferedReader br = new BufferedReader(new FileReader(path));
				int row=0;
				while ((line = br.readLine())!= null) {
					row++;
					docLines[i][row] = new Line(line);					
				}
				br.close();
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(@SuppressWarnings("hiding") IOException e) {
				e.printStackTrace();
			}
		}
		//Store list of paths
		files = paths;
		//Parse lines into a list of all courses
		this.parseCourses();
	}
	
	private void parseCourses() {
		ArrayList<Course> courses = new ArrayList<Course>();
		//Iterate through entire set of all lines
		for (int i = 0; i < docLines.length; i++) {
			for(int j = 0; j<docLines[i].length; j++) {
				//If line is a valid course, add it to courses
				if (docLines[i][j].type == true)
				{
					courses.add(new Course(docLines[i][j],i,j));
				}
			}
		}
		//Convert arrayList to array to return
		Course[] ret = new Course[courses.size()];
		this.courses = courses.toArray(ret);
	}
}
