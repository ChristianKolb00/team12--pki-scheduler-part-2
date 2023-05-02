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

	<section class= "fieldOne" style="margin-top:10%;">
		<form action="homePage" method="post">
	<table >
	<tr>
		<td>Course: </td>
		<td><select style="font-size:16px;width:150px; height:60px; border-radius:20px; text-align:center;"
		class="selection" name = "course" id = "course" >
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
				<option class ="<%= allCourses[i]%>" value="<%= allCourses[i]%>"><%= allCourses[i]%></option> 
				<%}%>
			</select> </td>
	</tr>
	<tr><td>Max Enrollment Change: </td>
	<td><select class="selection" name ="selection" id = "selection" >
				<option value="24"> 24 </option>
				<option value="30"> 30 </option>
				<option value="40"> 40 </option>
				<option value="50"> 50 </option>
				<option value="60"> 60 </option>
			</select></td>
	</tr>
	<tr>
	<td><input style="font-size:20px; "type="submit" class="buttons" name= "submit" value="Change"></td></tr>
	</table>
	</form>
	</section>
</div>
</body>
<style>
h1{ text-align:center;}
.selection{font-size:16px; width:70px; height:40px; border-radius:20px; text-align:center;}
.buttons{ margin-left:50%;}
.buttons:hover, .selection:hover{ transform:scale(1.02);background:#525f93; color:white;}
</style>
</html>