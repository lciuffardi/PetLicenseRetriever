package petLicenseRetriever.analytics;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import petLicenseRetriever.console.printer.PetLicenseConsolePrinter;
import petLicenseRetriever.object.PetDARecord;
import petLicenseRetriever.object.PetLicense;
import petLicenseRetriever.object.PetLicenseRetrieverListManager;
import petLicenseRetriever.object.PetLicensedInYearDARecord;

public class PetLicenseDataAnalytics {

	private PetLicenseDataAnalytics() {}
	
	public static void analysisPetLicenseData() {
		Map<String,PetDARecord> petNameDARecordMap = new HashMap<>();
		Map<String, PetDARecord> petSpeciesDARecordMap = new HashMap<>();
		Map<String, PetLicensedInYearDARecord> petLicensedinYearDARecordMap = new HashMap<>();
		Map<String, PetDARecord> petsInZipCodeDARecordMap = new HashMap<>();
		
		for(PetLicense petLicense: PetLicenseRetrieverListManager.getList()) {
			String name = petLicense.getName().toLowerCase();
			String species = petLicense.getSpecies().toLowerCase();
			String year = Integer.toString(petLicense.getIssueDate().get(Calendar.YEAR));
			String zipCode = petLicense.getZipCode();

			populateDARecordMap(petNameDARecordMap, name, "NAME");
			populateDARecordMap(petSpeciesDARecordMap, species, "SPECIES");
			
			if(petLicensedinYearDARecordMap.containsKey(year)) {
				petLicensedinYearDARecordMap.get(year).incrementCount();
				PetDARecord petDARecord = petLicensedinYearDARecordMap.get(year).getSpeciesDARecordMap().get(species);
				if(petDARecord != null)
					petLicensedinYearDARecordMap.get(year).getSpeciesDARecordMap().get(species).incrementCount();
				else
					petLicensedinYearDARecordMap.get(year).addToSpeciesDARecordMap(species);
			}else {
				PetLicensedInYearDARecord petLicenseInYearDARecord = new PetLicensedInYearDARecord("YEAR", year, species);
				petLicensedinYearDARecordMap.put(year, petLicenseInYearDARecord);
			}
			
			populateDARecordMap(petsInZipCodeDARecordMap, zipCode, "ZIP_CODE");
		}
		
		List<PetDARecord> petNameDARecordList = populateDARecordList(petNameDARecordMap);
		List<PetDARecord> petSpeciesDARecordList = populateDARecordList(petSpeciesDARecordMap);
		
		List<PetDARecord> petLicensedInYearDARecordList = new ArrayList<>();
		for(String fieldName : petLicensedinYearDARecordMap.keySet()) {
			petLicensedInYearDARecordList.add(petLicensedinYearDARecordMap.get(fieldName));
		}
		Collections.sort(petLicensedInYearDARecordList);

		List<PetDARecord> petsInZipCodeDARecordList = populateDARecordList(petsInZipCodeDARecordMap);

		PetLicenseConsolePrinter.displayDataAnalytics(petNameDARecordList, petSpeciesDARecordList, petLicensedInYearDARecordList, petsInZipCodeDARecordList);
	}

	private static void populateDARecordMap(Map<String, PetDARecord> petDARecordMap, String key, String fldVal) {
		if(petDARecordMap.containsKey(key)) 
			petDARecordMap.get(key).incrementCount();
		else {
			PetDARecord petDARecord = new PetDARecord(fldVal, key);
			petDARecordMap.put(key, petDARecord);
		}
	}
	
	private static List<PetDARecord> populateDARecordList(Map<String, PetDARecord> petDARecordMap) {
		List<PetDARecord> petDARecordList = new ArrayList<>();
		
		for(String fieldName : petDARecordMap.keySet()) {
			petDARecordList.add(petDARecordMap.get(fieldName));
		}
		
		Collections.sort(petDARecordList);
		
		return petDARecordList;
	}
	
}
