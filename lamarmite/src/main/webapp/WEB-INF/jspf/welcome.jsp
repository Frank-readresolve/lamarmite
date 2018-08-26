<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html lang="${pageContext.response.locale.language}">
<head>
<title><spring:message code="home.welcome.title" /></title>
<c:import url="commonsHead.jsp" />
</head>
<body>
	<c:import url="headerNav.jsp" />
	<div class="container-fluid">
		<section>
			<h1 class="text-primary">
				<spring:message code="home.welcome.courses" />
			</h1>
			<div class="table-responsive">
				<table class="table table-hover">
					<thead class="thead-light">
						<tr>
							<th><spring:message code="home.welcome.courses.type" /></th>
							<th><spring:message code="home.welcome.courses.code" /></th>
							<th><spring:message code="home.welcome.courses.price" /></th>
							<th><spring:message code="home.welcome.courses.name" /></th>
							<th><spring:message code="commons.symbols.nbsp" /></th>
						</tr>
					</thead>
					<tbody>
						<tr>
						<c:forEach items="${courses}" var="course">
							<tr>
								<td>${course.typeName}</td>
								<td>${course.code}</td>
								<td><spring:message code="commons.symbols.euro" />${course.price}</td>
								<td>${course.name}</td>
								<td><a href="<c:url value="/courses/toUpdate?id=${course.id}" />"><spring:message code="home.welcome.courses.update" /></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</section>
		<section>
			<h1 class="text-primary">
				<spring:message code="home.welcome.menus" />
			</h1>
			<div class="table-responsive">
				<table class="table table-hover">
					<thead class="thead-light">
						<tr>
							<th><spring:message code="home.welcome.menus.code" /></th>
							<th><spring:message code="home.welcome.menus.price" /></th>
							<th><spring:message code="home.welcome.menus.name" /></th>
							<th><spring:message code="commons.symbols.nbsp" /></th>
							<sec:authorize access="hasRole('ROLE_ADMIN')">
								<th><spring:message code="commons.symbols.nbsp" /></th>
							</sec:authorize>
						</tr>
					</thead>
					<tbody>
						<tr>
						<c:forEach items="${menus}" var="menu">
							<tr>
								<td>${menu.code}</td>
								<td><spring:message code="commons.symbols.euro" />${menu.price}</td>
								<td>${menu.name}</td>
								<td><a href="<c:url value="/menus/toUpdate?id=${menu.id}" />"><spring:message code="home.welcome.menus.update" /></a></td>
								<sec:authorize access="hasRole('ROLE_ADMIN')">
									<td><a href="<c:url value="/menus/delete/${menu.id}" />"><spring:message code="home.welcome.menus.delete" /></a></td>
								</sec:authorize>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</section>
	</div>
	<c:import url="footerNav.jsp" />
</body>
</html>