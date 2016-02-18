<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<jstl:if test="${gym != null}">
	<h3><spring:message code="comment.gymPlural"/>: <jstl:out value="${gym.name}" /></h3>
</jstl:if>
<jstl:if test="${service != null}">
	<h3><spring:message code="comment.servicePlural"/>: <jstl:out value="${service.name}" /></h3>
</jstl:if>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="comments" requestURI="${requestURI}" id="row_Comment">
	
	<!-- Action links -->
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="comment/administrator/delete.do?commentId=${row_Comment.id}">
				<spring:message	code="comment.delete" />
			</a>
		</display:column>		
	</security:authorize>
	
	<!-- Attributes -->
	<spring:message code="comment.actor" var="actorHeader" />
	<display:column title="${actorHeader}"
		sortable="false" >
		<jstl:out value="${row_Comment.actor.userAccount.username}"/>
	</display:column>
	
	<spring:message code="comment.moment" var="momentHeader" />
	<display:column title="${momentHeader}" 
		sortable="false" format="{0,date,yyyy/MM/dd }" >
		<jstl:out value="${row_Comment.moment}"/>
	</display:column>

	<spring:message code="comment.text" var="textHeader" />
	<display:column title="${textHeader}" 
		sortable="false" >
		<jstl:out value="${row_Comment.text}"/>
	</display:column>

	<spring:message code="comment.starRating" var="starRatingHeader" />
	<display:column title="${starRatingHeader}" 
		sortable="true" >
		<jstl:out value="${row_Comment.starRating}"/>
	</display:column>
		
</display:table>

<!-- Action links -->
<security:authorize access="isAuthenticated()">
	<jstl:if test="${gym != null}">
		<div>
			<a href="comment/create.do?gymId=${gym.id}"> <spring:message
					code="comment.create" />
			</a>
		</div>
	</jstl:if>
	<jstl:if test="${service != null}">
		<div>
			<a href="comment/create.do?serviceId=${service.id}"> <spring:message
					code="comment.create" />
			</a>
		</div>
	</jstl:if>
</security:authorize>
