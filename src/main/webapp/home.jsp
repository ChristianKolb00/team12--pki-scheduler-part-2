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
	<section >
	<form class = "container">
		<table class = "container2">
			<tr ><td> Course: 
			<select name = "CSCI XXXX" id = "CSCI XXXX" required>
				<option>csci 4830</option>
				<option>csci 3660</option>
				<option>csci 4220</option>
				<option>csci 3220></option>
			</select> 
			</td></tr> 
			
			<tr><td> Room: 
			<select name = "PKI XXX" id = "PKI XXX" required>
				<option> PKI 123</option>
				<option> PKI 234</option>
				<option> PKI 132</option>
				<option> PKI 213</option>
			</select> 
			</td></tr> 
			
			<tr><td> Max Enrollment: 
			<select name = "mEnrollment" id = "mEnrollment" required>
				<option>25</option>
				<option>30</option>
				<option>35</option>
				<option>40</option>
				<option>50</option>
				<option>60</option>
			</select> 
			</td></tr> 
			
			<tr><td> Current Enrollment: 
			<select name = "enrollment" id = "enrollment" required>
				<option>25</option>
				<option>30</option>
				<option>35</option>
				<option>40</option>
				<option>50</option>
				<option>60</option>
			</select> 
			</td></tr> 
			<tr><td>
			<button class= "buttons">Add</button>
			<button class= "buttons">Clear</button>
			</td></tr>
		</table>
	</form>
		
	</section>
	
	
	
	<section class="container">
		<div class="container2">
			<p>List of Changes</p>
			<div>
			<p> blah blah blah changes </p>
			<p> blah blah blah changes </p>
			<p> blah blah blah changes </p>
			<p> blah blah blah changes </p>
			</div>
			<div class="container3">
			<a href="/pkiClassroom/attemptChanges.jsp"><button class= "buttons">Attempt Changes</button></a>
			<a href="/pkiClassroom/home.jsp"><button class= "buttons">Discard Selected</button></a>
			<a href="/pkiClassroom/home.jsp"><button class= "buttons">Discard All</button></a>
			</div>
		</div>
			
		
	</section>
</div>
	

</body>
	
<style>
	*{
		background:gray;
		color:#fff;
  	 	margin:0;
   	 	padding:0;
    	box-sizing:border-box;
    	text-decoration:none;
		}
	.container {
		justify-content:center;
		align-items: center;
		height: auto;
		width:600px;
		margin-left: 50%; margin-top: 5%;
		border: 2px solid #fff;
		padding: 1% 1%;
		border-radius:6px;
		display: block;
		transform: translate(-50%);
		}
	.container2 {
		margin-left: 50%; 
		transform: translate(-50%);
		margin-top: 2%;
		align-items: center;
		color:#fff;
		}
	.container3{
		display:flex;
		}
	.buttons {
		color:#fff;
		background:#787f86;
		cursor:pointer;
		border-radius:5px;
		margin:10px;
		height: 35px;
		width: 80px;
		}
	.buttons:hover {
		background:#fff;
		color:#3a3ac5;
	}
</style>
</html>