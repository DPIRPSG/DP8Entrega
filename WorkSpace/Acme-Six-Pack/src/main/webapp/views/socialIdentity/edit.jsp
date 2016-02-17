<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access = "isAnonymous()">
	<!-- Form -->
	<form:form action="socialIdentity/customer/edit.do" modelAttribute="socialIdentity">
		<!-- Hidden Attributes -->
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		
		
		<!-- Editable Attributes -->
		<form:label path="nick">
			<spring:message code = "socialIdentity.nick"/>
		</form:label>
		<form:input path="nick"/>
		<form:errors cssClass="error" path="nick"/>
		<br />
		
		<form:label path="name">
			<spring:message code = "socialIdentity.name"/>
		</form:label>
		<form:input path="name"/>
		<form:errors cssClass="error" path="name"/>
		<br />
		
		<form:label path="homePage">
			<spring:message code = "socialIdentity.homePage"/>
		</form:label>
		<form:input path="homePage"/>
		<form:errors cssClass="error" path="homePage"/>
		<br />
					
		<form:label path="picture">
			<spring:message code = "socialIdentity.picture"/>
		</form:label>
		<form:input path="picture"/>
		<form:errors cssClass="error" path="picture"/>
		<br />
		
		
		<!-- Action buttons -->
		<input type="submit" name="save"
			value="<spring:message code="socialIdentity.save"/>"/>
		&nbsp;
		<input type="submit" name="delete"
			value="<spring:message code="socialIdentity.delete"/>"/>
		&nbsp;
		<input type="button" name="cancel"
			value="<spring:message code="socialIdentity.cancel" />"
			onclick="javascript: relativeRedir('socialIdentity/customer/display.do');" />
		<br />
		
	</form:form>

</security:authorize>
