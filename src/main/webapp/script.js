
const add = document.getElementById("add");
add.addEventListener("click", () => {
	var children = document.querySelectorAll("[id='elementChange']");
	if(children.length<5){
	const s3 = document.getElementById("s3"); 
    const div = document.createElement("div");
    div.setAttribute('id','elementChange');
    //get course title and section add to the list of change
    const div2 = document.getElementById("course").value;
    const div3 = document.getElementById("field").value;
    div.innerHTML = "Course: "+div2 +", enrollment change: "+div3;
    s3.appendChild(div);  
    }
})
//clear one row
	const clear = document.getElementById("clear");
	clear.addEventListener("click",()=>{
		const child = document.getElementById("elementChange");
		child.remove();
	})
//clear all changes
	const clearAll = document.getElementById("clearAll");
	clearAll.addEventListener("click",()=>{
		
		var children = document.querySelectorAll("[id='elementChange']");
		for(var i=0; i<children.length;i++){
			children[i].remove();
		}
	})

