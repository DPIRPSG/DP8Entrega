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
<form:form action="shopping-cart/consumer/edit.do" modelAttribute="shopping-cart">
	<!-- Hidden Attributes -->
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="consumer" />
	<form:hidden path="contents" />
	
	<spring:message code="shoppingCart.comments.addComment"/>
	<br />
	<spring:message code="shoppingCart.comments.editComment"/>
	<br />
	<spring:message code="shoppingCart.comments.deleteComment"/>
	<br /> <br />
	
	<!-- Editable Attributes -->
	<form:label path="comments">
		<spring:message code="shoppingCart.comments" />:
	</form:label>
	<form:textarea path="comments" />
	<form:errors cssClass="error" path="comments" />
	<br /> <br />
	
	<!-- Action buttons -->
	<input type="submit" name="save"
		value="<spring:message code="shoppingCart.comments.save" />" 
		onclick="return confirm('<spring:message code="shoppingCart.comment.save.advise" />')"/>&nbsp;
		
	<input type="button" name="cancel"
		value="<spring:message code="shoppingCart.comments.cancel" />"
		onclick="javascript: relativeRedir('shopping-cart/consumer/list.do');" />
	<br />

</form:form>