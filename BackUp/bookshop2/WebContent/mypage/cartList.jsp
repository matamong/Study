<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ include file="../header.jsp" %>
<%@ include file="sub_img.html" %>
<%@ include file="sub_menu.jsp" %>
<meta charset="UTF-8">
<h2>Cart List</h2>
<form name="formm" method="post" action="">
<c:choose>
	<c:when test="${cartList.size()==0}">
	 	<h3 style="color:red; text-align:center;">The Cart is empty</h3>
	</c:when>
	<c:otherwise> 
	<table id="cartList">
		<tr>
			<th>Item</th>
			<th>Count</th>
			<th>Price</th>
			<th>Order</th>
			<th>Delete</th>
			<th>CartNo</th>
		<tr>
		<c:forEach var="cartVO" items="${cartList}">
			<tr>
				<td><a href="bookShop?command=product_detail&pseq=${cartVO.pseq}">
				<h3>${cartVO.pname}</h3>
			</a></td>
				<td>${cartVO.quantity}</td>
				<td><fmt:formatNumber value="${cartVO.price2*cartVO.quantity}" type="currency"/></td>
				<td><fmt:formatDate value="${cartVO.indate}" type="date"/></td>
				<td><input type="checkbox" name="cseq" value = "${cartVO.cseq}"></td>
				<td>${cartVO.cseq}</td>
			</tr>
		</c:forEach>
			<tr>
				<th colspan="2"> Total Price</tr>
				<th colspan="2"><fmt:formatNumber value="${totalPrice}" type="currency"/> </th>
				<th><a href="#" onclick="go_delete()"><h3>Delete</h3></a></th>
				<th>Debug</th>
			</tr>
	</table>
 </c:otherwise> 
 </c:choose>
 
 <div class="clear"></div>
 
 <div id="button" style="float:right">
 		<input type="button" value="Keep Shoping" class="cancel" onclick="location.href='bookShop?command=index'">		
 	<c:if test="${cartList.size()!=0}">
 		<input type="button" value="Order" class="submit" onclick="go_order_insert()">
 	</c:if>
</div>
</form>
<%@ include file="../footer.jsp" %>