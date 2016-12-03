package ie.gmit.sw.StringCompService;

public class RequestJob implements RequestJobable {
	
	private String algorithm;
	private String str1;
	private String str2;
	private String taskNumber;
	
	public RequestJob(String algorithm, String str1, String str2, String taskNumber) {
		super();
		this.algorithm = algorithm;
		this.str1 = str1;
		this.str2 = str2;
		this.taskNumber = taskNumber;
	}
	
	public String getAlgorithm() {
		return algorithm;
	}
	
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	
	public String getStr1() {
		return str1;
	}
	
	public void setStr1(String str1) {
		this.str1 = str1;
	}
	
	public String getStr2() {
		return str2;
	}
	
	public void setStr2(String str2) {
		this.str2 = str2;
	}
	
	public String getTaskNumber() {
		return taskNumber;
	}
	
	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}

	@Override
	public String toString() {
		return "RequestJob [algorithm=" + algorithm + ", str1=" + str1 + ", str2=" + str2 + ", taskNumber=" + taskNumber
				+ "]";
	}
}