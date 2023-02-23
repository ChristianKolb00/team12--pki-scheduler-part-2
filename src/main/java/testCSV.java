
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class testCSV {
	public static void main(String[] args) {
		String Path = "C:\\Users\\cmlko\\Downloads\\Spring-2019-Files\\2019 files\\CSCI1191.csv";
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(Path));
			int row=0;
			while ((line = br.readLine())!= null) {
				row++;
				String[] values = line.split(",");
				//if(values.length >=38) {
						System.out.println(row + "row\t"  + values[0]);
				//}
					
				
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(@SuppressWarnings("hiding") IOException e) {
			e.printStackTrace();
		}
	}
}
