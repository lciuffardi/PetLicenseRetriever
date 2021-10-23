package petLicenseRetriever.scanner;

import java.util.Scanner;

public class PetLicenseAppScanner {
	private static Scanner inputSc;

	private PetLicenseAppScanner() {}
	
	public static Scanner getInputScanner() {
		if(inputSc == null)
			inputSc = new Scanner(System.in);
		return inputSc;
	}
	
}
