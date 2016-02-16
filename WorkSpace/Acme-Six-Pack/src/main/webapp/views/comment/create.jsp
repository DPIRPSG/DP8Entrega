<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<jstl:if test="${gym != null}">
	<h3><spring:message code="comment.gym"/>: <jstl:out value="${gym.name}" /></h3>
</jstl:if>
<jstl:if test="${service != null}">
	<h3><spring:message code="comment.service"/>: <jstl:out value="${service.name}" /></h3>
</jstl:if>

<!-- Form -->
<form:form action="comment/create.do" modelAttribute="comment">
	<!-- Hidden Attributes -->
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="moment"/>
	<form:hidden path="gym"/>
	<form:hidden path="service"/>
	
	<!-- Editable Attributes -->
	<form:label path="title">
		<spring:message code = "comment.title"/>
	</form:label>
	<form:input path="title"/>
	<form:errors cssClass="error" path="title"/>
	<br />
	
	<form:label path="text">
		<spring:message code = "comment.text"/>
	</form:label>
	<form:input path="text"/>
	<form:errors cssClass="error" path="text"/>
	<br />
				
	<form:label path="rating">
		<spring:message code = "comment.rating"/>
	</form:label>
	<form:input path="rating"/>
	<form:errors cssClass="error" path="rating"/>
	<br />
	
	<!-- Action buttons -->
	<input type="submit" name="save"
		value="<spring:message code="comment.create.save"/>"/>
	&nbsp;
	<input type="button" name="cancel"
		value="<spring:message code="comment.create.cancel" />"
		onclick="javascript: relativeRedir('/comment/list.do?itemId=${comment.item.id}');" />
	<br />
	
</form:form>


