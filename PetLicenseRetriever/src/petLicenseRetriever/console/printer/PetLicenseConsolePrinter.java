package petLicenseRetriever.console.printer;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import petLicenseRetriever.constants.SearchCriteria;
import petLicenseRetriever.object.PetDARecord;
import petLicenseRetriever.object.PetLicense;
import petLicenseRetriever.object.PetLicenseRetrieverListManager;
import petLicenseRetriever.resources.multilingual.constants.AnalyticsKey;
import petLicenseRetriever.resources.multilingual.constants.CommonKey;
import petLicenseRetriever.resources.multilingual.constants.ErrorsKey;
import petLicenseRetriever.resources.multilingual.constants.MultilingualPropertiesFile;
import petLicenseRetriever.resources.multilingual.util.PetLicenseResourceBundle;
import petLicenseRetriever.scanner.PetLicenseAppScanner;

/**
 * @author Luigi Ciuffardi
 *
 */
public class PetLicenseConsolePrinter{
		
	private PetLicenseConsolePrinter() {}
	
	/** displayAllPetLicenseData() - Displays all pet data retrieved from the Seattle Pet License Database
	 * 
	 */
	public static void displayAllPetLicenseData() {
		String input;
		Scanner sc = PetLicenseAppScanner.getInputScanner();
		List<PetLicense> petLicenseRetrieverList = PetLicenseRetrieverListManager.getList();
		int pages = petLicenseRetrieverList.size() / (int) Math.ceil(10);
		int currPage = 1;
		int currPet = 0;
		for(PetLicense petLicense: petLicenseRetrieverList) {
			currPet++;
			System.out.println(petLicense.toString());
			if(currPet % 10 == 0) {
				System.out.println(PetLicenseResourceBundle.getFormattedMessage(MultilingualPropertiesFile.COMMON, CommonKey.SHOWING_PAGE_OF, currPage, pages));
				System.out.println(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.COMMON, CommonKey.PRESS_ENTER_TO_CONTINUE_OR_EXIT) + "...");
				input = sc.nextLine();
				if(input.matches("e")) 
					break;
				currPage++;
			}
		}
	}

	/** printSearchByResults() - Prints out the pet data found based on search criteria passed in.
	 * 
	 * @param petLicenseList
	 * @param input
	 * @param searchCriteria
	 */
	public static void printSearchByResults(String input, SearchCriteria searchCriteria) {
		boolean found = false;
		boolean print = false;
		for(PetLicense petLicense: PetLicenseRetrieverListManager.getList()) {
			switch(searchCriteria) {
				case ISSUE_DATE:
					int year = Integer.parseInt(input.substring(0,4));
					int month = Integer.parseInt(input.substring(5,7));
					int day = Integer.parseInt(input.substring(8,10));
					
					Calendar issueDate = petLicense.getIssueDate();
					int issueYear = issueDate.get(Calendar.YEAR);
					int issueMonth = issueDate.get(Calendar.MONTH)+1;
					int issueDay = issueDate.get(Calendar.DAY_OF_MONTH);
					
					if(issueYear == year && issueMonth == month && issueDay == day)
						print = true;
					break;
				case LICENSE_NUMBER:
					if(petLicense.getLicenseNum().toLowerCase().matches(input.toLowerCase())) 
						print = true;	
					break;
				case NAME:
					if(petLicense.getName().toLowerCase().matches(input.toLowerCase())) 
						print = true;			
					break;
				case SPECIES:
					if(petLicense.getSpecies().toLowerCase().matches(input.toLowerCase())) 
						print = true;
					break;
				case PRIMARY_BREED:
					if(petLicense.getPrimBreed().toLowerCase().matches(input.toLowerCase())) 
						print = true;
					break;
				case SECONDARY_BREED:
					if(petLicense.getSecBreed().toLowerCase().matches(input.toLowerCase())) 
						print = true;
					break;
				case ZIP_CODE:
					if(petLicense.getZipCode().toLowerCase().matches(input.toLowerCase())) 
						print = true;
					break;
				default:
					break;
			}
			
			if(print) {
				System.out.println(petLicense.toString());
				found = true;
				print = false;
			}
			
		}
		
		if(!found)
			System.out.println(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.ERRORS, ErrorsKey.UNABLE_TO_LOCATE_PET_LICENSE_WITH_FOLLOWING_CRITERIA) 
					+ ": " + PetLicenseResourceBundle.getMessageFromSearchCriteria(searchCriteria) + " - " + input);
	}

	public static void displayDataAnalytics(List<PetDARecord> petNameDARecordList, List<PetDARecord> petSpeciesDARecordList, List<PetDARecord> petsLicensedInYearDARecordList,
			List<PetDARecord> petsInZipCodeDARecordList) {
		Scanner sc = PetLicenseAppScanner.getInputScanner();
		
		System.out.println("===" + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.ANALYTICS, AnalyticsKey.TOP_5_MOST_FREQUENTLY_USED_PET_NAMES) + "===");
		for(int i = 0; i < 5; i++) {
			if(i < petNameDARecordList.size()-1)
				System.out.println(petNameDARecordList.get(i).toString());
			else
				break;
		}
		System.out.println(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.COMMON, CommonKey.PRESS_ENTER_TO_CONTINUE) + "...");
		sc.nextLine();
		System.out.println();
		
		System.out.println("===" + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.ANALYTICS, AnalyticsKey.NUMBER_OF_LICENSED_SPECIES) + "===");
		for(PetDARecord petSpeciesDARecord : petSpeciesDARecordList)
			System.out.println(petSpeciesDARecord.toString());
		System.out.println(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.COMMON, CommonKey.PRESS_ENTER_TO_CONTINUE) + "...");
		sc.nextLine();
		System.out.println();
		
		System.out.println("===" + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.ANALYTICS, AnalyticsKey.NUMBER_OF_PETS_LICENSED_PER_YEAR) + "===");
		for(PetDARecord petLicensedInYearDARecord : petsLicensedInYearDARecordList)
			System.out.println(petLicensedInYearDARecord.toString());
		System.out.println(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.COMMON, CommonKey.PRESS_ENTER_TO_CONTINUE) + "...");
		sc.nextLine();
		System.out.println();
		
		System.out.println("===" + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.ANALYTICS, AnalyticsKey.NUMBER_OF_LICENSED_PETS_PER_ZIP_CODE) + "===");
		for(PetDARecord petsInzipCodeDARecord : petsInZipCodeDARecordList)
			System.out.println(petsInzipCodeDARecord.toString());
		System.out.println(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.COMMON, CommonKey.PRESS_ENTER_TO_CONTINUE) + "...");
		sc.nextLine();
		System.out.println();
		
	}

}
