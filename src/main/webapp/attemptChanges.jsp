<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>This is the attempt Changes page and list of potential changes</h1>
	<div>
	<form>
	<table class="table">
		<tr>
			<th>Course</th>
			<th>Relevant Changes</th>
		</tr>
		
		<tr >
		<td> CSCI XXXX </td>
		<td> Time from 3-4 to 5-6 </td>
		</tr> 
		
		<tr >
		<td> CSCI XXXX </td>
		<td> Time from 3-4 to 5-6 </td>
		</tr> 
		
	</table>
	
	<table class="table">
		<tr>
			<th>Feedback</th>
		</tr>
			<tr><td>* Able to change to PKI building XXX</td> </tr>
			<tr><td>* Unable to make change due to unavailable rooms</td> </tr>
			<tr><td>* CSCI XXXX moved to online</td> </tr>
	</table>
	
	</form>
	</div>
	
	<section>
		<a href="/pkiClassroom/attemptChanges.jsp"><button > Attempt to Apply </button> </a>
		<a href="/pkiClassroom/home.jsp"><button> Back </button> </a>
		<a href="/pkiClassroom/summary.jsp"><button> Finalize </button> </a>
	</section>
	
</body>
<style>
	
	body{
		background-color: gray;
		}
	.table{
		float:left;
		border: 2px solid black;
		height:300px;
		margin-right: 3%;
		}
	th,td{
		border: 2px solid black;
		}
	section{
		clear:left;
		}
	button{
		height:40px; 
		width: 120px;
		margin-top: 10px;
		margin-left: 5px;}
</style>
</html>