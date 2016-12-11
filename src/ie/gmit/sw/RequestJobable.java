package ie.gmit.sw;


/**
 * @author Will Hogan
 * This interface is an abstraction of the RequestJob class, 
 * which contain getters and setters for the Request variable parameters
 */
public interface RequestJobable {

	public String getAlgorithm();
	public void setAlgorithm(String algorithm);
	public String getStr1();
	public void setStr1(String str1);
	public String getStr2();
	public void setStr2(String str2);
	public String getTaskNumber();
	public void setTaskNumber(String taskNumber);
}