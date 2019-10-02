<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="sub_img.html" %>
<%@ include file="sub_menu.jsp" %>
<meta charset="UTF-8">
<article>
	<h2>Order List</h2>
	<form action="" name="formm" method="post">
		<table>
			<tr>
				<th>Item</th>
				<th>Count</th>
				<th>Price</th>
				<th>Order Date</th>
				<th>Order State</th>
			</tr>
			<c:forEach var="orderVO" items="${orderList}">
			<tr>
				<td><a href="bookShop?command=product_detail&pseq=${cartVO.pseq }"></a></td>
				<td>${orderVO.pname}</td>
				<td>${orderVO.quantity}</td>
				<td><fmt:formatNumber value="${orderVO.price2*orderVO.quantity}" type="currency"/></td>
				<td><fmt:formatDate value="${orderVO.indate}" type="date"/></td>
				<td>In Progress</td>
			</tr>
			</c:forEach>
			<tr>
				<th colspan="2"> Total Price</tr>
				<th colspan="2"><fmt:formatNumber value="${totalPrice}" type="currency"/><br></th>
				<th>The Order Process is complete</th>
			</tr>
		</table>
		
		<div class="clear"></div>
		
		<div id="button" style="float:right">
			<input type="button" value="Keep Shoping" class="cancel" 
			onclick="location.href='bookShop?command=index'">
		</div>
	</form>
</article>
<%@ include file="../footer.jsp" %>






