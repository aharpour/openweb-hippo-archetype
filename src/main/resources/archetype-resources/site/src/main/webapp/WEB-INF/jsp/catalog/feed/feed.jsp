<%--
    Copyright 2010-2013 Hippo B.V. (http://www.onehippo.com)

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

            http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

	modified by Smile Benelux (http://smile-benelux.com/)
--%>
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

<div class="catalog feed">
	<hst:defineObjects/>
	<c:set var="isCmsRequest" value="${symbol_dollar}{hstRequest.requestContext.cmsRequest}"/>
	<c:if test="${symbol_dollar}{empty model.feed and isCmsRequest}">
		<c:choose>
			<c:when test="${symbol_dollar}{not empty model.webmasterErrorMessage}">
				<p class="error-message">${symbol_dollar}{model.webmasterErrorMessage}</p>
			</c:when>
			<c:otherwise>
				<p class="error-message">There are no feeds for ${symbol_dollar}{model.info.feedUrl}. The widget will not display.</p>
			</c:otherwise>
		</c:choose>
	</c:if>

	<c:if test="${symbol_dollar}{not empty model.feed and model.info.numberOfItems > 0}">
  		<div class="box-general">
    		<c:if test="${symbol_dollar}{not empty model.info.title}">
     			<div class="title">
     				<h2 class="twitter-title"><c:out value="${symbol_dollar}{model.info.title}"/></h2>
      			</div>
    		</c:if>
    		<c:forEach items="${symbol_dollar}{model.feed.entries}" var="entry" end="${symbol_dollar}{model.info.numberOfItems - 1}">
        		<div class="feed-item">
        			<div class="image-date-container">
          				<c:choose>
            				<c:when test="${symbol_dollar}{fn:contains(model.info.feedUrl,'twitter')}">
             					<c:set var="twitterScreenName" value="${symbol_dollar}{fn:substringAfter(model.info.feedUrl,'screen_name=')}"/>
               					<div class="image">
               						<img src="http://api.twitter.com/1/users/profile_image/twitter.json?size=normal&amp;screen_name=${symbol_dollar}{twitterScreenName}"
                    						alt="Twitter" title="Twitter"/>
               					</div>
               					<div class="date">
			            			<fmt:formatDate value="${symbol_dollar}{entry.publishedDate}" type="date" pattern="d MMM yyyy"/>
			            			<c:if test="${symbol_dollar}{model.feed.author != null and not empty model.feed.author}">
			            				<c:out value="- ${symbol_dollar}{model.feed.author}"/>
			            			</c:if>
			          			</div>
            				</c:when>
				            <c:otherwise>
					        	<div class="image">
					            	<c:if test="${symbol_dollar}{model.feed.image != null and model.feed.image.url != null}">
					                	<img src="${symbol_dollar}{fn:escapeXml(model.feed.image.url)}" alt="image" title="image"/>
					                </c:if>
					     		</div>
					     		<div class="date">
			            			<fmt:formatDate value="${symbol_dollar}{entry.publishedDate}" type="date" pattern="d MMM yyyy"/>
			            			<c:if test="${symbol_dollar}{model.feed.author != null and not empty model.feed.author}">
			            				<c:out value="- ${symbol_dollar}{model.feed.author}"/>
			            			</c:if>
			          			</div>	
				   			</c:otherwise>
          				</c:choose>
         			</div>
         			<div class="content-container">
			          	<div class="content">
			            	<a href="${symbol_dollar}{fn:escapeXml(entry.link)}"><c:out escapeXml="true" value="${symbol_dollar}{entry.title}"/></a>
			          	</div>
		          	</div>
        		</div>
    		</c:forEach>
 	 	</div>
	</c:if>
</div>