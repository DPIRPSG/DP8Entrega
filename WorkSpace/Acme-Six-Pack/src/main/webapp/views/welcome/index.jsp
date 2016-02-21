<%--
 * index.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


	


<jstl:if test="${messageStatus != Null && messageStatus != ''}">
	<spring:message code="${messageStatus}" var="showAlert" />
	<script>$(document).ready(function(){
	    alert("${showAlert}");
	  });
	</script>

</jstl:if>

<!-- Listing grid -->

<security:authorize access="hasRole('CUSTOMER')">
	<jstl:if test="${service != null}">
		<h3>
			<spring:message code="customer.service.notBooked" />
			:
		</h3>
	
		<p>
			<spring:message code="customer.service.name" />
			:
			<jstl:out value="${service.name}" />
		</p>
		<p>
			<spring:message code="customer.service.description" />
			:
			<jstl:out value="${service.description}" />
		</p>
		
		<spring:message code="customer.service.pictures" />
		<jstl:forEach items="${service.pictures}" var="picture">
			<span><img src="${picture}" style="width:204px;height:128px;"/></span>
		</jstl:forEach>
	
<!-- 		Insertar link para reservar el Servicio -->
	</jstl:if>
	<jstl:if test="${service == null}">
		<spring:message code="welcome.allServicesBooked"/>
	</jstl:if>
</security:authorize>

<p>
	<spring:message code="welcome.greeting.current.time" />
	${moment}
</p>
