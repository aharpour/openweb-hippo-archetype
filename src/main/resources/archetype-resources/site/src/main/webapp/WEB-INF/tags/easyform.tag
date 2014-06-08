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

<%@ attribute name="form" type="org.onehippo.forge.easyforms.model.Form" rtexprvalue="true" required="true" %>
<%@ attribute name="multipart" type="Boolean" rtexprvalue="false" required="false" %>

<div class="catalog easyforms">
	<c:if test="${symbol_dollar}{not empty form.title}">
	  	<h2><c:out value="${symbol_dollar}{form.title}"/></h2>
	</c:if>
	<c:if test="${symbol_dollar}{not empty formIntro}">
	  	<p><c:out value="${symbol_dollar}{formIntro}"/></p>
	</c:if>
	<c:choose>
	  	<c:when test="${symbol_dollar}{processDone}">
	    	<p>${symbol_dollar}{afterProcessSuccessText}</p>
	  	</c:when>
	  	<c:otherwise>
		  	<c:if test="${symbol_dollar}{not empty ef_errors}">
	      		<div class="message error group">
	        		<img src="<hst:link path="images/catalog/alert-sign.png" />" alt="Warning!"/>
	        		<c:forEach items="${symbol_dollar}{ef_errors}" var="error">
	          			<span class="fieldError">${symbol_dollar}{error.value.localizedMessage}</span>
	        		</c:forEach>
	      		</div>
	    	</c:if>
	    	<c:if test="${symbol_dollar}{maxFormSubmissionsReached}">
		      	<c:choose>
		        	<c:when test="${symbol_dollar}{not empty maxFormSubmissionsReachedText}">
		          		<p><c:out value="${symbol_dollar}{maxFormSubmissionsReachedText}"/></p>
		        	</c:when>
		        	<c:otherwise>
		          		<p>The maximum number of submission for this form has been reached</p>
		        	</c:otherwise>
		      	</c:choose>
	    	</c:if>
	   		<c:if test="${symbol_dollar}{!maxFormSubmissionsReached}">
	   			<c:choose>
					<c:when test="${symbol_dollar}{not empty multipart and multipart eq true}">
						<form class="form" action="<hst:actionURL />" method="post" id="${symbol_dollar}{form.id}" name="${symbol_dollar}{form.name}" enctype="multipart/form-data">
					</c:when>
					<c:otherwise>
      					<form class="form" action="<hst:actionURL />" method="post" id="${symbol_dollar}{form.id}" name="${symbol_dollar}{form.name}">
        			</c:otherwise>
				</c:choose>
	        	<c:forEach var="field" items="${symbol_dollar}{form.fields}">
	          		<c:choose>
	            		<c:when test="${symbol_dollar}{field.simpleText}">
	              			<div class="ef-text">
	                			<h2>${symbol_dollar}{field.label}</h2>
								<p>${symbol_dollar}{field.hint}</p>
	              			</div>
            			</c:when>
			            <%-- simple types layout--%>
			            <c:when test="${symbol_dollar}{field.textField or field.password or field.textArea or field.dropdown or field.radioBox or field.checkBox}">
			              	<fieldset class="ef-field">
			                	<label>${symbol_dollar}{field.label}<span class="ef-req">${symbol_dollar}{field.requiredMarker}</span></label>
			                	<span class="ef-hint">${symbol_dollar}{field.hint}</span>
			                	<tag:formfieldvalidation field = "${symbol_dollar}{field }" ef_errors="${symbol_dollar}{ef_errors}"/> 
			              	</fieldset>
			            </c:when>
			            <c:when test="${symbol_dollar}{field.radioGroup}">
			              	<fieldset class="ef-field">
			                	<label>${symbol_dollar}{field.label}<span class="ef-req">${symbol_dollar}{field.requiredMarker}</span></label>
			                	<c:forEach var="radio" items="${symbol_dollar}{field.fields}">
			                  		<p>${symbol_dollar}{radio.html} <span>${symbol_dollar}{radio.label}</span></p>
			                	</c:forEach>
			                	<c:if test="${symbol_dollar}{field.allowOther}">
			                  		${symbol_dollar}{field.otherChoice} Other: <span>${symbol_dollar}{field.other}</span>
			                	</c:if>
			                	<span class="ef-hint">${symbol_dollar}{field.hint}</span>
			                	<tag:formfieldvalidation field = "${symbol_dollar}{field }" ef_errors="${symbol_dollar}{ef_errors}"/> 
			              	</fieldset>
			            </c:when>
			            <c:when test="${symbol_dollar}{field.dateField}">
			              	<fieldset class="ef-field">
			                	<label>${symbol_dollar}{field.label}<span class="ef-req">${symbol_dollar}{field.requiredMarker}</span></label>
			                	<span class="ef-hint">${symbol_dollar}{field.hint}</span>
			                	<tag:formfieldvalidation field = "${symbol_dollar}{field }" ef_errors="${symbol_dollar}{ef_errors}"/> 
			              	</fieldset>
				            <script>
				            	${symbol_dollar}(document).ready(function () {
				            		${symbol_dollar}(function () {
				            			${symbol_dollar}('input[name="${field.name}"]').datepicker({
				                      		showOn: "button",
				                      		buttonImage: "<hst:link path="/images/calendar.gif"/>",
				                      		buttonImageOnly: true,
				                      		dateFormat: '${field.dateFormat}',
				                      		autoSize: true
				                    	});
				                  	});
				                });
				            </script>
			            </c:when>
		            	<c:when test="${symbol_dollar}{field.checkBoxGroup}">
		              		<fieldset class="ef-field">
		                		<label>${symbol_dollar}{field.label}<span class="ef-req">${symbol_dollar}{field.requiredMarker}</span></label>
		                		<c:forEach var="box" items="${symbol_dollar}{field.fields}">
		                  			<p>${symbol_dollar}{box.html} ${symbol_dollar}{box.label}</p>
		                		</c:forEach>
		                		<c:if test="${symbol_dollar}{field.allowOther}">
		                  			${symbol_dollar}{field.otherChoice} Other: <span>${symbol_dollar}{field.other}</span>
		                		</c:if>
		                		<span class="ef-hint">${symbol_dollar}{field.hint}</span>
		              		</fieldset>
		            	</c:when>
	            		<%--  LIKERT--%>
			            <c:when test="${symbol_dollar}{field.likert}">
			              	<fieldset class="ef-field">
				                <label>${symbol_dollar}{field.label}<span class="ef-req">${symbol_dollar}{field.requiredMarker}</span></label>
				                <table class="ef-likert-table">
				                  	<tr>
					                    <td>&nbsp;</td>
					                    <c:forEach var="option" items="${symbol_dollar}{field.options}">
					                      	<td>${symbol_dollar}{option}</td>
					                    </c:forEach>
				                  	</tr>
				                  	<c:forEach var="map" items="${symbol_dollar}{field.htmlMap}">
					                    <tr>
					                      	<td>${symbol_dollar}{map.key.label}</td>
					                      	<c:forEach var="radio" items="${symbol_dollar}{map.value}">
					                        	<td>${symbol_dollar}{radio.html}</td>
					                      	</c:forEach>
					                    </tr>
				                  	</c:forEach>
				                </table>
			              	</fieldset>
			            </c:when>
			            <c:when test="${symbol_dollar}{field.fileupload }">
			            	<fieldset class="uploadField">
				            	<label>${symbol_dollar}{field.label}<span class="ef-req">${symbol_dollar}{field.requiredMarker}</span></label>
				            	<span class="ef-hint">${symbol_dollar}{field.hint}</span>
			            		<tag:formfieldvalidation field = "${symbol_dollar}{field }" ef_errors="${symbol_dollar}{ef_errors}"/>
		            		</fieldset> 
		            	</c:when>
         				</c:choose>
       			</c:forEach>
	        	<div class="ef-buttons">
	          		<c:forEach var="button" items="${symbol_dollar}{form.buttons}">
	            		${symbol_dollar}{button.html}
	          		</c:forEach>
	        	</div>
      			</form>
   			</c:if>
		</c:otherwise>
	</c:choose>
			
	<%--
		//########################################################################
	    //  HEADER CONTRIBUTIONS
	    //########################################################################
	--%>
	   
	<hst:headContribution keyHint="formCss">
	  <link rel="stylesheet" href="<hst:link path="/css/catalog/easyforms.css"/>" type="text/css"/>
	</hst:headContribution>
</div>

<%--    
<hst:headContribution keyHint="formValidationCss">
  <link rel="stylesheet" href="<hst:link path="/css/catalog/easyforms/formcheck.css"/>" type="text/css"/>
</hst:headContribution>
<hst:headContribution keyHint="jqueryUICss">
  <link rel="stylesheet" href="<hst:link path="/css/catalog/easyforms/jquery-ui-1.7.3.custom.css"/>" type="text/css"/>
</hst:headContribution>
<hst:headContribution keyHint="jquery">
  <script type="text/javascript" src="<hst:link path="/js/jquery-1.3.2.js"/>"></script>
</hst:headContribution>
<hst:headContribution keyHint="jquery-datepicker">
  <script type="text/javascript" src="<hst:link path="/js/jquery-ui-1.7.3.custom.min.js"/>"></script>
</hst:headContribution>
<hst:headContribution keyHint="formJsValidation">
  <script type="text/javascript" src="<hst:link path="/js/jquery.validate.min.js"/>"></script>
</hst:headContribution>
--%>
