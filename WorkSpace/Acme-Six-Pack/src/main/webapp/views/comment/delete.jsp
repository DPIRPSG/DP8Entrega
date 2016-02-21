<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<security:authorize access="hasRole('ADMIN')">

	<h3><spring:message code="comment.entity"/>: <jstl:out value="${commentedEntity.name}" /></h3>
	
	<!-- Form -->
	<form:form action="comment/administrator/delete.do" modelAttribute="comment">
		<!-- Hidden Attributes -->
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		<form:hidden path="deleted"/>
		<form:hidden path="commentedEntity"/>
		
		<!-- Shown Attributes -->
		
		<acme:textbox path="moment" code="comment.moment" readonly="true"/>
<%-- 		<form:hidden path="moment"/> --%>
<%-- 		<p><spring:message code = "comment.moment"/>: <jstl:out value="${comment.moment}" /></p> --%>
		
		<acme:textbox path="actor" pathValue="${comment.actor.name} ${comment.actor.surname}(${comment.actor.userAccount.username})" code="comment.actor" readonly="true" size="50px"/>
		
		<acme:textarea path="text" code="comment.text" readonly="true"/>
		
		<acme:textbox path="starRating" code="comment.starRating" readonly="true"/>
		
		<!-- Action buttons -->
		<acme:submit_confirm name="delete" code="comment.delete" codeConfirm="comment.confirm.delete"/>
		&nbsp;
		<acme:cancel code="comment.delete.cancel" url="/comment/list.do?commentedEntityId=${commentedEntity.id}"/>
		
	</form:form>
	
</security:authorize>