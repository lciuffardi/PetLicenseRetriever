package petLicenseRetriever.object;

import java.util.Calendar;

import petLicenseRetriever.resources.multilingual.constants.MultilingualPropertiesFile;
import petLicenseRetriever.resources.multilingual.constants.PetLicenseKey;
import petLicenseRetriever.resources.multilingual.util.PetLicenseResourceBundle;

/**
 * @author Luigi Ciuffardi
 *
 */
public class PetLicense {
	private Calendar issueDate;
	private String licenseNum;
	private String name;
	private String species;
	private String primBreed;
	private String secBreed;
	private String zipCode;
	
	public Calendar getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Calendar issueDate) {
		this.issueDate = issueDate;
	}
	public String getLicenseNum() {
		return licenseNum;
	}
	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getPrimBreed() {
		return primBreed;
	}
	public void setPrimBreed(String primBreed) {
		this.primBreed = primBreed;
	}
	public String getSecBreed() {
		return secBreed;
	}
	public void setSecBreed(String secBreed) {
		this.secBreed = secBreed;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	@Override
	public String toString() {
		return issueDate.getTime() + " - " 
				+ PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.PET_LICENSE, PetLicenseKey.LICENSE_NUMBER) + ": " + licenseNum 
				+ ", " + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.PET_LICENSE, PetLicenseKey.NAME) + ": " + name 
				+ ", " + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.PET_LICENSE, PetLicenseKey.SPECIES) + ": " + species 
				+ ", " + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.PET_LICENSE, PetLicenseKey.PRIMARY_BREED) + ": " + primBreed 
				+ ", " + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.PET_LICENSE, PetLicenseKey.SECONDARY_BREED) + ": " + secBreed 
				+ ", " + PetLicenseResourceBundle.getMessage(MultilingualPropertiesFile.PET_LICENSE, PetLicenseKey.ZIP_CODE) + ": " + zipCode;
	}
	
	public boolean isEmpty() {
		if(issueDate == null)
			return true;
		else
			return false;
	}
	
}
