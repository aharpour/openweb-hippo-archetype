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

<div class="mid">
	<h2>${symbol_dollar}{document.title}</h2>
	<ul class="sitemap">
		<c:forEach items="${symbol_dollar}{menu.menuItems}" var="item">
			<prototype:menuitem siteMenuItem="${symbol_dollar}{item}" depth="5"
				expandedClass="current" selectedClass="active" />
		</c:forEach>
		<c:forEach items="${symbol_dollar}{serviceMenu.menuItems}" var="item">
			<prototype:menuitem siteMenuItem="${symbol_dollar}{item}" depth="5"
				expandedClass="current" selectedClass="active" />
		</c:forEach>
	</ul>
</div>



