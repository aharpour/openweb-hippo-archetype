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

<div class="catalog navigation">
	<ul class="nav nav-pills nav-stacked">
		<c:forEach items="${symbol_dollar}{menu.menuItems}" var="topMenuItem">
			<c:if test="${symbol_dollar}{topMenuItem.expanded}">
				<c:forEach items="${symbol_dollar}{topMenuItem.childMenuItems}" var="item">
					<prototype:menuitem siteMenuItem="${symbol_dollar}{item}" depth="${symbol_dollar}{depth}"
						expandedClass="current arrow-down"
						selectedClass="active arrow-down"
						unexpandedClass="unexpanded arrow-side" leafClass="arrow-side" 
						recurseOnlyExpanded="${symbol_dollar}{recurseOnlyExpanded}"/>
				</c:forEach>
			</c:if>
		</c:forEach>
	</ul>
</div>