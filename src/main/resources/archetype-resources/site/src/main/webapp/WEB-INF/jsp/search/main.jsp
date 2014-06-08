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

<div class="container-fluid">
	<hst:include ref="top-container" />    
  	<div class="row-fluid">
		<nav class="span2-left">
			<hst:include ref="leftTop" />
			<tag:facetednavigation facetnav="${symbol_dollar}{model.facetBean}" labels="${symbol_dollar}{model.labels}"></tag:facetednavigation>
			<hst:include ref="leftBottom" />
		</nav>
		<div class="span8-center">
			<hst:include ref="contentTop" />
			<%--content in-lined for better performance --%>
			<c:choose>
				<c:when test="${symbol_dollar}{empty model.document}">
					<tag:pagenotfound />
				</c:when>
				<c:otherwise>
					
					<div class="inner">
						<c:if test="${symbol_dollar}{not empty model.document.introduction }">
							<p class="intro">
								<c:out value="${symbol_dollar}{model.document.introduction }" />
							</p>
						</c:if>
					</div>
						
					<tag:resultsummary paginator="${symbol_dollar}{model.paginator}" />
			
					<c:forEach var="item" items="${symbol_dollar}{model.items}">
						<hst:link var="link" hippobean="${symbol_dollar}{item}" />
						<article class="well well-large">
							<hst:cmseditlink hippobean="${symbol_dollar}{item}" />
							<h3>
								<a href="${link}">${symbol_dollar}{fn:escapeXml(item.title)}</a>
							</h3>
							<c:if test="${symbol_dollar}{hst:isReadable(item, 'releaseDate.time')}">
								<p class="badge badge-info">
									<fmt:formatDate value="${symbol_dollar}{item.releaseDate.time}" type="both" dateStyle="medium" timeStyle="short" />
								</p>
							</c:if>
							<p>${symbol_dollar}{fn:escapeXml(item.introduction)}</p>
						</article>
					</c:forEach>
					
				  	<div class="paginator-style">
			 			<prototype:simplepaginator paginator="${symbol_dollar}{model.paginator}"/>
					</div>
			
				</c:otherwise>
			</c:choose>

			<hst:include ref="contentBottom" />
		</div>
		<aside class="span2-right">
			<hst:include ref="rightTop" />
			<hst:include ref="right" />
			<hst:include ref="rightBottom" />
		</aside>
  	</div>
	<hst:include ref="bottom-container" />
</div>
