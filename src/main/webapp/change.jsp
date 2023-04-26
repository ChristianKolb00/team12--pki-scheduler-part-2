<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Attemp Change</title>
<link rel="stylesheet" href= "style.css" >
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" >

</head>

<body>
	<% if(session.getAttribute("field")==null)response.sendRedirect("home.jsp"); %>
	<section style="margin:5% auto; ">
		<h1>Here are some potential changes</h1>
		<h2 >Course: <%= session.getAttribute("course")  %> </h2>
		<h4 >Max Enrollment Change: <%= session.getAttribute("field")  %> </h4>
		<% String errorMessage = (String)request.getAttribute("errorMessage");
		if(errorMessage != null){%>
			<p style="text-align:center; color:red;"> <%=errorMessage %></p>
		<%  } %>
	</section>
	
	<form action="changesPage" method="post">
	<section class="fieldOne" >
	
		<table class="table">
		<tr>
			<th>Please choose a selection</th>
		</tr>
		<tr>
		<td style="display:flex; flex-direction:column;">
		<% String[] object = (String[]) session.getAttribute("object"); 
			request.getSession().setAttribute("object",object);
		%>
		<% for(int i=0; i<object.length; i++) {
			 %>
			 <div class="choice"><%= (i+1)+". "+object[i] %></div>
		<% }%>
		</td>
		</tr>
		<tr><td>
		<select class="selection" name="selection" id = "selection" >
			<option value=""> select </option>
			<% for(int i=1; i<=object.length; i++) {
			 %>
				<option value="<%=i%>"> <%=i%> </option>
			<% }%>
		</select>
		</td></tr>
		<tr><td>
		<input style="font-size:20px;" type="submit" class="buttons" 
			name= "submit" value="Confirm">
		</td></tr>
		
	</table>
	</section>
	</form>
	<section >
		<a href="/pkiClassroom/home.jsp"><button class="buttons"> Back </button></a>
	</section>
	
	
</body>

<style>
form {	width:70%;}

.buttons	{margin-top:50px;}

.selection	{
font-size:16px; width:90px; height:40px; 
border-radius:20px; text-align:center;}

.choice{	
	height:50px; width:auto; margin:10px auto;font-size:16px; 
	border-color:#a55a51; border-radius:10px; padding:5px 10px; 
}
.choice:hover{ 
	transform:scale(1.02); border:none; background:#525f93; color:white;
}
h1,h2,h4{
	text-align:center;
}
h1{ font-size:50px;} 

h1:hover,h2:hover,h4:hover{color:#525f93 ;}

.buttons:hover{ transform:scale(1.02);background:#525f93; color:white;}

fieldOne{width:60%; height:60%;align-items:center;text-alilgn:center;}

body{ display:flex;flex-direction:column;align-items:center;
	justify-content:center;text-align:center; over-flow:scroll;}
</style>

</html>