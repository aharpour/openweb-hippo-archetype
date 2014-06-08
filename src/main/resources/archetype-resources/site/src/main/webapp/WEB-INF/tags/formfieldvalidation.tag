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

<%@ attribute name="ef_errors" type="java.util.LinkedHashMap" required="true"%>
<%@ attribute name="field" type="org.onehippo.forge.easyforms.model.AbstractField" required="true"%>

<c:set var="fieldError" value="${symbol_dollar}{tag:getFieldError(ef_errors, field.name)}" />

<c:choose>
	<c:when test="${symbol_dollar}{empty fieldError}">
		${symbol_dollar}{field.html}
	</c:when>
	<c:otherwise>
		<div class="errorField">
			${symbol_dollar}{field.html}
			<p class="formFieldError">${symbol_dollar}{fieldError}</p>
		</div>
	</c:otherwise>
</c:choose>