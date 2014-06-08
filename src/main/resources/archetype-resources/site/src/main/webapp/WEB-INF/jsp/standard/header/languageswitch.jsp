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
<ul class="language-switch">
	<c:forEach items="${symbol_dollar}{translations}" var="translation">
		<hst:link var="link" link="${symbol_dollar}{translation.link}" fullyQualified="true" />
		<c:set var="languageName">
			<c:out value="${symbol_dollar}{labels[translation.language]}"
				default="${symbol_dollar}{translation.language}" escapeXml="true" />
		</c:set>
		<c:choose>
			<c:when test="${symbol_dollar}{translation.selected}">
				<li class="active"><a><span>${symbol_dollar}{languageName}</span></a></li>
			</c:when>
			<c:otherwise>
				<li><a title="${symbol_dollar}{languageName}" href="${symbol_dollar}{link}"><span>${symbol_dollar}{languageName}</span></a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</ul>
