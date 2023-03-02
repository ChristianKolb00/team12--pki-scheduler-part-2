<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
	<h1>This is the Summary Page!</h1>
	<form>
	<table>
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
	</form>
	<div>
		<button class="left">Download CSV</button>
		<a href="/pkiClassroom/home.jsp"><button class="mid">Back</button></a>
		<button class="right"> Download per room schedule</button>
	</div>
	
</body>
<style>
	body{
		background:gray;
    	
	}
	h1{
		display:flex;
		justify-content: center;
		margin-bottom:5%;}
	th,td{
		text-align:center;
		vertical-align:middle;
		border:2px solid black;}
	table{
		margin:auto;
		height: 300px;
		width:60%;
		border:2px solid black;
		}
	.left{
		margin-left:10%;
		margin-top:10%;
		float:left;
		height:60px;
		width:150px;}
	.mid{
		margin-top:10%;
		margin-left:25%;
		text-aligh:center;
		height:60px;
		width:150px;}
	.right{
		margin-right:10%;
		margin-top:10%;
		float:right;
		height:60px;
		width:150px;}
</style>
</html>