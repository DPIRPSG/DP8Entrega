<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h3><spring:message code="storage.warehouse"/>: <jstl:out value="${storage.wareHouse.name}" /></h3>
<h3><spring:message code="storage.item"/>: <jstl:out value="${storage.item.name}(${storage.item.sku})" /></h3>
<!-- Form -->
<form:form action="storage/administrator/edit.do" modelAttribute="storage">
	<!-- Hidden Attributes -->
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="wareHouse" />
	
	
	<!-- Editable Attributes -->
	<jstl:if test="${storage.item != null}">
		<form:hidden path="item" />
	</jstl:if>
	<jstl:if test="${storage.item == null}">
		<form:label path="item">
			<spring:message code="storage.item" />:
		</form:label>
		<form:select path="item" items="${items}" itemLabel="sku" itemValue="id"/>
		<form:errors cssClass="error" path="item" />
		<br />
	</jstl:if>

	<form:label path="units">
		<spring:message code="storage.units" />:
	</form:label>
	<form:input path="units" />
	<form:errors cssClass="error" path="units" />
	<br /> <br />
	
	<!-- Action buttons -->
	<input type="submit" name="save"
		value="<spring:message code="storage.save" />" />&nbsp;
		
	<input type="button" name="cancel"
		value="<spring:message code="storage.cancel" />"
		onclick="javascript: relativeRedir('storage/administrator/list.do?warehouseId=${storage.wareHouse.id}');" />
	<br />

</form:form>