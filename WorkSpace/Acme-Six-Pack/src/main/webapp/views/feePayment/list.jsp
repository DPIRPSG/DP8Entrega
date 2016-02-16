<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADMIN')">

	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
		name="feePayments" requestURI="${requestURI}" id="row_feePayment">
		
		<!-- Action links -->
		
		<display:column>
			<a href="feePayment/administrator/edit.do?feePaymentId=${row_feePayment.id}">
				<spring:message	code="feePayment.edit" />
			</a>
		</display:column>		
		
		
		<!-- Attributes -->
		<spring:message code="feePayment.customer" var="customerHeader" />
		<display:column title="${customerHeader}"
			sortable="false" >
			<jstl:out value="${row_feePayment.customer.actor.userAccount.username}"/>
		</display:column>
		
		<spring:message code="feePayment.gym" var="gymHeader" />
		<display:column title="${gymHeader}"
			sortable="false" >
			<jstl:out value="${row_feePayment.gym.name}"/>
		</display:column>
	
		<spring:message code="feePayment.paymentMoment" var="paymentMomentHeader" />
		<display:column title="${paymentMomentHeader}"
			sortable="false" format="{0,date,yyyy/MM/dd}" >
			<jstl:out value="${row_feePayment.paymentMoment}"/>
		</display:column>
	
		<spring:message code="feePayment.creditCard" var="creditCardHeader" />
		<display:column title="${creditCardHeader}"
			sortable="false" >
			<jstl:out value="${row_feePayment.creditCard.number}"/>
		</display:column>
		
		<spring:message code="feePayment.activeMoment" var="activeMomentHeader" />
		<display:column title="${activeMomentHeader}"
			sortable="false" format="{0,date,yyyy/MM/dd}" >
			<jstl:out value="${row_feePayment.activeMoment}"/>
		</display:column>
		
		<spring:message code="feePayment.inactiveMoment" var="inactiveMomentHeader" />
		<display:column title="${inactiveMomentHeader}"
			sortable="false" format="{0,date,yyyy/MM/dd}" >
			<jstl:out value="${row_feePayment.inactiveMoment}"/>
		</display:column>
		
		<spring:message code="feePayment.amount" var="amountHeader" />
		<display:column title="${amountHeader}"
			sortable="false" >
			<jstl:out value="${row_feePayment.amount}"/>
		</display:column>
			
	</display:table>

</security:authorize>
