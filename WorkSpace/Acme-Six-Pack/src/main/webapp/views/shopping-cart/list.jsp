<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('CONSUMER')">
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
		name="shoppingCarts" requestURI="${requestURI}" id="row_SCart">
		
	<spring:message code="shoppingCart.comments.edit.url" var="editHeader" />
	<display:column title="${editHeader}">
		<a href="shopping-cart/consumer/edit.do?shoppingCartId=${row_SCart.id}"> <spring:message
			code="shoppingCart.comments.edit" />
		</a>
	</display:column>

	<!-- Attributes -->
	<spring:message code="shoppingCart.comments" var="commentsHeader" />
	<display:column title="${commentsHeader}"
		sortable="false" >
		<jstl:out value="${row_SCart.comments}"/>
	</display:column>
	
	<jstl:if test="${row_SCart.contents != '[]'}">
		<display:column>
				<a href="content/consumer/list.do?shoppingCartId=${row_SCart.id}"> <spring:message
						code="shoppingCart.contents" />
				</a>
		</display:column>
	</jstl:if>
	
	</display:table>
	
	<!-- Action links -->
	
</security:authorize>