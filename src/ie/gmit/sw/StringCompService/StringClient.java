package ie.gmit.sw.StringCompService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class StringClient {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		
		String algorithm = "HAMMING_DISTANCE";
		String s = "will";
		String t = "will";
		String taskNumber = "T1";
		
		RequestJob requestJob = new RequestJob(algorithm, s, t, taskNumber);
		StringService strService = (StringService) Naming.lookup("rmi://localhost:1099/StringCompareService");
		System.out.println(strService);
		
		Resultator res = new ResultatorImpl();
		res = strService.compare(requestJob.getAlgorithm(), requestJob.getStr1(), requestJob.getStr2());
		
		System.out.println(res.toString());
	}
}