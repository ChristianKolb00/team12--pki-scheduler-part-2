package PKI_Testing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * pki_test conducts UI testing using Selenium on PKI Scheduler Part 2 project.
 * 
 * @author arahmanzai
 */

public class pki_test {

	private static final String LOG_FILE_PATH = "C:\\Users\\abdulghafarrahmanz\\Desktop\\pki_new\\team12--pki-scheduler-part-2\\src\\main\\java\\testing\\logfile.txt";

	private static ChromeOptions ops;
	private static WebDriver driver;
	private static String WEB_APP_ADDRESS = "http://localhost:8080/team12--pki-scheduler-part-2-christian/home.jsp";
	private static int SLEEP_TIME = 0;

	private static String CourseDropDownId = "course";
	private static String MaxDropDownId = "selection";
	private static String Course_Title_1 = "CIST 1300-001";
	private static String MaxNum = "30";

	private static String COURSE_ROOM_NUM_XPATH = "/html/body/section[1]/section/p[2]";
	private static String FINAL_CHANGE_ROOM_NUM = "/html/body/section[2]/table/tbody/tr[5]/td";
	private static String SCREEN_TITLE_XPATH = "/html/body/div/h1";

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\abdulghafarrahmanz\\Documents\\chromedriver_win32\\chromedriver.exe");

		// Chrome version 111 has some issues with Selenium, below code can fix it
		ops = new ChromeOptions();
		driver = new ChromeDriver(ops.addArguments("--remote-allow-origins=*"));
		driver.get(WEB_APP_ADDRESS);

		driver.manage().window().maximize();
		Thread.sleep(SLEEP_TIME);

		// Select a course from Course dropdown menu
		selectCourse(Course_Title_1);
		selectMaxEnrollment(MaxNum);
		clickChange();
		String currentRoomNum = getCourseRoomNum();

		scrollDown();
		selectRoomOption("20");
		clickConfirm();

		String oldRoomNum = getFinalChangeOldRoomNum();
		assert oldRoomNum == currentRoomNum;
		String newRoom = getFinalChangeNewRoomNum();
		scrollDown();
		clickUpdateCSV();

		// -- Confirm if the changes were made successfully we need to go back and
		// search
		// -- for the exact course and verify the changes.
		selectCourse(Course_Title_1);
		selectMaxEnrollment(MaxNum);
		clickChange();
		assert getCourseRoomNum() == newRoom;

		scrollDown();
		clickBack();
		assert verifyScreenTitle("Welcome, Here is the Home page where course can change Enrollments");

	}

	private static void selectCourse(String courseTitle) throws InterruptedException {
		selectOption(CourseDropDownId).selectByValue(courseTitle);
		Thread.sleep(SLEEP_TIME);
		logMessage("Success: Selected: " + courseTitle);
	}

	private static void selectMaxEnrollment(String maxNum) throws InterruptedException {
		selectOption(MaxDropDownId).selectByValue(maxNum);
		logMessage("Success: Selected: " + maxNum);
		Thread.sleep(SLEEP_TIME);
	}

	private static void clickChange() throws InterruptedException {
		clickButton("Change");
	}

	private static void clickConfirm() throws InterruptedException {
		clickButton("Confirm");
	}

	private static void clickBack() throws InterruptedException {
		WebElement button = driver.findElement(By.xpath("/html/body/section[2]/a/button"));
		button.click();
		logMessage("Success: Clicked: Back");
		Thread.sleep(SLEEP_TIME);
	}

	private static void clickUpdateCSV() throws InterruptedException {
		clickButton("Updata CSV Files");

	}

	private static void selectRoomOption(String selectNum) throws InterruptedException {
		selectOption("selection").selectByValue(selectNum);
		Thread.sleep(SLEEP_TIME);
	}

	private static String getCourseRoomNum() {
		String roomNum = getText(COURSE_ROOM_NUM_XPATH);
		roomNum = roomNum.substring(20);
		logMessage("Success: Retrieved -- Course Room Number: " + roomNum);
		return roomNum;
	}

	private static String getFinalChangeNewRoomNum() {
		String newRoomNum = getText(FINAL_CHANGE_ROOM_NUM);
		newRoomNum = newRoomNum.substring(50, 54);
		logMessage("Success: Retrieved -- Final Change new room: " + newRoomNum);
		return newRoomNum;
	}

	private static String getFinalChangeOldRoomNum() {
		String oldRoomNum = getText(FINAL_CHANGE_ROOM_NUM);
		oldRoomNum = oldRoomNum.substring(105, 109);
		logMessage("Success: Retrieved -- Final Change old room: " + oldRoomNum);
		return oldRoomNum;
	}

	private static boolean verifyScreenTitle(String expectedScreenTitle) {
		String currentScreenTitle = getText(SCREEN_TITLE_XPATH);
		if (currentScreenTitle == expectedScreenTitle) {
			return true;
		}
		return false;
	}

	private static void clickButton(String buttonName) throws InterruptedException {
		// find and click on a button
		WebElement button = driver.findElement(By.xpath("//input[@value='" + buttonName + "']"));
		button.click();
		logMessage("Success: Clicked: " + buttonName);
		Thread.sleep(SLEEP_TIME);
	}

	private static Select selectOption(String DropDownId) {
		// find the dropdown element
		WebElement dropdown = driver.findElement(By.id(DropDownId));
		// create a new Select object
		Select select = new Select(dropdown);
		return select;
	}

	private static String getText(String element_xpath) {
		WebElement element = driver.findElement(By.xpath(element_xpath));
		String text = element.getText();
		return text;
	}

	private static void scrollDown() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		logMessage("Success: Scrolled down");
		Thread.sleep(SLEEP_TIME);
	}

	private static void logMessage(String message) {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String logMessage = timestamp + " - " + message;

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
			writer.write(logMessage);
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
