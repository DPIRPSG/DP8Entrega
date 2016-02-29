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


	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
		name="feePayments" requestURI="${requestURI}" id="row_feePayment">
		
		<!-- Action links -->
		<security:authorize access="hasRole('ADMIN')">
			<display:column>
				<acme:link href="feePayment/administrator/edit.do?feePaymentId=${row_feePayment.id}" code="feePayment.edit"/>
			</display:column>
		</security:authorize>	
		
		
		<!-- Attributes -->
		<spring:message code="feePayment.customer" var="customerHeader" />
		<acme:displayColumn title="${customerHeader}" sorteable="true" value="${row_feePayment.customer.userAccount.username}"/>
		
		<spring:message code="feePayment.gym" var="gymHeader" />
		<acme:displayColumn title="${gymHeader}" sorteable="true" value="${row_feePayment.gym.name}"/>
	
		<spring:message code="feePayment.paymentMoment" var="paymentMomentHeader" />
		<acme:displayColumn title="${paymentMomentHeader}" sorteable="true" value="${row_feePayment.paymentMoment}" format="{0,date,yyyy/MM/dd}"/>
	
		<spring:message code="feePayment.creditCard" var="creditCardHeader" />
		<acme:displayColumn title="${creditCardHeader}" sorteable="true" value="${row_feePayment.creditCard.number}"/>
		
		<spring:message code="feePayment.activeMoment" var="activeMomentHeader" />
		<acme:displayColumn title="${activeMomentHeader}" sorteable="true" value="${row_feePayment.activeMoment}" format="{0,date,yyyy/MM/dd}"/>
		
		<spring:message code="feePayment.inactiveMoment" var="inactiveMomentHeader" />
		<acme:displayColumn title="${inactiveMomentHeader}" sorteable="true" value="${row_feePayment.inactiveMoment}" format="{0,date,yyyy/MM/dd}"/>
		
		<spring:message code="feePayment.amount" var="amountHeader" />
		<acme:displayColumn title="${amountHeader}" sorteable="true" value="${row_feePayment.amount}"/>
		
			
	</display:table>


