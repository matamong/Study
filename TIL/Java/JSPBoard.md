# JSP 게시판 만들기 예제

```jsp

<%
	BoardDAO dao = BoardDAO.getInstance();
	Vector<BoardDTO> vec = dao.getList();
%>

	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		<% for(int i=0; i<vec.size(); i++){ %>
		<tr>
			<td><%= vec.get(i).getIdx() %></td>
			<td><%= vec.get(i).getSubject() %></td>
			<td><%= vec.get(i).getName() %></td>
			<td><%= vec.get(i).getRedate() %></td>
		</tr>
		<% } %>
	</table>
	<input type="button" value="글쓰기" onclick="javascript:location.href='write.jsp'">
```