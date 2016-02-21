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
	<form:form action="${urlAction}" modelAttribute="administrator">
		<!-- Hidden Attributes -->
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		<form:hidden path="userAccount.authorities"/>
		
		<!-- Editable Attributes -->
		<acme:textbox code="administrator.name" path="name"/>
		<acme:textbox code="administrator.surname" path="surname"/>
		<acme:textbox code="administrator.phone" path="phone"/>
		
		<jstl:if test="${creating != null}">
			<acme:textbox code="administrator.username" path="userAccount.username"/>
			<br />
			<acme:textbox code="administrator.password" path="userAccount.password"/>
		</jstl:if>
		
		<br />
		
		<!-- Action buttons -->
		<acme:submit name="save" code="administrator.save"/>
		&nbsp;
		<acme:cancel url="/" code="administrator.cancel"/>
		<br />
		
	</form:form>
