<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access = "hasRole('ADMIN')">
	<!-- Form -->
	<form:form action="tax/administrator/edit.do" modelAttribute="tax">
		<!-- Hidden Attributes -->
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		
		<!-- Editable Attributes -->
		<form:label path="name">
			<spring:message code = "tax.name"/>
		</form:label>
		<form:input path="name"/>
		<form:errors cssClass="error" path="name"/>
		<br />
		
		<form:label path="value">
			<spring:message code = "tax.value"/>
		</form:label>
		<form:textarea path="value"/>
		<form:errors cssClass="error" path="value"/>
		<br />
	
		<!-- Action buttons -->
		<jstl:if test="${tax.id == 0 }">
			<input type="submit" name="save" value="<spring:message code="tax.edit.create" />" />&nbsp;	
		</jstl:if>
		<jstl:if test="${tax.id != 0 }">
			<input type="submit" name="save" value="<spring:message code="tax.save" />" />
			<input type="submit" name="delete" 
				value="<spring:message code="tax.delete" />" 
				onclick="return confirm('<spring:message code="tax.confirm.delete" />')" />&nbsp;
		</jstl:if>
	
		<input type="button" name="cancel"
			value="<spring:message code="tax.cancel" />"
			onclick="javascript: relativeRedir('tax/administrator/list.do');" />
		
	</form:form>

</security:authorize>
