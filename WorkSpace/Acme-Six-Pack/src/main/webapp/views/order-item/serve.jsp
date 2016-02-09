<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h3><spring:message code="orderItem.order"/>: <jstl:out value="${order.ticker}" /></h3>
<h3><spring:message code="orderItem.item"/>: <jstl:out value="${item.name}(${item.sku})" /></h3>
<!-- Form -->
<form:form action="order-item/clerk/serve.do" modelAttribute="orderItem">
	
	<!-- Hidden Attributes -->
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="sku"/>
	<form:hidden path="name"/>
	<form:hidden path="description"/>
	<form:hidden path="price" />
	<form:hidden path="tags" />
	<form:hidden path="picture"/>
	<form:hidden path="deleted"/>
	<form:hidden path="tax"/>
	<form:hidden path="nameCategory" />
	<form:hidden path="units"/>
	<form:hidden path="unitsServed" />
	<form:hidden path="order" />

	<spring:message code="orderItem.units" var="units" />
	<jstl:out value="${units}:"></jstl:out>
	<jstl:out value="${unitsNum}"></jstl:out>
	<br/>
	
	<spring:message code="orderItem.unitsServed" var="unitsServed" />
	<jstl:out value="${unitsServed}:"></jstl:out>
	<jstl:out value="${unitsServedNum}"></jstl:out>
	<br/>
	
	<spring:message code="orderItem.unitsToServe" var="unitsToServe"/>
	<jstl:out value="${unitsToServe}:"></jstl:out>
	<input type="number" name="unitsToServe" max="${unitsNum - unitsServedNum}" min="1" value="1">
	<br />

	
	<!-- Action buttons -->
	<input type="submit" name="serve"
		value="<spring:message code="orderItem.serve" />"/>
		
		<input type="button" name="cancel"
		value="<spring:message code="orderItem.cancel" />"
		onclick="javascript: relativeRedir('order-item/clerk/list.do?orderId=${orderId}');" />
	<br />

</form:form>