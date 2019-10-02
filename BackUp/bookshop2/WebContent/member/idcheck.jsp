<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID 중복 체크</title>
<style type="text/css">
	body{
		background-color: #B96DB5;
		font-family: Verdona; 
	}
	#wrap{margin: 0 20px;}
	h1 {
		font-family: "Times New Roman", Times, serif;
		font-size: 45px;
		color: #ccc;
		font-weight: normal;
	}
	input[type-button], input[type-submit]{
		flaot: right;
	}
</style>
<script>
function idok(){
 	opener.formm.id.value="${id}";  /*opener = open()메소드를 사용한 문서*/
	opener.formm.reid.value="${id}"; 
	self.close();
}
</script>
</head>
<body>
<div id="wrap">
	<h1>Check ID Redundancy</h1>
	<form action="bookShop?command=id_check_form" method="post" name="formm">
		User ID 
		<input type = "text" name="id" value="">
		<input type="submit" value="search" class="submit"><br>
		<div style="margin-top:20px">
			<c:if test="${message==1}">
				<script>
					opener.document.formm.id.value="";
				</script>
				${id }는 이미 사용중 입니다.
			</c:if>
			<c:if test="${message==-1 }">
				${id }는 사용 가능합니다.
				<input type="button" value="Use" class="cancel" onclick="idok()">
			</c:if>
		</div>
	</form>
</div>
</body>
</html>