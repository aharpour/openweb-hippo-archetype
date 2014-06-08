#set($namespace = ${rootArtifactId.replace($hyphen,$empty)})
package ${package}.components.catalog;

import java.util.Map;

import javax.jcr.RepositoryException;

import net.sourceforge.hstmixinsupport.DynamicProxyFactory;

import org.hippoecm.hst.component.support.forms.FormMap;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.parameters.ParametersInfo;
import org.hippoecm.hst.site.HstServices;
import org.onehippo.forge.easyforms.beans.FormBean;
import org.onehippo.forge.easyforms.behaviors.AfterProcessBehavior;
import org.onehippo.forge.easyforms.behaviors.ConfirmationBehavior;
import org.onehippo.forge.easyforms.behaviors.FormIntroBehavior;
import org.onehippo.forge.easyforms.behaviors.FormSubmissionCounterBehavior;
import org.onehippo.forge.easyforms.behaviors.MailFormDataBehavior;
import org.onehippo.forge.easyforms.behaviors.StoreFormDataBehavior;
import org.onehippo.forge.easyforms.hst.EasyFormComponent;
import org.onehippo.forge.easyforms.model.ErrorMessage;
import org.onehippo.forge.easyforms.model.Form;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${package}.beans.mixins.EasyFormMixin;
import ${package}.componentsinfo.catalog.FormWidgetInfo;
import ${package}.utils.Constants.Attributes;

@ParametersInfo(type = FormWidgetInfo.class)
public class FormWidget extends EasyFormComponent{
	
	private final DynamicProxyFactory dynamicProxyFactory = HstServices
			.getComponentManager().getComponent(DynamicProxyFactory.class);
	
	private static Logger log = LoggerFactory.getLogger(FormWidget.class);
		 
	@Override
	public FormBean getFormBean(final HstRequest request) {
		FormBean resultFormBean = null;
		FormWidgetInfo info = this.<FormWidgetInfo> getComponentParametersInfo(request);
		if(info.getUseFormMixin()){		
			resultFormBean = getFormFromMixin(request);
		}else{
			resultFormBean = getFormFromWidgetParameter(request, info);
		}
		return resultFormBean;
    }
	
	@Override
	public void doBeforeRender(final HstRequest request, final HstResponse response) throws HstComponentException {
		super.doBeforeRender(request, response);
		FormWidgetInfo info = this.<FormWidgetInfo> getComponentParametersInfo(request);
    	request.setAttribute("fileuploadEnabled", info.getFileuploadEnabled());
	}
	
	@Override
    protected void addConfiguredBehaviors(HstRequest request) {
        super.addConfiguredBehaviors(request);
        addSelectedBehaviors(request);
        FormWidgetInfo info = this.<FormWidgetInfo> getComponentParametersInfo(request);
        if(info.getEfDoneRedirect().isEmpty() && !info.getAfterBehavior()){
        	request.setAttribute(Attributes.WEBMASTER_ERROR_MESSAGE, "'Redirect to sitemap after submission' " +
        			"is empty and 'Text after submission behavior' is false. One of the two should be selected." +
        			"The widget will not display");
        }
    }
	
	@Override
    public boolean onValidationSuccess(final HstRequest request, final HstResponse response, final Form form, final FormMap map) {
        return true;
    }

    @Override
    public void onValidationError(final HstRequest request, final HstResponse response, final Form form, final FormMap
            map, final Map<String, ErrorMessage> errors) {
        log.info("Posted form is not valid");
    }
    
    @Override
    public void onProcessDone(final HstRequest request, final HstResponse response, final Form form, final FormMap map) {
    	FormWidgetInfo info = this.<FormWidgetInfo> getComponentParametersInfo(request);
    	if(info.getAfterBehavior()){
    		 request.setAttribute("processDone", "true");	
    	}else{
    		super.onProcessDone(request, response, form, map);
    	}
    }
    
    private FormBean getFormFromWidgetParameter(final HstRequest request, FormWidgetInfo info) {
		String formBeanPath = info.getFormBean();
		FormBean document = (formBeanPath != null && !formBeanPath.isEmpty())
				? (FormBean) getSiteContentBaseBean(request).getBean(formBeanPath) : null;
      
		if (document == null || !(document.isHippoDocumentBean()) || !(document instanceof FormBean)) {
        	request.setAttribute(Attributes.WEBMASTER_ERROR_MESSAGE, "No valid form specified and no mixin selected");  
        }
		return document;
	}
	
	private FormBean getFormFromMixin(final HstRequest request) {
		try {
			FormBean resultFormBean = null;
			HippoBean contentBean = getContentBean(request);
			HippoBean proxy = dynamicProxyFactory.getProxy(contentBean);
			if (proxy instanceof EasyFormMixin) {
				resultFormBean = ((EasyFormMixin) proxy).getFormBean();
			} else {
				request.setAttribute(Attributes.WEBMASTER_ERROR_MESSAGE, "The mixin option has been selected but " +
					"the corresponding content has not been maked by the appropriate mixin. " +
					"As a result this catalog is not going to be rendered.");
			}
			return resultFormBean;
		} catch (RepositoryException e) {
			throw new HstComponentException(e.getMessage(), e);
		}
	}
	
	private void addSelectedBehaviors(HstRequest request){
		FormWidgetInfo info = this.<FormWidgetInfo> getComponentParametersInfo(request);

		if(info.getConfirmBehavior()){
			addBehavior(new ConfirmationBehavior());
		}		
		if(info.getStoreBehaviour()){
			addBehavior(new StoreFormDataBehavior());
		}
		if(info.getMailBehavior()){
			addBehavior(new MailFormDataBehavior());
		}
		if(info.getCounterBehavior()){
			addBehavior(new FormSubmissionCounterBehavior());
		}
		if(info.getAfterBehavior()){
			addBehavior(new AfterProcessBehavior());
		}
		if(info.getIntroBehavior()){
			addBehavior(new FormIntroBehavior());
		}
	}
}
