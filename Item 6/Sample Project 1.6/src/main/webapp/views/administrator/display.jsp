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

	<acme:display code="administrator.name" value="${administrator.name}"/>
	<acme:display code="administrator.surname" value="${administrator.surname}"/>
	<acme:display code="administrator.phone" value="${administrator.phone}"/>
	<acme:display code="administrator.username" value="${administrator.userAccount.username}"/>
	
	</table>
	</div>

	
	<!-- Action links -->
	<div>
		<b><a href="administrator/administrator/edit.do"> 
			<spring:message code="administrator.edit" />
		</a></b>
	</div>
