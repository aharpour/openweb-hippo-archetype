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
<c:when test="${symbol_dollar}{fn:length(model.items) > 0}">
<hst:defineObjects />
<hst:headContribution keyHint="generic.jquery.js">
	<script type="text/javascript" src="<hst:link path="/js/jquery-2.0.1.min.js" />"></script>
</hst:headContribution>
<hst:headContribution keyHint="generic.carousel.js">
	<script type="text/javascript" src="<hst:link path="/js/dynamic-carousel.js" />"></script>
</hst:headContribution>
<hst:headContribution category="scripts">
<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery('.${symbol_dollar}{pageContext.request.referenceNamespace}').carousel({
			slider : '.carousel-list',
			slide : '.carousel-item',
			prevSlide : '.${symbol_dollar}{pageContext.request.referenceNamespace}_prev',
			nextSlide : '.${symbol_dollar}{pageContext.request.referenceNamespace}_next',
			addNav : false,
			addPagination : true,
			speed : 300,
			backToStart : true
		});
	});
</script>
</hst:headContribution>
<c:set var="backgroundColor" value="${symbol_dollar}{not empty model.parametersInfo.captionBackgroundColor ? 'style=\"background-color:': ''}${symbol_dollar}{not empty model.parametersInfo.captionBackgroundColor ? model.parametersInfo.captionBackgroundColor : ''}${symbol_dollar}{not empty model.parametersInfo.captionBackgroundColor ? '\"': ''}"/>
<c:set var="fontColor" value="${symbol_dollar}{not empty model.parametersInfo.fontColor ? 'style=\"color:': ''}${symbol_dollar}{not empty model.parametersInfo.fontColor ? model.parametersInfo.fontColor : ''}${symbol_dollar}{not empty model.parametersInfo.fontColor ? '\"': ''}"/>
<c:if test="${symbol_dollar}{not model.parametersInfo.showCaption}">
<hst:headContribution>
<style>
	.catalog.carousel .${symbol_dollar}{pageContext.request.referenceNamespace} .carousel-tabs {
		bottom: 0.5em;
	}
</style>
</hst:headContribution>
</c:if>
<div class="catalog carousel">
	<div class="${symbol_dollar}{pageContext.request.referenceNamespace}">
		<ul class="carousel-list"
			id="${symbol_dollar}{pageContext.request.referenceNamespace}_sliderName">
			<c:forEach items="${symbol_dollar}{model.items}" var="item">
				<hst:link hippobean="${symbol_dollar}{item.imageLink}" var="imageLink" />
				<c:choose>
					<c:when test="${symbol_dollar}{hst:isReadable(item.internalLink, 'title')}">
						<hst:link hippobean="${symbol_dollar}{item.internalLink}" var="link" />
						<c:set var="relationship" value="" />
					</c:when>
					<c:when test="${symbol_dollar}{not empty item.externalLink}">
						<c:set var="link" value="${symbol_dollar}{item.externalLink.url}" />
						<c:set var="relationship"
							value="${symbol_dollar}{item.externalLink.relationship}" />
					</c:when>
				</c:choose>
				<li>
					<div class="carousel-item">
						<div class="image">
							<c:choose>
								<c:when test="${symbol_dollar}{not empty link and relationship eq 'external'}">
									<a href="${symbol_dollar}{link}" target="_blank"><img alt="${symbol_dollar}{item.alt}"
										src="${symbol_dollar}{imageLink}"></a>
								</c:when>
								<c:when test="${symbol_dollar}{not empty link}">
									<a href="${symbol_dollar}{link}"><img alt="${symbol_dollar}{item.alt}" src="${symbol_dollar}{imageLink}"></a>
								</c:when>
								<c:otherwise>
									<img alt="${symbol_dollar}{item.alt}" src="${symbol_dollar}{imageLink}">
								</c:otherwise>
							</c:choose>
						</div>
						<c:if test="${symbol_dollar}{model.parametersInfo.showCaption}">
							<div class="caption" ${symbol_dollar}{backgroundColor}>
								<p ${symbol_dollar}{fontColor}>${symbol_dollar}{item.title}</p>
							</div>
						</c:if>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div class="controller">
		<div class="previous">
			<a href="#${symbol_dollar}{pageContext.request.referenceNamespace}_sliderName"
				class="${symbol_dollar}{pageContext.request.referenceNamespace}_prev"><img src="<hst:link path="/images/catalog/prev-icon.png"/>"/></a>
		</div>
		<div class="next">
			<a href="#${symbol_dollar}{pageContext.request.referenceNamespace}_sliderName"
				class="${symbol_dollar}{pageContext.request.referenceNamespace}_next"><img src="<hst:link path="/images/catalog/next-icon.png"/>"/></a>
		</div>
	</div>
</div>
</c:when>
<c:otherwise>
	<hst:defineObjects/>
	<c:set var="isCmsRequest" value="${symbol_dollar}{hstRequest.requestContext.cmsRequest}"/>
	<c:if test="${symbol_dollar}{isCmsRequest}">
		<div class="catalog carousel">
			<c:choose>
				<c:when test="${symbol_dollar}{not empty model.webmasterErrorMessage}">
					<p class="error-message">${symbol_dollar}{model.webmasterErrorMessage}</p>
				</c:when>
				<c:otherwise>
					<p class="error-message"><fmt:message key="carousel.no.items"/></p>
				</c:otherwise>
			</c:choose>
		</div>
	</c:if>
</c:otherwise>
</c:choose>