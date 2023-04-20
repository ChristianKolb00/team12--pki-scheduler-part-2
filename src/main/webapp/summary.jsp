<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Summary Page</title>
<link rel="stylesheet" href= "style.css" >
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" >

</head>
<body>
	<h1>This is the Summary Page!</h1>
	<section class="fieldOne fieldThree" style="margin-top:10%;">
	
		<table class="table">
		
		<tr><td>
			<Strong>Your Selection Was
			</Strong> 
			
		</td></tr>
		
		<tr><td><%= session.getAttribute("selection")  %></td></tr>
		
		<tr><td>
			<Strong>Final Change:</Strong> 
		</td></tr>
		
		
		<tr><td><%= session.getAttribute("result")  %></td></tr>
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
h1{
	margin-top:3%;
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
</style>
</html>