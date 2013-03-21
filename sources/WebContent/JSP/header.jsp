<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>myBooking :: ${param.title}</title>

<link rel="stylesheet" type="text/css" href="/myBooking/resources/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/myBooking/resources/css/bootstrap-responsive.min.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script type="text/javascript" src="/myBooking/resources/js/bootstrap.min.js"></script>

<style>
html, body {
    height: 100%;
}
footer {
    color: #666;
    background: #222;
    padding-top: 20px;
    margin: 0px;
    position: relative;
    height:100%;
    max-height: 300px;
    bottom: 0;
    width: 100%;
}
footer a {
    color: #999;
}
footer a:hover {
    color: #efefef;
}
 </style>
    
</head>
<body>


<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<span class="brand">&nbsp;<img src="/myBooking/resources/img/logo.png" /></span>
			<ul class="nav">
	      		<li<c:if test="${param.currentTab == ''}"> class="active"</c:if>><a href="<c:url value="/" />">Index</a></li>
	      		<li<c:if test="${param.currentTab == 'search'}"> class="active"</c:if>><a href="<c:url value="/search" />">Rechercher</a></li>
	      		<c:choose>
	      			<c:when test="${session.connected}">
	      				<li<c:if test="${param.currentTab == 'dropdown'}"> class="active"</c:if> class="dropdown">
                       		<a href="#" class="dropdown-toggle" data-toggle="dropdown">${session.userName}<b class="caret"></b></a>
                        	<ul class="dropdown-menu">
                        		<li class="nav-header">Start</li>
                         		<li><a href="<c:url value="/profile" />">Mon profil</a></li>
                          		<li><a href="<c:url value="/checkout/" />">Panier</a></li>
                          		<li><a href="<c:url value="/orders/" />">Commandes</a></li>
                          		<li class="divider"></li>
                          		<li class="nav-header">Utiles</li>
                          		<li><a href="<c:url value="/logout" />">Déconnexion</a></li>
                        	</ul>
                      	</li>
	      			</c:when>
	      			<c:otherwise>
	      				<li<c:if test="${param.currentTab == 'login'}"> class="active"</c:if>><a href="<c:url value="/login" />">Se connecter</a></li>
	      				<li<c:if test="${param.currentTab == 'register'}"> class="active"</c:if>><a href="<c:url value="/register" />">S'inscrire</a></li>
	      			</c:otherwise>
	      		</c:choose>
	      	</ul>
	  </div>
</div>

<div style="margin-top: 43px;"></div>
<div class="main">