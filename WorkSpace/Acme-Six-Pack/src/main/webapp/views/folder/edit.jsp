<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Form -->
<form:form action="folder/actor/edit.do" modelAttribute="folder">
	<!-- Hidden Attributes -->
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="actor" />
	<form:hidden path="isSystem" />
	<form:hidden path="messages" />
	
	

	<form:label path="name">
		<spring:message code="folder.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />
	
	<!-- Action buttons -->
	<input type="submit" name="save"
		value="<spring:message code="folder.save" />" />&nbsp; 
	<jstl:if test="${folder.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="folder.delete" />"
			onclick="return confirm('<spring:message code="folder.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="folder.cancel" />"
		onclick="javascript: relativeRedir('folder/actor/list.do');" />
	<br />

</form:form>