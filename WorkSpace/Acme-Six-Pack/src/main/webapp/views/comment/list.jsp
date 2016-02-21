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

<h3><spring:message code="comment.entityPlural"/>: <jstl:out value="${commentedEntity.name}" /></h3>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="comments" requestURI="${requestURI}" id="row_Comment">
	
	<!-- Action links -->
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<acme:link href="comment/administrator/delete.do?commentId=${row_Comment.id}" code="comment.delete"/>
		</display:column>
	</security:authorize>
	
	<!-- Attributes -->
	<acme:displayColumn code="comment.actor" var="actorHeader" title="${actorHeader}" sorteable="true" value="${row_Comment.actor.name} ${row_Comment.actor.surname}(${row_Comment.actor.userAccount.username})"/>
	
	<acme:displayColumn code="comment.moment" var="momentHeader" title="${momentHeader}" sorteable="true" value="${row_Comment.moment}" format="{0,date,yyyy/MM/dd}"/>

	<acme:displayColumn code="comment.text" var="textHeader" title="${textHeader}" sorteable="true" value="${row_Comment.text}"/>

	<acme:displayColumn code="comment.starRating" var="starRatingHeader" title="${starRatingHeader}" sorteable="true" value="${row_Comment.starRating}"/>
		
</display:table>

<security:authorize access="isAuthenticated()">
	<div>
		<acme:link href="comment/actor/create.do?commentedEntityId=${commentedEntity.id}" code="comment.create"/>
	</div>
</security:authorize>
