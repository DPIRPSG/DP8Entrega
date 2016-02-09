<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Form -->
<form:form action="item/administrator/edit.do" modelAttribute="item">
	<!-- Hidden Attributes -->
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="deleted"/>
	<form:hidden path="comments"/>
	<form:hidden path="storages"/>
	
	<jstl:if test="${item.id != 0}">
		<form:hidden path="sku"/>
	</jstl:if>
	
	<!-- Editable Attributes -->
	<jstl:if test="${item.id == 0}">
		<form:label path="sku">
			<spring:message code="item.sku" />:
		</form:label>
		<form:input path="sku" />
		<form:errors cssClass="error" path="sku" />
		<br />
	</jstl:if>

	<form:label path="name">
		<spring:message code="item.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />

	<form:label path="description">
		<spring:message code="item.description" />:
	</form:label>
	<form:textarea path="description" />
	<form:errors cssClass="error" path="description" />
	<br />

	<form:label path="price">
		<spring:message code="item.price" />:
	</form:label>
	<form:input path="price" />
	<form:errors cssClass="error" path="price" />
	<br />
	
	<form:label path="tags">
		<spring:message code="item.tags" />:
	</form:label>
	<form:textarea path="tags" />
	<form:errors cssClass="error" path="tags" />
	<br />

	<form:label path="picture">
		<spring:message code="item.picture" />:
	</form:label>
	<form:textarea path="picture" />
	<form:errors cssClass="error" path="picture" />
	<br />
	
	<!-- Select tax -->
	<form:label path="category">
		<spring:message code="item.category" />:
	</form:label>
	<form:select id="categories" path="category">
		<form:option value="0" label="----" />		
		<form:options items="${categories}" itemValue="id" itemLabel="name" />
	</form:select>
	<form:errors cssClass="error" path="category" />
	<br />
	
	<!-- Action buttons -->
	<input type="submit" name="save"
		value="<spring:message code="item.save" />" />&nbsp; 
	<jstl:if test="${item.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="item.delete" />"
			onclick="return confirm('<spring:message code="item.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="item.cancel" />"
		onclick="javascript: relativeRedir('item/administrator/list.do?keyword=');" />
	<br />

</form:form>