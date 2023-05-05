<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Summary Page</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" >

</head>
<body>
	<% if(session.getAttribute("course")==null)response.sendRedirect("home.jsp"); %>
	<h1>This is the Summary Page!</h1>
		<h2>Course: <%= session.getAttribute("course")  %> </h2>
		<h4>Course Meeting Time:<%= session.getAttribute("courseTime")  %> </h4>
		<h4 >Course Room Number: <%= session.getAttribute("roomNum")  %> </h4>
		<h4 >Max Enrollment Change: <%= session.getAttribute("field")  %> </h4>
	<section class="fieldOne" style="margin-top:5%;">
	
		<table class="table">
		
		<tr><td>
			<Strong>Your Selection Was
			</Strong> 
			
		</td></tr>
		
		<tr><td><%= session.getAttribute("selection")  %></td></tr>
		<tr><td><%= session.getAttribute("result")  %></td></tr>
		<tr><td>
			<Strong>Final Change:</Strong> 
		</td></tr>
		
		<tr><td><%= session.getAttribute("feedback")  %></td></tr>
		</table>
	</section>
	
	<section class="fieldTwo">
	
		<div class="container2">
		
			<a href="/pkiClassroom/home.jsp">
				<button class="buttons">Back</button>
			</a>
			
			<button class="buttons">Download CSV</button>
		
			<button class="buttons large"> Download per room schedule</button>
		
		</div>
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
h1, h2, h4{
	margin-top:1%;
	text-align:center;}
.fieldTwo{
	margin-top:3%;
	display:flex;
	flex-direction:row;
	align-items:center;
	justify-content:center;
	text-align:center;
	}
.large{
	width:150px;}
h1:hover{color: #370e9b; }
td:hover{ transform:scale(1.05); color:#00b560;
}
.buttons:hover{ transform:scale(1.05);background:#370e9b; color:white;}


</style>
</html>