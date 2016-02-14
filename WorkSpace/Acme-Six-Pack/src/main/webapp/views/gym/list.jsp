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

	<!-- Attributes -->
	<spring:message code="gym.name" var="nameHeader" />
	<display:column title="${nameHeader}"
		sortable="true">
		<jstl:out value="${row_Gym.name}"/>
	</display:column>

	<spring:message code="gym.description" var="descriptionHeader" />
	<display:column title="${descriptionHeader}"
		sortable="true">
		<jstl:out value="${row_Gym.description}"/>
	</display:column>
	
	<spring:message code="gym.postalAddress" var="postalAddressHeader" />
	<display:column title="${postalAddressHeader}"
		sortable="true">
		<jstl:out value="${row_Gym.postalAddress}"/>
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
		<a href="comment/list.do?gymId=${row_Gym.id}"> <spring:message
				code="gym.comments" />
		</a>
	</display:column>

</display:table>


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
