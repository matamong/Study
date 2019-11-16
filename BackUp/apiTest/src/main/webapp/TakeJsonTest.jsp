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


var json = ${requestScope.json};
console.log("json : " + json);
var position = [];

function plus(x,y){
    return x + y;
}
for(var i=0; i<json.length; i++){
    var obj = {};
    obj.title = json[i].title;
    obj.latlng = plus(Number(json[i].x), Number(json[i].y));
    console.log("position.latlng: " + obj.latlng);
}

$(document).ready(function(){
	
});
</script>
</body>
</html>