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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<!-- Form -->
<form:form action="booking/customer/create.do" modelAttribute="booking">
	<!-- Hidden Attributes -->
	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:hidden path="creationMoment" />
	<form:hidden path="approved" />
	<form:hidden path="denied" />
	<form:hidden path="canceled" />

	<jstl:if test="${!muestraGyms}">
		<form:hidden path="gym" />
	</jstl:if>
	<form:hidden path="service" />

	<form:hidden path="customer" />
	<form:hidden path="administrator" />

	<!-- Editable Attributes -->

	<acme:textbox code="booking.requestMoment" path="requestMoment" />

	<acme:textbox code="booking.duration" path="duration" />

	<jstl:if test="${muestraGyms}">
		<acme:select items="${gyms}" itemLabel="name" code="booking.gym"
			path="gym" />
	</jstl:if>

	<!-- Action buttons -->

	<acme:submit name="save" code="booking.save" />

	<acme:cancel code="booking.cancel2" url="booking/customer/list.do?" />


</form:form>