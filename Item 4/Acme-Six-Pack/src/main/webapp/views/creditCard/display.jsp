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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


	<!-- Listing grid -->
<jstl:if test="${creditCard != null}">	
	<div>
	<table>
		<acme:display code="creditCard.holderName" value="${creditCard.holderName}"/>
		<acme:display code="creditCard.brandName" value="${creditCard.brandName}"/>
		<acme:display code="creditCard.number" value="${creditCard.number}"/>
		<acme:display code="creditCard.expirationMonth" value="${creditCard.expirationMonth}"/>
		<acme:display code="creditCard.expirationYear" value="${creditCard.expirationYear}"/>
		<acme:display code="creditCard.cvvCode" value="${creditCard.cvvCode}"/>
	
	</table>
	</div>
</jstl:if>
<jstl:if test="${creditCard == null}">	
	<b><spring:message code="creditCard.null" /></b>
</jstl:if>
	
	<!-- Action links -->
<jstl:if test="${customer != null}">
	<div>
		<b><a href="creditCard/customer/edit.do"> 
			<spring:message code="creditCard.edit" />
		</a></b>
	</div>
</jstl:if>
