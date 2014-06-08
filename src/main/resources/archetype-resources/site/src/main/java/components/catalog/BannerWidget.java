package ${package}.components.catalog;

import generated.beans.Banner;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.RepositoryException;

import org.apache.commons.lang3.StringUtils;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.parameters.ParametersInfo;

import ${package}.beans.mixins.BannerMixin;
import ${package}.componentsinfo.catalog.BannerInfo;
import ${package}.utils.Constants.Attributes;
import com.tdclighthouse.prototype.components.AjaxEnabledComponent;

@ParametersInfo(type = BannerInfo.class)
public class BannerWidget extends AjaxEnabledComponent<Map<String, Object>> {

	private static final String BANNER_ERROR_MESSAGE = "No banner has been selected from the banner mixin. " +
			"The widget will not display";

	@Override
	public Map<String, Object> getModel(HstRequest request, HstResponse response) throws HstComponentException {
		try {
			Map<String, Object> model = new HashMap<String, Object>(2);
			BannerInfo parametersInfo = this.<BannerInfo> getComponentParametersInfo(request);
			if (parametersInfo.getUseMixin()) {
				populateModelFromMixin(request, model);
			} else {
				model.put(Attributes.DOCUMENT, getBannerFromParameterInfo(request));
			}
			return model;
		} catch (RepositoryException e) {
			throw new HstComponentException(e.getMessage(), e);
		}
	}

	private Banner getBannerFromParameterInfo(HstRequest request) {
		Banner result = null;
		BannerInfo parametersInfo = getComponentParametersInfo(request);
		String bannerLink = parametersInfo.getBanner();
		if (StringUtils.isNotBlank(bannerLink)) {
			Object object = getSiteContentBaseBean(request).getBean(bannerLink);
			if (object instanceof Banner) {
				result = (Banner) object;
			}
		}
		return result;
	}

	private void populateModelFromMixin(HstRequest request, Map<String, Object> model) throws RepositoryException {
		HippoBean proxy = getMixinProxy(getContentBean(request));
		if (proxy instanceof BannerMixin) {
			if (((BannerMixin) proxy).getBannerCompoundMixin().getBanner() != null) {
				model.put(Attributes.DOCUMENT, ((BannerMixin) proxy).getBannerCompoundMixin().getBanner());
			} else {
				model.put(Attributes.WEBMASTER_ERROR_MESSAGE, BANNER_ERROR_MESSAGE);
			}
		} else {
			model.put(Attributes.WEBMASTER_ERROR_MESSAGE, Attributes.MIXIN_ERROR_MESSAGE);
		}
	}
}
