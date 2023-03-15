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
	<h1>This is the Summary Page!</h1>
	<section class="fieldOne fieldThree">
		<table class="table">
		<tr>
			<th>Course</th>
			<th>Old Classroom</th>
			<th>New Classroom</th>
			<th>Old Time</th>
			<th>New Time</th>
			<th>Possible Max Enroll</th>
		</tr>
		<tr>
			<td>CSCI xxxx</td>
			<td>PKI 246</td>
			<td>PKI 260</td>
			<td>TR: 3-4 PM</td>
			<td>MW: 6-7 PM</td>
			<td>41</td>
		</tr>
		<tr>
			<td>BIO xxxx</td>
			<td>PKI 260</td>
			<td>PKI 246</td>
			<td>MW: 6-7 PM</td>
			<td>TR: 3-4 PM</td>
			<td>28</td>
		</tr>
		<tr>
			<td>CSCI xxyy</td>
			<td>Online</td>
			<td>PKI 250</td>
			<td> - </td>
			<td>T: 6-8:30 PM</td>
			<td> 26 </td>
		</tr>
		</table>
	</section>
	
	<section class="fieldTwo">
		<div class="container2">
		<a href="/pkiClassroom/attemptChanges.jsp">
			<button class="buttons">Back</button></a>
		<button class="buttons">Download CSV</button>
		<button class="buttons"> Download per room schedule</button>
		
		
	</div>
	</section>
	
</body>
</html>