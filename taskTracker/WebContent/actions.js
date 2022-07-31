/**
 * 
 */
 
 function deleteTask(id){
  if(!id) {return ;}
  let idHtml = `task-${id}`;
  
  let conf = confirm("Delete this task ? ");
  if(conf){
  	 
  	
  	const requestOptions = {
	    method: 'POST',
	    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
	    body: `id=${id}`
	};
	fetch('delete', requestOptions)
		.then(response => response.json())
	    .then( data=>{
	    	if(data.hasOwnProperty("state") && data.state=="ok"){
	    		let task = document.getElementById(idHtml);
	    		if(task){
	    			task.remove();
	    		}
	    	}
	    });
	  	
	  }
 }