#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
#set($hyphen = '-')
#set($empty = '')
#set($namespace = ${rootArtifactId.replace($hyphen,$empty)})
<%@ page contentType="text/html; charset=UTF-8" language="java"	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<!doctype html>
<%@ taglib prefix="prototype" uri="http://www.tdclighthouse.com/hippo/prototype"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="hst" uri="http://www.hippoecm.org/jsp/hst/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="tag" uri="/WEB-INF/tags/tags.tld"%>
<%@ taglib prefix='properties' uri="http://www.onehippo.org/properties/jsp/tags"%>


<html lang="${symbol_dollar}{pageContext.request.locale.language}">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<c:if test="${symbol_dollar}{prototype:issubclassofwebdocument(document)}">
<c:if test="${symbol_dollar}{not empty document.description }">
<meta name="description" content="<c:out value="${symbol_dollar}{document.description}" escapeXml="true" />" />
</c:if>
<c:if test="${symbol_dollar}{not empty document.author }">
<meta name="author" content="<c:out value="${symbol_dollar}{document.author}" escapeXml="true" />" />
</c:if>
<c:if test="${symbol_dollar}{not empty document.keywords }">
<meta name="keywords" content="<c:out value="${symbol_dollar}{document.keywords}" escapeXml="true" />" />
</c:if>
</c:if>

<script type="text/javascript">
	var contextPath = "${symbol_dollar}{prototype:contextPath(pageContext.request)}";
</script>

<link rel="shortcut icon" href="<hst:link path="/images/favicon.ico" />" />
<link rel="stylesheet" href="<hst:link path="/css/style.css" />" type="text/css" />
<link rel="stylesheet" href="<hst:link path="/css/catalog.css" />" type="text/css" />

<c:choose>
<c:when test="${symbol_dollar}{prototype:issubclassofwebdocument(document)}">
	<title><c:out value="${symbol_dollar}{document.browserTitle}" escapeXml="true" /></title>
</c:when>
<c:otherwise>
	<title><c:out value="${symbol_dollar}{tag:getDefaultBrowserTitle(pageContext.request)}" escapeXml="true" /></title>
</c:otherwise>
</c:choose>

<hst:headContributions categoryExcludes="scripts" xhtml="true" />

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>
<body>
	<div class="wrapper">
		<div class="page">
			<div class="header-container">
				<hst:include ref="header" />
			</div>
			<div class="main-container">
				<hst:include ref="main" />
			</div>
			<div class="footer-container">
				<hst:include ref="footer" />
			</div>
		</div>
	</div>
	
	<hst:headContributions categoryIncludes="scripts" xhtml="true" />
		
	<c:if test="${symbol_dollar}{not composermode}">
	    <ga:accountId/>
	    <hst:link var="googleAnalytics" path="/resources/google-analytics.js"/>
    	<script src="${symbol_dollar}{googleAnalytics}" type="text/javascript"></script>
  	</c:if>
  	
</body>
</html>