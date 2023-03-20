<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href= "style.css" >
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" >

</head>

<body>
<div>
	<h1>Welcome to the Home page where you can change Course Conflicts</h1>
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
			<h4> Field To Change: </h4>
			<select id = "field" >
				<option value="select">Select</option>
				<option value="Room">Room</option>
				<option value="MaxEnroll">Max Enrollment</option>
				<option value="Enroll"> Current Enrollment</option>
				<option value="Time"> Time </option>
			</select>
			
		</div>
		<div class="container">
			<h4 id="h4"></h4>
			<select id="s2" required hidden="hidden"></select>
		</div>
		<div class="container">
			<button id = "add" class= "buttons">Add</button>
			<button class= "buttons">Clear</button>
		</div>
	</section>
	
	<section class="fieldOne">
		<div>
			<h4>List of Changes</h4>
			<p> blah blah blah changes </p>
			<p> blah blah blah changes </p>
			<p> blah blah blah changes </p>
			<p id="s3"> blah blah blah changes </p>
		</div>
	</section>
	
	<section class="fieldTwo">
		<div class="container2">
			<a href="/pkiClassroom/home.jsp"><button class= "buttons">Discard Selected</button></a>
			<a href="/pkiClassroom/home.jsp"><button class= "buttons">Discard All</button></a>
			<a href="/pkiClassroom/attemptChanges.jsp"><button class= "buttons">Attempt Changes</button></a>
		</div>
	</section>
</div>
<script src="script.js"></script>
</body>
<style>
	
</style>
</html>