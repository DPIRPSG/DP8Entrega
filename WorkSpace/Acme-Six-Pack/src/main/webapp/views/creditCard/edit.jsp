<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

	<!-- Form -->
	<form:form action="${urlAction}" modelAttribute="creditCard">
		<!-- Hidden Attributes -->
	
		<!-- Editable Attributes -->

		<acme:textbox code="creditCard.holderName" path="holderName" />
		<br />
		<acme:textbox code="creditCard.brandName" path="brandName" />
		<br />
		<acme:textbox code="creditCard.number" path="number" />
		<br />
		<acme:textbox code="creditCard.expirationMonth" path="expirationMonth" />
		<br />
		<acme:textbox code="creditCard.expirationYear" path="expirationYear" />
		<br />		
		<acme:textbox code="creditCard.cvvCode" path="cvvCode" />
		<br />		
		
		<!-- Action buttons -->
		<input type="submit" name="save"
			value="<spring:message code="creditCard.save"/>"/>
		&nbsp;
		<jstl:if test="${idCustomer != null}">
			<input type="submit" name="delete"
				value="<spring:message code="creditCard.delete"/>"/>
			&nbsp;
		</jstl:if>
		
		<input type="button" name="cancel"
			value="<spring:message code="creditCard.cancel" />"
			onclick="javascript: relativeRedir('${urlReturn}');" />
		<br />
		
	</form:form>