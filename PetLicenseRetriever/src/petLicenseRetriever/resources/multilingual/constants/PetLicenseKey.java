package petLicenseRetriever.resources.multilingual.constants;

public enum PetLicenseKey implements MultilingualKey {
	ISSUE_DATE("ISSUE_DATE"),
	LICENSE_NUMBER("LICENSE_NUMBER"),
	NAME("NAME"),
	PRIMARY_BREED("PRIMARY_BREED"),
	SECONDARY_BREED("SECONDARY_BREED"),
	SPECIES("SPECIES"),
	ZIP_CODE("ZIP_CODE");
	
	private final String fldVal;
	
	PetLicenseKey(String fldVal) {
		this.fldVal = fldVal;
	}

	public String getFldVal() {
		return fldVal;
	};
}
