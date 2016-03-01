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
<form:form action="message/actor/create.do" modelAttribute="messa">
	<!-- Hidden Attributes -->
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="sentMoment" />
	<form:hidden path="sender" />
	<form:hidden path="folders" />

	<form:label path="recipients">
		<spring:message code="message.recipients" />:
	</form:label>
	<form:select id="recipients" path="recipients" items="${actors}"
		itemLabel="userAccount.username" itemValue="id" multiple="multiple" />
	<form:errors cssClass="error" path="recipients" />
	<br />

	<acme:textbox code="message.subject" path="subject" />
	<acme:textarea code="message.body" path="body" />

	<br />

	<!-- Action buttons -->
	<acme:submit name="send" code="message.save"/>
	&nbsp; 
	<acme:cancel url="folder/actor/list.do" code="message.cancel"/>
	<br />

</form:form>