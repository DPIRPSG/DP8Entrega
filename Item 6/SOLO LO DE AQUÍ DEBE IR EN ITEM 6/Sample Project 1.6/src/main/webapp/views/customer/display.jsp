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

	<acme:display code="customer.name" value="${customer.name}"/>
	<acme:display code="customer.surname" value="${customer.surname}"/>
	<acme:display code="customer.phone" value="${customer.phone}"/>
	<acme:display code="customer.username" value="${customer.userAccount.username}"/>
	
	</table>
	</div>

	
	<!-- Action links -->
	<div>
		<b><a href="customer/customer/edit.do"> 
			<spring:message code="customer.edit" />
		</a></b>
	</div>
	<br/>
	<spring:message code="customer.delete"/>
