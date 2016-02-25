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
	
	<div style="position: fixed; bottom: 0; width: 100%; height: 50px; background-color: #BDBDBD;" id="infoCookies"><p>Utilizamos cookies de personalización propias para mejorar nuestros servicios y mostrarle información personalizada según sus preferencias. Si continúa navegando, consideramos que acepta su uso. Puede obtener más información <a href="./legal-terms.do">aquí</a>.  <button onclick="hideInfoCookies()">Entendido</button></p></div>
	
	<script>
		function hideInfoCookies(){
			$("#infoCookies").hide();
		}
	</script>