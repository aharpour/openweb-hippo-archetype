package ${package}.components.catalog;

import javax.jcr.RepositoryException;

import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.parameters.ParametersInfo;

import ${package}.beans.mixins.DiscussMixin;
import ${package}.componentsinfo.catalog.DisqusInfo;
import ${package}.utils.Constants.Attributes;
import com.tdclighthouse.prototype.components.AjaxEnabledComponent;

@ParametersInfo(type = DisqusInfo.class)
public class Disqus extends AjaxEnabledComponent<DisqusInfo>{
	
	@Override
	public DisqusInfo getModel(HstRequest request, HstResponse response) throws HstComponentException {
		DisqusInfo info = getComponentParametersInfo(request);
		try{
			if(info.getUseMixin()){
				HippoBean proxy = getMixinProxy(getContentBean(request));
				if (!(proxy instanceof DiscussMixin)) {
					request.setAttribute(Attributes.WEBMASTER_ERROR_MESSAGE, Attributes.MIXIN_ERROR_MESSAGE);
					info = null;
				}
			}
			return info;
		}catch(RepositoryException e){
			throw new HstComponentException(e.getMessage(), e);
		}
	}
}
