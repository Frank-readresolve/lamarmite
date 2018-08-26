<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale.language}">
<head>
<title><spring:message code="entities.menu.create.title" /></title>
<c:import url="commonsHead.jsp" />
</head>
<body>
	<c:import url="headerNav.jsp" />
	<div class="container-fluid">
		<section>
			<h1 class="text-primary"><spring:message code="entities.menu.create.title" /></h1>
			<form:form action="create" method="POST" modelAttribute="menu">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<div class="form-row">
					<div class="form-group col">
						<form:label path="starter.id"><spring:message code="entities.menu.starter" /><span class="text-danger"><spring:message code="commons.symbols.required" /></span></form:label>
						<form:select path="starter.id" cssClass="form-control" cssErrorClass="form-control is-invalid">
							<form:option value="0"><spring:message code="commons.forms.select" /></form:option>
							<form:options items="${starters}" itemValue="id" itemLabel="name" />
						</form:select>
						<form:errors element="div" path="starter.id" cssClass="invalid-feedback" />
					</div>
					<div class="form-group col">
						<form:label path="mainCourse.id"><spring:message code="entities.menu.mainCourse" /><span class="text-danger"><spring:message code="commons.symbols.required" /></span></form:label>
						<form:select path="mainCourse.id" cssClass="form-control" cssErrorClass="form-control is-invalid">
							<form:option value="0"><spring:message code="commons.forms.select" /></form:option>
							<form:options items="${mainCourses}" itemValue="id" itemLabel="name" />
						</form:select>
						<form:errors element="div" path="mainCourse.id" cssClass="invalid-feedback" />
					</div>
					<div class="form-group col">
						<form:label path="dessert.id"><spring:message code="entities.menu.dessert" /><span class="text-danger"><spring:message code="commons.symbols.required" /></span></form:label>
						<form:select path="dessert.id" cssClass="form-control" cssErrorClass="form-control is-invalid">
							<form:option value="0"><spring:message code="commons.forms.select" /></form:option>
							<form:options items="${desserts}" itemValue="id" itemLabel="name" />
						</form:select>
						<form:errors element="div" path="dessert.id" cssClass="invalid-feedback" />
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col">
						<form:label path="frenchName"><spring:message code="entities.menu.frenchName" /><span class="text-danger"><spring:message code="commons.symbols.required" /></span></form:label>
						<div class="input-group">
							<div class="input-group-prepend">
						        <div class="input-group-text"><spring:message code="commons.symbols.isoFrench" /></div>
						     </div>
							<form:input path="frenchName" maxlength="50" cssClass="form-control" cssErrorClass="form-control is-invalid" autocomplete="off" lang="fr" />
							<form:errors element="div" path="frenchName" cssClass="invalid-feedback" />
						</div>
					</div>
					<div class="form-group col">
						<form:label path="englishName"><spring:message code="entities.menu.englishName" /><span class="text-danger"><spring:message code="commons.symbols.required" /></span></form:label>
						<div class="input-group">
							<div class="input-group-prepend">
						        <div class="input-group-text"><spring:message code="commons.symbols.isoEnglish" /></div>
						    </div>
							<form:input path="englishName" maxlength="50" cssClass="form-control" cssErrorClass="form-control is-invalid" autocomplete="off" lang="en" />
							<form:errors element="div" path="englishName" cssClass="invalid-feedback" />
						</div>
					</div>
				</div>
			 	<div class="form-row">
			 		<spring:message code="commons.formats.date" var="datePattern" />
					<div class="form-group col">
						<form:label path="startDate"><spring:message code="entities.menu.startDate" /><span class="text-danger"><spring:message code="commons.symbols.required" /></span></form:label>
						<form:input path="startDate" maxlength="10" placeHolder="${datePattern}" cssClass="form-control datepicker" cssErrorClass="form-control datepicker is-invalid" autocomplete="off" />
						<form:errors element="div" path="startDate" cssClass="invalid-feedback" />
					</div>
					<div class="form-group col">
						<form:label path="endDate"><spring:message code="entities.menu.endDate" /><span class="text-danger"><spring:message code="commons.symbols.required" /></span></form:label>
						<form:input path="endDate" maxlength="10" placeHolder="${datePattern}" cssClass="form-control datepicker" cssErrorClass="form-control datepicker is-invalid" autocomplete="off" />
						<form:errors element="div" path="endDate" cssClass="invalid-feedback" />
					</div>
					<div class="form-group col">
						<form:label path="price"><spring:message code="entities.menu.price" /><span class="text-danger"><spring:message code="commons.symbols.required" /></span></form:label>
						<div class="input-group">
							<div class="input-group-prepend">
					        	<div class="input-group-text"><spring:message code="commons.symbols.euro" /></div>
					        </div>
							<form:input path="price" maxlength="5" placeHolder="0.00" cssClass="form-control" cssErrorClass="form-control is-invalid" autocomplete="off" />
							<form:errors element="div" path="price" cssClass="invalid-feedback" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<form:label path="frenchDescription"><spring:message code="entities.menu.frenchDescription" /></form:label>
					<div class="input-group">
						<div class="input-group-prepend">
						    <div class="input-group-text"><spring:message code="commons.symbols.isoFrench" /></div>
						</div>
						<form:textarea path="frenchDescription" rows="5" cssClass="form-control" lang="fr" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="englishDescription"><spring:message code="entities.menu.englishDescription" /></form:label>
					<div class="input-group">
						<div class="input-group-prepend">
						    <div class="input-group-text"><spring:message code="commons.symbols.isoEnglish" /></div>
						</div>
						<form:textarea path="englishDescription" rows="5" cssClass="form-control" lang="en" />
					</div>
				</div>
				<button type="reset" class="btn btn-primary mb-3"><spring:message code="commons.forms.reset" /></button>
				<form:button class="btn btn-primary mb-3"><spring:message code="commons.forms.save" /></form:button>
			</form:form>
		</section>
	</div>
	<c:import url="footerNav.jsp" />
</body>
</html>