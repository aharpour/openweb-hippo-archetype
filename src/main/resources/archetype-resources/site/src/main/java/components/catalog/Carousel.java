package ${package}.components.catalog;

import generated.beans.CarouselBanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;

import org.apache.commons.lang3.StringUtils;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.parameters.ParametersInfo;

import ${package}.beans.mixins.CarouselBannerMixin;
import ${package}.componentsinfo.catalog.CarouselInfo;
import ${package}.utils.Constants.Attributes;
import com.tdclighthouse.prototype.components.AjaxEnabledComponent;

@ParametersInfo(type = CarouselInfo.class)
public class Carousel extends AjaxEnabledComponent<Map<String, Object>> {

	@Override
	public Map<String, Object> getModel(HstRequest request, HstResponse response)
			throws HstComponentException {
		try {
			Map<String, Object> model = new HashMap<String, Object>(2);
			CarouselInfo parametersInfo = this.<CarouselInfo> getComponentParametersInfo(request);
			if (parametersInfo.getUseMixin()) {
				populateModelFromMixin(request, model);
			} else {
				model.put("parametersInfo", parametersInfo);
				model.put(Attributes.ITEMS, getItemsFromParameterInfo(request));
			}
			return model;
		} catch (RepositoryException e) {
			throw new HstComponentException(e.getMessage(), e);
		}
	}

	private List<CarouselBanner> getItemsFromParameterInfo(HstRequest request) {
		List<CarouselBanner> result = new ArrayList<CarouselBanner>(6);
		String[] bannerLinks = getBannerLinks(request);
		for (String bannerLink : bannerLinks) {
			if (StringUtils.isNotBlank(bannerLink)) {
				Object object = getSiteContentBaseBean(request).getBean(
						bannerLink);
				if (object instanceof CarouselBanner) {
					result.add((CarouselBanner) object);
				}
			}
		}
		return result;
	}

	private String[] getBannerLinks(HstRequest request) {
		CarouselInfo parametersInfo = getComponentParametersInfo(request);
		String[] bannerLinks = new String[6];
		bannerLinks[0] = parametersInfo.getBanner1();
		bannerLinks[1] = parametersInfo.getBanner2();
		bannerLinks[2] = parametersInfo.getBanner3();
		bannerLinks[3] = parametersInfo.getBanner4();
		bannerLinks[4] = parametersInfo.getBanner5();
		bannerLinks[5] = parametersInfo.getBanner6();
		return bannerLinks;
	}
	
	private void populateModelFromMixin(HstRequest request, Map<String, Object> model) throws RepositoryException {
		HippoBean proxy = getMixinProxy(getContentBean(request));
		if (proxy instanceof CarouselBannerMixin) {
			model.put("parametersInfo",((CarouselBannerMixin) proxy).getCarouselCompoundMixin().getConfigObject());
			model.put(Attributes.ITEMS, ((CarouselBannerMixin) proxy).getCarouselCompoundMixin().getBanners());
		}else{
			model.put(Attributes.WEBMASTER_ERROR_MESSAGE, Attributes.MIXIN_ERROR_MESSAGE);
		}
	}
}
