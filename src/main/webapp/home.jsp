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
			<select name = "course" id = "course" required>
				<option>csci 4830</option>
				<option>csci 3660</option>
				<option>csci 4220</option>
				<option>csci 3220</option>
			</select> 
		</div>
		<div class="container">
			<h4> Field To Change: </h4>
			<select id = "field" required>
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
			<button class= "buttons">Add</button>
			<button class= "buttons">Clear</button>
		</div>
	</section>
	
	<section class="fieldOne">
		<div>
			<h4>List of Changes</h4>
			<p> blah blah blah changes </p>
			<p> blah blah blah changes </p>
			<p> blah blah blah changes </p>
			<p> blah blah blah changes </p>
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
<script>
document.getElementById("field").onchange= function(){
	document.getElementById('s2').disabled = false; //enabling s2 select
	document.getElementById('s2').innerHTML = "";
    var h4 = document.getElementById('h4');
    var s2 = document.getElementById('s2');
	var opt1 = document.createElement('option');
	var opt2 = document.createElement('option');
	var opt3 = document.createElement('option');
	var opt4 = document.createElement('option');
	s2.removeAttribute("hidden");
	if(this.value=='select'){
		h4.innerHTML="";
		s2.setAttribute("hidden", "hidden");
	}else if(this.value=='Room'){
		h4.innerHTML="Room: ";
		opt1.textContent = "PKI 123";
		opt2.textContent = "PKI 223";
		opt3.textContent = "PKI 345";
		opt4.textContent = "PKI 567";
		document.getElementById('s2').appendChild(opt1);
		document.getElementById('s2').appendChild(opt2);
		document.getElementById('s2').appendChild(opt3);
		document.getElementById('s2').appendChild(opt4);
	}else if(this.value=='MaxEnroll'){
		h4.innerHTML="Max Enrollment: ";
		opt1.textContent = "20";
		opt2.textContent = "25";
		opt3.textContent = "30";
		opt4.textContent = "40";
		document.getElementById('s2').appendChild(opt1);
		document.getElementById('s2').appendChild(opt2);
		document.getElementById('s2').appendChild(opt3);
		document.getElementById('s2').appendChild(opt4);
	}
	else if(this.value=='Enroll'){
		h4.innerHTML="Enrollment: ";
		opt1.textContent = "15";
		opt2.textContent = "19";
		opt3.textContent = "24";
		opt4.textContent = "32";
		document.getElementById('s2').appendChild(opt1);
		document.getElementById('s2').appendChild(opt2);
		document.getElementById('s2').appendChild(opt3);
		document.getElementById('s2').appendChild(opt4);
	}
	else if(this.value=='Time'){
		h4.innerHTML="Time: ";
		opt1.textContent = "10-12";
		opt2.textContent = "1-3";
		opt3.textContent = "4-6";
		opt4.textContent = "6:30-8:30";
		document.getElementById('s2').appendChild(opt1);
		document.getElementById('s2').appendChild(opt2);
		document.getElementById('s2').appendChild(opt3);
		document.getElementById('s2').appendChild(opt4);
	}
}
</script>	

</body>
	
<style>
	
</style>
</html>