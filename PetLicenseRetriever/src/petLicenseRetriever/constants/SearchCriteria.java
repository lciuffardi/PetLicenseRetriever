package petLicenseRetriever.constants;

/**
 * @author Luigi Ciuffardi
 *
 */
public enum SearchCriteria {
	ISSUE_DATE("ISSUE_DATE"),
	LICENSE_NUMBER("LICENSE_NUMBER"),
	NAME("NAME"),
	SPECIES("SPECIES"),
	PRIMARY_BREED("PRIMARY_BREED"),
	SECONDARY_BREED("SECONDARY_BREED"),
	ZIP_CODE("ZIP_CODE"),
	EXPORT_ALL("EXPORT_ALL");
	
	private final String fldVal;
	
	SearchCriteria(String fldVal){
		this.fldVal = fldVal;
	}

	public String getFldVal() {
		return fldVal;
	}
}
