package ${package}.componentsinfo.catalog;

import generated.beans.Banner;

import org.hippoecm.hst.core.parameters.DocumentLink;
import org.hippoecm.hst.core.parameters.FieldGroup;
import org.hippoecm.hst.core.parameters.FieldGroupList;
import org.hippoecm.hst.core.parameters.Parameter;

@FieldGroupList({
	@FieldGroup(titleKey = "mixin.group.title", value = { BannerInfo.FIELD_USER_MIXIN }),
	@FieldGroup(titleKey = "pickers.group.title", value = { BannerInfo.FIELD_BANNER }) 
})
public interface BannerInfo {

	public static final String FIELD_BANNER = "banner";
	public static final String FIELD_USER_MIXIN = "useMixin";

	@Parameter(name = FIELD_BANNER)
	@DocumentLink(docType = Banner.JCR_TYPE, allowCreation = true, docLocation = "components/banners")
	public String getBanner();

	@Parameter(name = FIELD_USER_MIXIN, defaultValue = "off")
	public boolean getUseMixin();

}
