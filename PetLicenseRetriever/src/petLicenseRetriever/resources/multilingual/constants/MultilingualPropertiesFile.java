package petLicenseRetriever.resources.multilingual.constants;

public enum MultilingualPropertiesFile {

	ANALYTICS("Analytics"),
	COMMON("Common"),
	ERRORS("Errors"),
	INFO("Info"),
	MENU("Menu"),
	PET_LICENSE("PetLicense");
	
	private final String fldVal;
	
	MultilingualPropertiesFile(String fldVal){
		this.fldVal = fldVal;
	}

	public String getFldVal() {
		return fldVal;
	}
}
