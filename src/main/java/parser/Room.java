package parser;

public class Room extends TimeTable{
	private String building;
	private String roomNum;
	private int cap, comp;
	private TimeTable schedule;
	
	//Generalized explicit constructor
	public Room(String b, String n, int ca, int co)
	{
		super();
		building = b;
		roomNum = n;
		cap = ca;
		comp = co;
	}
	
	//Special case constructor from constants
	public Room(Object[] a)
	{
		super();
		building = "PKI";
		roomNum = (String) a[0];
		cap = (int) a[1];
		comp = (int) a[2];
	}
	
	public String getBuilding()
	{
		return building;
	}
	
	public String getRoomNumber()
	{
		return roomNum;
	}
}
