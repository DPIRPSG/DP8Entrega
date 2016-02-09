<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access = "hasRole('ADMIN')">
	<!-- Form -->
	<form:form action="category/administrator/edit.do" modelAttribute="category">
		<!-- Hidden Attributes -->
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		
		<!-- Editable Attributes -->
		<form:label path="name">
			<spring:message code = "category.edit.name"/>
		</form:label>
		<form:input path="name"/>
		<form:errors cssClass="error" path="name"/>
		<br />
		
		<form:label path="description">
			<spring:message code = "category.edit.description"/>
		</form:label>
		<form:textarea path="description"/>
		<form:errors cssClass="error" path="description"/>
		<br />
		
		<form:label path="picture">
			<spring:message code = "category.edit.picture"/>
		</form:label>
		<form:input path="picture"/>
		<form:errors cssClass="error" path="picture"/>
		<br />
		
		<!-- Select tax -->
		<form:label path="tax">
			<spring:message code = "category.edit.tax"/>
		</form:label>
		<form:select path="tax">
		 	<jstl:if test="${category.id == 0}">
				<form:option label="---" value="0"/>
			</jstl:if>
		    <jstl:forEach items="${taxes}" var="t">
		        <jstl:choose>
		            <jstl:when test="${t.id eq category.tax.id}">
		                <option value="${t.id}" selected="selected">${t.name}</option>
		            </jstl:when>
		            <jstl:otherwise>
		                <option value="${t.id}">${t.name}</option>
		            </jstl:otherwise>
		        </jstl:choose> 
		    </jstl:forEach>
		</form:select>
		<form:errors cssClass="error" path="tax"/>
		<br />
	
		<!-- Action buttons -->
		<jstl:if test="${category.id == 0 }">
			<input type="submit" name="save" value="<spring:message code="category.create" />" />&nbsp;	
		</jstl:if>
		<jstl:if test="${category.id != 0 }">
			<input type="submit" name="save" value="<spring:message code="category.save" />" />
			<input type="submit" name="delete" 
				value="<spring:message code="category.delete" />" 
				onclick="return confirm('<spring:message code="category.confirm.delete" />')" />&nbsp;
		</jstl:if>
	
		<input type="button" name="cancel"
			value="<spring:message code="category.cancel" />"
			onclick="javascript: relativeRedir('category/administrator/list.do');" />
		
	</form:form>

</security:authorize>
