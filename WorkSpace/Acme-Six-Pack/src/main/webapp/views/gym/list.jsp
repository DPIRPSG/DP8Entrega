<%@page language="java" contentType="text/html; charset=ISO-8859-1"
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
	name="gyms" requestURI="${requestURI}" id="row_Gym">
	<!-- Action links -->

	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="gym/administrator/edit.do?gymId=${row_Gym.id}"> <spring:message
					code="gym.edit" />
			</a>
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('CUSTOMER')">
		<jstl:if test="${paid}">
			<display:column>
				<a href="feePayment/customer/list.do?gymId=${row_Gym.id}"> <spring:message
						code="gym.feePayments" />
				</a>
			</display:column>
		</jstl:if>
		
		
			<display:column>
				<a href="feePayment/customer/create.do?gymId=${row_Gym.id}"> <spring:message
						code="gym.create.feePayments" />
				</a>
			</display:column>
		
	</security:authorize>

	<!-- Attributes -->
	<spring:message code="gym.name" var="nameHeader" />
	<display:column title="${nameHeader}"
		sortable="true">
		<jstl:out value="${row_Gym.name}"/>
	</display:column>

	<spring:message code="gym.description" var="descriptionHeader" />
	<display:column title="${descriptionHeader}"
		sortable="false">
		<jstl:out value="${row_Gym.description}"/>
	</display:column>
	
	<spring:message code="gym.customers" var="customersHeader" />	
	<display:column title="${customersHeader}"
		sortable="true" >
		<jstl:forEach var="gyms" items="${customers }">
			<jstl:if test="${gyms[0] == row_Gym.id}">
				<jstl:out value="${gyms[1]}" />
			</jstl:if>
		</jstl:forEach>
	</display:column>
	
	<spring:message code="gym.postalAddress" var="postalAddressHeader" />
	<display:column title="${postalAddressHeader}"
		sortable="false">
		<a href="https://www.google.es/maps/place/${row_Gym.postalAddress}">
			<jstl:out value="${row_Gym.postalAddress}"/>
		</a>
	</display:column>

	<spring:message code="gym.phone" var="phoneHeader" />
	<display:column title="${phoneHeader}"
		sortable="true">
		<jstl:out value="${row_Gym.phone}"/>
	</display:column>

	<spring:message code="gym.fee" var="feeHeader" />
	<display:column title="${feeHeader}"
		sortable="true">
		<jstl:out value="${row_Gym.fee}"/>
	</display:column>

	<spring:message code="gym.picture" var="pictureHeader" />
	<display:column title="${pictureHeader}"
		sortable="false" >
		<img src="${row_Gym.picture}" style="width:204px;height:128px;"/>
	</display:column>
	
	<display:column>
		<a href="${requestUri2}gymId=${row_Gym.id}"> <spring:message
				code="gym.services" />
		</a>
	</display:column>

	<display:column>
		<a href="comment/list.do?commentedEntityId=${row_Gym.id}"> <spring:message
				code="gym.comments" />
		</a>
	</display:column>


</display:table>

<br/>
<br/>
<br/>
<br/>


<form action="${requestURI}">
	<input type="text" name="keyword"> <input type="submit"
		value="<spring:message code="gym.search" />" />&nbsp;
</form>


<!-- Action links -->
<security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="gym/administrator/create.do"> <spring:message
				code="gym.create" />
		</a>
	</div>
</security:authorize>

<!-- Alert -->
<jstl:if test="${messageStatus != Null && messageStatus != ''}">
	<spring:message code="${messageStatus}" var="showAlert" />
			<script>$(document).ready(function(){
		    alert("${showAlert}");
		  });
		</script>
</jstl:if>	
