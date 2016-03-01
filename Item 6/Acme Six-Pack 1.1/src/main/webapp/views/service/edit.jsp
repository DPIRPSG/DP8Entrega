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
<form:form action="service/administrator/edit.do" modelAttribute="serviceEntity">
	<!-- Hidden Attributes -->
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="comments" />
	<form:hidden path="bookings"/>
	<form:hidden path="gyms"/>
	
	
	<!-- Editable Attributes -->
	

	<acme:textbox code="service.name" path="name" />

	<acme:textarea code="service.description" path="description" />
	
	<br />
	<spring:message code="service.picture.addPicture"/>
	<br />
	<spring:message code="service.picture.editPicture"/>
	<br />
	<spring:message code="service.picture.deletePicture"/>
	<br /> <br />

	<acme:textarea code="service.pictures" path="pictures" />
	
	
	<!-- Action buttons -->

	<acme:submit name="save" code="service.save"/>	

	<jstl:if test="${service.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="service.delete" />"
			onclick="return confirm('<spring:message code="service.confirm.delete" />')" />&nbsp;
	</jstl:if>
	
	<acme:cancel code="service.cancel" url="service/administrator/list.do?"/>

</form:form>