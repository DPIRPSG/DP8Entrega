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

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="bookings" requestURI="${requestURI}" id="row_Booking">
	
	<!-- Action links -->
	<security:authorize access="hasRole('CUSTOMER')">
		<spring:message code="booking.cancel" var="cancelHeader" />


		<jstl:if
			test="${row_Booking.approved == false && row_Booking.denied == false && row_Booking.canceled == false}">
			<display:column title="${cancelHeader}" sortable="false">
				<jstl:if test="${row_Booking.requestMoment > moment}">
					<a href="booking/customer/cancel.do?bookingId=${row_Booking.id}"
						onclick="return confirm('<spring:message code="booking.cancel.advise" />')">
						<spring:message code="booking.cancel2" />		
					</a>	
				</jstl:if>
				
			</display:column>
		</jstl:if>


		<jstl:if test="${row_Booking.approved == true || row_Booking.denied == true || row_Booking.canceled == true}">
			<display:column title="${cancelHeader}"
				sortable="false" />
		</jstl:if>
	</security:authorize>

	<!-- Attributes -->
	<security:authorize access="hasRole('ADMIN')">
		<spring:message code="booking.customer" var="customerHeader" />
		<display:column title="${customerHeader}"
			sortable="true">
			<jstl:out value="${row_Booking.customer.name}"/>
		</display:column>
	</security:authorize>
	
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
			
		<security:authorize access="hasRole('CUSTOMER')">
			<display:column title="${approvedHeader}" sortable="false">
			<jstl:out value="${row_Booking.approved}"/>
			</display:column>
		</security:authorize>
		
		<security:authorize access="hasRole('ADMIN')">
			<display:column title="${approvedHeader}" sortable="false">
			<jstl:if test="${row_Booking.requestMoment > moment}">
			<jstl:if test="${row_Booking.approved == false && row_Booking.denied == false && row_Booking.canceled == false}">
				<a href="booking/administrator/approve.do?bookingId=${row_Booking.id}"
				onclick = "return confirm('<spring:message code="booking.approve.advise" />')"> <spring:message
					code="booking.approve" />
				</a>
			</jstl:if>
			</jstl:if>
			<jstl:if test="${row_Booking.approved != false || row_Booking.denied != false || row_Booking.canceled != false || row_Booking.requestMoment <= moment}">
				<jstl:out value="${row_Booking.approved}"/>
			</jstl:if>
			</display:column>
		</security:authorize>
		
	<spring:message code="booking.denied" var="deniedHeader" />
		
		<security:authorize access="hasRole('CUSTOMER')">
			<display:column title="${deniedHeader}" sortable="false">
				<jstl:out value="${row_Booking.denied}"/>
			</display:column>
		</security:authorize>

		<security:authorize access="hasRole('ADMIN')">
			<display:column title="${deniedHeader}" sortable="false">
			<jstl:if test="${row_Booking.requestMoment > moment}">
			<jstl:if test="${row_Booking.approved == false && row_Booking.denied == false && row_Booking.canceled == false}">
				<a href="booking/administrator/deny.do?bookingId=${row_Booking.id}"
				onclick = "return confirm('<spring:message code="booking.deny.advise" />')"> <spring:message
					code="booking.deny" />
				</a>
			</jstl:if>
			</jstl:if>
			<jstl:if test="${row_Booking.approved != false || row_Booking.denied != false || row_Booking.canceled != false || row_Booking.requestMoment <= moment}">
				<jstl:out value="${row_Booking.denied}"/>
			</jstl:if>
			</display:column>	
		</security:authorize>
	
	<spring:message code="booking.canceled" var="canceledHeader" />
		<display:column title="${canceledHeader}" sortable="false">
			<jstl:out value="${row_Booking.canceled}"/>
		</display:column>
	

</display:table>

<!-- Action links -->
<%-- <security:authorize access="hasRole('CUSTOMER')"> --%>
<!-- 	<div> -->
<!-- 		<a href="booking/customer/create.do"> -->
<%-- 			<spring:message code="booking.create" /> --%>
<!-- 		</a> -->
<!-- 	</div> -->
<%-- </security:authorize> --%>

<!-- Alert -->
<jstl:if test="${messageStatus != Null && messageStatus != ''}">
	<spring:message code="${messageStatus}" var="showAlert" />
			<script>
				$(document).ready(function(){
		    		alert("${showAlert}");
				});
			</script>
</jstl:if>	
