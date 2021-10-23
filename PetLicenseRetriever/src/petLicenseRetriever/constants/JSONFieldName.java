package petLicenseRetriever.constants;

/**
 * @author Luigi Ciuffardi
 *
 */
public enum JSONFieldName {
	ISSUE_DATE("license_issue_date"),
	LICENSE_NUMBER("license_number"),
	NAME("animal_s_name"),
	NONE("None"),
	PRIMARY_BREED("primary_breed"),
	SECONDARDY_BREED("secondary_breed"),
	SPECIES("species"),
	ZIP_CODE("zip_code");
	
	private final String fldName;
	
	JSONFieldName(String fldName){
		this.fldName = fldName;
	}

	public String getFldName() {
		return fldName;
	}
	
}
