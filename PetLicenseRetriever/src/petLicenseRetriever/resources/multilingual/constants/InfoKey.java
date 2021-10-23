package petLicenseRetriever.resources.multilingual.constants;

public enum InfoKey implements MultilingualKey {
	APP_DESCRIPTION("APP_DESCRIPTION"),
	AUTHOR("AUTHOR"),
	DATA_PROVIDED_BY_THE_CITY_OF_SEATTLE("DATA_PROVIDED_BY_THE_CITY_OF_SEATTLE"),
	EXPORT_COMPLETE("EXPORT_COMPLETE"),
	FOUND_LICENSED_PETS_IN_SEATTLE("FOUND_LICENSED_PETS_IN_SEATTLE"),
	GITHUB("GITHUB"),
	LAST_UPDATED_ON("LAST_UPDATED_ON"),
	LINKEDIN("LINKEDIN"),
	LOADING_SEATTLE_PET_LICENSE_DATA("LOADING_SEATTLE_PET_LICENSE_DATA"),
	TRANSLATIONS_PROVIDED_BY_GOOGLE("TRANSLATIONS_PROVIDED_BY_GOOGLE"),
	VERSION("VERSION");
	
	private final String fldVal;
	
	InfoKey(String fldVal) {
		this.fldVal = fldVal;
	}

	public String getFldVal() {
		return fldVal;
	};
}
