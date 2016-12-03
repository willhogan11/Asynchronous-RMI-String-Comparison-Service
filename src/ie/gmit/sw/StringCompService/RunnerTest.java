package ie.gmit.sw.StringCompService;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class RunnerTest {

	public static void main(String[] args) {
		
		String algorithm = "Hamming";
		String str1 = "will";
		String str2 = "william";
		String taskNumber = "T1";
		
		RequestJob rj = new RequestJob(algorithm, str1, str2, taskNumber);
		System.out.println(rj);
		
		Queue<String> queue = new LinkedBlockingQueue<String>();
		queue.add(rj.toString());
		
		System.out.println(queue.size());
		
		for (String string : queue) {
			System.out.println(string);
		}
	}
}