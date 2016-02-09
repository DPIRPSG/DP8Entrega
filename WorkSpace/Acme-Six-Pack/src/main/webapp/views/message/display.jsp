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

<security:authorize access="hasAnyRole('ADMIN', 'CLERK', 'CONSUMER')">
	<!-- Listing grid -->
	<div>
	<table>
	<tr>
		<th><spring:message code="message.moment" /> :</th>
		<td><jstl:out value="${messa.moment}" /></td>
	</tr>
	<tr>
		<th><spring:message code="message.folders" /> :</th>
		<td><jstl:forEach var="temp" items="${folders}">
			<a href="message/actor/list.do?folderId=${temp.id}">
				<jstl:out value="${temp.name}" />
			</a> &nbsp;
		</jstl:forEach>
		<b><a href="message/actor/edit.do?messageId=${messa.id}"> 
			<spring:message code="message.addToFolder" />
		</a></b>
		</td>
	</tr>
	<tr>
		<th><spring:message code="message.sender" /> :</th>
		<td><jstl:out value="${messa.sender.userAccount.username}" /></td>
	</tr>
	<tr>
		<th><spring:message code="message.recipients" /> :</th>
		<td><jstl:forEach var="temp" items="${messa.recipients}">
			<jstl:out value="${temp.userAccount.username}" /> &nbsp;
		</jstl:forEach>
		</td>
	</tr>
	<tr>
		<th><spring:message code="message.subject" /> :</th>
		<td><jstl:out value="${messa.subject}" /></td>
	</tr>
	<tr>
		<th><spring:message code="message.body" /> :</th>
		<td><jstl:out value="${messa.body}" /></td>
	</tr>
	
	
	</table>
	</div>
	
	<!-- Action links -->
	<div>
		<b><a href="message/actor/edit.do?messageId=${messa.id}"> 
			<spring:message code="message.addToFolder" />
		</a></b>
	</div>

</security:authorize>