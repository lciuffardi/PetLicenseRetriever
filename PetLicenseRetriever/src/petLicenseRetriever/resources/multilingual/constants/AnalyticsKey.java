package petLicenseRetriever.resources.multilingual.constants;

public enum AnalyticsKey implements MultilingualKey {
	NUMBER_OF_LICENSED_PETS_PER_ZIP_CODE("NUMBER_OF_LICENSED_PETS_PER_ZIP_CODE"),
	NUMBER_OF_LICENSED_SPECIES("NUMBER_OF_LICENSED_SPECIES"),
	NUMBER_OF_PETS_LICENSED_PER_YEAR("NUMBER_OF_PETS_LICENSED_PER_YEAR"),
	TOP_5_MOST_FREQUENTLY_USED_PET_NAMES("TOP_5_MOST_FREQUENTLY_USED_PET_NAMES");
	
	private final String fldVal;
	
	AnalyticsKey(String fldVal) {
		this.fldVal = fldVal;
	}

	public String getFldVal() {
		return fldVal;
	};
}
