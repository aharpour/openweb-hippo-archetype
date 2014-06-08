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
	<div class="row-fluid">
		<div class="span8-header">
			<div class="navbar">
				<div class="navbar-inner">

					<%-- maybe there are too many divs before main content ?? --%>
					<div class="container">
						<hst:link var="homeLink" path="/" />
						<div class="logo">
							<a href="${symbol_dollar}{homeLink}"> 
							    <c:choose>
									<c:when test="${symbol_dollar}{not empty logo}">
										<img src="<hst:link hippobean="${symbol_dollar}{logo.paragraphImage}"/>"
											alt="${symbol_dollar}{fn:escapeXml(headerName)}"
											title="${symbol_dollar}{fn:escapeXml(headerName)}" />
									</c:when>
									<c:otherwise>
										<img src="<hst:link path="images/logo.png"/>"
											alt="${symbol_dollar}{fn:escapeXml(headerName)}"
											title="${symbol_dollar}{fn:escapeXml(headerName)}" />
									</c:otherwise>
								</c:choose>
							</a>
						</div>
						<div class="navbar nav pull-right h-menu">
							<hst:include ref="serviceMenu" />
							<hst:include ref="langaugeswitch" />
							<fmt:message var="submitText" key="search.submit.text" /> <hst:link var="link" path="/search" />
							<form class="navbar-search form-search" action="${symbol_dollar}{link}" method="get">
								<p>
									<input type="text" name="q" class="search-query input-xlarge" placeholder="${symbol_dollar}{submitText}" required="required" />
									<button class="btn btn-primary inline" type="submit" value="${symbol_dollar}{submitText}">${symbol_dollar}{submitText}</button>
								</p>
							</form>
						</div>
						
						<div class="nav-collapse">
							
							<hst:include ref="mainMenu" />
						</div>

						<div class="clear-both"></div>
					</div>
					<%-- maybe there are too many divs before main content ?? --%>

				</div>
			</div>
		</div>
	</div>
</div>