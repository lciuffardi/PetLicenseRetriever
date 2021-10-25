package petLicenseRetriever.object;

import java.util.ArrayList;
import java.util.List;

public class PetLicenseRetrieverListManager{
	private static List<PetLicense> list;
	
	private PetLicenseRetrieverListManager() {
		list = new ArrayList<>();
	}
	
	public static List<PetLicense> getList(){
		if(list == null) {
			list = new ArrayList<>();
		}
		
		return list;
	}
	
}
