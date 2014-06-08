#set( $symbol_dollar = '$' )
package ${package}.components.catalog;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.RepositoryException;

import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.parameters.ParametersInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${package}.beans.mixins.AssetVideoMixin;
import ${package}.componentsinfo.catalog.VideoInfo;
import ${package}.utils.Constants.Attributes;
import com.tdclighthouse.prototype.components.AjaxEnabledComponent;

@ParametersInfo(type = VideoInfo.class)
public class Video extends AjaxEnabledComponent<Map<String, Object>> {

	public static final Logger log = LoggerFactory.getLogger(Video.class);
	
	@Override
	public Map<String, Object> getModel(HstRequest request, HstResponse response) throws HstComponentException {
		try{
			Map<String, Object> model = new HashMap<String, Object>();
			VideoInfo info = getComponentParametersInfo(request);
			if (info.getUseMixin()) {
				populateModelFromMixin(request, model);
			}else{
				addVideoToModel(model, request);
				addVideoCustomizationToModel(model, request);
			}
			return model;
		} catch (RepositoryException e) {
			throw new HstComponentException(e.getMessage(), e);
		}
	}
	
	private void populateModelFromMixin(HstRequest request, Map<String, Object> model) throws RepositoryException {
		HippoBean proxy = getMixinProxy(getContentBean(request));
		if (proxy instanceof AssetVideoMixin) {
			model.put(VideoInfo.VIDEO_PATH, ((AssetVideoMixin) proxy).getAssetVideoCompoundMixin().getVideoPicker());
			model.put("info", ((AssetVideoMixin) proxy).getAssetVideoCompoundMixin().getConfigObject());
		}else{
			model.put(Attributes.WEBMASTER_ERROR_MESSAGE, Attributes.MIXIN_ERROR_MESSAGE);
		}
	}

	private void addVideoToModel(Map<String, Object> model, HstRequest request) {
		VideoInfo info = getComponentParametersInfo(request);
		try{
			if(!info.getVideoPath().isEmpty()){
				model.put(VideoInfo.VIDEO_PATH, getBeanFromAbsolutePath(request, info.getVideoPath()));
			}
		}catch(ObjectBeanManagerException e){
			model.put("error", true);
		}
	}
	
	private void addVideoCustomizationToModel(Map<String, Object> model, HstRequest request) {
		VideoInfo info = getComponentParametersInfo(request);
		model.put("info", info);
	}
	
	@SuppressWarnings("unchecked")
	private <T extends HippoBean> T getBeanFromAbsolutePath(HstRequest request, String absolutePath) 
			throws ObjectBeanManagerException {
		T result = null;
		try {
			result = (T) getObjectBeanManager(request).getObject(absolutePath);
		} catch (ObjectBeanManagerException e) {
			log.warn("No bean found at" + absolutePath);
			throw e;
		}
		return result;
	}	
}
