#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
#set($hyphen = '-')
#set($empty = '')
#set($namespace = ${rootArtifactId.replace($hyphen,$empty)})
<%@ page contentType="text/html; charset=UTF-8" language="java"	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="prototype" uri="http://www.tdclighthouse.com/hippo/prototype"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="hst" uri="http://www.hippoecm.org/jsp/hst/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="tag" uri="/WEB-INF/tags/tags.tld"%>
<%@ taglib prefix='properties' uri="http://www.onehippo.org/properties/jsp/tags"%>

<ul class="nav mainMenuLink">
	<c:forEach items="${symbol_dollar}{menu.menuItems}" var="siteMenuItem" varStatus="step">
		<c:choose>
			<c:when test="${symbol_dollar}{not empty siteMenuItem.hstLink }">
				<hst:link var="link" link="${symbol_dollar}{siteMenuItem.hstLink}" />
			</c:when>
			<c:otherwise>
				<c:set var="link" value="${symbol_dollar}{siteMenuItem.externalLink}" />
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${symbol_dollar}{siteMenuItem.expanded and step.last}">
				<c:set var="cssClass" value="active last"/>
			</c:when>
			<c:when test="${symbol_dollar}{siteMenuItem.expanded}">
				<c:set var="cssClass" value="active"/>
			</c:when>
			<c:when test="${symbol_dollar}{step.last}">
				<c:set var="cssClass" value="last"/>
			</c:when>
			<c:otherwise>
				<c:set var="cssClass" value=""/>
			</c:otherwise>
		</c:choose>
		
		<li ${symbol_dollar}{not empty cssClass ? ' class=\"': ''}${symbol_dollar}{cssClass}${symbol_dollar}{not empty cssClass ? '\"': ''}>
			<a href="${symbol_dollar}{link}"> 
				<c:out value="${symbol_dollar}{siteMenuItem.name}" />
			</a>
		</li>
	</c:forEach>
</ul>