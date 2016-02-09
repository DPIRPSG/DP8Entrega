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

<h3><spring:message code="orderItem.order"/>: <jstl:out value="${order.ticker}" /></h3>

<br/>

<form action="${requestURI}">
<input type="hidden" name="orderId" value="${orderId}"/>
	<select name="exchangeRateId">
		<jstl:forEach var="exchangeRateSel" items="${moneyList}">
			<jstl:if test="${exchangeRateSel.id == exchangeRate.id}">
				<option value="${exchangeRateSel.id}" selected="selected">${exchangeRateSel.name}</option>
			</jstl:if>
			<jstl:if test="${exchangeRateSel.id != exchangeRate.id}">
				<option value="${exchangeRateSel.id}">${exchangeRateSel.name}</option>
			</jstl:if>
		</jstl:forEach>
	</select> <input type="submit" value="<spring:message code="orderItem.change" />" />&nbsp;
</form>

<br/>

<spring:message code="orderItem.exchangeRate" var="message"/>
<jstl:out value="${message}: ${exchangeRate.name} [${exchangeRate.currency}]"/>
<br/>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="orders-item" requestURI="${requestURI}" id="row_OItem">
	
	<!-- Action links -->
	<display:column>
			<a href="order-item/clerk/serve.do?orderItemId=${row_OItem.id}"> <spring:message
					code="orderItem.serve" />
			</a>
	</display:column>

	<!-- Attributes -->
	<spring:message code="orderItem.sku" var="skuHeader" />
	<display:column title="${skuHeader}"
		sortable="true" >
		<jstl:out value="${row_OItem.sku}"/>
	</display:column>

	<spring:message code="orderItem.name" var="nameHeader" />
	<display:column title="${nameHeader}"
		sortable="true" >
		<jstl:out value="${row_OItem.name}"/>
	</display:column>
	
	<spring:message code="orderItem.price" var="priceHeader" />
	<display:column title="${priceHeader}"
		sortable="true" >
		<fmt:formatNumber value="${row_OItem.price * exchangeRate.rate}" maxFractionDigits="2" minFractionDigits="2"/>
	</display:column>
	
	<spring:message code="orderItem.price.tax" var="priceTaxHeader" />
	<display:column title="${priceTaxHeader}"
		sortable="true">
		<fmt:formatNumber value="${row_OItem.price * exchangeRate.rate * (1 - row_OItem.tax/100)}" maxFractionDigits="2" minFractionDigits="2"/>
	</display:column>
	
	<spring:message code="orderItem.units" var="unitsHeader" />
	<display:column title="${unitsHeader}"
		sortable="true" >
		<jstl:out value="${row_OItem.units}"/>
	</display:column>
	
	<spring:message code="orderItem.unitsServed" var="unitsServedHeader" />
	<display:column title="${unitsServedHeader}"
		sortable="true" >
		<jstl:out value="${row_OItem.unitsServed}"/>
	</display:column>

</display:table>

<input type="button" name="cancel"
		value="<spring:message code="orderItem.return" />"
		onclick="javascript: relativeRedir('order/clerk/list.do');" />
	<br />