package ${package}.components.catalog;

import generated.beans.LatestItemsOverviewParameters;
import generated.beans.LatestItemsParemeters;
import generated.beans.LatestItemsSortSizeParameters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;

import org.apache.commons.lang3.StringUtils;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.parameters.ParametersInfo;

import ${package}.beans.mixins.LatestItemsMixin;
import ${package}.componentsinfo.catalog.LatestItemsInfo;
import ${package}.utils.Constants.Attributes;
import ${package}.utils.Utils;
import com.tdclighthouse.prototype.components.AjaxEnabledComponent;
import com.tdclighthouse.prototype.utils.Constants;
import com.tdclighthouse.prototype.utils.PaginatorWidget;

@ParametersInfo(type = LatestItemsInfo.class)
public class LatestItems extends AjaxEnabledComponent<Map<String, Object>> {

	private static final String OVERVIEW_LINK = "overviewLink";

	public Map<String, Object> getModel(HstRequest request, HstResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		LatestItemsInfo parametersInfo = this.<LatestItemsInfo> getComponentParametersInfo(request);
		if (parametersInfo.getUseMixin()) {
			populateModelFromMixin(request, model);
		}else{
			populateModelFromParametrs(request, model);
		}
		return model;
	}

	private void populateModelFromParametrs(HstRequest request, Map<String, Object> model) {
		try{
			addGenericInfoToModel(request, model);
			addItemsAndPaginatorToModel(request, model);
			addOverviewLinkToModel(request, model);
		} catch (RepositoryException e) {
			throw new HstComponentException(e.getMessage(), e);
		}
	}
	
	private void populateModelFromMixin(HstRequest request, Map<String, Object> model) {
		try{
			HippoBean proxy = getMixinProxy(getContentBean(request));
			if (proxy instanceof LatestItemsMixin) {
				model.put("info", ((LatestItemsMixin) proxy).getLatestItemsCompoundMixin().getConfigObject());
				addItemsAndPaginatorFromMixin(request, model, proxy);
				addOverviewLinkFromMixin(request, model, proxy);
			}else{
				model.put(Attributes.WEBMASTER_ERROR_MESSAGE, Attributes.MIXIN_ERROR_MESSAGE);
			}
		} catch (RepositoryException e) {
			throw new HstComponentException(e.getMessage(), e);
		}
	}
	
	private void addGenericInfoToModel(HstRequest request, Map<String, Object> model) throws RepositoryException {
		LatestItemsInfo parametersInfo = this.<LatestItemsInfo> getComponentParametersInfo(request);
		model.put("info", parametersInfo);
	}

	private void addItemsAndPaginatorToModel(HstRequest request, Map<String, Object> model) {
		try {
			HstQuery query = getQuery(request);
			HstQueryResult queryResult = query.execute();
			List<HippoBean> items = getItems(queryResult);
			model.put(Attributes.ITEMS, items);

			PaginatorWidget paginator = new PaginatorWidget(queryResult.getTotalSize(), getPageNumber(request),
					getPageSize(request));

			model.put(Attributes.PAGINATOR, paginator);
		} catch (QueryException e) {
			throw new HstComponentException(e.getMessage(), e);
		}
	}
	
	protected HstQuery getQuery(HstRequest request) throws QueryException {
		LatestItemsInfo parametersInfo = getComponentParametersInfo(request);
		HippoBean scope = getBean(parametersInfo.getContentBeanPath(), request);
		HstQuery query = getQueryManager(request).createQuery(scope,
				Utils.getNamespacedNodeTypes(parametersInfo.getShowType()));	
		addSorting(request, query);
		query.setLimit(getPageSize(request));
		query.setOffset((getPageNumber(request) - 1) * getPageSize(request));
		addFilter(query);
		return query;
	}
	
	@Override
	public int getPageSize(HstRequest request) {
		LatestItemsInfo parametersInfo = getComponentParametersInfo(request);
		int result = parametersInfo.getSize();
		result = retrieveComponentSpecificParameter(request, result);
		return result;
	}

	private int retrieveComponentSpecificParameter(HstRequest request, int result) {
		String pageSzieString = getComponentSpecificParameter(request, Constants.Parameters.PAGE_SIZE);
		if (StringUtils.isNotBlank(pageSzieString) && StringUtils.isNumeric(pageSzieString)) {
			result = Integer.parseInt(pageSzieString);
		}
		return result;
	}
	
	private void addOverviewLinkToModel(HstRequest request, Map<String, Object> model) {
		LatestItemsInfo parametersInfo = getComponentParametersInfo(request);
		HippoBean overviewLink = getOverviewPageBean(request);
		if (parametersInfo.getShowOverviewLink() && overviewLink != null) {
			model.put(OVERVIEW_LINK, overviewLink);
		}
	}

