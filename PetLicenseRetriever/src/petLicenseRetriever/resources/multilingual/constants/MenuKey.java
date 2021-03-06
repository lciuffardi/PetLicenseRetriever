package petLicenseRetriever.resources.multilingual.constants;

public enum MenuKey implements MultilingualKey {
	EXIT_APPLICATION("EXIT_APPLICATION"),
	EXPORT_ALL("EXPORT_ALL"),
	EXPORT_BY_LICENSE_ISSUE_DATE("EXPORT_BY_LICENSE_ISSUE_DATE"),
	EXPORT_BY_LICENSE_NUMBER("EXPORT_BY_LICENSE_NUMBER"),
	EXPORT_BY_NAME("EXPORT_BY_NAME"),
	EXPORT_BY_PRIMARY_BREED("EXPORT_BY_PRIMARY_BREED"),
	EXPORT_BY_SECONDARY_BREED("EXPORT_BY_SECONDARY_BREED"),
	EXPORT_BY_SPECIES("EXPORT_BY_SPECIES"),
	EXPORT_BY_ZIP_CODE("EXPORT_BY_ZIP_CODE"),
	EXPORT_PET_LICENSE_DATA("EXPORT_PET_LICENSE_DATA"),
	PLEASE_ENTER_FOLLOWING_SEARCH_CRITERIA("PLEASE_ENTER_FOLLOWING_SEARCH_CRITERIA"),
	RELOAD_PET_LICENSE_DATA("RELOAD_PET_LICENSE_DATA"),
	SEARCH_BY_LICENSE_ISSUE_DATE("SEARCH_BY_LICENSE_ISSUE_DATE"),
	SEARCH_BY_LICENSE_NUMBER("SEARCH_BY_LICENSE_NUMBER"),
	SEARCH_BY_NAME("SEARCH_BY_NAME"),
	SEARCH_BY_PRIMARY_BREED("SEARCH_BY_PRIMARY_BREED"),
	SEARCH_BY_SECONDARY_BREED("SEARCH_BY_SECONDARY_BREED"),
	SEARCH_BY_SPECIES("SEARCH_BY_SPECIES"),
	SEARCH_BY_ZIP_CODE("SEARCH_BY_ZIP_CODE"),
	SEARCH_FOR_PET_LICENSE("SEARCH_FOR_PET_LICENSE"),
	SEE_DATA_ANALYTICS("SEE_DATA_ANALYTICS"),
	SELECT_FROM_FOLLOWING("SELECT_FROM_FOLLOWING"),
	VIEW_ALL_PET_LICENSES("VIEW_ALL_PET_LICENSES");
	private final String fldVal;
	
	MenuKey(String fldVal) {
		this.fldVal = fldVal;
	}

	public String getFldVal() {
		return fldVal;
	};
}
