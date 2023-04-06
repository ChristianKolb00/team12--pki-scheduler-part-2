package util;
public class helper {
	//(10,12,16,16,16,16,16),(24,24,30,30,30,32),(40,40,40,40,40,42,42,50,56,58,60) 3 sections for room size
	/*
	 * parse time of the class to timeslot number with each 15 min = 1 timeslot
	 * Ex. 8:00pm 
	 * the output would be 48 (for 12hr in am) + 32 (for 8hr in pm) = 80
	 */
	public int parseTime(String timeRange)
	{
		int t = 0;
		if(timeRange.contains("pm"))
				t += 12 * 4;
		//Replace 12pm with 0 for easier hour shifting
		timeRange.replace("12","0");
		String[] minHour = timeRange.split(":",2);
		//If only hours, split give just hours, if mixed earlier split reduced and still functions
		t += Integer.parseInt(minHour[0].split("am|pm")[0]) * 4;
		//If there are minutes
		if(minHour.length > 1)
		{
			String min = minHour[1].split("am|pm")[0];
			//Divide minutes by 15 and round to nearest 15 minute so there is 10-20 minutes between all classes
			t += Math.round(Integer.parseInt(min)/15);
		}
		return t;
	}

	/*
	 * parse day of week to a number
	 * 
	 */
	public int parseDays(String days)
	{
		switch(days)
		{
			case "M": return 0;
			case "T": return 1;
			case "W": return 2;
			case "Th": return 3;
			case "F": return 4;
			case "MW": return 5;
			case "TTh": return 6;
			case "Sa": return -1;
			default: return -2;
		}
	}

	
}
