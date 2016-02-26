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
<br/>

<a href="about-us.do"><spring:message code="master.page.aboutUs" /></a>
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
	
<a href="legal-terms.do"><spring:message code="master.page.legalTerms" /></a>
	
	<div style="position: fixed; bottom: 0; width: 100%; background-color: #BDBDBD;" id="infoCookies"><p>Utilizamos cookies de personalización propias para mejorar nuestros servicios y mostrarle información personalizada según sus preferencias. Si continúa navegando, consideramos que acepta su uso. Puede obtener más información <a href="./legal-terms.do">aquí</a>.  <button onclick="hideInfoCookies()">Entendido</button></p></div>

	<jstl:set var="jsessionid" value="${cookie['JSESSIONID'].value}"/>

<script>
document.write("a1");
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
		document.write(cookies + "_");
		for(var i=0; i<arrayCookies.length; i++){
			var cook = arrayCookies[i].split(",");
			var name = cook[0];
			var value = cook[1];
			var p = "${jsessionid}";
			// document.write("·" + p + "·");
			//document.cookie = p + "_" +name + "=" + value + "; path=/ ";
			document.cookie = name + "=" + value + "; path=/ ";
		}
	}
	function hideInfoCookies() {
		$("#infoCookies").hide();
		loadCookies("infoCookies,hide");
	}
	document.write("a2");
</script>

	<jstl:set var="cookiestemp" value="${loadToCookie}"/>
	<jstl:set var="cookiestempo" value="${loadCookies}"/>

<jstl:if test="${loadCookies != Null && loadCookies != ''}">
	
	<script>
	document.write("b1");
	document.write("Showing cookies! ! ->");
	document.write("${loadToCookie}");
	document.write("${cookiestempo}");
	document.write("${messageStatus}");
	document.write("<- Fin cookies");
	loadCookies("${cookiestemp}");
	loadCookies("${loadToCookie}");
		
	</script>
</jstl:if>
	<script>
	if(getCookie("infoCookies")=="hide"){
		hideInfoCookies();
	}

</script>

