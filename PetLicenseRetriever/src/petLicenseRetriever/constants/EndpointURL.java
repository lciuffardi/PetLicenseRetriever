package petLicenseRetriever.constants;

/**
 * @author Luigi Ciuffardi
 *
 */
public enum EndpointURL {
	
	SEATTLE_PET_LICENSE_DATA_URL("https://data.seattle.gov/resource/jguv-t9rb.json?$limit=1000&$offset=");
	
	private final String fldVal;
	
	EndpointURL(String fldVal) {
		this.fldVal = fldVal;
	}

	public String getFldVal() {
		return fldVal;
	};
	
}