package ${package}.componentsinfo.catalog;

import org.hippoecm.hst.core.parameters.Parameter;

public interface DisqusInfo {

	public static final String FIELD_USER_MIXIN = "useMixin";

	@Parameter(name = "forumShortname", displayName = "Disqus forum shortname", required = true)
	public String getForumShortname();
	
	@Parameter(name = FIELD_USER_MIXIN, defaultValue = "off")
	public boolean getUseMixin();

}
