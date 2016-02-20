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
	<div>
	<table>

	<acme:display code="actor.name" value="${actor.name}"/>
	<acme:display code="actor.surname" value="${actor.surname}"/>
	<acme:display code="actor.phone" value="${actor.phone}"/>
	<acme:display code="actor.username" value="${actor.userAccount.username}"/>
	
	<security:authorize access="hasRole('CUSTOMER')">
	
	<tr>
		<th><spring:message code="actor.creditCard" /> :</th>
		<td><a href="creditCard/customer/display.do"> 
				<spring:message code="actor.display" />
			</a>
		</td>
	</tr>
	<tr>
		<th><spring:message code="actor.socialIdentity" /> :</th>
		<td><a href="socialIdentity/customer/display.do"> 
				<spring:message code="actor.display" />
			</a>
		</td>
	</tr>
	</security:authorize>
	</table>
	</div>

	
	<!-- Action links -->
	<div>
		<b><a href="actor/actor/edit.do"> 
			<spring:message code="actor.edit" />
		</a></b>
	</div>
