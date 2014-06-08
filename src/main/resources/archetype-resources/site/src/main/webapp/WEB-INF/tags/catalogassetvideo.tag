#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
#set($hyphen = '-')
#set($empty = '')
#set($namespace = ${rootArtifactId.replace($hyphen,$empty)})
<%@ tag language="java"	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="prototype" uri="http://www.tdclighthouse.com/hippo/prototype"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="hst" uri="http://www.hippoecm.org/jsp/hst/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="tag" uri="/WEB-INF/tags/tags.tld"%>
<%@ taglib prefix='properties' uri="http://www.onehippo.org/properties/jsp/tags"%>

<%@ attribute name="videoAssetPath" rtexprvalue="true" required="false" type="org.hippoecm.hst.content.beans.standard.HippoBean" %>
<%@ attribute name="model" rtexprvalue="true" required="true" type="java.util.Map" %>

<hst:headContribution keyHint="generic.swfobject.js">
	<script type="text/javascript" src="<hst:link path="/js/swfobject.js" />"></script>
</hst:headContribution>

<hst:link var="pathToPlayer" path="/swf/flvplayer.swf"/>
<c:choose>
	<c:when test="${symbol_dollar}{not empty videoAssetPath }">
		<hst:link var="videoPath" hippobean="${symbol_dollar}{videoAssetPath}" />
		<div class="video block clearfix">
			<div class="mediaspace">
				<div id='mediaspace-${symbol_dollar}{pageContext.request.referenceNamespace}'>
		      	</div>
			</div>
			<script type='text/javascript'>
				var s${symbol_dollar}{pageContext.request.referenceNamespace} = new SWFObject("${symbol_dollar}{pathToPlayer}","single","${symbol_dollar}{model.info.width}","${symbol_dollar}{model.info.height}","7");
				s${symbol_dollar}{pageContext.request.referenceNamespace}.addParam("allowfullscreen","${model.info.allowFullScreen}");
				s${symbol_dollar}{pageContext.request.referenceNamespace}.addVariable("file","${symbol_dollar}{videoPath}");
				s${symbol_dollar}{pageContext.request.referenceNamespace}.addVariable("width","${symbol_dollar}{model.info.width}");
				s${symbol_dollar}{pageContext.request.referenceNamespace}.addVariable("height","${symbol_dollar}{model.info.height}");
				s${symbol_dollar}{pageContext.request.referenceNamespace}.write('mediaspace-${symbol_dollar}{pageContext.request.referenceNamespace}'); 
			</script>
		</div>
	</c:when>
	<%-- 
	<c:otherwise>
		<hst:headContribution keyHint="embed.dynamicheight.js">
			<script type="text/javascript" src="<hst:link path="/js/dynamic-hight.js" />"></script>
		</hst:headContribution>
		<object class="youtube">
      		<param name="movie" value="${symbol_dollar}{model.info.videoURL}"/>
      		<param name="allowFullScreen" value="${symbol_dollar}{model.info.allowFullScreen}"/>
      		<param name="allowScriptAccess" value="always"/>
      		<embed src="${symbol_dollar}{model.info.videoURL}" type="application/x-shockwave-flash" allowfullscreen="${symbol_dollar}{model.info.allowFullScreen}"
       	 		allowScriptAccess="always" wmode="opaque" />
    	</object>
	</c:otherwise>
	--%>
</c:choose>
