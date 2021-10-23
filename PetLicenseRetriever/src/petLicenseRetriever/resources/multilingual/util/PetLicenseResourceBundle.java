package petLicenseRetriever.resources.multilingual.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import petLicenseRetriever.constants.SearchCriteria;
import petLicenseRetriever.resources.app.constants.AppKey;
import petLicenseRetriever.resources.app.constants.AppPropertiesFile;
import petLicenseRetriever.resources.multilingual.constants.MenuKey;
import petLicenseRetriever.resources.multilingual.constants.MultilingualKey;
import petLicenseRetriever.resources.multilingual.constants.MultilingualPropertiesFile;
import petLicenseRetriever.resources.multilingual.constants.PetLicenseKey;


public class PetLicenseResourceBundle {
    		
    private static Locale currentLocale;

    private PetLicenseResourceBundle() {};
    
    public static String getMessage(MultilingualPropertiesFile propertiesFile, MultilingualKey key) {
		if(currentLocale == null) 
			currentLocale = Locale.getDefault();
				
		return ResourceBundle.getBundle(propertiesFile.getFldVal(), currentLocale).getString(key.getFldVal());
	}
    
    public static String getMessage(AppPropertiesFile propertiesFile, AppKey key) {
		if(currentLocale == null) 
			currentLocale = Locale.getDefault();
		
		return ResourceBundle.getBundle(propertiesFile.getFldVal(), currentLocale).getString(key.getFldVal());
	}
    
    public static String getFormattedMessage(MultilingualPropertiesFile propertiesFile, MultilingualKey key, Object ... arguements) {
    	if(currentLocale == null) 
			currentLocale = Locale.getDefault();
    	
    	String message = ResourceBundle.getBundle(propertiesFile.getFldVal(), currentLocale).getString(key.getFldVal());
    	
    	return MessageFormat.format(message, arguements);
    }
    
    
    public static String getMessageFromSearchCriteria(SearchCriteria searchCriteria) {
    	
    	String message = "";
    	
    	switch(searchCriteria) {
	    	case ISSUE_DATE:
	    		message = getMessage(MultilingualPropertiesFile.PET_LICENSE, PetLicenseKey.ISSUE_DATE);
		    	break;
			case EXPORT_ALL:
	    		message = getMessage(MultilingualPropertiesFile.PET_LICENSE, MenuKey.EXPORT_ALL);
				break;
			case LICENSE_NUMBER:
	    		message = getMessage(MultilingualPropertiesFile.PET_LICENSE, PetLicenseKey.LICENSE_NUMBER);
				break;
			case NAME:
	    		message = getMessage(MultilingualPropertiesFile.PET_LICENSE, PetLicenseKey.NAME);
				break;
			case PRIMARY_BREED:
	    		message = getMessage(MultilingualPropertiesFile.PET_LICENSE, PetLicenseKey.PRIMARY_BREED);
				break;
			case SECONDARY_BREED:
	    		message = getMessage(MultilingualPropertiesFile.PET_LICENSE, PetLicenseKey.SECONDARY_BREED);
				break;
			case SPECIES:
	    		message = getMessage(MultilingualPropertiesFile.PET_LICENSE, PetLicenseKey.SPECIES);
				break;
			case ZIP_CODE:
	    		message = getMessage(MultilingualPropertiesFile.PET_LICENSE, PetLicenseKey.ZIP_CODE);
				break;
    	}
    	
    	return message;
    }
    
}
