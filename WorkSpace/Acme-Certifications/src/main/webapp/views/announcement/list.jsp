<%--
 * list.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="announcements" requestURI="${requestURI}" id="row">
	
	<!-- Action links -->

	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="announcement/administrator/edit.do?announcementId=${row.id}">
				<spring:message	code="announcement.edit" />
			</a>
		</display:column>		
	</security:authorize>

	<security:authorize access="hasRole('CUSTOMER')">
		<display:column>
			<jstl:choose>
				<jstl:when test="${registeredAnnouncements.contains(row)}">
					<a href="announcement/customer/unregister.do?announcementId=${row.id}" 
					   onclick="javascript: return confirm('<spring:message code="announcement.confirm.unregister" />')">
						<spring:message code="announcement.unregister" />
					</a>					
				</jstl:when>
				<jstl:otherwise>
					<a href="announcement/customer/register.do?announcementId=${row.id}">
						<spring:message code="announcement.register" />
					</a>
				</jstl:otherwise>
			</jstl:choose>
		</display:column>
	</security:authorize>
	
	<!-- Attributes -->
	
	<spring:message code="announcement.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />

	<spring:message code="announcement.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}" />

	<spring:message code="announcement.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false" />

</display:table>

<!-- Action links -->

<security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="announcement/administrator/create.do"> <spring:message
				code="announcement.create" />
		</a>
	</div>
</security:authorize>