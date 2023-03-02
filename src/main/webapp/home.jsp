<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div>
	<section >
	<form class = "container">
		<table>
			<tr class = "container2"><td> Course: 
			<select name = "CSCI XXXX" id = "CSCI XXXX" required>
				<option>csci 4830</option>
				<option>csci 3660</option>
				<option>csci 4220</option>
				<option>csci 3220></option>
			</select> 
			</td></tr> 
			
			<tr class = "container2"><td> Room: 
			<select name = "PKI XXX" id = "PKI XXX" required>
				<option> PKI 123</option>
				<option> PKI 234</option>
				<option> PKI 132</option>
				<option> PKI 213</option>
			</select> 
			</td></tr> 
			
			<tr class = "container2"><td> Max Enrollment: 
			<select name = "mEnrollment" id = "mEnrollment" required>
				<option>25</option>
				<option>30</option>
				<option>35</option>
				<option>40</option>
				<option>50</option>
				<option>60</option>
			</select> 
			</td></tr> 
			
			<tr class = "container2"><td> Current Enrollment: 
			<select name = "enrollment" id = "enrollment" required>
				<option>25</option>
				<option>30</option>
				<option>35</option>
				<option>40</option>
				<option>50</option>
				<option>60</option>
			</select> 
			</td></tr> 
			<tr class = "container2"><td>
			<button class= "buttons">Add</button>
			<button class= "buttons">Clear</button>
			</td></tr>
		</table>
	</form>
		
	</section>
	
	
	
	<section class="container3">
		<div>
			<p>List of Changes</p>
		</div>
		<div>
			<p> blah blah blah changes </p>
			<p> blah blah blah changes </p>
			<p> blah blah blah changes </p>
			<p> blah blah blah changes </p>
		</div>
		<div>
			<a href="/pkiClassroom/attemptChanges.jsp"><button>Attempt Changes</button></a>
			<a href="/pkiClassroom/home.jsp"><button>Discard Selected</button></a>
			<a href="/pkiClassroom/home.jsp"><button>Discard All</button></a>
		</div>
		
	</section>
</div>
	

</body>
<style>
	body{
		background-color: gray;
		}
	.container {
		height: 200px;
		width: 300px;
		margin-left: 2%; margin-top: 2%;
		padding: 1% 1%;
		border: 2px solid black;
		display: flex;
		justify-content:center;
		align-items: center;
		height:30%;
		}
	.container2 {
		margin-top: 1%;
		}
	.buttons {
		margin-top: 2%;
		height: 30px;
		width: 70px;
		}
	.rightSide {
		margin: 2% 2% 2% 2%;
		}
	.container3 {
		heigth: 200px;
		width: 400px;
		margin-left: 2%; margin-top: 2%;
		border: 2px solid black;
		}
</style>
</html>