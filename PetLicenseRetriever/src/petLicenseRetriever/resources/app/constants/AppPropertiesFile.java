package petLicenseRetriever.resources.app.constants;

public enum AppPropertiesFile {

	APP_PROP("AppProp");
	
	private final String fldVal;
	
	AppPropertiesFile(String fldVal){
		this.fldVal = fldVal;
	}

	public String getFldVal() {
		return fldVal;
	}
}
