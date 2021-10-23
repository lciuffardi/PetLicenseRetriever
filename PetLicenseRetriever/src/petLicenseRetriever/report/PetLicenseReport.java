package petLicenseRetriever.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import petLicenseRetriever.constants.FilePath;
import petLicenseRetriever.constants.SearchCriteria;
import petLicenseRetriever.object.PetLicense;
import petLicenseRetriever.resources.multilingual.constants.ErrorsKey;
import petLicenseRetriever.resources.multilingual.constants.InfoKey;
import petLicenseRetriever.resources.multilingual.constants.MultilingualPropertiesFile;
import petLicenseRetriever.resources.multilingual.util.PetLicenseResourceBundle;

public class PetLicenseReport {
	
	private static PetLicenseReport petLicenseReport;
	
	private PetLicenseReport() {}
	
	public static PetLicenseReport getInstance() {
		if(petLicenseReport == null)
			petLicenseReport = new PetLicenseReport();
		return petLicenseReport;
	}
	
	public static void exportSearchByResults(List<PetLicense> petLicenseList, String input, SearchCriteria searchCriteria) throws IOException {
		String time = getTime();

		File directory = new File(FilePath.PET_LICENSE_EXPORT_PATH.getFilePath()+ "\\" + time);
		File file;
		
		if(searchCriteria == SearchCriteria.EXPORT_ALL) 
			file = new File(directory.getAbsolutePath() + "\\" + searchCriteria.toString() + ".txt");
		else
			file = new File(directory.getAbsolutePath() + "\\" + "SEARCH_BY_" + searchCriteria.toString() + "_" + input.toUpperCase() + ".txt");
		
		if(!directory.exists())
			directory.mkdirs();
		if(!file.exists())
			file.createNewFile();
		
		System.out.println("Exporting to " + file.getAbsolutePath());
		FileWriter fw = new FileWriter(file);
		
		BufferedWriter bw = new BufferedWriter(fw);
		
		boolean found = false;
		boolean print = false;
		for(PetLicense petLicense: petLicenseList) {
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
				case EXPORT_ALL:
					print = true;
					break;
			}
			
			if(print) {
				bw.write(petLicense.toString());
				bw.newLine();
				found = true;
				print = false;
			}
			
		}
		
		bw.close();

		if(!found)
			System.out.println(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.ERRORS, ErrorsKey.UNABLE_TO_LOCATE_PET_LICENSE_WITH_FOLLOWING_CRITERIA) 
					+ ": " + PetLicenseResourceBundle.getMessageFromSearchCriteria(searchCriteria) + " - " + input);
		else
			System.out.println(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.INFO, InfoKey.EXPORT_COMPLETE));
	}
	
	private static String getTime() {
		String pattern = "yyyy-MM-dd";
		Date today = Calendar.getInstance().getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		return sdf.format(today);
	}
}
