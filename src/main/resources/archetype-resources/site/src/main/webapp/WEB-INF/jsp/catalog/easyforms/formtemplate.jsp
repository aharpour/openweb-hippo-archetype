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

<div class="catalog formtemplate">	
	<hst:defineObjects/>
	<c:set var="isCmsRequest" value="${symbol_dollar}{hstRequest.requestContext.cmsRequest}"/>
		<c:if test="${symbol_dollar}{not empty webmasterErrorMessage and isCmsRequest}">
			<c:forEach items="${symbol_dollar}{webmasterErrorMessage}" var="message">
				<p class="error-message">${symbol_dollar}{message}</p>
			</c:forEach>
		</c:if>
	
	<c:if test="${symbol_dollar}{ empty webmasterErrorMessage }">	
		<c:choose>
			<c:when test="${symbol_dollar}{not empty form and fileuploadEnabled}">
		  		<tag:easyform form="${symbol_dollar}{form}" multipart="true" />
			</c:when>
			<c:otherwise>
				<tag:easyform form="${symbol_dollar}{form}"/>
			</c:otherwise>
		</c:choose>
	</c:if>
</div>
