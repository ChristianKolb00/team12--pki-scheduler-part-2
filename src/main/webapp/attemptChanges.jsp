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
	<h1>This is the attempt Changes page and list of potential changes</h1>
	<section class="fieldOne">
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
	</section>
	<section class="fieldOne">
	<table class="table">
		<tr>
			<th>Feedback</th>
		</tr>
			<tr><td >* Able to change to PKI building XXX</td> </tr>
			<tr><td >* Unable to make change due to unavailable rooms</td> </tr>
			<tr><td >* CSCI XXXX moved to online</td> </tr>
	</table>
	
	</section>
	
	<section class="fieldTwo">
		<a href="/pkiClassroom/home.jsp"><button class="buttons"> Back </button> </a>
		<a href="/pkiClassroom/attemptChanges.jsp"><button class="buttons"> Attempt to Apply </button> </a>
		<a href="/pkiClassroom/summary.jsp"><button class="buttons"> Finalize </button> </a>
	</section>
	
</body>
</html>