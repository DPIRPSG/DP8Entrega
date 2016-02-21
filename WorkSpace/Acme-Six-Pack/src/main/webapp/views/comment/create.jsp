<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<security:authorize access="isAuthenticated()">

	<h3><spring:message code="comment.entity"/>: <jstl:out value="${commentedEntity.name}" /></h3>
	
	<!-- Form -->
	<form:form action="comment/actor/create.do" modelAttribute="comment">
		<!-- Hidden Attributes -->
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		<form:hidden path="moment"/>
		<form:hidden path="commentedEntity"/>
		<form:hidden path="actor"/>
		
		<!-- Editable Attributes -->
		
		<acme:textarea path="text" code="comment.text"/>
					
		<acme:textbox path="starRating" code="comment.starRating"/>
		
		<!-- Action buttons -->
		<acme:submit name="save" code="comment.create.save"/>
		&nbsp;
		<acme:cancel code="comment.create.cancel" url="/comment/list.do?commentedEntityId=${commentedEntity.id}"/>
		
	</form:form>

</security:authorize>
