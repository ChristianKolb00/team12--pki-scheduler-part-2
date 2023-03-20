
/*document.getElementById("field").onchange= function(){
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
		opt4.textContent = "PKI 321";
		opt1.value = "PKI 123";
		opt2.value = "PKI 223";
		opt3.value = "PKI 345";
		opt4.value = "PKI 321";
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
		opt1.value = "20";
		opt2.value = "25";
		opt3.value = "30";
		opt4.value = "40";
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
		opt1.value = "15";
		opt2.value = "19";
		opt3.value = "24";
		opt4.value = "32";
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
		opt1.value = "10-12";
		opt2.value = "1-3";
		opt3.value = "4-6";
		opt4.value = "6:30-8:30";
		document.getElementById('s2').appendChild(opt1);
		document.getElementById('s2').appendChild(opt2);
		document.getElementById('s2').appendChild(opt3);
		document.getElementById('s2').appendChild(opt4);
	}
};*/
    const add = document.getElementById("add");
	add.addEventListener("click", () => {
		var children = document.querySelectorAll("[id='elementChange']");
		while(children.length<=10){
			
		const s3 = document.getElementById("s3");
    	const div = document.createElement("div");
    	div.setAttribute('id','elementChange');
    	const div2 = document.getElementById("course").value;
    	const div3 = document.getElementById("field").value;
    	//const div4 = document.getElementById("s2").value;
    	div.innerHTML = "Course: "+div2 +", enrollment change: "+div3;
    	s3.appendChild(div);  
		}
});	
//clear 
	const clear = document.getElementById("clear");
	clear.addEventListener("click",()=>{
		const child = document.getElementById("elementChange");
		child.remove();
	})
	const clearAll = document.getElementById("clearAll");
	clearAll.addEventListener("click",()=>{
		
		var children = document.querySelectorAll("[id='elementChange']");
		for(var i=0; i<children.length;i++){
			children[i].remove();
		}
	});

