package petLicenseRetriever.constants;

public enum FilePath {
	PET_LICENSE_EXPORT_PATH(System.getProperty("user.dir") + "\\exports\\");
	
	private final String filePath;
	
	FilePath(String filePath){
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}
	
}
