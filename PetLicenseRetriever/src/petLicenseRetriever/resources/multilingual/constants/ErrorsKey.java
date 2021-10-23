package petLicenseRetriever.resources.multilingual.constants;

public enum ErrorsKey implements MultilingualKey {
	INVALID_ENTRY("INVALID_ENTRY"),
	UNABLE_TO_CONNECT_TO_DATABASE("UNABLE_TO_CONNECT_TO_DATABASE"),
	UNABLE_TO_EXPORT_DATA("UNABLE_TO_EXPORT_DATA"),
	UNABLE_TO_LOCATE_PET_LICENSE_WITH_FOLLOWING_CRITERIA("UNABLE_TO_LOCATE_PET_LICENSE_WITH_FOLLOWING_CRITERIA");
	
	private final String fldVal;
	
	ErrorsKey(String fldVal) {
		this.fldVal = fldVal;
	}

	public String getFldVal() {
		return fldVal;
	};
}
