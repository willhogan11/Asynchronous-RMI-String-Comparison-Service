package ie.gmit.sw.StringCompService;

import java.rmi.Naming;

public class StringClient {

	public static void main(String[] args) {
		
		String algorithm = "HAMMING_DISTANCE";
		String s = "will";
		String t = "will";
		String taskNumber = "T1";
		
		RequestJob requestJob = new RequestJob(algorithm, s, t, taskNumber);
		
		try {
			
			StringService strServ = (StringService) Naming.lookup("rmi://localhost:1099/StringCompareService");
			
			// Test Display the Object Id for the String Service
			System.out.println(strServ);
			
			Resultator res = new ResultatorImpl();
			
			res = strServ.compare("", "", "");
			
			System.out.println(res.getResult());
			
			
//			Resultator res = new 
//			System.out.println(res);
//			Resultator res = new ResultatorImpl();
//			res = strService.compare(requestJob.getAlgorithm(), requestJob.getStr1(), requestJob.getStr2());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}