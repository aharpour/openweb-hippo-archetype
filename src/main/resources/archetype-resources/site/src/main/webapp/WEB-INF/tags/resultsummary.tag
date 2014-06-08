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

<%@ attribute name="paginator" type="com.tdclighthouse.prototype.utils.PaginatorWidget" required="true" %>

<c:if test="${symbol_dollar}{not empty paginator}">
 	<c:choose>
	   	<c:when test="${symbol_dollar}{paginator.totalRows eq 0 }">			
	     	<h3><properties:property name="search.result.no" documentPath="search"/></h3>
	   	</c:when>
	   	<c:when test="${symbol_dollar}{paginator.totalRows > 1 }">
	     	<h3><span class="important">${symbol_dollar}{model.facetBean.count}</span>&nbsp;<properties:property name="search.result.multiple" documentPath="search"/></h3>
	   	</c:when>
	   	<c:otherwise>
	     	<h3><span class="important">${symbol_dollar}{model.facetBean.count }</span>&nbsp;<properties:property name="search.result.single" documentPath="search"/></h3>
		</c:otherwise>
 	</c:choose>
</c:if>