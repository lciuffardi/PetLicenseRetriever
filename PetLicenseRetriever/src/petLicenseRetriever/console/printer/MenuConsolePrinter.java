package petLicenseRetriever.console.printer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import petLicenseRetriever.analytics.PetLicenseDataAnalytics;
import petLicenseRetriever.constants.SearchCriteria;
import petLicenseRetriever.dao.PetLicenseDAO;
import petLicenseRetriever.object.PetLicenseRetrieverListManager;
import petLicenseRetriever.report.PetLicenseReport;
import petLicenseRetriever.resources.multilingual.constants.CommonKey;
import petLicenseRetriever.resources.multilingual.constants.ErrorsKey;
import petLicenseRetriever.resources.multilingual.constants.MenuKey;
import petLicenseRetriever.resources.multilingual.constants.MultilingualPropertiesFile;
import petLicenseRetriever.resources.multilingual.util.PetLicenseResourceBundle;
import petLicenseRetriever.scanner.PetLicenseAppScanner;
/**
 * @author Luigi Ciuffardi
 *
 */
public class MenuConsolePrinter {
	
	private MenuConsolePrinter() {}
	
	/** showMenuOptions()
	 * 
	 * @param petLicenseList
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public static void showMenuOptions() throws MalformedURLException, IOException, ParseException {
		Scanner sc = PetLicenseAppScanner.getInputScanner();
		boolean exit = false;
		
		while(!exit) {

			System.out.println();
			System.out.println(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.MENU, MenuKey.SELECT_FROM_FOLLOWING));
			System.out.println("1: " + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.MENU, MenuKey.VIEW_ALL_PET_LICENSES));
			System.out.println("2: " + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.MENU, MenuKey.SEARCH_FOR_PET_LICENSE));
			System.out.println("3: " + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.MENU, MenuKey.EXPORT_PET_LICENSE_DATA));
			System.out.println("4: " + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.MENU, MenuKey.SEE_DATA_ANALYTICS));
			System.out.println("9: " + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.MENU, MenuKey.RELOAD_PET_LICENSE_DATA));
			System.out.println("0: " + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.MENU, MenuKey.EXIT_APPLICATION));
			String input = sc.nextLine();
			System.out.println();

			switch(input) {
				case "1":
					PetLicenseConsolePrinter.displayAllPetLicenseData();
					break;
				case "2":
					printSearchByOptions();
					break;
				case "3":
					printExportOptions();
					break;
				case "4":
					PetLicenseDataAnalytics.analysisPetLicenseData();
					break;
				case "9":
					PetLicenseRetrieverListManager.getList().clear();
					PetLicenseDAO.getPetLicenseData();
					break;
				case "0":
					exit = true;
					break;
				default:
					System.out.println(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.ERRORS, ErrorsKey.INVALID_ENTRY) + "...");
			}
		}
		sc.close();
	}
	
	/**	printSearchByOptions()
	 * 
	 */
	public static void printSearchByOptions() {
		Scanner sc = PetLicenseAppScanner.getInputScanner();

		System.out.println("1. " + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.MENU, MenuKey.SEARCH_BY_LICENSE_ISSUE_DATE));
		System.out.println("2. " + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.MENU, MenuKey.SEARCH_BY_LICENSE_NUMBER));
		System.out.println("3. " + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.MENU, MenuKey.SEARCH_BY_NAME));
		System.out.println("4. " + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.MENU, MenuKey.SEARCH_BY_SPECIES));
		System.out.println("5. " + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.MENU, MenuKey.SEARCH_BY_PRIMARY_BREED));
		System.out.println("6. " + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.MENU, MenuKey.SEARCH_BY_SECONDARY_BREED));
		System.out.println("7. " + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.MENU, MenuKey.SEARCH_BY_ZIP_CODE));
		
		String input = sc.nextLine();
		
		SearchCriteria searchCriteria = getSearchCriteria(input);
		
		System.out.print(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.MENU, MenuKey.PLEASE_ENTER_FOLLOWING_SEARCH_CRITERIA) + " - " 
				+ PetLicenseResourceBundle.getMessageFromSearchCriteria(searchCriteria) + ": ");
		input = sc.nextLine();
		
		PetLicenseConsolePrinter.printSearchByResults(input, searchCriteria);
		
		System.out.println(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.COMMON, CommonKey.PRESS_ENTER_TO_CONTINUE) + "...");
		sc.nextLine();
	}
	
	/**	printSearchByOptions()
	 * 
	 */
	public static void printExportOptions() {
		System.out.println("1. " + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.MENU, MenuKey.EXPORT_BY_LICENSE_ISSUE_DATE));
		System.out.println("2. " + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.MENU, MenuKey.EXPORT_BY_LICENSE_NUMBER));
		System.out.println("3. " + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.MENU, MenuKey.EXPORT_BY_NAME));
		System.out.println("4. " + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.MENU, MenuKey.EXPORT_BY_SPECIES));
		System.out.println("5. " + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.MENU, MenuKey.EXPORT_BY_PRIMARY_BREED));
		System.out.println("6. " + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.MENU, MenuKey.EXPORT_BY_SECONDARY_BREED));
		System.out.println("7. " + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.MENU, MenuKey.EXPORT_BY_ZIP_CODE));
		System.out.println("0. " + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.MENU, MenuKey.EXPORT_ALL));

		Scanner sc = PetLicenseAppScanner.getInputScanner();
		String input = sc.nextLine();
		
		SearchCriteria searchCriteria;

		if(!input.matches("0")) {
			searchCriteria = getSearchCriteria(input);
			System.out.print(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.MENU, MenuKey.PLEASE_ENTER_FOLLOWING_SEARCH_CRITERIA) + " - " + PetLicenseResourceBundle.getMessageFromSearchCriteria(searchCriteria) + ": ");
			input = sc.nextLine();
		}else
			searchCriteria = SearchCriteria.EXPORT_ALL;
		
		try {
			PetLicenseReport.exportSearchByResults(input, searchCriteria);
		} catch (IOException e) {
			System.out.println(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.ERRORS, ErrorsKey.UNABLE_TO_EXPORT_DATA) + "...");
			e.printStackTrace();
		}
		
		System.out.println(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.COMMON, CommonKey.PRESS_ENTER_TO_CONTINUE) + "...");
		sc.nextLine();
	}
	
	/** getSearchCriteria() - Returns search criteria based on user input.
	 * 
	 * @param input
	 * @return
	 */
	public static SearchCriteria getSearchCriteria(String input) {
		SearchCriteria searchCriteria = null;
		switch(input) {
		case "1":
			System.out.println(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.COMMON, CommonKey.DATE_FORMAT_YYYY_MM_DD));
			searchCriteria = SearchCriteria.ISSUE_DATE;
			break;
		case "2":
			searchCriteria = SearchCriteria.LICENSE_NUMBER;
			break;
		case "3":
			searchCriteria = SearchCriteria.NAME;
			break;
		case "4":
			searchCriteria = SearchCriteria.SPECIES;
			break;
		case "5":
			searchCriteria = SearchCriteria.PRIMARY_BREED;
			break;
		case "6":
			searchCriteria = SearchCriteria.SECONDARY_BREED;
			break;
		case "7":
			searchCriteria = SearchCriteria.ZIP_CODE;
		default:
			break;
		}
		return searchCriteria;
	}
}
