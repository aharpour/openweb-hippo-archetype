#set( $symbol_dollar = '$' )
#set($hyphen = '-')
#set($empty = '')
package ${package}.components;

import java.util.ArrayList;
import java.util.List;

import ${package}.channels.WebsiteInfo;
import ${package}.utils.Constants.Attributes;
import org.hippoecm.hst.configuration.hosting.Mount;
import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.content.beans.standard.HippoAvailableTranslationsBean;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.linking.HstLink;
import org.hippoecm.hst.core.parameters.ParametersInfo;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tdclighthouse.prototype.componentsinfo.LabelsInfo;
import com.tdclighthouse.prototype.components.BaseTdcComponent;

@ParametersInfo(type = LabelsInfo.class)
public class LanguageSwitch extends BaseTdcComponent {

	private static final String CONTENT_BEAN_AVAILABLE_TRANSLATIONS = "contentBeanAvailableTranslations";

	static final Logger log = LoggerFactory.getLogger(LanguageSwitch.class);

	@Override
	public void doBeforeRender(HstRequest request, HstResponse response) {
		try {
			List<Translation> result = new ArrayList<Translation>();
			HippoBean baseBean = this.getSiteContentBaseBean(request);
			HippoAvailableTranslationsBean<HippoBean> translations = baseBean.getAvailableTranslations();

			for (String baseLocale : translations.getAvailableLocales()) {
				result.add(createTranslation(request, baseLocale));
			}

			request.setAttribute(Attributes.TRANSLATIONS, result);
			request.setAttribute(Attributes.LABELS, getLabels(request));
			request.setAttribute(Attributes.CURRENT_LANGUAGE, request.getLocale().getLanguage());
		} catch (ObjectBeanManagerException e) {
			throw new HstComponentException(e);
		}
	}

	private Translation createTranslation(final HstRequest request, final String locale)
			throws ObjectBeanManagerException {
		boolean selected = getSelected(request, locale);
		boolean available = true;
		HippoBean translationBean = getContentBeanTranslation(request).getTranslation(locale);
		if (translationBean == null) {
			available = false;
			String path = getDefaultPath(request, locale);
			translationBean = (HippoBean) getObjectBeanManager(request).getObject(path);
		}
		return new Translation(locale, createLink(request, translationBean), available, selected);
	}

	@SuppressWarnings("unchecked")
	private HippoAvailableTranslationsBean<HippoBean> getContentBeanTranslation(final HstRequest request) {
		HippoAvailableTranslationsBean<HippoBean> result;
		Object obj = request.getAttribute(CONTENT_BEAN_AVAILABLE_TRANSLATIONS);
		if (obj instanceof HippoAvailableTranslationsBean) {
			result = (HippoAvailableTranslationsBean<HippoBean>) obj;
		} else {
			result = getContentBean(request).getAvailableTranslations();
			request.setAttribute(CONTENT_BEAN_AVAILABLE_TRANSLATIONS, result);
		}
		return result;
	}

	private boolean getSelected(final HstRequest request, final String locale) {
		final String requestLocale = request.getLocale().getLanguage();
		boolean selected = locale.equals(requestLocale);
		return selected;
	}

	private HstLink createLink(final HstRequest request, HippoBean translationBean) {
		HstLink link = null;
		HstRequestContext requestContext = request.getRequestContext();
		if (translationBean != null) {
			link = requestContext.getHstLinkCreator().create(translationBean.getNode(), requestContext);
		}
		return link;
	}

	private String getDefaultPath(final HstRequest request, final String locale) {
		Mount mount = getMount(request, locale);
		WebsiteInfo info = mount.getChannelInfo();
		String path = info.getLanguageDefaultPage();
		return path;
	}

	private Mount getMount(final HstRequest request, final String locale) {
		return request.getRequestContext().getResolvedMount().getMount();
	}

	public static class Translation {

		private final String language;
		private final boolean selected;
		private final boolean available;
		private final HstLink link;

		public Translation(String language, final HstLink link, boolean available, boolean selected) {
			this.language = language;
			this.available = available;
			this.link = link;
			this.selected = selected;
		}

		public String getLanguage() {
			return language;
		}

		public boolean isAvailable() {
			return available;
		}

		public HstLink getLink() {
			return link;
		}

		public boolean isSelected() {
			return selected;
		}
	}
}
