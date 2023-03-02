package parser;

import java.util.ArrayList;

public class Aggregator {
	protected Document[] documents;
	public Aggregator(String[] paths)
	{
		documents = new Document[paths.length];
		for(int i = 0; i < paths.length; i++) {
			documents[i] = new Document(paths[i]);
		}
	}
	private Course[] parseCourses() {
		ArrayList<Course> courses = new ArrayList<Course>();
		//Iterate through entire set of all lines
		for (int i = 0; i < documents.length; i++) {
			for(int j = 0; j<documents[i].docLines.size(); j++) {
				//If line is a valid course, add it to courses
				if (documents[i].docLines.get(j).type == true)
				{
					courses.add(new Course(documents[i].docLines.get(j),i,j));
				}
			}
		}
		//Convert arrayList to array to return
		Course[] ret = new Course[courses.size()];
		courses.toArray(ret);
		return ret;
	}
	
	@Override
	public String toString()
	{
		String ret ="";
		for(int i = 0; i< documents.length; i++) {
			ret = ret.concat(documents[i].toString() + "\n\n");
		}
		return ret;
	}
}