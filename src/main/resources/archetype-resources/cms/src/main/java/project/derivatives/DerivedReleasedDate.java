#set( $symbol_dollar = '$' )
package project.${package}.derivatives;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.jcr.PropertyType;
import javax.jcr.RepositoryException;
import javax.jcr.Value;

import org.apache.commons.lang.LocaleUtils;
import org.hippoecm.repository.ext.DerivedDataFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DerivedReleasedDate extends DerivedDataFunction {

	public static Logger log = LoggerFactory.getLogger(DerivedReleasedDate.class);
	
	public static final String RELEASE_DATE = "releaseDate";
	public static final String LOCALE = "locale";
	public static final String DEFAULT_LOCALE = "en";
	public static final String MONTH = "month";
	
	@Override
	public Map<String, Value[]> compute(Map<String, Value[]> parameters) {
		Map<String, Value[]> result = new HashMap<String, Value[]>(0);
		try {
			String locale = getStringFieldValue(parameters, LOCALE);
			Calendar date = parameters.get(RELEASE_DATE)[0].getDate();
			
			if(locale.isEmpty()){
				locale = DEFAULT_LOCALE;
			}else{
				locale = LocaleUtils.toLocale(locale).getLanguage();
			}
			
			parameters.put(MONTH, new Value[] { getValueFactory().createValue(
					getMonthName(date.get(Calendar.MONTH), LocaleUtils.toLocale(locale))) });
			
			return parameters;	
		} catch (Exception e) {
			log.error("DerivedReleasedDate could not complete its task due to the following exception:", e);
		}
		return result;
	}
	
	private String getStringFieldValue(Map<String, Value[]> parameters, String propertyName)
			throws RepositoryException {
		String result = "";
		Value[] values = parameters.get(propertyName);
		if ((values != null) && (values.length > 0) && (values[0].getType() == PropertyType.STRING)) {
			result = values[0].getString();
		}
		return result;
	}
	
	private String getMonthName(int month, Locale locale) {
	    return DateFormatSymbols.getInstance(locale).getMonths()[month];
	}
}
