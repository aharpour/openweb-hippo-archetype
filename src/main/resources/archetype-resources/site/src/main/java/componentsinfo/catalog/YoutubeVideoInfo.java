package ${package}.componentsinfo.catalog;

import org.hippoecm.hst.core.parameters.DropDownList;
import org.hippoecm.hst.core.parameters.FieldGroup;
import org.hippoecm.hst.core.parameters.FieldGroupList;
import org.hippoecm.hst.core.parameters.Parameter;

@FieldGroupList({
	@FieldGroup(titleKey = "mixin.group.title", value = { YoutubeVideoInfo.FIELD_USER_MIXIN }),
	@FieldGroup(titleKey = "youtube.url.group", value = { YoutubeVideoInfo.VIDEOURL}),
	@FieldGroup(titleKey = "customization.parameters.group", value = { YoutubeVideoInfo.ALLOW_FULL_SCREEN, YoutubeVideoInfo.AUTOPLAY, 
			YoutubeVideoInfo.DISABLEKB, YoutubeVideoInfo.LOOP, YoutubeVideoInfo.SHOWINFO, YoutubeVideoInfo.REL, YoutubeVideoInfo.THEME}) 
})
public interface YoutubeVideoInfo {
    
	public static String VIDEOURL = "videoURL";
	
	public static String ALLOW_FULL_SCREEN = "allowFullScreen";
	public static String AUTOPLAY = "autoplay";
	public static String DISABLEKB = "disablekb";
	public static String LOOP = "loop";
	public static String SHOWINFO = "showinfo";
	public static String THEME = "theme";
	public static String REL = "rel";
	
	public static String TRUE = "true";
	public static String FALSE = "false";
	
	public static String THEME_DARK = "dark";
	public static String THEME_LIGHT = "light";
    
	public static final String FIELD_USER_MIXIN = "useMixin";

    @Parameter(name = FIELD_USER_MIXIN, defaultValue = "off")
	public boolean getUseMixin();

    @Parameter(name = VIDEOURL, required = true, defaultValue = "")
    public String getVideoURL();
    
    @Parameter(name = THEME, defaultValue = THEME_DARK)
    @DropDownList(value = {THEME_DARK, THEME_LIGHT})
    public String getTheme();
  
    @Parameter(name = ALLOW_FULL_SCREEN, defaultValue = TRUE)
    public boolean getAllowFullScreen();
    
    @Parameter(name = AUTOPLAY, defaultValue = FALSE)
    public boolean getAutoplay();
    
    @Parameter(name = DISABLEKB, defaultValue = TRUE)
    public boolean getDisablekb();
    
    @Parameter(name = LOOP, defaultValue = FALSE)
    public boolean getLoop();
    
    @Parameter(name = SHOWINFO, defaultValue = TRUE)
    public boolean getShowinfo();
    
    @Parameter(name = REL, defaultValue = FALSE)
    public boolean getRel();
}
