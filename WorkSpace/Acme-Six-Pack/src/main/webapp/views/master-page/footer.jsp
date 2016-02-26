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
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>



<jsp:useBean id="date" class="java.util.Date" />

<hr />
<br/>

<security:authorize access="isAuthenticated()">
		<script>document.write('<a href="https://' + window.location.hostname + ':443/about-us.do"><spring:message code="master.page.aboutUs" /></a>');</script>
	</security:authorize>
	<security:authorize access="isAnonymous()">
		<script>document.write('<a href="http://' + window.location.hostname + ':80/about-us.do"><spring:message code="master.page.aboutUs" /></a>');</script>
	</security:authorize>
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
	
	<security:authorize access="isAuthenticated()">
		<script>document.write('<a href="https://' + window.location.hostname + ':443/legal-terms.do"><spring:message code="master.page.legalTerms" /></a>');</script>
	</security:authorize>
	<security:authorize access="isAnonymous()">
		<script>document.write('<a href="http://' + window.location.hostname + ':80/legal-terms.do"><spring:message code="master.page.legalTerms" /></a>');</script>
	</security:authorize>
	
	<div style="position: fixed; bottom: 0; width: 100%; background-color: #BDBDBD;" id="infoCookies"><p>Utilizamos cookies de personalización propias para mejorar nuestros servicios y mostrarle información personalizada según sus preferencias. Si continúa navegando, consideramos que acepta su uso. Puede obtener más información 
	<security:authorize access="isAuthenticated()">
		<script>document.write('<a href="https://' + window.location.hostname + ':443/legal-terms.do" >aquí</a>');</script>
	</security:authorize>
	<security:authorize access="isAnonymous()">
		<script>document.write('<a href="http://' + window.location.hostname + ':80/legal-terms.do" >aquí</a>');</script>
	</security:authorize>
	.<button onclick="hideInfoCookies()">Entendido</button></p></div>
	
	<script>
		function hideInfoCookies(){
			$("#infoCookies").hide();
		}
	</script>

