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

<%@ attribute name="facetnav" required="true" type="org.hippoecm.hst.content.beans.standard.HippoFacetNavigationBean"%>
<%@ attribute name="labels" required="false" type="java.util.Map"%>

<properties:property var="facetnavtitle" name="facet.navigation.title" documentPath="labels"/>

<div id="searchresult-category-container">
	<c:if test="${symbol_dollar}{facetnav.count gt 0}">
		<div id="facets">
			<h4><c:out value="${symbol_dollar}{facetnavtitle}" escapeXml="true"/></h4>
			<div class="form">
				<c:forEach var="facet" items="${symbol_dollar}{facetnav.folders}">
					<c:if test="${symbol_dollar}{facet.resultSet.count > 0}">
						<div class="fieldset">
							<c:choose>
								<c:when test="${symbol_dollar}{facet.leaf}">
									<h5 class="facetName">
										<c:out value="${symbol_dollar}{facet.name}" escapeXml="true" />
									</h5>
								</c:when>
								<c:otherwise>
									<h5 class="facetName">
										<c:out value="${symbol_dollar}{facet.name}" escapeXml="true" />
									</h5>
									<c:if test="${symbol_dollar}{not empty facet.folders}">
										<c:forEach items="${symbol_dollar}{facet.folders}" var="item">
											<c:choose>
												<c:when test="${symbol_dollar}{item.leaf and item.count gt 0}">
													<hst:facetnavigationlink remove="${symbol_dollar}{item}" current="${symbol_dollar}{facetnav}" var="removeLink" />
													<div class="input selected">
														<span> 
															<c:out value="${symbol_dollar}{labels[item.name]}" default="${symbol_dollar}{item.name}" escapeXml="true" />
														</span> 
														<a href="${symbol_dollar}{removeLink}" class="removeLink"></a>
													</div>
												</c:when>
												<c:when test="${symbol_dollar}{item.leaf and item.count eq 0}">
												</c:when>
												<c:otherwise>
													<hst:link var="link" hippobean="${symbol_dollar}{item}" navigationStateful="true" />
													<div class="input">
														<c:choose>
															<c:when test="${symbol_dollar}{query eq null}">
																<a href="${symbol_dollar}{link }"> 
																	<c:out value="${symbol_dollar}{labels[item.name]}" default="${symbol_dollar}{item.name}" escapeXml="true" />
																</a>
                            	  								(${symbol_dollar}{item.count })
                          									</c:when>
															<c:otherwise>
																<a href="${symbol_dollar}{link }"> 
																	<c:out value="${symbol_dollar}{labels[item.name]}" default="${symbol_dollar}{item.name}" escapeXml="true" />
																</a>
						                            	  		(${symbol_dollar}{item.count })
						                          			</c:otherwise>
														</c:choose>
													</div>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:if>
								</c:otherwise>
							</c:choose>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</c:if>
</div>
