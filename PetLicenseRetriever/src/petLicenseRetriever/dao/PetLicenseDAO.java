package petLicenseRetriever.dao;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import petLicenseRetriever.constants.EndpointURL;
import petLicenseRetriever.constants.JSONFieldName;
import petLicenseRetriever.constants.URLParameter;
import petLicenseRetriever.object.PetLicense;
import petLicenseRetriever.resources.multilingual.constants.InfoKey;
import petLicenseRetriever.resources.multilingual.constants.MultilingualPropertiesFile;
import petLicenseRetriever.resources.multilingual.util.PetLicenseResourceBundle;

/**
 * @author Luigi Ciuffardi
 *
 */
public class PetLicenseDAO {
		
	private PetLicenseDAO() {}
	
	/** getPetLicenseData() - Retrieves Pet Data from the Seattle Pet License Database
	 * 
	 */
	public static List<PetLicense> getPetLicenseData() throws MalformedURLException, IOException, ParseException {
	    List<PetLicense> petLicenseList = new ArrayList<>();
		boolean hasData = true;
		int offset = 0;
		int count = 0;
	
		System.out.print(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.INFO, InfoKey.LOADING_SEATTLE_PET_LICENSE_DATA));
		while(hasData) {
			
			System.out.print(".");
			URL url = new URL(EndpointURL.SEATTLE_PET_LICENSE_DATA_URL.getFldVal() + offset + "&$" + URLParameter.ORDER.getFldVal() + "="
					+ JSONFieldName.ISSUE_DATE.getFldName());
			
			int respCode = getResponseCode(url);

	        if(respCode != 200) {
	        	throw new RuntimeException("HttpResponseCode: " + respCode);
	        } else {
	        	
	        	String inline = "";
	        	Scanner sc = new Scanner(url.openStream());
	        
	        	while(sc.hasNext()) {
	        		inline += sc.nextLine();
	        	}
	        	
	        	sc.close();
	        	
	        	JSONParser parser = new JSONParser();
	        	Object obj = parser.parse(inline);
	        	JSONArray dataObj = (JSONArray) obj;
	        	if(dataObj.size() > 0) {
		        	count = populatePetLicenseList(petLicenseList, count, dataObj);
		        	offset += 1000;
		        }else
		        	hasData = false;
	        }
		}
		System.out.println();
		System.out.println(PetLicenseResourceBundle.getFormattedMessage(MultilingualPropertiesFile.INFO, InfoKey.FOUND_LICENSED_PETS_IN_SEATTLE, count)+ "...");
		System.out.println(PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.INFO, InfoKey.LAST_UPDATED_ON) + ": " + petLicenseList.get(petLicenseList.size()-1).getIssueDate().getTime().toString());
	
	    return petLicenseList;
	}
	
	/** getResponseCode()
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws ProtocolException
	 */
	private static int getResponseCode(URL url) throws IOException, ProtocolException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		
		int respCode = conn.getResponseCode();
		conn.disconnect();
		return respCode;
	}
	
	/** populatePetLicenseList()
	 * 
	 * @param petLicenseList
	 * @param count
	 * @param dataObj
	 * @return
	 */
	private static int populatePetLicenseList(List<PetLicense> petLicenseList, int count, JSONArray dataObj) {
		for(int i = 0; i < dataObj.size(); i++) {
			count++;

			JSONObject jsonObj = (JSONObject) dataObj.get(i);
			
			PetLicense petLicense = createPetLicense(jsonObj);
		    
			if(!petLicense.isEmpty())
				petLicenseList.add(petLicense);
			}
		return count;
	}

	/** createLicensePet()
	 * 
	 * @param jsonObj
	 * @return
	 */
	private static PetLicense createPetLicense(JSONObject jsonObj) {
		PetLicense petLicense = new PetLicense();
		if(jsonObj.get(JSONFieldName.ISSUE_DATE.getFldName()) != null) {			

			String issueDateStr = jsonObj.get(JSONFieldName.ISSUE_DATE.getFldName()).toString();
			petLicense.setIssueDate(populateIssueDate(issueDateStr));
			
			if(jsonObj.get(JSONFieldName.LICENSE_NUMBER.getFldName()) != null) 
				petLicense.setLicenseNum(jsonObj.get(JSONFieldName.LICENSE_NUMBER.getFldName()).toString().trim());
			else
				petLicense.setLicenseNum(JSONFieldName.NONE.getFldName().trim());
			
			if(jsonObj.get(JSONFieldName.NAME.getFldName()) != null)
				petLicense.setName(jsonObj.get(JSONFieldName.NAME.getFldName()).toString().trim());
			else
				petLicense.setName(JSONFieldName.NONE.getFldName());
			
			if(jsonObj.get(JSONFieldName.SPECIES.getFldName()) != null)
				petLicense.setSpecies(jsonObj.get(JSONFieldName.SPECIES.getFldName()).toString().trim());
			else
				petLicense.setSpecies(JSONFieldName.NONE.getFldName());
			
			if(jsonObj.get(JSONFieldName.PRIMARY_BREED.getFldName()) != null)
				petLicense.setPrimBreed(jsonObj.get(JSONFieldName.PRIMARY_BREED.getFldName()).toString().trim());
			else
				petLicense.setPrimBreed(JSONFieldName.NONE.getFldName());
			
			if(jsonObj.get(JSONFieldName.SECONDARDY_BREED.getFldName()) != null)
				petLicense.setSecBreed(jsonObj.get(JSONFieldName.SECONDARDY_BREED.getFldName()).toString().trim());
			else
				petLicense.setSecBreed(JSONFieldName.NONE.getFldName());
			
			if(jsonObj.get(JSONFieldName.ZIP_CODE.getFldName()) != null)
				petLicense.setZipCode(jsonObj.get(JSONFieldName.ZIP_CODE.getFldName()).toString().trim());
			else
				petLicense.setZipCode(JSONFieldName.NONE.getFldName());
		}
		return petLicense;
	}

	/** populateIssueDate() - Populates license issue date onto Calendar object and returns it.
	 * 
	 * @param dateStr
	 * @return
	 */
	private static Calendar populateIssueDate(String dateStr) {
		Calendar date = Calendar.getInstance();
		int year = Integer.parseInt(dateStr.substring(0, 4));
		int month = Integer.parseInt(dateStr.substring(5,7))-1;
		int day = Integer.parseInt(dateStr.substring(8,10));
		int hour = Integer.parseInt(dateStr.substring(11, 13));
		int minute = Integer.parseInt(dateStr.substring(14, 16));
		int second = Integer.parseInt(dateStr.substring(17, 19));
		date.set(Calendar.YEAR, year);
		date.set(Calendar.MONTH, month);
		date.set(Calendar.DAY_OF_MONTH, day);
		date.set(Calendar.HOUR, hour);
		date.set(Calendar.MINUTE, minute);
		date.set(Calendar.SECOND, second);
		return date;
	}
}
