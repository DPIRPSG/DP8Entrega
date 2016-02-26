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

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<security:authorize access="isAuthenticated()">
		<script>document.write('<a href="https://' + window.location.hostname + ':443" ><img src="images/logo.png" style="height:128px;" alt="Sample Project 1.6 Co., Inc." /></a>');</script>
	</security:authorize>
	<security:authorize access="isAnonymous()">
		<script>document.write('<a href="http://' + window.location.hostname + ':80" ><img src="images/logo.png" style="height:128px;" alt="Sample Project 1.6 Co., Inc." /></a>');</script>
	</security:authorize>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/action-1.do"><spring:message code="master.page.administrator.action.1" /></a></li>
					<li><a href="administrator/action-2.do"><spring:message code="master.page.administrator.action.2" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv"><spring:message	code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="customer/action-1.do"><spring:message code="master.page.customer.action.1" /></a></li>
					<li><a href="customer/action-2.do"><spring:message code="master.page.customer.action.2" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li> <script>document.write('<a class="fNiv" href="http://' + window.location.hostname + ':80/customer/create.do" >');</script> <spring:message code="master.page.register" /></a></li>
			<li> <script>document.write('<a class="fNiv" href="https://' + window.location.hostname + ':443/security/login.do" >');</script> <spring:message code="master.page.login" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
						<security:authorize access="hasRole('CUSTOMER')">
							<li><a href="customer/customer/display.do"><spring:message code="master.page.profile.display" /></a></li>						
						</security:authorize>
						<security:authorize access="hasRole('ADMIN')">
							<li><a href="administrator/administrator/display.do"><spring:message code="master.page.profile.display" /></a></li>						
						</security:authorize>
					<li><a href="profile/action-1.do"><spring:message code="master.page.profile.action.1" /></a></li>
					<li><a href="profile/action-2.do"><spring:message code="master.page.profile.action.2" /></a></li>
					<li><a href="profile/action-3.do"><spring:message code="master.page.profile.action.3" /></a></li>					
					<li><b> <script>document.write('<a class="fNiv" href="http://' + window.location.hostname + ':80/j_spring_security_logout" >');</script> <spring:message code="master.page.logout" /></a></b></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<security:authorize access="isAuthenticated()">
		<script>document.write('<a href="https://' + window.location.hostname + ':443?language=en" >en</a>');</script> | <script>document.write('<a href="https://' + window.location.hostname + ':443?language=es" >es</a>');</script>
	</security:authorize>
	<security:authorize access="isAnonymous()">
		<script>document.write('<a href="http://' + window.location.hostname + ':80?language=en" >en</a>');</script> | <script>document.write('<a href="http://' + window.location.hostname + ':80?language=es" >es</a>');</script>
	</security:authorize>
</div>

