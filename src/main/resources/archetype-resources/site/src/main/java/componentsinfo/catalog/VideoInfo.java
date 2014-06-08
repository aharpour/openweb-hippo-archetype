package ${package}.componentsinfo.catalog;

import org.hippoecm.hst.core.parameters.FieldGroup;
import org.hippoecm.hst.core.parameters.FieldGroupList;
import org.hippoecm.hst.core.parameters.JcrPath;
import org.hippoecm.hst.core.parameters.Parameter;

@FieldGroupList({
	@FieldGroup(titleKey = "mixin.group.title", value = { LatestItemsInfo.FIELD_USER_MIXIN }),
	@FieldGroup(titleKey = "video.url.group", value = { VideoInfo.VIDEO_PATH}),
	@FieldGroup(titleKey = "video.customization.group", value = { VideoInfo.ALLOW_FULL_SCREEN, VideoInfo.WIDTH, VideoInfo.HEIGHT}) 
})
public interface VideoInfo {
	
	public static final String FIELD_USER_MIXIN = "useMixin";
	public static final String VIDEOURL = "videoURL";
	public static final String VIDEO_PATH = "videoPath";
	
	public static final String ALLOW_FULL_SCREEN = "allowFullScreen";
	
	public static final String WIDTH = "width";
	public static final String HEIGHT = "height";
	
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	
	public static final String DEFAULT_WIDTH = "219";
	public static final String DEFAULT_HEIGHT = "185";
	
	public static String INITIAL_PATH = "/content/assets/video";
	public static final String ASSET_NODE_SELECTABLE = "hippogallery:exampleAssetSet";
    
	@Parameter(name = FIELD_USER_MIXIN, defaultValue = "off")
	public boolean getUseMixin();
	
    @Parameter(name = ALLOW_FULL_SCREEN, defaultValue = TRUE)
    public boolean getAllowFullScreen();
    
    @Parameter(name = WIDTH, defaultValue = DEFAULT_WIDTH)
    public int getWidth();
    
    @Parameter(name = HEIGHT, defaultValue = DEFAULT_HEIGHT)
    public int getHeight();
    
    @Parameter(name = VIDEO_PATH, defaultValue = "")
    @JcrPath(isRelative = false, pickerInitialPath = INITIAL_PATH, pickerSelectableNodeTypes = {ASSET_NODE_SELECTABLE})
	public String getVideoPath();
    
    
}
