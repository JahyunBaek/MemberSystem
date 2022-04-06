function AjaxSync(url,callback,param,acc){
httpRequest = new XMLHttpRequest();
	
	    httpRequest.onreadystatechange = () => {
	    	
		    if (httpRequest.readyState === XMLHttpRequest.DONE) {
			      if (httpRequest.status === 200) {
			    	var result = httpRequest.response;
		if(callback != null)
					callback(result);
					//console.log(result);
			      } else {
			        alert('Request Error!');
			      }
		    }
	    };

	    
		if(param != null){
			httpRequest.open('GET', url+"?"+param);
		}else if(acc != null){
			httpRequest.open('GET', url+"/"+acc);	
		}else{
			httpRequest.open('GET', url);	
		}
	    
	   
	    httpRequest.responseType = "json";
	
	    httpRequest.send();
}