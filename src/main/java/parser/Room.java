package parser;

/**
 * Room specific version of time table, with needed attribute storage
 * @author jcwga
 *
 */

public class Room extends TimeTable{
	/*
	 * The name of the building the Room is in
	 */
	private String building;
	/*
	 * The alphanumeric designator of this Room
	 */
	private String roomNum;
	/*
	 * The person capacity of this Room
	 */
	private int cap;
	/*
	 * The computer capacity of this Room
	 */
	private int comp;
	
	/*
	 * General constructor to create the TimeTable and fill attributes
	 */
	public Room(String b, String n, int ca, int co)
	{
		super();
		building = b;
		roomNum = n;
		cap = ca;
		comp = co;
	}
	
	/*
	 * Specialized constructor that uses the Constant array structure to create a Room
	 */
	public Room(Object[] a)
	{
		super();
		building = "Peter Kiewit Institute";
		roomNum = (String) a[0];
		cap = (int) a[1];
		comp = (int) a[2];
	}
	
	//GETTERS
	/**
	 * Accessor for the building name of this Room
	 * @return building name of this Room
	 */
	public String getBuilding()
	{
		return building;
	}
	
	/**
	 * Accessor for the alphanumeric designator of this Room
	 * @return designator of this Room
	 */
	public String getRoomNumber()
	{
		return roomNum;
	}
	
	/**
	 * Accessor for the person capacity of this Room
	 * @return the number of people this Room can hold
	 */
	public int getCapacity()
	{
		return cap;
	}
	
	/**
	 * Accessor for the computer capacity of this Room
	 * @return the number of computers in this Room
	 */
	public int getComputerCapacity()
	{
		return comp - 1;
	}
}
