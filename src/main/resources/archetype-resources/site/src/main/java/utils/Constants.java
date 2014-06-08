#set( $symbol_dollar = '$' )
package ${package}.utils;

public class Constants extends com.tdclighthouse.prototype.utils.Constants {

	public static class Attributes extends com.tdclighthouse.prototype.utils.Constants.Attributes {
		
		public static final String LOGO = "logo";
		public static final String HEADER_NAME = "headerName";
		public static final String TRANSLATIONS = "translations";
		public static final String CURRENT_LANGUAGE = "currentLanguage";
		public static final String MIXIN_ERROR_MESSAGE = "The mixin option has been selected," +
				" but the corresponding content has not been maked by the appropriate mixin. " +
				"As a result this catalog is not going to be rendered.";
	}
	
	public static class DisplayedFieldNames {
		
		public static final String TDC_RELEASE_DATE = "Release Date";
		public static final String TDC_TITLE = "Title";

	} 

	public static class HippoNodeTypes extends com.tdclighthouse.prototype.utils.Constants.HippoNodeTypes {
		
		public static final String ARTICLE_PAGE = "${rootArtifactId.replace($hyphen,$empty)}:ArticlePage";
		public static final String GENERIC_PAGE = "${rootArtifactId.replace($hyphen,$empty)}:GenericPage";
		public static final String TEXT_PAGE = "${rootArtifactId.replace($hyphen,$empty)}:TextPage";
		public static final String HOME_PAGE = "${rootArtifactId.replace($hyphen,$empty)}:HomePage";
	}
	
	public static class DisplayedNodeTypes {
		
		public static final String ARTICLE_PAGE = "Article Page";
		public static final String GENERIC_PAGE = "Generic Page";
		public static final String TEXT_PAGE = "Text Page";
		public static final String HOME_PAGE = "Text Page";
		
	}
}
