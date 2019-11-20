<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="writeproc2.jsp" method="post">
	<table>
		<tr>
			<td colspan="2">새로 글 쓰기</td>
		</tr>
		<tr>
			<td><b>Password</b></td>
			<td><input type="password" name="pw" required></td>
		</tr>
		<tr>
			<td><b>Name</b></td>
			<td><input type="text" name="name" required></td>
		</tr>
		<tr>
			<td>E-mail</td>
			<td><input type="text" name="email"></td>
		</tr>
		<tr>
			<td>Homepage</td>
			<td><input type="text" name="homepage"></td>
		</tr>
		<tr>
			<td><b>Subject</b></td>
			<td><input type="text" name="subject" required></td>
		</tr>
		<tr>
			<td><b>Memo</b></td>
			<td><textarea required name="memo"></textarea></td>
		</tr>
		<tr>
			<td>Upload #1</td>
			<td><input type="file" name="file1"></td>
		</tr>
		<tr>
			<td>Upload #2</td>
			<td><input type="file" name="file2"></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="submit" value="작성완료">
				<input type="button" value="취소" onclick="javascript:history.back()">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>