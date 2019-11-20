<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ page import="kr.ac.green.dbcp.dao.*" %>
<%@ page import="java.util.*" %> 
<%
	String start = request.getParameter("currentnum");
	
	if(start == null){
		start = "1";
	}
	
	int cnt = 5;//한페이지에 출력될 갯수 
	BoardDAO dao = BoardDAO.getInstance();
	
	Vector<BoardDTO> vec 
 = dao.getList((Integer.parseInt(start)-1)*cnt, cnt);
	
	double count = Math.ceil(dao.getCount()/cnt);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		<% for(int i=0;i<vec.size();i++){  %>
		<tr>
			<td><%=vec.get(i).getIdx() %></td>
			<td><a href="view.jsp?idx=<%=vec.get(i).getIdx() %>"><%=vec.get(i).getSubject()%></a></td>
			<td><%=vec.get(i).getName() %></td>
			<td><%=vec.get(i).getReday() %></td>
		</tr>
		<% } %>
	</table>
	<input type="button" value="글쓰기" 
	onclick="javascript:location.href='write.jsp'">
	
	
	
	<%for(int i=1;i<=count;i++){ %>
	<a href="list.jsp?currentnum=<%=i%>">[<%=i%>]</a>
	<%} %>

</body>
</html>








