package parser;

public class Room extends TimeTable{
	private String building;
	private int roomNum;
	private int cap, comp;
	private TimeTable schedule;
	
	//Generalized explicit constructor
	public Room(String b, int n, int ca, int co)
	{
		super();
		building = b;
		roomNum = n;
		cap = ca;
		comp = co;
	}
	
	//Special case constructor from constants
	public Room(int[] a)
	{
		super();
		building = "PKI";
		roomNum = a[0];
		cap = a[1];
		comp = a[2];
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
