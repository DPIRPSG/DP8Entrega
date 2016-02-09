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
	<form:form action="consumer/create.do" modelAttribute="consumer">
		<!-- Hidden Attributes -->
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		<form:hidden path="folders"/>
		<form:hidden path="received"/>
		<form:hidden path="sent"/>
		<form:hidden path="orders"/>
		<form:hidden path="userAccount.authorities"/>
		
		
		<!-- Editable Attributes -->
		<form:label path="name">
			<spring:message code = "consumer.name"/>
		</form:label>
		<form:input path="name"/>
		<form:errors cssClass="error" path="name"/>
		<br />
		
		<form:label path="surname">
			<spring:message code = "consumer.surname"/>
		</form:label>
		<form:input path="surname"/>
		<form:errors cssClass="error" path="surname"/>
		<br />
		
		<form:label path="email">
			<spring:message code = "consumer.email"/>
		</form:label>
		<form:input path="email"/>
		<form:errors cssClass="error" path="email"/>
		<br />
					
		<form:label path="phone">
			<spring:message code = "consumer.phone"/>
		</form:label>
		<form:input path="phone"/>
		<form:errors cssClass="error" path="phone"/>
		<br />
		
		
		<form:label path="userAccount.username">
			<spring:message code="consumer.username" />
		</form:label>
		<form:input path="userAccount.username" />	
		<form:errors class="error" path="userAccount.username" />
		<br />

		<form:label path="userAccount.password">
			<spring:message code="consumer.password" />
		</form:label>
		<form:password path="userAccount.password" />
		<form:errors class="error" path="userAccount.password" />
		<br />
		
		
		<!-- Action buttons -->
		<input type="submit" name="save"
			value="<spring:message code="consumer.save"/>"/>
		&nbsp;
		<input type="button" name="cancel"
			value="<spring:message code="consumer.cancel" />"
			onclick="javascript: relativeRedir('/');" />
		<br />
		
	</form:form>

</security:authorize>
