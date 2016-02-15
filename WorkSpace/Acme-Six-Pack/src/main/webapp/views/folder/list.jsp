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
	<display:table pagesize="5" class="displaytag" keepStatus="true"
		name="folders" requestURI="${requestURI}" id="row_folder">

		<spring:message code="folder.editHeader" var="editHeader" />
		<jstl:if test="${!row_folder.isSystem}">
			<display:column title="${editHeader}">
				<a href="folder/actor/edit.do?folderId=${row_folder.id}"> 
					<spring:message code="folder.edit" />
				</a>
			</display:column>
		</jstl:if>
		<jstl:if test="${row_folder.isSystem}">
			<display:column title="${editHeader}">

			</display:column>
		</jstl:if>


		<!-- Attributes -->
		<spring:message code="folder.name" var="nameHeader" />
		<display:column title="${nameHeader}"
			sortable="true" >
			<jstl:out value="${row_folder.name}"/>
		</display:column>

		<spring:message code="folder.messages" var="messageHeader" />
		<display:column>
			<a href="message/actor/list.do?folderId=${row_folder.id}"> <spring:message
					code="folder.messages" />
			</a>
		</display:column>

	</display:table>
	
	<!-- Action links -->
	<div>
		<b><a href="folder/actor/create.do"> 
			<spring:message code="folder.create" />
		</a></b>
	</div>
	<div>
		<b><a href="message/actor/create.do"> 
			<spring:message code="message.create" />
		</a></b>
	</div>

</security:authorize>