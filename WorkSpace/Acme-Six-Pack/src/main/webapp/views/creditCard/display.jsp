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

	<!-- Listing grid -->
<jstl:if test="${creditCard != null}">	
	<div>
	<table>

	<tr>
		<th><spring:message code="creditCard.holderName" /> :</th>
		<td><jstl:out value="${creditCard.holderName}" /></td>
	</tr>
	<tr>
		<th><spring:message code="creditCard.brandName" /> :</th>
		<td><jstl:out value="${creditCard.brandName}" /></td>
	</tr>
	<tr>
		<th><spring:message code="creditCard.number" /> :</th>
		<td><jstl:out value="${creditCard.number}" /></td>
	</tr>
	<tr>
		<th><spring:message code="creditCard.expirationMonth" /> :</th>
		<td><jstl:out value="${creditCard.expirationMonth}" /></td>
	</tr>
	<tr>
		<th><spring:message code="creditCard.expirationYear" /> :</th>
		<td><jstl:out value="${creditCard.expirationYear}" /></td>
	</tr>
	<tr>
		<th><spring:message code="creditCard.cvvCode" /> :</th>
		<td><jstl:out value="${creditCard.cvvCode}" /></td>
	</tr>
	
	
	</table>
	</div>
</jstl:if>
<jstl:if test="${creditCard == null}">	
	<b><spring:message code="creditCard.null" /></b>
</jstl:if>
	
	<!-- Action links -->
<jstl:if test="${idCustomer != null}">
	<div>
		<b><a href="creditCard/customer/edit.do"> 
			<spring:message code="creditCard.edit" />
		</a></b>
	</div>
</jstl:if>
