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

<c:forEach var="item" items="${symbol_dollar}{model.items}" varStatus="zebra">
	<div class="item-with-image">
		<div class="image-space">
			<c:if test="${symbol_dollar}{hst:isReadable(item, 'image')}">
				<c:if test="${symbol_dollar}{ not empty item.image.link.deref }">
					<hst:link var="image"
						hippobean="${symbol_dollar}{item.image.link.deref.listSmallImage }" />
					<img alt="${symbol_dollar}{item.title }" src="${symbol_dollar}{image }" />
				</c:if>
			</c:if>
			<div class="date">
				<fmt:formatDate value="${symbol_dollar}{item.releaseDate.time}" type="Date"
					pattern="dd.MM.yyyy" />
			</div>
		</div>
		<div class="itemContent">
			<div class=itemTitle>
				<hst:link hippobean="${symbol_dollar}{item }" var="link" />
				<c:set var="title"><c:out value="${symbol_dollar}{item.title }" escapeXml="true" /></c:set>
				<a href="${symbol_dollar}{link }" title="${symbol_dollar}{title }"><h5>${symbol_dollar}{title }</h5></a>
			</div>

			<c:if test="${symbol_dollar}{not empty item.introduction }">
				<div class=introduction>
					<p>
						<prototype:string-chopper maxLength="${symbol_dollar}{model.info.maxLength}" bean="${symbol_dollar}{item }"
							stringPath="introduction" allowedLengthTolerance="${symbol_dollar}{model.info.cuttingTolerance }"
							showDots="true" />
					</p>
				</div>
			</c:if>
		</div>
		<div class="clear-both"></div>
	</div>
</c:forEach>