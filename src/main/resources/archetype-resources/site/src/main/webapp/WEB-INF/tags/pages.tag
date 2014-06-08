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

<%@ attribute name="pages" required="true" type="java.util.List" rtexprvalue="true" %>
<%@ attribute name="page" required="true" type="java.lang.Integer" rtexprvalue="true" %>
<c:if test="${symbol_dollar}{not empty pages}">
  <div class="pagination">
    <ul>
      <c:forEach var="p" items="${symbol_dollar}{pages}">
        <c:set var="active" value=""/>
        <c:choose>
          <c:when test="${symbol_dollar}{page == p}">
            <li class="active"><a href="#">${symbol_dollar}{page}</a></li>
          </c:when>
          <c:otherwise>
            <hst:renderURL var="pagelink">
              <hst:param name="page" value="${symbol_dollar}{p}"/>
            </hst:renderURL>
            <li><a href="${symbol_dollar}{pagelink}" title="${symbol_dollar}{p}">${symbol_dollar}{p}</a></li>
          </c:otherwise>
        </c:choose>
      </c:forEach>
    </ul>
  </div>
</c:if>