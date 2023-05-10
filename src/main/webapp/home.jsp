<%@ page import="parser.Aggregator" %>
<%@ page import="util.Utils" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home page</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" >
</head>

<body>
<div>

	<h1>Welcome, Here is the Home page where course can change Enrollments</h1>
	<hr>
	<section class= "fieldOne" style="margin-top:10%;">
		<form action="homePage" method="post">
	<table >
	<tr>
		<td><Strong>Course: </Strong></td>
		<td><select style="font-size:16px;width:150px; height:60px; border-radius:20px; text-align:center;"
		class="selection" name = "course" id = "course" >
				<%
				String pathName = "C:\\Users\\cmlko_ki76ph6\\pkiClassroom\\team12--pki-scheduler-part-2\\src\\main\\java\\csvFiles\\";
				String Path = pathName+ "BIOI1191.csv";
				String Path2 = pathName+ "BMI1191.csv";
				String Path3 = pathName+ "CIST_EMIT1191.csv";
				String Path4 = pathName+ "CSCI1191.csv";
				String Path5 = pathName+ "CYBR1191.csv";
				String Path6 =pathName+ "ISQA1191.csv";
				String Path7 = pathName+ "ITIN1191.csv";
				
				String[] AllFile = new String[]{Path, Path2, Path3, Path4, Path5, Path6, Path7};
				Aggregator tester = new Aggregator(AllFile);
				Utils u = new Utils(tester);
				request.getSession().setAttribute("u",u);
				String[] allCourses = tester.getCourseNames();
				for(int i=0; i<allCourses.length;i++) {%>
				<option class ="<%= allCourses[i]%>" value="<%= allCourses[i]%>"><%= allCourses[i]%></option> 
				<%}%>
			</select> </td>
	</tr>
	<tr><td><Strong>Max Enrollment Change: </Strong></td>
	<td><select class="selection" name ="selection" id = "selection" >
				<option value="16"> 16 </option>
				<option value="24"> 24 </option>
				<option value="30"> 30 </option>
				<option value="40"> 40 </option>
				<option value="50"> 50 </option>
				<option value="60"> 60 </option>
		</select></td>
	</tr>
	<tr>
	<td><input style="font-size:20px;" type="submit" class="buttons" name= "submit" value="Change"/></td></tr>
	</table>
	</form>
	</section>
</div>
</body>
<style>
	body{
	background:#b4f9d6; }
	.fieldOne{
	padding:2rem;
	width:70%;
	height:auto;
	display:flex;
	flex-direction:column;
	align-items:center;
	justify-content:center;
	text-align:center;
	margin:auto 50%;
	border-radius:10px;
	transform:translate(-50%);
	border:2px solid #00b560;
	}
	.buttons{
	font-size:20px;
	margin:10px 30px;
	height:60px;
	width:120px;
	color:#fff;
	background:#a55a51;
	outline:none;
	border:none;
	border-radius:15px;
	cursor:pointer;
	}
	table{
	border-collapse:collapse;}
	tr{
	margin:1% auto;}
	th{
	padding:15px 30px;}
	td{
	font-size:20px;
	border-style:hidden;
	display:start;
	padding:10px 30px;
	border-top:1px solid #fff;}
	
	h1{ font-size: 3rem;text-align:center; color:#a55a51}
	hr{width: 60%; border-top: 2px solid red;}
	.selection{font-size:16px; width:70px; height:40px; border-radius:20px; text-align:center;}
	
	.buttons{ margin-left:50%;}
	
	.selection:hover, Strong:hover, h1:hover { color: #370e9b; }
	.buttons:hover { transform:scale(1.05);background:#370e9b; color:white;}
</style>
</html>