package parser;

public class Tester {
	public static void main(String[] args) {
		String[] paths = {"C:\\Users\\jcwga\\Downloads\\CSCI1191.csv"};//Replace this with local directory of a schedule csv
		Aggregator tester = new Aggregator(paths);
		System.out.println(tester.toString());
	}
}