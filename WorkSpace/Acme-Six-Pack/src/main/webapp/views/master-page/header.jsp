<%--
 * header.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<a href=""> 
		<img src="images/logo.png" alt="ACME, Inc.  Your certification Company" /></a>
	<br/>

</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv" href="gym/customer/list.do?"><spring:message code="master.page.manage.gym" /></a></li>
			<li><a class="fNiv" href="folder/actor/list.do"><spring:message code="master.page.messages" /></a></li>
			
		</security:authorize>
		
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.manage" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="gym/administrator/list.do?"><spring:message code="master.page.manage.gym" /></a></li>
					<li><a href="service/administrator/list.do?"><spring:message code="master.page.manage.service" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.list" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="consumer/administrator/list.do"><spring:message code="master.page.list.consumers" /></a></li>
					<li><a href="order/administrator/list.do"><spring:message code="master.page.list.orders" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv" href="dashboard/administrator/list.do"><spring:message code="master.page.dashboard" /></a></li>
			<li><a class="fNiv" href="clerk/administrator/create.do"><spring:message code="master.page.new-clerk" /></a></li>
			<li><a class="fNiv" href="folder/actor/list.do"><spring:message code="master.page.messages" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="gym/list.do?"><spring:message code="master.page.gym" /></a></li>
			<li><a class="fNiv" href="customer/create.do"><spring:message code="master.page.register" /></a></li>
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" />(<security:authentication property="principal.username" />)</a></li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>



