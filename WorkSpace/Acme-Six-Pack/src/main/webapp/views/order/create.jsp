<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

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
<br/>


<security:authorize access = "hasRole('CONSUMER')">
	<!-- Form -->
		<form:form action="order/consumer/create.do" modelAttribute="order">
		<!-- Hidden Attributes -->
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		
		<form:hidden path="placementMoment"/>
		<form:hidden path="ticker"/>
		<form:hidden path="amount"/>

		<form:hidden path="clerk"/>
		<form:hidden path="orderItems"/>
		<form:hidden path="consumer"/>
		
		<!-- Editable Attributes -->
		<form:label path="address">
			<spring:message code = "order.address"/>
		</form:label>
		<form:input path="address"/>
		<form:errors cssClass="error" path="address"/>
		<br /> <br/>
		
		<fieldset>
			<legend align="left">
				<spring:message code = "order.creditCard"/>
			</legend>
		
		<form:label path="creditCard.holderName">
			<spring:message code = "order.creditCard.holderName"/>
		</form:label>
		<form:input path="creditCard.holderName"/>
		<form:errors cssClass="error" path="creditCard.holderName"/>
		<br />
		
		<form:label path="creditCard.number">
			<spring:message code = "order.creditCard.number"/>
		</form:label>
		<form:input path="creditCard.number"/>
		<form:errors cssClass="error" path="creditCard.number"/>
		<br />
		
		<form:label path="creditCard.brandName">
			<spring:message code = "order.creditCard.brandName"/>
		</form:label>
		<form:input path="creditCard.brandName"/>
		<form:errors cssClass="error" path="creditCard.brandName"/>
		<br />
		
		<form:label path="creditCard.cvvCode">
			<spring:message code = "order.creditCard.cvvCode"/>
		</form:label>
		<form:input path="creditCard.cvvCode"/>
		<form:errors cssClass="error" path="creditCard.cvvCode"/>
		<br />
		
		<form:label path="creditCard.expirationMonth">
			<spring:message code = "order.creditCard.expirationMonth"/>
		</form:label>
		<form:input path="creditCard.expirationMonth"/>
		<form:errors cssClass="error" path="creditCard.expirationMonth"/>
		<br />
		
		<form:label path="creditCard.expirationYear">
			<spring:message code = "order.creditCard.expirationYear"/>
		</form:label>
		<form:input path="creditCard.expirationYear"/>
		<form:errors cssClass="error" path="creditCard.expirationYear"/>
		<br />
		
		</fieldset>
		<br/>
		
		<p><spring:message code = "order.amount"/>: <fmt:formatNumber value="${order.amount * exchangeRate.rate}" maxFractionDigits="2" minFractionDigits="2"/></p>
		
		<!-- Action buttons -->
		<input type="submit" name="save"
			value="<spring:message code="order.checkout"/>"
			onclick="return confirm('<spring:message code="order.checkout.advise" />')"/>
		&nbsp;
		<input type="button" name="cancel"
			value="<spring:message code="order.cancel" />"
			onclick="javascript: relativeRedir('shopping-cart/consumer/list.do');" />
		<br />
		
		</form:form>
</security:authorize>
