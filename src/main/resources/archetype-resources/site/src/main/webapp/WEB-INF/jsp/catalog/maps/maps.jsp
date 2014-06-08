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
	<c:when
		test="${symbol_dollar}{not empty model.maps.latitude && not empty model.maps.longitude && (not (model.maps.latitude eq 0.0) or not (model.maps.longitude eq 0.0))}">
		<c:set var="markerLocation">${symbol_dollar}{model.maps.latitude},${symbol_dollar}{model.maps.longitude}</c:set>
		<c:set var="mode" value="coordinates" />
	</c:when>
	<c:when test="${symbol_dollar}{not empty model.maps.address}">
		<c:set var="markerLocation" value="${symbol_dollar}{prototype:urlEncode(model.maps.address)}" />
		<c:set var="mode" value="address" />
	</c:when>
	<c:otherwise>
		<c:set var="mode" value="none" />
	</c:otherwise>
</c:choose>


<c:if test="${symbol_dollar}{model.maps.showDynamicMap && not (mode eq 'none')}">
	<hst:headContribution keyHint="generic.googleapis.maps.js">
		<c:choose>
			<c:when test="${symbol_dollar}{not empty model.maps.apiKey}">
				<script type="text/javascript"
					src="https://maps.googleapis.com/maps/api/js?key=${symbol_dollar}{model.maps.apiKey}&sensor=${symbol_dollar}{model.maps.sensor}"></script>
			</c:when>
			<c:otherwise>
				<script type="text/javascript"
					src="https://maps.googleapis.com/maps/api/js?sensor=${symbol_dollar}{model.maps.sensor}"></script>
			</c:otherwise>
		</c:choose>
	</hst:headContribution>
	<hst:headContribution category="scripts">
		<c:choose>
			<c:when test="${symbol_dollar}{mode eq 'address'}">
				<script type="text/javascript">
	function initialize${symbol_dollar}{pageContext.request.referenceNamespace}() {
		var geocoder = new google.maps.Geocoder();
		geocoder.geocode({ address : '${symbol_dollar}{model.maps.address}'},
						function(results, status) {
							if (status == google.maps.GeocoderStatus.OK) {
								var mapOptions = {
									center : results[0].geometry.location,
									<c:if test="${symbol_dollar}{not empty model.maps.backgroundColor}">backgroundColor: '${symbol_dollar}{model.maps.backgroundColor}',</c:if>
									zoom : ${symbol_dollar}{model.maps.zoom},
									mapTypeId : google.maps.MapTypeId.${symbol_dollar}{model.maps.mapType},
									disableDefaultUI : ${symbol_dollar}{model.maps.disableDefaultUI},
									overviewMapControl : ${symbol_dollar}{model.maps.overviewMapControl},
									panControl : ${symbol_dollar}{model.maps.panControl},
									tilt: ${symbol_dollar}{model.maps.tilt},
									rotateControl : ${symbol_dollar}{model.maps.rotateControl},
									scaleControl : ${symbol_dollar}{model.maps.scaleControl},
									scrollwheel : ${symbol_dollar}{model.maps.scrollwheel},
									streetViewControl : ${symbol_dollar}{model.maps.streetViewControl},
									draggable : ${symbol_dollar}{model.maps.draggable},
									disableDoubleClickZoom : ${symbol_dollar}{model.maps.disableDoubleClickZoom} 
								};
								var map = new google.maps.Map(
										document
												.getElementById("${symbol_dollar}{pageContext.request.referenceNamespace}"),
										mapOptions);
								var marker = new google.maps.Marker({
									<c:if test="${symbol_dollar}{not empty model.maps.markerTitle}">title: '${symbol_dollar}{model.maps.markerTitle}',</c:if>
									map : map,
									position : results[0].geometry.location
								});
							}
						});
		

	}
	
	google.maps.event.addDomListener(window, 'load', initialize${symbol_dollar}{pageContext.request.referenceNamespace});
	</script>
			</c:when>
			<c:when test="${symbol_dollar}{mode eq 'coordinates'}">
				<script type="text/javascript">
	function initialize${symbol_dollar}{pageContext.request.referenceNamespace}() {
		var mapOptions = {
			center : new google.maps.LatLng(${symbol_dollar}{markerLocation}),
			<c:if test="${symbol_dollar}{not empty model.maps.backgroundColor}">backgroundColor: '${symbol_dollar}{model.maps.backgroundColor}',</c:if>
			zoom : ${symbol_dollar}{model.maps.zoom},
			mapTypeId : google.maps.MapTypeId.${symbol_dollar}{model.maps.mapType},
			disableDefaultUI : ${symbol_dollar}{model.maps.disableDefaultUI},
			overviewMapControl : ${symbol_dollar}{model.maps.overviewMapControl},
			panControl : ${symbol_dollar}{model.maps.panControl},
			rotateControl : ${symbol_dollar}{model.maps.rotateControl},
			scaleControl : ${symbol_dollar}{model.maps.scaleControl},
			scrollwheel : ${symbol_dollar}{model.maps.scrollwheel},
			streetViewControl : ${symbol_dollar}{model.maps.streetViewControl},
			draggable : ${symbol_dollar}{model.maps.draggable},
			disableDoubleClickZoom : ${symbol_dollar}{model.maps.disableDoubleClickZoom}
		};
		var map = new google.maps.Map(document.getElementById("${symbol_dollar}{pageContext.request.referenceNamespace}"), mapOptions);
		var marker = new google.maps.Marker({
			<c:if test="${symbol_dollar}{not empty model.maps.markerTitle}">title: '${symbol_dollar}{model.maps.markerTitle}',</c:if>
			map : map,
			position : new google.maps.LatLng(${symbol_dollar}{markerLocation})
		});
	}
	google.maps.event.addDomListener(window, 'load', initialize${symbol_dollar}{pageContext.request.referenceNamespace});	
	</script>
			</c:when>
		</c:choose>
	</hst:headContribution>
