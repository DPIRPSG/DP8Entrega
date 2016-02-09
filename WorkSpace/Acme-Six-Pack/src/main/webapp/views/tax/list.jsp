<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<security:authorize access="hasRole('ADMIN')">
<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="taxes" requestURI="${requestURI}" id="row_Tax">
		<!-- Action links -->
		<display:column>
			<a href="tax/administrator/edit.do?taxId=${row_Tax.id}">
				<spring:message	code="tax.edit" />
			</a>
		</display:column>
		<!-- Attributes -->
		
		<spring:message code="tax.name" var="nameHeader" />
		<display:column title="${nameHeader}" 
			sortable="true">
			<jstl:out value="${row_Tax.name}"/>
		</display:column>
		
		<spring:message code="tax.value" var="valueHeader" />
		<display:column title="${valueHeader}" 
			sortable="true">
			<jstl:out value="${row_Tax.value}"/>
		</display:column>
		
</display:table>

<!-- Action links -->
<div>
	<a href="tax/administrator/create.do"> <spring:message
			code="tax.list.create" />
	</a>
</div>
	
</security:authorize>	
	



