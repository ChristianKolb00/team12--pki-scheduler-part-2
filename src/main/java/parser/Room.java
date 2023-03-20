package parser;

public class Room {
	private String building;
	private int roomNum;
	private TimeTable schedule;
	
	public Room(String b, int n)
	{
		building = b;
		roomNum = n;
		schedule = new TimeTable();
	}
}
