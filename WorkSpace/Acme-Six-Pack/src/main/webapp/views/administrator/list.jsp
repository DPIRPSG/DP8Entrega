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

	
	<!-- Dashboard 1 -->
	<h3><spring:message code="administrator.consumerMoreOrders"/></h3>
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
		name="consumerMoreOrders" requestURI="${requestURI}" id="row">
		<!-- Attributes -->
		<spring:message code="consumer.name" var="nameHeader" />
		<display:column title="${nameHeader}" 
			sortable="false" >
			<jstl:out value="${row.name}"/>
		</display:column>
	
	</display:table>
	 

	<!-- Dashboard 2 -->
	<h3><spring:message code="administrator.consumerSpentMoreMoney"/></h3>
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
		name="consumerSpentMoreMoney" requestURI="${requestURI}" id="row">
		<!-- Attributes -->
		<spring:message code="consumer.name" var="nameHeader" />
		<display:column title="${nameHeader}"
			sortable="false" >
			<jstl:out value="${row.name}"/>
		</display:column>
	</display:table>
	 
	
	<!-- Dashboard 3 -->
	<h3><spring:message code="administrator.bestSellingItem"/></h3>
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
		name="bestSellingItem" requestURI="${requestURI}" id="row">
		<!-- Attributes -->
		<spring:message code="item.name" var="nameHeader" />
		<display:column title="${nameHeader}"
			sortable="false" >
			<jstl:out value="${row.name}"/>
		</display:column>
	
	</display:table>
	
	
	<!-- Dashboard 4 -->
	<h3><spring:message code="administrator.worstSellingItem"/></h3>
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
		name="worstSellingItem" requestURI="${requestURI}" id="row">
		<!-- Attributes -->
		<spring:message code="item.name" var="nameHeader" />
		<display:column title="${nameHeader}"
			sortable="false" >
			<jstl:out value="${row.name}"/>
		</display:column>
	
	</display:table>
	
	
	<!-- Dashboard 5 -->
	<h3><spring:message code="administrator.clerkMoreOrders"/></h3>
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
		name="clerkMoreOrders" requestURI="${requestURI}" id="row">
		<!-- Attributes -->
		<spring:message code="clerk.name" var="nameHeader" />
		<display:column title="${nameHeader}" 
			sortable="false" >
			<jstl:out value="${row.name}"/>
		</display:column>
	</display:table>
	
	
	<!-- Dashboard 6 -->
	<h3><spring:message code="administrator.clerkLessOrders"/></h3>
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
		name="clerkLessOrders" requestURI="${requestURI}" id="row">
		<!-- Attributes -->
		<spring:message code="clerk.name" var="nameHeader" />
		<display:column title="${nameHeader}" 
			sortable="false" >
			<jstl:out value="${row.name}"/>
		</display:column>
	
	</display:table>
	
	
	<!-- Dashboard 7 -->
	<h3><spring:message code="administrator.consumerCancelledMoreOrders"/></h3>
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
		name="consumerCancelledMoreOrders" requestURI="${requestURI}" id="row">
		<!-- Attributes -->
		<spring:message code="consumer.name" var="nameHeader" />
		<display:column title="${nameHeader}" 
			sortable="false" >
			<jstl:out value="${row.name}"/>
		</display:column>
	</display:table>
	
	
	<!-- Dashboard 8 -->
	<h3><spring:message code="administrator.consumerCancelledLessOrders"/></h3>
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
		name="consumerCancelledLessOrders" requestURI="${requestURI}" id="row">
		<!-- Attributes -->
		<spring:message code="consumer.name" var="nameHeader" />
		<display:column title="${nameHeader}" 
			sortable="false" >
			<jstl:out value="${row.name}"/>
		</display:column>
	
	</display:table>
	
	
	<!-- Dashboard 9 -->
	<h3><spring:message code="administrator.ratioCancelledCurrentMonth"/></h3>
	<!-- Result -->
	<jstl:choose>
  		<jstl:when test="${ratioCancelledCurrentMonth == 0}">
 			<spring:message code="administrator.ratio.null"/>
		</jstl:when>
  		<jstl:otherwise>
			<jstl:out value="${ratioCancelledCurrentMonth}" />
		</jstl:otherwise>
	</jstl:choose>
	
	
	
	<!-- Dashboard 10 -->
	<h3><spring:message code="administrator.itemMoreComment"/></h3>
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
		name="itemMoreComment" requestURI="${requestURI}" id="row">
		<!-- Attributes -->
		<spring:message code="item.name" var="nameHeader" />
		<display:column title="${nameHeader}"
			sortable="false" >
			<jstl:out value="${row.name}"/>
		</display:column>
	
	</display:table>


</security:authorize>