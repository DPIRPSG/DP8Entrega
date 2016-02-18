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
<jstl:if test="${socialIdentity.id != 0}">
	<div>
	<table>
		<acme:display code="socialIdentity.name" value="${socialIdentity.name}"/>
		<acme:display code="socialIdentity.homePage" value="${socialIdentity.homePage}"/>
		<acme:display code="socialIdentity.nick" value="${socialIdentity.nick}"/>
		<tr>
			<th><spring:message code="socialIdentity.picture" /> :</th>
			<td><img src="${socialIdentity.picture}" style="width:204px;height:128px;"/></td>
		</tr>

	</table>
	</div>
</jstl:if>
<jstl:if test="${socialIdentity.id == 0}">	
	<b><spring:message code="socialIdentity.null" /></b>
</jstl:if>
	<br />
	<br />
	<!-- Action links -->
	<div>
		<b><a href="socialIdentity/customer/edit.do"> 
			<spring:message code="socialIdentity.edit" />
		</a></b>
	</div>

