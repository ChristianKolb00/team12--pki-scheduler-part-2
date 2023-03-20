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
				<option value="csci 4830">csci 4830</option>
				<option value="csci 3660">csci 3660</option>
				<option value="csci 4220">csci 4220</option>
				<option value="csci 3220">csci 3220</option>
			</select> 
		</div>
		<div class="container">
			<h4> Max Enrollment Change: </h4>
			<select id = "field" >
				<option value="20">20</option>
				<option value="25">25</option>
				<option value="30">30</option>
				<option value="40"> 40</option>
				<option value="50"> 50 </option>
				<option value="60"> 60 </option>
				<option value="60"> 70 </option>
				<option value="60"> 80 </option>
			</select>
			
		</div>
		<div class="container">
			<h4 id="h4"></h4>
			<select id="s2" required hidden="hidden"></select>
		</div>
		<div class="container">
			<button id = "add" class= "buttons">Add</button>
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