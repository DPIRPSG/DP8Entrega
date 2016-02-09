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
	name="categories" requestURI="${requestURI}" id="row_Category">
		<!-- Action links -->
		<display:column>
			<a href="category/administrator/edit.do?categoryId=${row_Category.id}">
				<spring:message	code="category.list.edit" />
			</a>
		</display:column>
		<!-- Attributes -->
		<spring:message code="category.list.name" var="nameHeader" />
		<display:column title="${nameHeader}"
			sortable="true">
			<jstl:out value="${row_Category.name}"/>
		</display:column>
		
		<spring:message code="category.list.description" var="descriptionHeader" />
		<display:column title="${descriptionHeader}"
			sortable="true" >
			<jstl:out value="${row_Category.description}"/>
		</display:column>
		
		<spring:message code="category.list.picture" var="pictureHeader" />
		<display:column title="${pictureHeader}" sortable="false" >
			<img src="${row_Category.picture}" style="width:204px;height:128px;"/>
		</display:column>
		
		<spring:message code="category.list.taxName" var="taxNameHeader" />
		<display:column title="${taxNameHeader}"
			sortable="true">
			<jstl:out value="${row_Category.tax.name}"/>
		</display:column>
		
		<spring:message code="category.list.taxValue" var="taxValueHeader" />
		<display:column title="${taxValueHeader}"
			sortable="true">
			<jstl:out value="${row_Category.tax.value}"/>
		</display:column>
		
</display:table>
<!-- Action links -->
<div>
	<a href="category/administrator/create.do"> <spring:message
			code="category.list.create" />
	</a>
</div>
	
</security:authorize>	
	



