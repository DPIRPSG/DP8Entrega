<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<!-- Form -->
<form:form action="gym/administrator/edit.do" modelAttribute="gym">
	<!-- Hidden Attributes -->
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="comments" />
	<form:hidden path="service" />
	<form:hidden path="feePayment" />
	
	
	<!-- Editable Attributes -->
	

	<acme:textbox code="gym.name" path="name" />

	<acme:textbox code="gym.description" path="description" />

	<acme:textbox code="gym.postalAddress" path="postalAddress" />
	
	<acme:textbox code="gym.phone" path="phone" />

	<acme:textbox code="gym.fee" path="fee" />
	
	<acme:textbox code="gym.picture" path="picture" />
	
	
	<!-- Action buttons -->

	<acme:submit name="save" code="gym.save"/>	

	<jstl:if test="${gym.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="gym.delete" />"
			onclick="return confirm('<spring:message code="gym.confirm.delete" />')" />&nbsp;
	</jstl:if>
	
	<acme:cancel code="gym.cancel" url="gym/administrator/list.do?"/>

</form:form>