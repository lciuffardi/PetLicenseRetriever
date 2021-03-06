package petLicenseRetriever.main;

import org.json.simple.parser.ParseException;

import petLicenseRetriever.console.printer.AppInfoConsolePrinter;
import petLicenseRetriever.console.printer.MenuConsolePrinter;
import petLicenseRetriever.dao.PetLicenseDAO;
import petLicenseRetriever.resources.multilingual.constants.ErrorsKey;
import petLicenseRetriever.resources.multilingual.constants.MultilingualPropertiesFile;
import petLicenseRetriever.resources.multilingual.util.PetLicenseResourceBundle;

import java.io.IOException;

/**
 * @author Luigi Ciuffardi
 *
 */
public class PetLicenseRetriever {
		
	public static void main(String[] args) {
		
		AppInfoConsolePrinter.printAllInfo();
		try {
			PetLicenseDAO.getPetLicenseData();
			MenuConsolePrinter.showMenuOptions();
		} catch(RuntimeException e) {
			System.out.println();
			System.out.println(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.ERRORS, ErrorsKey.UNABLE_TO_CONNECT_TO_DATABASE) + "...");
			e.printStackTrace();
		}
		catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	

}