	private HippoBean getOverviewPageBean(HstRequest request) {
		HippoBean overviewLink = null;
		LatestItemsInfo parametersInfo = getComponentParametersInfo(request);
		if (parametersInfo.getOverviewBeanPath() != null && !parametersInfo.getOverviewBeanPath().isEmpty()) {
			overviewLink = getBean(parametersInfo.getOverviewBeanPath(), request);
		}
		return overviewLink;
	}
	
	private List<HippoBean> getItems(HstQueryResult queryResult) {
		List<HippoBean> items = new ArrayList<HippoBean>();
		for (HippoBeanIterator hippoBeans = queryResult.getHippoBeans(); hippoBeans.hasNext();) {
			items.add(hippoBeans.nextHippoBean());
		}
		return items;
	}

	private void addSorting(HstRequest request, HstQuery query) {
		LatestItemsInfo parametersInfo = getComponentParametersInfo(request);
		String sortBy = Utils.getNamespacedFieldName(parametersInfo.getSortBy());
		if (StringUtils.isNotBlank(sortBy)) {
			if (Constants.Values.DESCENDING.equals(parametersInfo.getSortOrder())) {
				query.addOrderByDescending(sortBy);
			} else {
				query.addOrderByAscending(sortBy);
			}
		}
	}
	
	@Override
	public int getPageNumber(HstRequest request) {
		int result = 1;
		String pageString = getComponentSpecificParameter(request, Constants.Parameters.PAGE);
		if (StringUtils.isNotBlank(pageString) && StringUtils.isNumeric(pageString)) {
			result = Integer.parseInt(pageString);
		}
		return result;
	}
	
	// TODO: override if want to filter the selected catalogue items on some field value
	protected void addFilter(HstQuery query) {
	}
	
	private void addItemsAndPaginatorFromMixin(HstRequest request, Map<String, Object> model, HippoBean proxy) throws RepositoryException {
		try {
			HstQuery query = getQueryFromMixin(request, model, proxy);
			HstQueryResult queryResult = query.execute();
			List<HippoBean> items = getItems(queryResult);
			model.put(Attributes.ITEMS, items);
			
			PaginatorWidget paginator = new PaginatorWidget(queryResult.getTotalSize(), getPageNumber(request),
					getPageSizeFromMixin(request, proxy));
			
			model.put(Attributes.PAGINATOR, paginator);		
		} catch (QueryException e) {
			throw new HstComponentException(e.getMessage(), e);
		}
	}
	
	private HstQuery getQueryFromMixin(HstRequest request, Map<String, Object> model, HippoBean proxy) throws QueryException, RepositoryException {
		LatestItemsParemeters params = ((LatestItemsMixin) proxy).getLatestItemsCompoundMixin()
				.getLatestItemsParemeters();
		HippoBean scope = params.getContentBeanPath();
		HstQuery query = getQueryManager(request).createQuery(scope, params.getShowType().getFirstItem().getKey());
		addSortingFromMixin(request, query, proxy);
		query.setLimit(getPageSizeFromMixin(request, proxy));
		query.setOffset((getPageNumber(request) - 1) * getPageSizeFromMixin(request, proxy));
		
		addFilter(query);
		return query;
	}
	
	private void addSortingFromMixin(HstRequest request, HstQuery query, HippoBean proxy) {
		LatestItemsSortSizeParameters params = ((LatestItemsMixin) proxy).getLatestItemsCompoundMixin()
				.getLatestItemsSortSizeParameters();
		String sortBy = params.getSortBy().getFirstItem().getKey();
		if (StringUtils.isNotBlank(sortBy)) {
			if (Constants.Values.DESCENDING.equals(params.getSortOrder())) {
				query.addOrderByDescending(sortBy);
			} else {
				query.addOrderByAscending(sortBy);
			}
		}
	}
	
	private int getPageSizeFromMixin(HstRequest request, HippoBean proxy) {
		LatestItemsSortSizeParameters params = ((LatestItemsMixin) proxy).getLatestItemsCompoundMixin()
				.getLatestItemsSortSizeParameters();
		int result = params.getSize().intValue();
		result = retrieveComponentSpecificParameter(request, result);
		return result;
	}
	
	private void addOverviewLinkFromMixin(HstRequest request, Map<String, Object> model, HippoBean proxy) {
		HippoBean overviewLink = getOverviewPageBeanFromMixin(request, proxy);
		LatestItemsOverviewParameters params = ((LatestItemsMixin) proxy).getLatestItemsCompoundMixin()
				.getLatestItemsOverviewParameters();
		if (params.getShowOverviewLink() && overviewLink != null) {
			model.put(OVERVIEW_LINK, overviewLink);
		}
	}
	
	private HippoBean getOverviewPageBeanFromMixin(HstRequest request, HippoBean proxy) {
		HippoBean overviewLink = null;
		LatestItemsOverviewParameters params = ((LatestItemsMixin) proxy).getLatestItemsCompoundMixin()
				.getLatestItemsOverviewParameters();
		if (params.getOverviewBeanPath() != null) {
			overviewLink = params.getOverviewBeanPath();
		}
		return overviewLink;
	}
}
