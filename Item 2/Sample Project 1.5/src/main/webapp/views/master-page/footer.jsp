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
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>


<jsp:useBean id="date" class="java.util.Date" />

<hr />
<a href="about-us/index.do"><spring:message code="master.page.aboutUs" /></a>
<b>Copyright &copy; <fmt:formatDate value="${date}" pattern="yyyy" />
	Sample Project 1.5 Co., Inc.
</b>

<script type="text/javascript">
	function relativeRedir(loc) {
		var b = document.getElementsByTagName('base');
		if (b && b[0] && b[0].href) {
			if (b[0].href.substr(b[0].href.length - 1) == '/'
					&& loc.charAt(0) == '/')
				loc = loc.substr(1);
			loc = b[0].href + loc;
		}
		window.location.replace(loc);
	}
</script>

<a href="legal-terms/index.do"><spring:message
		code="master.page.legalTerms" /></a>

<div
	style="position: fixed; bottom: 0; width: 100%; background-color: #BDBDBD;"
	id="infoCookies">
	<p> <spring:message code="master.page.cookies.message"/>
		 <a href="legal-terms/index.do"><spring:message code="master.page.cookies.here"/></a>.
		<button onclick="hideInfoCookies()"><spring:message code="master.page.cookies.ok"/></button>
	</p>
</div>

<script>
	function getCookie(cname) {
    	var name = cname + "=";
    	var ca = document.cookie.split(';');
    	for(var i=0; i<ca.length; i++) {
        	var c = ca[i];
        	while (c.charAt(0)==' ') c = c.substring(1);
        	if (c.indexOf(name) == 0) return c.substring(name.length,c.length);
    	}
    	return "";
	} 
	
	function loadCookies(cookies){
		// First cookie name, First cookie value
		// ; Second cookie name, Second cookie value
		arrayCookies = cookies.split(";");
		for(var i=0; i<arrayCookies.length; i++){
			var cook = arrayCookies[i].split(",");
			var name = cook[0];
			var value = cook[1];
			document.cookie = name + "=" + value + "; path=/ ";
		}
	}

	function hideInfoCookies() {
		$("#infoCookies").hide();
		loadCookies("infoCookies,hide");
	}
	
	if(getCookie("infoCookies")=="hide"){
		hideInfoCookies();
	}

</script>