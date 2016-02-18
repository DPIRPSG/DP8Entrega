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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="bookings" requestURI="${requestURI}" id="row_Booking">
	<!-- Action links -->

	<security:authorize access="hasRole('ADMIN')">
		<spring:message code="booking.cancel" var="cancelHeader" />
		<jstl:if test="${row_Booking.approved == true || row_Booking.denied == true}">
			<display:column>
				<a href="booking/consumer/cancel.do?bookingId=${row_Booking.id}" onclick="return confirm('<spring:message code="booking.cancel.advise" />')"> <spring:message
					code="booking.cancel2" />
				</a>
			</display:column>	
		</jstl:if>
		<jstl:if test="${row_Booking.approved == false || row_Booking.denied == false}">
			<display:column title="${cancelHeader}"
				sortable="false" />
		</jstl:if>
	</security:authorize>

	<!-- Attributes -->
	
	<spring:message code="booking.customer" var="customerHeader" />
	<display:column title="${customerHeader}"
		sortable="true">
		<jstl:out value="${row_Booking.customer.name}"/>
	</display:column>
	
	<spring:message code="booking.service" var="serviceHeader" />
	<display:column title="${serviceHeader}"
		sortable="true">
		<jstl:out value="${row_Booking.service.name}"/>
	</display:column>
	
	<spring:message code="booking.creationMoment" var="creationMomentHeader" />
	<display:column title="${creationMomentHeader}"
		sortable="true">
		<jstl:out value="${row_Booking.creationMoment}"/>
	</display:column>
	
	<spring:message code="booking.requestMoment" var="requestMomentHeader" />
	<display:column title="${requestMomentHeader}"
		sortable="true">
		<jstl:out value="${row_Booking.requestMoment}"/>
	</display:column>		
	
	<spring:message code="booking.duration" var="durationHeader" />
	<display:column title="${durationHeader}"
		sortable="true">
		<jstl:out value="${row_Booking.duration}"/>
	</display:column>
	
	<spring:message code="booking.approved" var="approvedHeader" />
	<display:column title="${approvedHeader}"
		sortable="true">
		<jstl:out value="${row_Booking.approved}"/>
	</display:column>
	
	<spring:message code="booking.denied" var="deniedHeader" />
	<display:column title="${deniedHeader}"
		sortable="true">
		<jstl:out value="${row_Booking.denied}"/>
	</display:column>
	
	<spring:message code="booking.canceled" var="canceledHeader" />
	<display:column title="${canceledHeader}"
		sortable="true">
		<jstl:out value="${row_Booking.canceled}"/>
	</display:column>

</display:table>

<!-- Action links -->
<security:authorize access="hasRole('CUSTOMER')">
	<div>
		<a href="booking/customer/create.do">
			<spring:message code="booking.create" />
		</a>
	</div>
</security:authorize>

<!-- Alert -->
<jstl:if test="${messageStatus != Null && messageStatus != ''}">
	<spring:message code="${messageStatus}" var="showAlert" />
			<script>
				$(document).ready(function(){
		    		alert("${showAlert}");
				});
			</script>
</jstl:if>	
