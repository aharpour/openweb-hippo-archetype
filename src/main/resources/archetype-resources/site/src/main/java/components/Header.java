#set( $symbol_dollar = '$' )
#set($hyphen = '-')
#set($empty = '')
package ${package}.components;

import org.hippoecm.hst.component.support.bean.BaseHstComponent;
import org.hippoecm.hst.configuration.hosting.Mount;
import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${package}.channels.WebsiteInfo;
import ${package}.utils.Constants.Attributes;
import ${package}.components.Header;

public class Header extends BaseHstComponent {
    
    public static final Logger log = LoggerFactory.getLogger(Header.class);

    @Override
	public void doBeforeRender(final HstRequest request, final HstResponse response) throws HstComponentException {
		final Mount mount = request.getRequestContext().getResolvedMount().getMount();
		final WebsiteInfo info = mount.getChannelInfo();

		if (info != null) {
			request.setAttribute(Attributes.HEADER_NAME, info.getHeaderName());
			request.setAttribute(Attributes.LOGO, getLogoBean(request, info));
		} else {
			log.warn("No channel info available for website '{}'", mount.getMountPath());
		}
	}

	protected Object getLogoBean(HstRequest request, WebsiteInfo info) {
		Object result = null;
		String logoPath = info.getLogoPath();
		try {
			result = getObjectBeanManager(request).getObject(logoPath);
		} catch (ObjectBeanManagerException e) {
			// ignore it, if ImageSet will be null, will use static image 
		}
		return result;
	}
}
