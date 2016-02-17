<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADMIN')">

	<!-- Form -->
	<form:form action="feePayment/edit.do" modelAttribute="feePayment">
		<!-- Hidden Attributes -->
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		
		<!-- Editable Attributes -->
		
		<form:hidden path="customer"/>
		<p><spring:message code = "feePayment.customer"/>: <jstl:out value="${feePayment.customer.actor.name}" />(<jstl:out value="${feePayment.customer.actor.userAccount.userName}" />)</p>
		
		<form:hidden path="gym"/>
		<p><spring:message code = "feePayment.gym"/>: <jstl:out value="${feePayment.gym.name}" /></p>
		
		<form:hidden path="paymentMoment"/>
		<p><spring:message code = "feePayment.paymentMoment"/>: <jstl:out value="${feePayment.paymentMoment}" /></p>
		
		<form:hidden path="creditCard"/>
		<p><spring:message code = "feePayment.creditCard"/>: <jstl:out value="${feePayment.creditCard}" /></p>
		
		<form:hidden path="activeMoment"/>
		<p><spring:message code = "feePayment.activeMoment"/>: <jstl:out value="${feePayment.activeMoment}" /></p>
		
		<form:label path="inactiveMoment">
			<spring:message code = "feePayment.inactiveMoment"/>
		</form:label>
		<form:input path="inactiveMoment"/>
		<form:errors cssClass="error" path="inactiveMoment"/>
		<br />
		
		<form:hidden path="amount"/>
		<p><spring:message code = "feePayment.amount"/>: <jstl:out value="${feePayment.amount}" /></p>
		
		<!-- Action buttons -->
		<input type="submit" name="save"
			value="<spring:message code="feePayment.edit.save"/>"/>
		&nbsp;
		<input type="button" name="cancel"
		value="<spring:message code="feePayment.edit.cancel" />"
		onclick="javascript: relativeRedir('/feePayment/administrator/list.do');" />
		
	</form:form>
	
</security:authorize>


