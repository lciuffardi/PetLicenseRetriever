package petLicenseRetriever.object;

import java.util.HashMap;
import java.util.Map;

public class PetLicensedInYearDARecord extends PetDARecord {
	private Map<String, PetDARecord> speciesDARecordMap;
	
	public PetLicensedInYearDARecord(String fieldName, String fieldVal, String species) {
		super(fieldName, fieldVal);
		
		speciesDARecordMap = new HashMap<>();
	
		speciesDARecordMap.put(species, new PetDARecord("SPECIES", species));
		
	}

	public Map<String, PetDARecord> getSpeciesDARecordMap() {
		return speciesDARecordMap;
	}

	public void addToSpeciesDARecordMap(String species) {
		speciesDARecordMap.put(species, new PetDARecord("SPECIES", species));
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getFieldVal() + ": " + count + "\n--------------------------\n");
		for(String petType : speciesDARecordMap.keySet()) {
			PetDARecord petSpeciesDARecord = speciesDARecordMap.get(petType);
			sb.append(petSpeciesDARecord + "\n");
		}
		sb.append("--------------------------\n");
		return sb.toString();
	}
	
    public int compareTo(PetDARecord o) {
    	return this.getFieldVal().compareTo(o.getFieldVal());
    }

}
