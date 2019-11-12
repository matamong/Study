<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
$.getJSON( "Action/Servlet", function(data) {
    console.log(JSON.parse(data));
});
</script>
</body>
</html>