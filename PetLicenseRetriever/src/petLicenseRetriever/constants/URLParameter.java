package petLicenseRetriever.constants;

/**
 * @author Luigi Ciuffardi
 *
 */
public enum URLParameter {
	
	ORDER("order");
	
	private final String fldVal;
	
	URLParameter(String fldVal) {
		this.fldVal = fldVal;
	}

	public String getFldVal() {
		return fldVal;
	};
	
}