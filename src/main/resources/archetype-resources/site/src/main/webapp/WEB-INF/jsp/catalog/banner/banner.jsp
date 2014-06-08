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

<c:choose>
<c:when test="${symbol_dollar}{not empty model.document}">
	<hst:defineObjects />
	
	<hst:headContribution keyHint="catalog.banner.css">
		<link rel="stylesheet" href="<hst:link path="/css/catalog/banner.css"/>" type="text/css" />
	</hst:headContribution>
	
	<div class="catalog banner">
		<ul class="banner-list">
			<hst:link hippobean="${symbol_dollar}{model.document.imageLink}" var="imageLink" />
			<c:choose>
				<c:when test="${symbol_dollar}{hst:isReadable(model.document.internalLink, 'title')}">
					<hst:link hippobean="${symbol_dollar}{model.document.internalLink}" var="link" />
					<c:set var="relationship" value="" />
				</c:when>
				<c:when test="${symbol_dollar}{not empty model.document.externalLink}">
					<c:set var="link" value="${symbol_dollar}{model.document.externalLink.url}" />
					<c:set var="relationship" value="${symbol_dollar}{model.document.externalLink.relationship}" />
				</c:when>
			</c:choose>
			<li>
				<div class="banner-item">
					<c:if test="${symbol_dollar}{not empty imageLink}">
						<div class="image">
							<c:choose>
								<c:when test="${symbol_dollar}{not empty link and relationship eq 'external'}">
									<a href="${symbol_dollar}{link}" target="_blank">
										<img alt="${symbol_dollar}{model.document.alt}" src="${symbol_dollar}{imageLink}" title="${symbol_dollar}{model.document.title}">
									</a>
								</c:when>
								<c:when test="${symbol_dollar}{not empty link}">
									<a href="${symbol_dollar}{link}">
										<img alt="${symbol_dollar}{model.document.alt}" src="${symbol_dollar}{imageLink}" title="${symbol_dollar}{model.document.title}">
									</a>
								</c:when>
								<c:otherwise>
									<img alt="${symbol_dollar}{model.document.alt}" src="${symbol_dollar}{imageLink}" title="${symbol_dollar}{model.document.title}">
								</c:otherwise>
							</c:choose>
						</div>
					</c:if>
					<div class="banner-html">
						<hst:html hippohtml="${symbol_dollar}{model.document.html}"/>
					</div>
				</div>
			</li>
		</ul>
	</div>
</c:when>
<c:otherwise>
	<hst:defineObjects/>
	<c:set var="isCmsRequest" value="${symbol_dollar}{hstRequest.requestContext.cmsRequest}"/>
	<c:if test="${symbol_dollar}{isCmsRequest}">
		<div class="catalog banner">
			<c:choose>
				<c:when test="${symbol_dollar}{not empty model.webmasterErrorMessage}">
					<p class="error-message">${symbol_dollar}{model.webmasterErrorMessage}</p>
				</c:when>
				<c:otherwise>
					<p class="error-message"><fmt:message key="banner.no.items"/></p>
				</c:otherwise>
			</c:choose>
		</div>
	</c:if>
</c:otherwise>
</c:choose>