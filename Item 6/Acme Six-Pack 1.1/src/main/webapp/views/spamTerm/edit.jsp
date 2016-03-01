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
<form:form action="spamTerm/administrator/edit.do" modelAttribute="spamTerm">
	<!-- Hidden Attributes -->
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<acme:textbox code="spamTerm.term" path="term" />
	<br />
	
	<!-- Action buttons -->
	<acme:submit name="save" code="spamTerm.save"/>
	&nbsp;
	<jstl:if test="${spamTerm.id != 0}">
		<acme:submit_confirm name="delete" code="spamTerm.delete" codeConfirm="spamTerm.confirm.delete"/>
		&nbsp;
	</jstl:if>
	<acme:cancel url="spamTerm/administrator/list.do" code="spamTerm.cancel"/>
	<br />

</form:form>