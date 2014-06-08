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
<hst:defineObjects/>
<c:choose>
<c:when test="${symbol_dollar}{not empty model.forumShortname}">
	<hst:headContribution keyHint="component.disqus.js" category="scripts">
	<script type="text/javascript" async="" src="http://${symbol_dollar}{model.forumShortname}.disqus.com/embed.js"></script>
	</hst:headContribution>
	
	<div id="disqus_thread"></div>
	<noscript>Please enable JavaScript to view the comments.</noscript>
</c:when>
<c:when test="${symbol_dollar}{empty model.forumShortname and hstRequest.requestContext.cmsRequest}">
	<c:choose>
		<c:when test="${symbol_dollar}{not empty webmasterErrorMessage}">
			<p style="color: red">${symbol_dollar}{webmasterErrorMessage}</p>
		</c:when>
		<c:otherwise>
			<p style="color: red">You need to set Disqus forum shortname otherwise this component would not get rendered.</p>
		</c:otherwise>
	</c:choose>
</c:when>
</c:choose>