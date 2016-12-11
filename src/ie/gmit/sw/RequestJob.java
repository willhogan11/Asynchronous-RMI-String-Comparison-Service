package ie.gmit.sw;

/**
 * @author Will Hogan
 * This class contains the getter and setters methods that work 
 * in conjunction with the request parameters variables that are coming from the Requester side. 
 */
public class RequestJob implements RequestJobable {
	
	private String algorithm;
	private String str1;
	private String str2;
	private String taskNumber;
	
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
	// For testing purposes...
	public String toString() {
		return "RequestJob [algorithm=" + algorithm + ", str1=" + str1 + ", str2=" + str2 + ", taskNumber=" + taskNumber + "]";
	}
}