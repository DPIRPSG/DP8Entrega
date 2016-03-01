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
	<h3><spring:message code="administrator.mostPopularGyms"/></h3>
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
 		name="mostPopularGyms" requestURI="${requestURI}" id="row">
		<!-- Attributes -->
		<spring:message code="gym.name" var="nameHeader" />
		<display:column title="${nameHeader}" 
 			sortable="false" >
 			<jstl:out value="${row.name}"/>
 		</display:column>
 	</display:table>
 	
 	<!-- Dashboard 2 -->
	<h3><spring:message code="administrator.leastPopularGyms"/></h3>
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
 		name="leastPopularGyms" requestURI="${requestURI}" id="row">
		<!-- Attributes -->
		<spring:message code="gym.name" var="nameHeader" />
		<display:column title="${nameHeader}" 
 			sortable="false" >
 			<jstl:out value="${row.name}"/>
 		</display:column>
 	</display:table>
 	
 	<!-- Dashboard 3 -->
	<h3><spring:message code="administrator.mostPopularService"/></h3>
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
 		name="mostPopularService" requestURI="${requestURI}" id="row">
		<!-- Attributes -->
		<spring:message code="service.name" var="nameHeader" />
		<display:column title="${nameHeader}" 
 			sortable="false" >
 			<jstl:out value="${row.name}"/>
 		</display:column>
 	</display:table>
 	
  	<!-- Dashboard 4 -->
	<h3><spring:message code="administrator.leastPopularService"/></h3>
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
 		name="leastPopularService" requestURI="${requestURI}" id="row">
		<!-- Attributes -->
		<spring:message code="service.name" var="nameHeader" />
		<display:column title="${nameHeader}" 
 			sortable="false" >
 			<jstl:out value="${row.name}"/>
 		</display:column>
 	</display:table>
 	
  	<!-- Dashboard 5 -->
	<h3><spring:message code="administrator.paidMoreFees"/></h3>
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
 		name="paidMoreFees" requestURI="${requestURI}" id="row">
		<!-- Attributes -->
		<spring:message code="customer.name" var="nameHeader" />
		<display:column title="${nameHeader}" 
 			sortable="false" >
 			<jstl:out value="${row.name}"/>
 		</display:column>
 	</display:table>
	 
  	<!-- Dashboard 6 -->
	<h3><spring:message code="administrator.paidLessFees"/></h3>
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
 		name="paidLessFees" requestURI="${requestURI}" id="row">
		<!-- Attributes -->
		<spring:message code="customer.name" var="nameHeader" />
		<display:column title="${nameHeader}" 
 			sortable="false" >
 			<jstl:out value="${row.name}"/>
 		</display:column>
 	</display:table>
 	
  	<!-- Dashboard 7 -->
	<h3><spring:message code="administrator.sendMoreSpam"/></h3>
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
 		name="sendMoreSpam" requestURI="${requestURI}" id="row">
		<!-- Attributes -->
		<spring:message code="customer.name" var="nameHeader" />
		<display:column title="${nameHeader}" 
 			sortable="false" >
 			<jstl:out value="${row.name}"/>
 		</display:column>
 	</display:table>
 	
  	<!-- Dashboard 8 -->
	<h3><spring:message code="administrator.averageNumberOfMessages"/></h3>
	<!-- Result -->
	<jstl:choose>
  		<jstl:when test="${averageNumberOfMessages == 0}">
 			<spring:message code="administrator.ratio.null"/>
		</jstl:when>
  		<jstl:otherwise>
			<jstl:out value="${averageNumberOfMessages}" />
		</jstl:otherwise>
	</jstl:choose>
	
	  	<!-- Dashboard 9 -->
	<h3><spring:message code="administrator.moreCommentedGyms"/></h3>
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
 		name="moreCommentedGyms" requestURI="${requestURI}" id="row">
		<!-- Attributes -->
		<spring:message code="gym.name" var="nameHeader" />
		<display:column title="${nameHeader}" 
 			sortable="false" >
 			<jstl:out value="${row.name}"/>
 		</display:column>
 	</display:table>
 	
  	<!-- Dashboard 10 -->
	<h3><spring:message code="administrator.moreCommentedServices"/></h3>
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
 		name="moreCommentedServices" requestURI="${requestURI}" id="row">
		<!-- Attributes -->
		<spring:message code="service.name" var="nameHeader" />
		<display:column title="${nameHeader}" 
 			sortable="false" >
 			<jstl:out value="${row.name}"/>
 		</display:column>
 	</display:table>
 	
  	<!-- Dashboard 11 -->
	<h3><spring:message code="administrator.averageNumberOfComments"/></h3>
	<!-- Result -->
	<jstl:choose>
  		<jstl:when test="${averageNumberOfComments == 0}">
 			<spring:message code="administrator.ratio.null"/>
		</jstl:when>
  		<jstl:otherwise>
			<jstl:out value="${averageNumberOfComments}" />
		</jstl:otherwise>
	</jstl:choose>

  	<!-- Dashboard 11 -->
	<h3><spring:message code="administrator.standardDeviationNumberOfComments"/></h3>
	<!-- Result -->
	<jstl:choose>
  		<jstl:when test="${standardDeviationNumberOfComments == 0}">
 			<spring:message code="administrator.ratio.null"/>
		</jstl:when>
  		<jstl:otherwise>
			<jstl:out value="${standardDeviationNumberOfComments}" />
		</jstl:otherwise>
	</jstl:choose>
	
  	<!-- Dashboard 12 -->
	<h3><spring:message code="administrator.averageNumberOfCommentsPerGym"/></h3>
	<!-- Result -->
	<jstl:choose>
  		<jstl:when test="${averageNumberOfCommentsPerGym == 0}">
 			<spring:message code="administrator.ratio.null"/>
		</jstl:when>
  		<jstl:otherwise>
			<jstl:out value="${averageNumberOfCommentsPerGym}" />
		</jstl:otherwise>
	</jstl:choose>
	
  	<!-- Dashboard 13 -->
	<h3><spring:message code="administrator.averageNumberOfCommentsPerService"/></h3>
	<!-- Result -->
	<jstl:choose>
  		<jstl:when test="${averageNumberOfCommentsPerService == 0}">
 			<spring:message code="administrator.ratio.null"/>
		</jstl:when>
  		<jstl:otherwise>
			<jstl:out value="${averageNumberOfCommentsPerService}" />
		</jstl:otherwise>
	</jstl:choose>
	
  	<!-- Dashboard 14 -->
	<h3><spring:message code="administrator.removedMoreComments"/></h3>
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
 		name="removedMoreComments" requestURI="${requestURI}" id="row">
		<!-- Attributes -->
		<spring:message code="customer.name" var="nameHeader" />
		<display:column title="${nameHeader}" 
 			sortable="false" >
 			<jstl:out value="${row.name}"/>
 		</display:column>
 	</display:table>

</security:authorize>