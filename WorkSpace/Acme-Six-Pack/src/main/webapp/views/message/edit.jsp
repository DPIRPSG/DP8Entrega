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
<form:form action="message/actor/edit.do" modelAttribute="messa">
	<!-- Hidden Attributes -->
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment" />
	<form:hidden path="sender" />
	<form:hidden path="recipients" />
	<form:hidden path="body" />
	<form:hidden path="subject" />

	<form:label path="folders">
		<spring:message code="message.folders" />:
	</form:label>
	<form:select id="folders" path="folders" items="${foldersActor}"
		itemLabel="name" itemValue="id" multiple="multiple" />
	<form:errors cssClass="error" path="folders" />
	<br />

	<!-- Action buttons -->
	<input type="submit" name="save"
		value="<spring:message code="message.save" />" />&nbsp; 

	<input type="button" name="cancel"
		value="<spring:message code="message.cancel" />"
		onclick="javascript: relativeRedir('message/actor/display.do?messageId=${messa.id}');" />
	<br />

</form:form>