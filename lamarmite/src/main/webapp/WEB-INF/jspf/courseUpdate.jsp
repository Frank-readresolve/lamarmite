<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale.language}">
<head>
<title><spring:message code="entities.course.update.title" /></title>
<c:import url="commonsHead.jsp" />
</head>
<body>
	<c:import url="headerNav.jsp" />
	<div class="container-fluid">
		<section>
			<h1 class="text-primary"><spring:message code="entities.course.update.title" /></h1>
			<form:form action="update" method="POST" modelAttribute="course">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<form:hidden path="id" />
			<form:hidden path="code"/>
				<div class="form-row">
					<div class="form-group col">
						<form:label path="frenchName"><spring:message code="entities.course.frenchName" /><span class="text-danger"><spring:message code="commons.symbols.required" /></span></form:label>
						<div class="input-group">
							<div class="input-group-prepend">
						        <div class="input-group-text"><spring:message code="commons.symbols.isoFrench" /></div>
						     </div>
							<form:input path="frenchName" maxlength="50" cssClass="form-control" cssErrorClass="form-control is-invalid" autocomplete="off" lang="fr" />
							<form:errors element="div" path="frenchName" cssClass="invalid-feedback" />
						</div>
					</div>
					<div class="form-group col">
						<form:label path="englishName"><spring:message code="entities.course.englishName" /><span class="text-danger"><spring:message code="commons.symbols.required" /></span></form:label>
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
					<div class="form-group col">
						<form:label path="code"><spring:message code="entities.course.code" /></form:label>
						<form:input path="code" maxlength="4" cssClass="form-control" disabled="true" autocomplete="off" />
					</div>
					<div class="form-group col">
					<form:label path="type.id"><spring:message code="entities.course.type" /><span class="text-danger"><spring:message code="commons.symbols.required" /></span></form:label>
						<form:select path="type.id" cssClass="form-control" cssErrorClass="form-control is-invalid">
							<form:option value="0"><spring:message code="commons.forms.select" /></form:option>
							<form:options items="${courseTypes}" itemValue="id" itemLabel="name" />
						</form:select>
						<form:errors element="div" path="type.id" cssClass="invalid-feedback" />
					</div>
					<div class="form-group col">
						<form:label path="price"><spring:message code="entities.course.price" /><span class="text-danger"><spring:message code="commons.symbols.required" /></span></form:label>
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
					<form:label path="frenchDescription"><spring:message code="entities.course.frenchDescription" /></form:label>
					<div class="input-group">
						<div class="input-group-prepend">
						    <div class="input-group-text"><spring:message code="commons.symbols.isoFrench" /></div>
						</div>
						<form:textarea path="frenchDescription" rows="5" cssClass="form-control" lang="fr" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="englishDescription"><spring:message code="entities.course.englishDescription" /></form:label>
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