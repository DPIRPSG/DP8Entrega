<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


	<!-- Form -->
	<form:form action="${urlAction}" modelAttribute="actorForm">
		<!-- Hidden Attributes -->
		
		<!-- Editable Attributes -->
		<acme:textbox code="customer.name" path="name"/>
		<acme:textbox code="customer.surname" path="surname"/>
		<acme:textbox code="customer.phone" path="phone"/>
		
		<jstl:if test="${creating != null}">
			<acme:textbox code="customer.username" path="username"/>
		</jstl:if>
		
		<br />
		
		<!-- Action buttons -->
		<acme:submit name="save" code="customer.save"/>
		&nbsp;
		<acme:cancel url="/" code="customer.cancel"/>
		<br />
		
	</form:form>
