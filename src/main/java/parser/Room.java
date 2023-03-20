package parser;

public class Room extends TimeTable{
	private String building;
	private int roomNum;
	private TimeTable schedule;
	
	public Room(String b, int n)
	{
		super();
		building = b;
		roomNum = n;
	}
	
	public String getBuilding()
	{
		return building;
	}
	
	public int getRoomNumber()
	{
		return roomNum;
	}
}
