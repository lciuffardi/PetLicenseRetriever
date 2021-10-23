package petLicenseRetriever.resources.multilingual.constants;

public enum CommonKey implements MultilingualKey {
	DATE_FORMAT_YYYY_MM_DD("DATE_FORMAT_YYYY_MM_DD"),
	PRESS_ENTER_TO_CONTINUE("PRESS_ENTER_TO_CONTINUE"),
	PRESS_ENTER_TO_CONTINUE_OR_EXIT("PRESS_ENTER_TO_CONTINUE_OR_EXIT"),
	SHOWING_PAGE_OF("SHOWING_PAGE_OF");
	
	private final String fldVal;
	
	CommonKey(String fldVal) {
		this.fldVal = fldVal;
	}

	public String getFldVal() {
		return fldVal;
	};
}
