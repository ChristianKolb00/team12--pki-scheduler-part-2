package parser;

public class Tester {
	public static void main(String[] args) {
		String[] paths = {"C:\\Users\\jcwga\\Downloads\\CSCI1191.csv"};//Replace this with local directory of a schedule csv
		Aggregator tester = new Aggregator(paths);
		Course[] temp = tester.getCourses();
		for (int i = 0; i < temp.length; i++)
			System.out.println(temp[i].toString() + "\n\n");
	}
}