<%@ page import="parser.Aggregator" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home page</title>

<link rel="stylesheet" href= "style.css" >
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" >
</head>
<body>
<div>
	<h1>Welcome to the Home page where you can change Enrollments</h1>
	<section class= "fieldOne">
		<div class="container">
			<h4> Course: </h4>
			<select name = "course" id = "course" >
				<%
				String Path = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\BIOI1191.csv";
				String Path2 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\BMI1191.csv";
				String Path3 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\CIST_EMIT1191.csv";
				String Path4 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\CSCI1191.csv";
				String Path5 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\CYBR1191.csv";
				String Path6 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\ISQA1191.csv";
				String Path7 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\ITIN1191.csv";
				
				String[] AllFile = new String[]{Path, Path2, Path3, Path4, Path5, Path6, Path7};
				Aggregator tester = new Aggregator(AllFile);
				String[] allCourses = tester.getCourseNames();
				for(int i=0; i<allCourses.length;i++) {%>
				<option value="<%= allCourses[i]%>"><%= allCourses[i]%></option> 
				<%}%>
			</select> 
		</div>
		<div class="container">
			<h4> Max Enrollment Change: </h4>
			<select id = "field" >
				<option value="20"> 20 </option>
				<option value="25"> 25 </option>
				<option value="30"> 30 </option>
				<option value="40"> 40 </option>
				<option value="50"> 50 </option>
				<option value="60"> 60 </option>
				<option value="70"> 70 </option>
				<option value="80"> 80 </option>
			</select>
		</div>
		<div class="container">
			<h4 id="h4"></h4>
			<select id="s2" required hidden="hidden"></select>
		</div>
		<div class="container">
			<button id = "add" class= "buttons">Add to List</button>
		</div>
	</section>
	
	<section class="fieldOne">
		<div>
			<h4 id="list">List of Changes</h4>
			<p id="s3"></p>
		</div>
	</section>
	
	<section class="fieldTwo">
		<div class="container2">
			<button id = "clear" class= "buttons">Discard Last</button>
			<button id = "clearAll" class= "buttons">Discard All</button>
			<a href="/pkiClassroom/attemptChanges.jsp"><button class= "buttons">Attempt Changes</button></a>
		</div>
	</section>
</div>
<script src="script.js"></script>
</body>
<style>
	
</style>
</html>