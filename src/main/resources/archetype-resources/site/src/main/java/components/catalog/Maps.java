package ${package}.components.catalog;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.RepositoryException;

import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.parameters.ParametersInfo;

import ${package}.beans.MapsBean;
import ${package}.beans.mixins.GoogleMapMixin;
import ${package}.componentsinfo.catalog.MapsInfo;
import ${package}.utils.Constants.Attributes;
import com.tdclighthouse.prototype.components.AjaxEnabledComponent;

@ParametersInfo(type = MapsInfo.class)
public class Maps extends AjaxEnabledComponent<Map<String, Object>> {

	@Override
	public Map<String, Object> getModel(HstRequest request, HstResponse response) throws HstComponentException {
		try {
			Map<String, Object> model = new HashMap<String, Object>();
			MapsBean mapsbean = getMapsBean(request, model);
			model.put("maps", mapsbean);
			return model;
		} catch (RepositoryException e) {
			throw new HstComponentException(e.getMessage(), e);
		}
	}

	private MapsBean getMapsBean(HstRequest request, Map<String, Object> model) throws RepositoryException {
		MapsBean mapsbean = null;
		MapsInfo mapsInfo = getComponentParametersInfo(request);
		if (mapsInfo.getUseMixin()) {
			mapsbean = getMapsBeanFromMixin(request, model, mapsbean);
		} else {
			mapsbean = new MapsBean(mapsInfo);
		}
		return mapsbean;
	}

	private MapsBean getMapsBeanFromMixin(HstRequest request, Map<String, Object> model, MapsBean mapsbean)
			throws RepositoryException {
		HippoBean proxy = getMixinProxy(getContentBean(request));
		if (proxy instanceof GoogleMapMixin) {
			mapsbean = new MapsBean(((GoogleMapMixin) proxy).getMapCompoundMixin().getConfigObject());
		}else{
			model.put(Attributes.WEBMASTER_ERROR_MESSAGE, Attributes.MIXIN_ERROR_MESSAGE);
		}
		return mapsbean;
	}
}