</c:if>
<hst:defineObjects/>

<c:if test="${symbol_dollar}{(not (mode eq 'none')) or hstRequest.requestContext.cmsRequest}">
	<div class="catalog maps">
		<c:if test="${symbol_dollar}{not (mode eq 'none')}">
			<div id="${symbol_dollar}{pageContext.request.referenceNamespace}" style="height: ${symbol_dollar}{model.maps.height}px;">
				<c:if test="${symbol_dollar}{model.maps.showStaticsMap }">
					<c:choose>
						<c:when test="${symbol_dollar}{not empty model.maps.signature and not empty model.maps.client}">
							<c:set var="staticMapLink"
								value="http://maps.googleapis.com/maps/api/staticmap?zoom=${symbol_dollar}{model.maps.zoom}&size=${symbol_dollar}{model.maps.width}x${symbol_dollar}{model.maps.height}&maptype=${symbol_dollar}{fn:toLowerCase(model.maps.mapType)}&markers=color:${symbol_dollar}{model.maps.markerColor}%7Clabel:${symbol_dollar}{model.maps.markerLabel}%7C${symbol_dollar}{markerLocation}&sensor=${symbol_dollar}{model.maps.sensor}&client=${symbol_dollar}{model.maps.client}&signature=${symbol_dollar}{model.maps.signature}" />
						</c:when>
						<c:otherwise>
							<c:set var="staticMapLink"
								value="http://maps.googleapis.com/maps/api/staticmap?zoom=${symbol_dollar}{model.maps.zoom}&size=${symbol_dollar}{model.maps.width}x${symbol_dollar}{model.maps.height}&maptype=${symbol_dollar}{fn:toLowerCase(model.maps.mapType)}&markers=color:${symbol_dollar}{model.maps.markerColor}%7Clabel:${symbol_dollar}{model.maps.markerLabel}%7C${symbol_dollar}{markerLocation}&sensor=${symbol_dollar}{model.maps.sensor}" />
						</c:otherwise>
					</c:choose>
					<img alt="maps" src="${symbol_dollar}{staticMapLink}">
				</c:if>
			</div>
		</c:if>
		<c:if test="${symbol_dollar}{hstRequest.requestContext.cmsRequest}">
			<c:forEach items="${symbol_dollar}{model.webmasterErrorMessage}" var="error">
				<p class="error-message">${symbol_dollar}{error}</p>
			</c:forEach>
			<c:choose>
				<c:when test="${symbol_dollar}{mode eq 'none'}">
					<p class="error-message"><fmt:message key="maps.address.or.coordinates.required"/></p>
				</c:when>
				<c:when test="${symbol_dollar}{mode eq 'address'}">
					<p class="warn-message"><fmt:message key="maps.coordinates.quicker"/></p>
				</c:when>
				<c:when test="${symbol_dollar}{mode eq 'coordinates'&& not empty model.maps.address}">
					<p class="warn-message"><fmt:message key="maps.coordinates.prevails"/></p>
				</c:when>
			</c:choose>
			<c:forEach items="${symbol_dollar}{model.webmasterWarningMessage}" var="warn">
				<p class="warn-message"><fmt:message key="warn"/></p>
			</c:forEach>
		</c:if>
	</div>
</c:if>
