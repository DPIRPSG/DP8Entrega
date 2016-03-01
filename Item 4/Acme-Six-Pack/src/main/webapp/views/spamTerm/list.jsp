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

	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
		name="spamTerms" requestURI="${requestURI}" id="row_spamTerm">

		<spring:message code="spamTerm.editHeader" var="editHeader" />
		<display:column title="${editHeader}">
			<a href="spamTerm/administrator/edit.do?spamTermId=${row_spamTerm.id}"> 
				<spring:message code="spamTerm.edit" />
			</a>
		</display:column>

		<!-- Attributes -->
		<spring:message code="spamTerm.term" var="termHeader" />
		<display:column title="${termHeader}"
			sortable="true" >
			<jstl:out value="${row_spamTerm.term}"/>
		</display:column>

	</display:table>
	
	<!-- Action links -->
	<div>
		<b><a href="spamTerm/administrator/create.do"> 
			<spring:message code="spamTerm.create" />
		</a></b>
	</div>