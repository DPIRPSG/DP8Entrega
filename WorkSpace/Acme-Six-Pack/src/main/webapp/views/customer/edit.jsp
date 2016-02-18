<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

	<!-- Form -->
	<form:form action="${urlAction}" modelAttribute="customer">
		<!-- Hidden Attributes -->
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		<form:hidden path="messageBoxs"/>
		<!--<form:hidden path="creditCard"/>-->
		<!--<form:hidden path="socialIdentity"/>-->
		<form:hidden path="feePayment"/>
		<form:hidden path="booking"/>
		<form:hidden path="comments"/>
		<form:hidden path="received"/>
		<form:hidden path="sent"/>
		<form:hidden path="userAccount.authorities"/>
		
		<!-- Editable Attributes -->
		<form:label path="name">
			<spring:message code = "customer.name"/>
		</form:label>
		<form:input path="name"/>
		<form:errors cssClass="error" path="name"/>
		<br />
		
		<form:label path="surname">
			<spring:message code = "customer.surname"/>
		</form:label>
		<form:input path="surname"/>
		<form:errors cssClass="error" path="surname"/>
		<br />
		
		<form:label path="phone">
			<spring:message code = "customer.phone"/>
		</form:label>
		<form:input path="phone"/>
		<form:errors cssClass="error" path="phone"/>
		<br />
		
		<jstl:if test="${creating != null}">
			<form:label path="userAccount.username">
				<spring:message code="customer.username" />
			</form:label>
			<form:input path="userAccount.username" />	
			<form:errors class="error" path="userAccount.username" />
			
			<br />

			<form:label path="userAccount.password">
				<spring:message code="customer.password" />
			</form:label>
			<form:password path="userAccount.password" />
			<form:errors class="error" path="userAccount.password" />
		</jstl:if>
		
		<br />
		
		<!-- Action buttons -->
		<input type="submit" name="save"
			value="<spring:message code="customer.save"/>"/>
		&nbsp;
		<input type="button" name="cancel"
			value="<spring:message code="customer.cancel" />"
			onclick="javascript: relativeRedir('/');" />
		<br />
		
	</form:form>
