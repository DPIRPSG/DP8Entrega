<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<jstl:if test="${byWarehouse}">
	<h3>
		<spring:message code="storage.warehouse" />: <jstl:out value="${warehouse.name}" />
	</h3>

	<br />

	<form action="${requestURI}">
		<input type="hidden" name="warehouseId" value="${warehouseId}" /> <input
			type="hidden" name="itemId" value="${itemId}" /> <select
			name="exchangeRateId">
			<jstl:forEach var="exchangeRateSel" items="${moneyList}">
				<jstl:if test="${exchangeRateSel.id == exchangeRate.id}">
					<option value="${exchangeRateSel.id}" selected="selected">${exchangeRateSel.name}</option>
				</jstl:if>
				<jstl:if test="${exchangeRateSel.id != exchangeRate.id}">
					<option value="${exchangeRateSel.id}">${exchangeRateSel.name}</option>
				</jstl:if>
			</jstl:forEach>
		</select> <input type="submit" value="<spring:message code="storage.change" />" />&nbsp;
	</form>

	<br />

	<spring:message code="storage.exchangeRate" var="message" />
	<jstl:out
		value="${message}: ${exchangeRate.name} [${exchangeRate.currency}]" />
</jstl:if>
<jstl:if test="${byItem}">
	<h3>
		<spring:message code="storage.item" /> <jstl:out value="${item.name}(${item.sku})" />
	</h3>
</jstl:if>

<br />

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" name="storages"
	requestURI="${requestURI}" id="row_storage">
	<!-- Action links -->
	<security:authorize access="hasRole('ADMIN')">
		<spring:message code="storage.edit" var="editHeader" />
		<display:column>
			<a href="storage/administrator/edit.do?storageId=${row_storage.id}">
				<spring:message code="storage.edit" />
			</a>
		</display:column>
	</security:authorize>
	<!-- Attributes -->

	<spring:message code="storage.units" var="unitsHeader" />
	<display:column property="units" title="${unitsHeader}"
		sortable="false" />

	<jstl:if test="${byWarehouse}">



		<spring:message code="storage.item.name" var="nameHeader" />
		<display:column title="${nameHeader}" sortable="true">
			<jstl:out value="${row_storage.item.name}" />
		</display:column>

		<spring:message code="storage.item.description"
			var="descriptionHeader" />
		<display:column title="${descriptionHeader}" sortable="false">
			<jstl:out value="${row_storage.item.description}" />
		</display:column>

		<spring:message code="storage.item.price" var="priceHeader" />
		<display:column title="${priceHeader}" sortable="true">
			<fmt:formatNumber value="${row_storage.item.price * exchangeRate.rate}" maxFractionDigits="2" minFractionDigits="2"/>
		</display:column>
		
		<spring:message code="storage.item.price.tax" var="priceTaxHeader" />
	<display:column title="${priceTaxHeader}"
		sortable="true">
		<fmt:formatNumber value="${row_storage.item.price * exchangeRate.rate * (1 - row_storage.item.category.tax.value/100)}" maxFractionDigits="2" minFractionDigits="2"/>
	</display:column>

	</jstl:if>

	<jstl:if test="${byItem}">

		<spring:message code="storage.warehouse.name" var="nameHeader" />
		<display:column title="${nameHeader}" sortable="true">
			<jstl:out value="${row_storage.wareHouse.name}" />
		</display:column>

		<spring:message code="storage.warehouse.address" var="addressHeader" />
		<display:column title="${addressHeader}" sortable="true">
			<jstl:out value="${row_storage.wareHouse.address}" />
		</display:column>

	</jstl:if>

</display:table>

<!-- Action links -->

<security:authorize access="hasRole('ADMIN')">
	<div>
		<b><a
			href="storage/administrator/create.do?warehouseId=${warehouseId}">
				<spring:message code="storage.create" />
		</a></b>
	</div>
</security:authorize>
