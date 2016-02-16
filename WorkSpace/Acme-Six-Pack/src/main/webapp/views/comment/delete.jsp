<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADMIN')">

	<jstl:if test="${gym != null}">
		<h3><spring:message code="comment.gym"/>: <jstl:out value="${gym.name}" /></h3>
	</jstl:if>
	<jstl:if test="${service != null}">
		<h3><spring:message code="comment.service"/>: <jstl:out value="${service.name}" /></h3>
	</jstl:if>
	
	<!-- Form -->
	<form:form action="comment/administrator/delete.do" modelAttribute="comment">
		<!-- Hidden Attributes -->
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		<form:hidden path="gym"/>
		<form:hidden path="service"/>
		
		<!-- Shown Attributes -->
		<form:hidden path="moment"/>
		<p><spring:message code = "comment.moment"/>: <jstl:out value="${comment.moment}" /></p>
		
		<form:hidden path="actor"/>
		<p><spring:message code = "comment.actor"/>: <jstl:out value="${comment.actor.name}" />(<jstl:out value="${comment.actor.userAccount.username}" />)</p>
					
		<form:hidden path="text"/>
		<p><spring:message code = "comment.text"/>: <jstl:out value="${comment.text}" /></p>
		
		<form:hidden path="starRating"/>
		<p><spring:message code = "comment.starRating"/>: <jstl:out value="${comment.starRating}" /></p>
		
		<!-- Action buttons -->
		<input type="submit" name="delete"
				value="<spring:message code="comment.delete" />"
				onclick="return confirm('<spring:message code="comment.confirm.delete" />')" />
		&nbsp;
		<input type="button" name="cancel"
			value="<spring:message code="comment.delete.cancel" />"
			onclick="javascript: relativeRedir('/comment/list.do?itemId=${item.id}');" />
		
	</form:form>
	
</security:authorize>