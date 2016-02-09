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


<form action="${requestURI}">
	<input type="hidden" name="keyword" value="${keyword}"/>
	<select name="exchangeRateId">
		<jstl:forEach var="exchangeRateSel" items="${moneyList}">
			<jstl:if test="${exchangeRateSel.id == exchangeRate.id}">
				<option value="${exchangeRateSel.id}" selected="selected">${exchangeRateSel.name}</option>
			</jstl:if>
			<jstl:if test="${exchangeRateSel.id != exchangeRate.id}">
				<option value="${exchangeRateSel.id}">${exchangeRateSel.name}</option>
			</jstl:if>
		</jstl:forEach>
	</select> <input type="submit" value="<spring:message code="item.change" />" />&nbsp;
</form>

<br/>

<spring:message code="item.exchangeRate" var="message"/>
<jstl:out value="${message}: ${exchangeRate.name} [${exchangeRate.currency}]"/>
<br/>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="items" requestURI="${requestURI}" id="row_Item">
	<!-- Action links -->
	<security:authorize access="hasRole('CONSUMER')">
		<display:column>
			<a href="item/consumer/add.do?itemId=${row_Item.id}&?keyword=" onclick="return confirm('<spring:message code="item.add.advise" />')"> 
				<spring:message code="item.add" />
				
			</a>
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="item/administrator/edit.do?itemId=${row_Item.id}"> <spring:message
					code="item.edit" />
			</a>
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('CLERK')">
		<display:column>
			<a href="storage/clerk/list.do?warehouseId=&itemId=${row_Item.id}"> <spring:message
					code="item.storage" />
			</a>
		</display:column>
	</security:authorize>


	<!-- Attributes -->
	<spring:message code="item.category" var="categoryHeader" />
	<display:column property="category.name" title="${categoryHeader}"
		sortable="true" />

	<spring:message code="item.name" var="nameHeader" />
	<display:column title="${nameHeader}"
		sortable="true">
		<jstl:out value="${row_Item.name}"/>
	</display:column>

	<spring:message code="item.price" var="priceHeader" />
	<display:column title="${priceHeader}"
		sortable="true">
		<fmt:formatNumber value="${row_Item.price * exchangeRate.rate}" maxFractionDigits="2" minFractionDigits="2"/>
	</display:column>
	
	<spring:message code="item.price.tax" var="priceTaxHeader" />
	<display:column title="${priceTaxHeader}"
		sortable="true">
		<fmt:formatNumber value="${row_Item.price * exchangeRate.rate * (1 - row_Item.category.tax.value/100)}" maxFractionDigits="2" minFractionDigits="2"/>
	</display:column>

	<spring:message code="item.description" var="descriptionHeader" />
	<display:column title="${descriptionHeader}"
		sortable="false">
		<jstl:out value="${row_Item.description}"></jstl:out>
	</display:column>

	<spring:message code="item.tags" var="tagsHeader" />
	<display:column title="${tagsHeader}" 
		sortable="false">
		<jstl:out value="${row_Item.tags}"/>
	</display:column>

	<spring:message code="item.picture" var="pictureHeader" />
	<display:column title="${pictureHeader}"
		sortable="false" >
		<img src="${row_Item.picture}" style="width:204px;height:128px;"/>
	</display:column>

	<display:column>
		<a href="comment/list.do?itemId=${row_Item.id}"> <spring:message
				code="item.comments" />
		</a>
	</display:column>

</display:table>


<form action="${requestURI}">
	<input type="hidden" name="exchangeRateId" value="${exchangeRate.id}">
	<input type="text" name="keyword"> <input type="submit"
		value="<spring:message code="item.search" />" />&nbsp;
</form>


<!-- Action links -->
<security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="item/administrator/create.do"> <spring:message
				code="item.create" />
		</a>
	</div>
</security:authorize>

<!-- Alert -->
<jstl:if test="${messageStatus != Null && messageStatus != ''}">
	<spring:message code="${messageStatus}" var="showAlert" />
			<script>$(document).ready(function(){
		    alert("${showAlert}");
		  });
		</script>
</jstl:if>	
