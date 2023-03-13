package parser;

public class Room {
	private String building;
	private int roomNum;
	private int[] schedule;
	
	public Room(String b, int n)
	{
		building = b;
		roomNum = n;
		schedule = new int[95];
		initializeSchedule();
	}
	
	//Schedule functions on chopping 24 hours day into 96 50 minute segemtsn
	private void initializeSchedule()
	{
		//-1 initialization for invalid class slots like 5am and 11pm etc
		//Front and end loop for times		
	}
}
