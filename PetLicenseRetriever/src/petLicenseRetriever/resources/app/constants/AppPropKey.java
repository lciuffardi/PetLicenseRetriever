package petLicenseRetriever.resources.app.constants;

public enum AppPropKey implements AppKey {
	AUTHOR("AUTHOR"),
	GITHUB("GITHUB"),
	LINKEDIN("LINKEDIN"),
	VERSION("VERSION");
	
	private final String fldVal;
	
	AppPropKey(String fldVal) {
		this.fldVal = fldVal;
	}

	public String getFldVal() {
		return fldVal;
	};
}
