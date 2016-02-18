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
<form:form action="spamTerm/administrator/edit.do" modelAttribute="spamTerm">
	<!-- Hidden Attributes -->
	<form:hidden path="id" />
	<form:hidden path="version" />
	

	<form:label path="term">
		<spring:message code="spamTerm.term" />:
	</form:label>
	<form:input path="term" />
	<form:errors cssClass="error" path="term" />
	<br />
	
	<!-- Action buttons -->
	<input type="submit" name="save"
		value="<spring:message code="spamTerm.save" />" />&nbsp; 
	<jstl:if test="${spamTerm.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="spamTerm.delete" />"
			onclick="return confirm('<spring:message code="spamTerm.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="spamTerm.cancel" />"
		onclick="javascript: relativeRedir('spamTerm/administrator/list.do');" />
	<br />

</form:form>