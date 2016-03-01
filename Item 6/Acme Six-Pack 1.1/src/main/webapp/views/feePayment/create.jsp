<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<security:authorize access = "hasRole('CUSTOMER')">
	<!-- Form -->
		<form:form action="feePayment/customer/create.do" modelAttribute="feePayment">
		<!-- Hidden Attributes -->
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		
		<form:hidden path="paymentMoment"/>
		<form:hidden path="inactiveMoment"/>
		<form:hidden path="amount"/>

		<form:hidden path="gym"/>
		<form:hidden path="customer"/>
		
		<!-- Editable Attributes -->
		
		<spring:message code="feePayment.dateFormat" />
		<acme:textbox code="feePayment.activeMoment" path="activeMoment" />
		
		<fieldset>
			<legend align="left">
				<spring:message code = "feePayment.creditCard"/>
			</legend>
		
		<acme:textbox code="feePayment.holderName" path="creditCard.holderName" />
		<acme:textbox code="feePayment.brandName" path="creditCard.brandName" />
		<acme:textbox code="feePayment.number" path="creditCard.number" />
		<acme:textbox code="feePayment.expirationMonth" path="creditCard.expirationMonth" />
		<acme:textbox code="feePayment.expirationYear" path="creditCard.expirationYear" />
		<acme:textbox code="feePayment.cvvCode" path="creditCard.cvvCode" />
		
		</fieldset>
		<br/>
		
		<!-- Action buttons -->
		<acme:submit name="save" code="feePayment.edit.save"/>
		<input type="button" name="cancel"
			value="<spring:message code="feePayment.edit.cancel" />"
			onclick="javascript: relativeRedir('gym/customer/list-feepayments-not-active.do?');" />
		<br />
		
		</form:form>
</security:authorize>
