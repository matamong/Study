<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookShop</title>
<script type="text/javascript" src="member/member.js"></script>
<script type="text/javascript" src="mypage/mypage.js"></script>
<link href= "css/Style.css" rel = "stylesheet" type="text/css">
</head>
<body>
<div id = "wrap">
<!--헤더 들어가는곳 시작-->
<header>
<!--로고 들어가는곳 시작-->
	<div id="logo">
		<a href="bookShop?command=index">
		<img src="images/logo.gif" width="180" height ="100" alt="BookShop">
		</a>
	</div>
<nav id="category_menu">
			<ul>  
 			<c:choose>
			<c:when test="${empty sessionScope.loginUser}">
				<li><a href="bookShop?command=login_form">LOGIN</a></li>
				<li><a href="bookShop?command=contract">JOIN</a></li>
			</c:when>
			<c:otherwise>
				<li style="color:orange">
					${sessionScope.loginUser.name}(${sessionScope.loginUser.id})
				</li>
				<li><a href="bookShop?command=logout">LOGOUT</a></li>
			</c:otherwise>
			</c:choose> 
				<li><a href="bookShop?command=cart_list">CART</a></li>
				<li><a href="bookShop?command=mypage">MY PAGE</a></li>
				<li><a href="bookShop?command=qna_list">Q&amp;A(1:1)</a></li>
		</ul>
	</nav>
	<nav id="top_menu">
		<ul>
			<li><a href="bookShop?command=category&kind=1">JSP&amp;Servlet</a></li>
			<li><a href="bookShop?command=category&kind=2">DateBase</a></li>
			<li><a href="bookShop?command=category&kind=3">Java</a></li>
			<li><a href="bookShop?command=category&kind=4">JQuery</a></li>
			<li><a href="bookShop?command=category&kind=5">Spring Framwork</a></li>
		</ul>
	</nav>
</header>
<div class = "clear"></div>	
<hr>

	
	
	
