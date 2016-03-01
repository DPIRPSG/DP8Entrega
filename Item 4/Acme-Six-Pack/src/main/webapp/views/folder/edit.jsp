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
<form:form action="folder/actor/edit.do" modelAttribute="folder">
	<!-- Hidden Attributes -->
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="actor" />
	<form:hidden path="isSystem" />
	<form:hidden path="messages" />
	
	<acme:textbox code="folder.name" path="name" />
	<br />
	
	<!-- Action buttons -->
	<acme:submit name="save" code="folder.save"/>
	&nbsp;
	<jstl:if test="${folder.id != 0}">
		<acme:submit_confirm name="delete" code="folder.delete" codeConfirm="folder.confirm.delete" />
		&nbsp;
	</jstl:if>
	<acme:cancel url="folder/actor/list.do" code="folder.cancel"/>
	<br />

</form:form>