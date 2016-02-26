<%--
 * footer.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<jsp:useBean id="date" class="java.util.Date" />

<hr />
<br/>

<a href="about-us/index.do"><spring:message code="master.page.aboutUs" /></a>
<b>Copyright &copy; <fmt:formatDate value="${date}" pattern="yyyy" /> Acme-Six-Pack Co., Inc.</b>

	<script type="text/javascript">
		function relativeRedir(loc) {	
			var b = document.getElementsByTagName('base');
			if (b && b[0] && b[0].href) {
	  			if (b[0].href.substr(b[0].href.length - 1) == '/' && loc.charAt(0) == '/')
	    		loc = loc.substr(1);
	  			loc = b[0].href + loc;
			}
			window.location.replace(loc);
		}
	</script>
	
<a href="legal-terms/index.do"><spring:message code="master.page.legalTerms" /></a>
	
	<div
	style="position: fixed; bottom: 0; width: 100%; background-color: #BDBDBD;"
	id="infoCookies">
	<p> <spring:message code="master.page.cookies.message"/>
		 <a href="legal-terms/index.do"><spring:message code="master.page.cookies.here"/></a>.
		<button onclick="hideInfoCookies()"><spring:message code="master.page.cookies.ok"/></button>
	</p>
</div>
	
	<script>
		function hideInfoCookies(){
			$("#infoCookies").hide();
		}
	</script>

