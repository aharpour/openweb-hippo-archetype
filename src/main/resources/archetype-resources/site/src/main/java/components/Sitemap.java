#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.components;

import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.sitemenu.HstSiteMenu;

import com.tdclighthouse.prototype.components.Detail;
import com.tdclighthouse.prototype.provider.RepoBasedMenuProvider;
import com.tdclighthouse.prototype.utils.Constants;

public class Sitemap extends Detail {

	public static final String SERVICE_MENU = "serviceMenu";

	@Override
	public void doBeforeRender(HstRequest request, HstResponse response) throws HstComponentException {
		super.doBeforeRender(request, response);
		RepoBasedMenuProvider repoBasedMenuProvider = new RepoBasedMenuProvider(getSiteContentBaseBean(request), request);
		
		HstSiteMenu mainMenu = request.getRequestContext().getHstSiteMenus().getSiteMenu("main");
		HstSiteMenu serviceMenu = request.getRequestContext().getHstSiteMenus().getSiteMenu("service");
		
		request.setAttribute(Constants.Attributes.MENU, repoBasedMenuProvider.addRepoBasedMenuItems(mainMenu.getEditableMenu()));
		request.setAttribute(SERVICE_MENU, repoBasedMenuProvider.addRepoBasedMenuItems(serviceMenu.getEditableMenu()));
	}
}
