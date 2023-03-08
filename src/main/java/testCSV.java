import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class testCSV {
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\CSCI1191.csv";
        int rowNumber = 2;
        int columnNumber = 1;
        String newData = "CLSS ID";
        //Update csv files
        //need row, column, and newData to update
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] row = lines.get(rowNumber).split(",\"");
        row[columnNumber] = newData+"\"";
        lines.set(rowNumber, String.join(",\"", row));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}