<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


	<!-- Form -->
	<form:form action="${urlAction}" modelAttribute="creditCard">
		<!-- Hidden Attributes -->
	
		<!-- Editable Attributes -->

		<acme:textbox code="creditCard.holderName" path="holderName" />
		<acme:textbox code="creditCard.brandName" path="brandName" />
		<acme:textbox code="creditCard.number" path="number" />
		<acme:textbox code="creditCard.expirationMonth" path="expirationMonth" />
		<acme:textbox code="creditCard.expirationYear" path="expirationYear" />
		<acme:textbox code="creditCard.cvvCode" path="cvvCode" />
		<br />
		
		<!-- Action buttons -->
		<acme:submit name="save" code="creditCard.save"/>
		&nbsp;
		<jstl:if test="${customer != null}">
			<acme:submit name="delete" code="creditCard.delete"/>
			&nbsp;
		</jstl:if>
		
		<acme:cancel url="${urlReturn}" code="creditCard.cancel"/>
		<br />
		
	</form:form>