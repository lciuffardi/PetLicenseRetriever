package petLicenseRetriever.constants;

public enum FileType {
	TXT(".txt"),
	XLSX(".xlsx");

	private final String fileType;

	FileType(String fileType){
		this.fileType = fileType;
	}

	public String getFileType() {
		return fileType;
	}
	
}
