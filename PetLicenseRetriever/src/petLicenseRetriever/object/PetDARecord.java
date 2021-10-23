package petLicenseRetriever.object;

public class PetDARecord implements Comparable<PetDARecord> {
	private String fieldName;
	private String fieldVal;
	protected int count = 1;
	
	public PetDARecord(String fieldName, String fieldVal) {
		this.fieldName = fieldName;
		this.fieldVal = fieldVal;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	
	public String getFieldVal() {
		return fieldVal;
	}
	public void setFieldVal(String name) {
		this.fieldVal = name;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public int getCount() {
		return count;
	}
	public void incrementCount() {
		count++;
	}
	
	public String toString() {
		String fieldValCap = fieldVal.substring(0,1).toUpperCase() + fieldVal.substring(1,fieldVal.length());
		return fieldValCap + ": " + count;
	}
	
    public int compareTo(PetDARecord o) {
    	if(this.getCount() > o.getCount())
    		return -1;
    	else if(this.getCount() < o.getCount())
    		return 1;
    	else
    		return compareToFieldName(o);
    }
    
    private int compareToFieldName(PetDARecord o) {
    	return this.getFieldVal().compareTo(o.getFieldVal());
    }


}
