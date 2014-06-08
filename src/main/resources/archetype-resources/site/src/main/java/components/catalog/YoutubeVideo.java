package ${package}.components.catalog;

import generated.beans.YoutubePlayerParameters;
import generated.beans.YoutubeUrlParameters;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jcr.RepositoryException;

import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.parameters.ParametersInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${package}.beans.mixins.YoutubeMixin;
import ${package}.componentsinfo.catalog.YoutubeVideoInfo;
import ${package}.utils.Constants.Attributes;
import com.tdclighthouse.prototype.components.AjaxEnabledComponent;
import com.tdclighthouse.prototype.utils.URL;

@ParametersInfo(type = YoutubeVideoInfo.class)
public class YoutubeVideo extends AjaxEnabledComponent<Map<String, Object>> {

    public static final Logger log = LoggerFactory.getLogger(YoutubeVideo.class);
    
    private static final String YOUTUBE_REGEX = 
			"https?://www.youtube.com/.*(\\?|&)(video_id=|/|v=)([^(&\\\r\\\n\\?)]*).*";
	private static final String YOUTUBE_START = "http://www.youtube.com/v/";

	@Override
	public Map<String, Object> getModel(HstRequest request, HstResponse response) throws HstComponentException {
		try{
			Map<String, Object> model = new HashMap<String, Object>();
			YoutubeVideoInfo info = getComponentParametersInfo(request);
			if(info.getUseMixin()){
				populateModelFromMixin(request, model);
			}else{			
				addVideoToModel(model, request);
				model.put("allowFullScreen", info.getAllowFullScreen());
			}
			return model;
		} catch (RepositoryException e) {
			throw new HstComponentException(e.getMessage(), e);
		}
	}
	
	private void populateModelFromMixin(HstRequest request, Map<String, Object> model) throws RepositoryException {
		HippoBean proxy = getMixinProxy(getContentBean(request));
		if (proxy instanceof YoutubeMixin) {
			addVideoFromMixin(model, request, proxy);
			YoutubePlayerParameters playerParams = ((YoutubeMixin) proxy).getYoutubeCompoundMixin()
					.getYoutubePlayerParameters();
			model.put("allowFullScreen", playerParams.getAllowFullScreen());
		}else{
			model.put(Attributes.WEBMASTER_ERROR_MESSAGE, Attributes.MIXIN_ERROR_MESSAGE);
		}
	}

	private void addVideoToModel(Map<String, Object> model, HstRequest request) {
		YoutubeVideoInfo info = getComponentParametersInfo(request);
		if (info.getVideoURL().matches(YOUTUBE_REGEX)) {
			String videoURL = addVideoParameters(request);
			model.put("videoURL", videoURL);
		} 
	}
	
	private void addVideoFromMixin(Map<String, Object> model, HstRequest request, HippoBean proxy) {
		YoutubeUrlParameters urlParams = ((YoutubeMixin) proxy).getYoutubeCompoundMixin().getYoutubeUrlParameters();
		if (urlParams.getYoutubeUrl().matches(YOUTUBE_REGEX)) {
			String videoURL = addVideoParametersFromMixin(request, proxy);
			model.put("videoURL", videoURL);
		} 
	}

	private String getVideoId(String videoURL) {
		String result = "";
		Pattern pat = Pattern.compile(YOUTUBE_REGEX);
		Matcher mSumm = pat.matcher(videoURL);
		if (mSumm.find()) {
			result = mSumm.group(3);
		}
		return result;
	}

	private String addVideoParameters(HstRequest request) {
		String result = "";
		YoutubeVideoInfo info = getComponentParametersInfo(request);
		String videoId = getVideoId(info.getVideoURL());
	
		if(!videoId.isEmpty()){			
			String videoURL = YOUTUBE_START + videoId;
			URL url = new URL(videoURL);
			url.setQueryParameter(YoutubeVideoInfo.AUTOPLAY, booelanToString(info.getAutoplay()));
			url.setQueryParameter(YoutubeVideoInfo.DISABLEKB, booelanToString(info.getDisablekb()));
			url.setQueryParameter(YoutubeVideoInfo.SHOWINFO, booelanToString(info.getShowinfo()));
			url.setQueryParameter(YoutubeVideoInfo.REL, booelanToString(info.getRel()));			
			
			if (info.getLoop()) {
				url.setQueryParameter(YoutubeVideoInfo.LOOP, "1");
				url.setQueryParameter("playlist", videoId);
			}
			
			url.setQueryParameter(YoutubeVideoInfo.THEME, info.getTheme());
			result = url.toString();
		}
		return result;
	}

	private String addVideoParametersFromMixin(HstRequest request, HippoBean proxy) {
		String result = "";
		YoutubeUrlParameters urlParams = ((YoutubeMixin) proxy).getYoutubeCompoundMixin().getYoutubeUrlParameters();
		YoutubePlayerParameters playerParams = ((YoutubeMixin) proxy).getYoutubeCompoundMixin().getYoutubePlayerParameters();
		String videoId = getVideoId(urlParams.getYoutubeUrl());
		
		if(!videoId.isEmpty()){			
			String videoURL = YOUTUBE_START + videoId;
			URL url = new URL(videoURL);
			url.setQueryParameter(YoutubeVideoInfo.AUTOPLAY, booelanToString(playerParams.getAutoplay()));
			url.setQueryParameter(YoutubeVideoInfo.DISABLEKB, booelanToString(playerParams.getDisablekb()));
			url.setQueryParameter(YoutubeVideoInfo.SHOWINFO, booelanToString(playerParams.getShowinfo()));
			url.setQueryParameter(YoutubeVideoInfo.REL, booelanToString(playerParams.getRel()));					
			if (playerParams.getLoop()) {
				url.setQueryParameter(YoutubeVideoInfo.LOOP, "1");
				url.setQueryParameter("playlist", videoId);
			}		
			url.setQueryParameter(YoutubeVideoInfo.THEME, urlParams.getTheme().getFirstItem().getKey());
			result = url.toString();
		}
		return result;
	}

	private String booelanToString(boolean value) {
		return value ? "1" : "0";
	}
}
