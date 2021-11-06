package petLicenseRetriever.report;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import petLicenseRetriever.constants.FilePath;
import petLicenseRetriever.constants.FileType;
import petLicenseRetriever.constants.SearchCriteria;
import petLicenseRetriever.object.PetLicense;
import petLicenseRetriever.object.PetLicenseRetrieverListManager;
import petLicenseRetriever.resources.multilingual.constants.ErrorsKey;
import petLicenseRetriever.resources.multilingual.constants.InfoKey;
import petLicenseRetriever.resources.multilingual.constants.MultilingualPropertiesFile;
import petLicenseRetriever.resources.multilingual.constants.PetLicenseKey;
import petLicenseRetriever.resources.multilingual.util.PetLicenseResourceBundle;

public class PetLicenseReport {
	
	private static PetLicenseReport petLicenseReport;
	
	private PetLicenseReport() {}
	
	public static void exportSearchByResults(String input, SearchCriteria searchCriteria, FileType selectedFileType) throws IOException {
		String time = getTime();

		File directory = new File(FilePath.PET_LICENSE_EXPORT_PATH.getFilePath()+ "\\" + time);
		File file;
		
		if(SearchCriteria.EXPORT_ALL.equals(searchCriteria))
			file = new File(directory.getAbsolutePath() + "\\" + searchCriteria.getFldVal() + selectedFileType.getFileType());
		else
			file = new File(directory.getAbsolutePath() + "\\" + "SEARCH_BY_" + searchCriteria.getFldVal() + "_" + input.toUpperCase() + selectedFileType.getFileType());
		
		if(!directory.exists())
			directory.mkdirs();
		if(!file.exists())
			file.createNewFile();
		
		System.out.println("Exporting to " + file.getAbsolutePath());
		boolean found = false;

		switch(selectedFileType) {
			case TXT:
				found = extractToTXT(input, searchCriteria, file);
				break;
			case XLSX:
				found = extractToXLSX(input, searchCriteria, file);
				break;
			default:
				break;
		}

		if(!found)
			System.out.println(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.ERRORS, ErrorsKey.UNABLE_TO_LOCATE_PET_LICENSE_WITH_FOLLOWING_CRITERIA) 
					+ ": " + PetLicenseResourceBundle.getMessageFromSearchCriteria(searchCriteria) + " - " + input);
		else
			System.out.println(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.INFO, InfoKey.EXPORT_COMPLETE));
	}

	private static boolean extractToTXT(String input, SearchCriteria searchCriteria, File file) throws IOException {
		FileWriter fw = new FileWriter(file);

		BufferedWriter bw = new BufferedWriter(fw);
		boolean found = false;

		for(PetLicense petLicense: PetLicenseRetrieverListManager.getList()) {
			boolean print = isPrintable(input, searchCriteria, petLicense);

			if(print) {
				bw.write(petLicense.toString());
				bw.newLine();
				found = true;
			}

		}

		bw.close();
		return found;
	}

	private static boolean extractToXLSX(String input, SearchCriteria searchCriteria, File file) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Pet Data");

		Row headerRow = sheet.createRow(0);

		Cell headerCell = headerRow.createCell(0);
		headerCell.setCellValue(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.PET_LICENSE, PetLicenseKey.ISSUE_DATE));

		headerCell = headerRow.createCell(1);
		headerCell.setCellValue(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.PET_LICENSE, PetLicenseKey.LICENSE_NUMBER));

		headerCell = headerRow.createCell(2);
		headerCell.setCellValue(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.PET_LICENSE, PetLicenseKey.NAME));

		headerCell = headerRow.createCell(3);
		headerCell.setCellValue(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.PET_LICENSE, PetLicenseKey.SPECIES));

		headerCell = headerRow.createCell(4);
		headerCell.setCellValue(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.PET_LICENSE, PetLicenseKey.PRIMARY_BREED));

		headerCell = headerRow.createCell(5);
		headerCell.setCellValue(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.PET_LICENSE, PetLicenseKey.SECONDARY_BREED));

		headerCell = headerRow.createCell(6);
		headerCell.setCellValue(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.PET_LICENSE, PetLicenseKey.ZIP_CODE));

		boolean found = false;
		int rowCount = 1;

		for(PetLicense petLicense: PetLicenseRetrieverListManager.getList()) {
			boolean print = isPrintable(input, searchCriteria, petLicense);

			if(print) {
				int cellCount = 0;

				Row row = sheet.createRow(rowCount++);
				Cell cell = row.createCell(cellCount++);
				cell.setCellValue(formatIssueDate(petLicense.getIssueDate()));
				cell = row.createCell(cellCount++);
				cell.setCellValue(petLicense.getLicenseNum());
				cell = row.createCell(cellCount++);
				cell.setCellValue(petLicense.getName());
				cell = row.createCell(cellCount++);
				cell.setCellValue(petLicense.getSpecies());
				cell = row.createCell(cellCount++);
				cell.setCellValue(petLicense.getPrimBreed());
				cell = row.createCell(cellCount++);
				cell.setCellValue(petLicense.getSecBreed());
				cell = row.createCell(cellCount);
				cell.setCellValue(petLicense.getZipCode());

				found = true;
			}

		}

		for(int i = 0; i < 7; i++)
			sheet.autoSizeColumn(i);

		FileOutputStream outputStream = new FileOutputStream(file);
		workbook.write(outputStream);
		workbook.close();

		return found;
	}

	private static boolean isPrintable(String input, SearchCriteria searchCriteria, PetLicense petLicense) {
		boolean print = false;

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
		return print;
	}

	private static String getTime() {
		String pattern = "yyyy-MM-dd";
		Date today = Calendar.getInstance().getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		return sdf.format(today);
	}

	private static String formatIssueDate(Calendar calendar) {
		String pattern = "yyyy-MM-dd";

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		return sdf.format(calendar.getTime());
	}
}
