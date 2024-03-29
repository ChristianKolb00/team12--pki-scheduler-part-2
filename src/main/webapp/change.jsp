<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Attemp Change</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" >

</head>

<body>
	<% if(session.getAttribute("course")==null)response.sendRedirect("home.jsp"); %>
	<section style="margin:5% auto; ">
		<h1>Here are some potential changes</h1>
		<hr> <br><br>
		<section class="fieldOne"> 
		<h2>Course Change: <%= session.getAttribute("course")  %> </h2>
		<p>Course Meeting Time:<%= session.getAttribute("courseTime")  %> </p>
		<p>Course Room Number: <%= session.getAttribute("roomNum")  %> </p>
		<p>
			Current Enrollment: <%= session.getAttribute("enroll") %>
			, Room Capacity: <%= session.getAttribute("MaxEnroll") %>
		 </p>
		 <p>Max Enrollment Wanted: <%= session.getAttribute("MaxEnrollWanted") %> </p>
		</section>
		
		<% String errorMessage = (String)request.getAttribute("errorMessage");
		if(errorMessage != null){%>
			<p style="text-align:center; color:red;"> <%=errorMessage %></p>
		<%  } %>
	</section>
	
	<form action="changesPage" method="post">
	<section class="fieldOne" >
	
		<table class="table">
		<tr>
			<th style="font-size:2rem;">Please choose a selection</th>
		</tr>
		<tr>
		<td style="display:flex; flex-direction:column;">
		<% 
		ArrayList<String> object = (ArrayList<String>) session.getAttribute("object"); 
		
		for(int i=0; i<object.size(); i++) {
				
			%>
			<div class="choice"><%= (i+1)+". "+object.get(i) %></div>
			<%
		}  
		
		ArrayList<String> object2 = (ArrayList<String>) session.getAttribute("object2");
		
		for(int j=0; j<object2.size(); j++) {
			%>
			<div class="choice2"><%= (j+1+object.size())+". "+object2.get(j) %></div>
			<%  
		}
		%> 
		
		
		</td>
		</tr>
		<tr><td>
		<select class="selection" name="selection" id = "selection" >
			<option value=""> select </option>
			<% for(int i=1; i<=object.size() +object2.size(); i++) {
			 %>
				<option value="<%=i%>"> <%=i%> </option>
			<% }%>
		</select>
		</td></tr>
		<tr><td>
		<input style="font-size:20px;" type="submit" class="buttons" 
			name= "submit" value="Confirm">
		</td></tr>
		
	</table>
	</section>
	</form>
	<section >
		<a href="/pkiClassroom/home.jsp"><button class="buttons"> Back </button></a>
	</section>
	
	
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
form {	width:70%;}

.buttons	{margin-top:50px;}

.selection	{
font-size:16px; width:90px; height:40px; 
border-radius:20px; text-align:center;}

.choice, .choice2{	
	height:50px; width:auto; margin:10px auto;font-size:16px; 
	border-color:#a55a51; border-radius:10px; padding:5px 10px; 
	background:none; color:black;
}
.choice:hover,.choice2:hover{ 
	transform:scale(1.02); border:none; background:#00b560; color:white;
}
h1,h2,h4{
	text-align:center;
} 
h1{ font-size: 3rem;text-align:center; color:#a55a51}
hr{margin-top:0; width: 60%; border-top: 2px solid red;}

h1:hover,h2:hover,h4:hover,p:hover, th:hover{color:#370e9b ;}

.buttons:hover{ transform:scale(1.02);background:#370e9b; color:white;}

fieldOne{width:60%; height:60%;align-items:center;text-alilgn:center;}

body{ display:flex;flex-direction:column;align-items:center;
	justify-content:center;text-align:center; over-flow:scroll;}
</style>

</html>