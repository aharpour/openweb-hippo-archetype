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

<hst:headContribution keyHint="generic.jquery.js">
	<script type="text/javascript" src="<hst:link path="/js/jquery-2.0.1.min.js" />"></script>
</hst:headContribution>

<hst:headContribution keyHint="embed.dynamicheight.js">
	<script type="text/javascript" src="<hst:link path="/js/dynamic-hight.js" />"></script>
</hst:headContribution>

<div class="catalog video">
	<hst:defineObjects/>
	<c:set var="isCmsRequest" value="${symbol_dollar}{hstRequest.requestContext.cmsRequest}"/>
	<c:if test="${symbol_dollar}{isCmsRequest && empty model.videoURL}">
	    <c:choose>
	    	<c:when test="${symbol_dollar}{not empty model.webmasterErrorMessage}">
				<p class="error-message">${symbol_dollar}{model.webmasterErrorMessage}</p>
			</c:when>
	    	<c:otherwise>
	    		<p class="error-message">The specified url is not correct. Please copy-paste it from youtube. The widget will not display</p>
	    	</c:otherwise>
	    </c:choose>
	</c:if>
		
	<c:if test="${symbol_dollar}{not empty model.videoURL}">
		<object class="youtube">
			<param name="movie" value="${symbol_dollar}{model.videoURL}"/>
		    <param name="allowFullScreen" value="${symbol_dollar}{model.allowFullScreen}"/>
		    <param name="allowScriptAccess" value="always"/>
		    <embed src="${symbol_dollar}{model.videoURL}" type="application/x-shockwave-flash" 
		      	allowfullscreen="${symbol_dollar}{model.allowFullScreen}"
		        allowscriptaccess="always"/> 
		</object>
	</c:if>
</div>