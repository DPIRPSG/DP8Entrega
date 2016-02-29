<%--
 * textbox.tag
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ tag language="java" body-content="empty" %>

<%-- Taglibs --%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<%-- Attributes --%> 
 
<%@ attribute name="path" required="true" %>
<%@ attribute name="code" required="true" %>

<%@ attribute name="readonly" required="false" %>
<%@ attribute name="pathValue" required="false" %>
<%@ attribute name="size" required="false" %>

<jstl:if test="${readonly == null}">
	<jstl:set var="readonly" value="false" />
</jstl:if>
<%-- <jstl:if test="${pathValue == null}"> --%>
<%-- 	<jstl:set var="pathValue" value="${path}" /> --%>
<%-- </jstl:if> --%>

<%-- Definition --%>

<div>
	<form:label path="${path}">
		<spring:message code="${code}" />: &nbsp;
	</form:label>	
	<form:input path="${path}" value="${pathValue}" readonly="${readonly}" size="${size}"/>	
	<form:errors path="${path}" cssClass="error" />
</div>	
