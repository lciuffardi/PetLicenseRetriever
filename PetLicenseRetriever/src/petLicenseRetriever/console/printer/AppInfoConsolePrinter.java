package petLicenseRetriever.console.printer;

import petLicenseRetriever.resources.app.constants.AppPropKey;
import petLicenseRetriever.resources.app.constants.AppPropertiesFile;
import petLicenseRetriever.resources.multilingual.constants.InfoKey;
import petLicenseRetriever.resources.multilingual.constants.MultilingualPropertiesFile;
import petLicenseRetriever.resources.multilingual.util.PetLicenseResourceBundle;

public class AppInfoConsolePrinter {
	
	public static void printAllInfo() {
		printAppInfo();
		printAuthorInfo();
	}
	
	public static void printAppInfo() {
		System.out.println(PetLicenseResourceBundle.getFormattedMessage(MultilingualPropertiesFile.INFO, InfoKey.VERSION,
				PetLicenseResourceBundle.getMessage(AppPropertiesFile.APP_PROP, AppPropKey.VERSION)));
		System.out.println(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.INFO, InfoKey.DATA_PROVIDED_BY_THE_CITY_OF_SEATTLE));
		System.out.println(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.INFO, InfoKey.TRANSLATIONS_PROVIDED_BY_GOOGLE));
		System.out.println();
		System.out.println(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.INFO, InfoKey.APP_DESCRIPTION));
		System.out.println("");
	}
	
	public static void printAuthorInfo() {
		System.out.println(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.INFO, InfoKey.AUTHOR) + ": "
				+ PetLicenseResourceBundle.getMessage(AppPropertiesFile.APP_PROP, AppPropKey.AUTHOR));
		System.out.println(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.INFO, InfoKey.LINKEDIN) + ": " 
				+ PetLicenseResourceBundle.getMessage(AppPropertiesFile.APP_PROP, AppPropKey.LINKEDIN));
		System.out.println(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.INFO, InfoKey.GITHUB) + ": " 
				+ PetLicenseResourceBundle.getMessage(AppPropertiesFile.APP_PROP, AppPropKey.GITHUB));
		System.out.println();
	}
}
