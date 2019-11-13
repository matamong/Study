<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>

<title>Insert title here</title>
</head>
<body>
<div id="target"></div>
<script>

var position = ${requestScope.json};
$(document).ready(function(){
	$("#target").text(position.test);
});
//$(document).ready(function() { 
	/* $.getJSON('JSONGiver', function(data) { 
		
		var html = []; 
		$.each(data, function(i, item) {
		html.push('<div >'); 
		html.push('<h3 >' + item.firstName + '</h3>'); 
		html.push('<div >' + item.lastName + '</div>'); 
		html.push('<div >' + item.age + '</div>'); 
		html.push('<div >' + item.address.streetAddress + '</div>'); 
		html.push('<div >' + item.phoneNumber[0].type + '</div>'); 
		html.push('</div>'); html.push('</div>'); 
		html.push('<li id="' + key + '">' + val + '</li>'); 
		}); 
		
			console.log(html); 
			$('#target').html(html.join('')); 
		}); 
	});
 */


/* $.getJSON("JSONGiver", function(data) {
    console.log(JSON.parse(data));
}); */
</script>
</body>
</html>