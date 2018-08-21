<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<span id="TOP"></span>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<ul class="nav navbar-nav">
		<span class="navbar-brand text-info"><spring:message code="commons.nav.brand" /></span>
		<li class="nav-item"><a class="nav-link" href="<c:url value="/home/welcome" />"><spring:message code="commons.nav.home" /></a></li>
		<li class="nav-item"><a class="nav-link" href="<c:url value="/courses/toCreate" />"><spring:message code="commons.nav.course" /></a></li>
		<li class="nav-item"><a class="nav-link" href="<c:url value="/menus/toCreate" />"><spring:message code="commons.nav.menu" /></a></li>
		<li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			<spring:message code="commons.nav.languages" />
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
			<a class="dropdown-item" href="<c:url value="/home/welcome?lang=fr" />"><spring:message code="commons.nav.languages.french" /></a>
			<a class="dropdown-item" href="<c:url value="/home/welcome?lang=en" />"><spring:message code="commons.nav.languages.english" /></a>
        </div>
      </li>
	</ul>
</nav>