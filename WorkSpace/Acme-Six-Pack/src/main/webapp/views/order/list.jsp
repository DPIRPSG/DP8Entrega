<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasAnyRole('ADMIN', 'CLERK', 'CONSUMER')">

<form action="${requestURI}">
	<select name="exchangeRateId">
		<jstl:forEach var="exchangeRateSel" items="${moneyList}">
			<jstl:if test="${exchangeRateSel.id == exchangeRate.id}">
				<option value="${exchangeRateSel.id}" selected="selected">${exchangeRateSel.name}</option>
			</jstl:if>
			<jstl:if test="${exchangeRateSel.id != exchangeRate.id}">
				<option value="${exchangeRateSel.id}">${exchangeRateSel.name}</option>
			</jstl:if>
		</jstl:forEach>
	</select> <input type="submit" value="<spring:message code="order.change" />" />&nbsp;
</form>

<br/>

<spring:message code="order.exchangeRate" var="message"/>
<jstl:out value="${message}: ${exchangeRate.name} [${exchangeRate.currency}]"/>
<br/>

	
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
		name="orders" requestURI="${requestURI}" id="row_order">
		
		<security:authorize access="hasRole('CONSUMER')">
			<spring:message code="order.delete" var="deleteHeader" />
			<jstl:if test="${row_order.clerk == null && row_order.cancelMoment == null}">
				<display:column>
					<a href="order/consumer/cancel.do?orderId=${row_order.id}" onclick="return confirm('<spring:message code="order.cancel.advise" />')"> <spring:message
						code="order.cancel" />
					</a>
				</display:column>	
			</jstl:if>
			<jstl:if test="${row_order.clerk != null || row_order.cancelMoment != null}">
				<display:column title="${deleteHeader}"
					sortable="false" />
			</jstl:if>
		</security:authorize>
		
		<!-- Attributes -->
		<spring:message code="order.ticker" var="tickerHeader" />
		<display:column title="${tickerHeader}"
			sortable="false" >
			<jstl:out value="${row_order.ticker}"/>
		</display:column>

		<spring:message code="order.placementMoment"
			var="placementMomentHeader" />
		<display:column title="${placementMomentHeader}"
			sortable="true" format="{0,date,yyyy/MM/dd }" >
			<jstl:out value="${row_order.placementMoment}"/>
		</display:column>

		<spring:message code="order.deliveryMoment" var="deliveryMomentHeader" />
		<display:column title="${deliveryMomentHeader}"
			sortable="true" format="{0,date,yyyy/MM/dd }" >
			<jstl:out value="${row_order.deliveryMoment}"/>
		</display:column>

		<spring:message code="order.cancelMoment" var="cancelMomentHeader" />
		<display:column title="${cancelMomentHeader}"
			sortable="true" format="{0,date,yyyy/MM/dd }" >
			<jstl:out value="${row_order.cancelMoment}"/>
		</display:column>

		<spring:message code="order.amount" var="amountHeader" />
		<display:column title="${amountHeader}"
			sortable="true" >
			<fmt:formatNumber value="${row_order.amount * exchangeRate.rate}" maxFractionDigits="2" minFractionDigits="2"/>
		</display:column>

		<security:authorize access="hasRole('CLERK')">
			<spring:message code="order.clerk" var="clerkHeader" />
			<jstl:set var="clerkUsername"
				value="${row_order.clerk.userAccount.username}" />
			<jstl:if test="${clerkUsername == null}">
				<display:column sortable="true" title="${clerkHeader}">
					<a href="order/clerk/self-assign.do?orderId=${row_order.id}"> <spring:message
							code="order.self-assign" />
					</a>
				</display:column>
			</jstl:if>
			<jstl:if test="${clerkUsername != null}">
				<display:column title="${clerkHeader}" sortable="true">
					<jstl:out value="${clerkUsername}" />
				</display:column>
			</jstl:if>
		</security:authorize>

		<security:authorize access="hasRole('ADMIN')">
			<spring:message code="order.clerk" var="clerkHeader" />
			<display:column title="${clerkHeader}"
				sortable="false" >
				<jstl:out value="${row_order.clerk.userAccount.username}"/>
			</display:column>
		</security:authorize>

		<security:authorize access="!hasAnyRole('CONSUMER')">
			<spring:message code="order.consumer" var="consumerHeader" />
			<display:column title="${consumerHeader}"
				sortable="false" >
				<jstl:out value="${row_order.consumer.userAccount.username}"/>
			</display:column>
		</security:authorize>
		
		<security:authorize access="hasRole('ADMIN')">
			<spring:message code="order.creditCard.number.list" var="creditCardHeader"/>
			<display:column title="${creditCardHeader}"
				sortable="false">
				<jstl:out value="${row_order.creditCard.number}"/>
			</display:column>
		</security:authorize>
		
		<security:authorize access="hasRole('CLERK')">
				<display:column>
					<a href="order-item/clerk/list.do?orderId=${row_order.id}"> <spring:message
						code="order.orderItems" />
					</a>
				</display:column>	
		</security:authorize>
	</display:table>

	<!-- Alert -->
	<jstl:if test="${messageStatus != Null && messageStatus != ''}">
		<spring:message code="${messageStatus}" var="showAlert" />
		<script>$(document).ready(function(){
		    alert("${showAlert}");
		  });
		</script>
		
	</jstl:if>	

</security:authorize>