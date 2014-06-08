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
  <c:when test="${symbol_dollar}{empty document}">
    <tag:pagenotfound/>
  </c:when>
  <c:otherwise>

    <article class="well well-large">
      <hst:cmseditlink hippobean="${symbol_dollar}{document}"/>
      <header>
        <h2>${symbol_dollar}{fn:escapeXml(document.title)}</h2>
        <h3>${symbol_dollar}{fn:escapeXml(document.subtitle)}</h3>
      </header>
      <hst:html hippohtml="${symbol_dollar}{document.html}"/>
    </article>

  </c:otherwise>
</c:choose>