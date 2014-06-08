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

<c:if test="${symbol_dollar}{prototype:issubclassofwebdocument(document)}">
		<div class="inner">
			<c:if test="${symbol_dollar}{not empty document.introduction }">
				<p class="intro">
					<c:out value="${symbol_dollar}{document.introduction }" />
				</p>
			</c:if>
		</div>
</c:if>
				
<c:forEach var="item" items="${symbol_dollar}{items}">
    <hst:link var="link" hippobean="${symbol_dollar}{item}"/>
    <article class="well well-large">
        <hst:cmseditlink hippobean="${symbol_dollar}{item}"/>
        <h3><a href="${link}">${symbol_dollar}{fn:escapeXml(item.title)}</a></h3>
        <c:if test="${symbol_dollar}{hst:isReadable(item, 'releaseDate.time')}">
          	<p class="badge badge-info">
            	<fmt:formatDate value="${symbol_dollar}{item.releaseDate.time}" type="both" dateStyle="medium" timeStyle="short"/>
          	</p>
        </c:if>
        <p>${symbol_dollar}{fn:escapeXml(item.introduction)}</p>
	</article>
</c:forEach>

<div class="paginator-style">
	<prototype:simplepaginator paginator="${symbol_dollar}{paginator}"/> 
</div>