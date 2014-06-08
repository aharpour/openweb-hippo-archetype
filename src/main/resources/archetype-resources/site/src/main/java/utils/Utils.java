#set( $symbol_dollar = '$' )
package ${package}.utils;

import ${package}.utils.Constants.DisplayedFieldNames;
import ${package}.utils.Constants.DisplayedNodeTypes;
import ${package}.utils.Constants.HippoNodeTypes;
import com.tdclighthouse.prototype.utils.Constants.FieldName;

public class Utils {

	public static String getNamespacedFieldName(String displayName){
		String result = "";
		
		if(DisplayedFieldNames.TDC_RELEASE_DATE.equals(displayName)){
			result = FieldName.TDC_RELEASE_DATE;
		}else if(DisplayedFieldNames.TDC_TITLE.equals(displayName)){
			result = FieldName.TDC_TITLE;
		}
		
		return result;
	}
	
	public static String getNamespacedNodeTypes(String displayName){
		String result = "";
		
		if(DisplayedNodeTypes.ARTICLE_PAGE.equals(displayName)){
			result = HippoNodeTypes.ARTICLE_PAGE;
		}else if(DisplayedNodeTypes.GENERIC_PAGE.equals(displayName)){
			result = HippoNodeTypes.GENERIC_PAGE;
		}else if(DisplayedNodeTypes.TEXT_PAGE.equals(displayName)){
			result = HippoNodeTypes.TEXT_PAGE;
		}else if(DisplayedNodeTypes.HOME_PAGE.equals(displayName)){
			result = HippoNodeTypes.HOME_PAGE;
		}
		
		return result;
	}
}
