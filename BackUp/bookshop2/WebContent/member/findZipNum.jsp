<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>우편번호검색</title>
<style type="text/css">
	body {
		background-color: #B96DB5;
		font-family: Verdana;
	}
	
	#popup {
		padding: 0 10px;
	}
	
	#popup h1 {
		font-family: "Times New Roman", Times, serif;
		font-size: 45px;
		color: #CCC;
		font-weight: normal;
	}
	
	table#zipcode {
		border-collapse: collapse; /* border 사이의 간격 없앰 */
		border-top: 3px solid #fff;
		border-bottom: 3px solid #fff;
		width: 100%;
		margin-top: 15px;
	}
	
	table#zipcode th, table#zipcode td {
		text-align: center;
		border-bottom: 1px dotted #fff;
		color: #FFF;
	}
	
	table th, td {
		padding: 10px;
	}
	
	table#zipcode  a {
		display: block;
		height: 20px;
		text-decoration: none;
		color: #fff;
		padding: 10px;
	}
	
	table#zipcode a:hover {
		color: #F90;
		font-weight: bold;
	}
</style>
<script type="text/javascript">
	function result(zipNum, sido, gugun, dong) {
		opener.document.formm.zipNum.value=zipNum;
		opener.document.formm.addr1.value=sido+""+gugun+""+dong;
		self.close();
	}


</script>
</head>
<body>
			<%request.setCharacterEncoding("UTF-8");%>
	<div id="popup">
		<h1>우편번호 검색</h1>
		<form action="bookShop?command=find_zip_num" name=formm method="post" >
			동 이름 : <input type="text" name="dong" >
			<input type="submit" value="검색" class="submit">
		</form>
		<table id="zipcode">
			<tr>
				<th>Zip Code</th>
				<th>Address</th>
			</tr>
			<c:forEach var="addressVO" items="${list}">
				<tr>
					<td>${addressVO.zipNum}</td>
					<td>
						<a href="#" onclick="return result(
							'${addressVO.zipNum}',
							'${addressVO.sido}',
							'${addressVO.gugun}',
							'${addressVO.dong}')">
							
							 ${addressVO.sido} ${addressVO.gugun}
							${addressVO.dong}
						</a>
					</td>
				<tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>