package ie.gmit.sw.StringCompService;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class RunnerTest {

	public static void main(String[] args) throws InterruptedException {
		
		String algorithm = "Hamming";
		String str1 = "will";
		String str2 = "william";
		String taskNumber = "T1";
		
		String algorithm1 = "Levenshtein";
		String str1a = "bananas";
		String str2b = "bans";
		String taskNumber1 = "T2";
		
		String algorithm2 = "Euclidien";
		String str1aa = "Dimos";
		String str2bb = "Phobos";
		String taskNumber2 = "T3";
		
		
		RequestJob rj = new RequestJob(algorithm, str1, str2, taskNumber);
		// System.out.println(rj); // Proof that RequestJob Object has been created
		
		Queue<RequestJob> queue = new LinkedBlockingQueue<RequestJob>();
		
		queue.offer(new RequestJob(algorithm, str1, str2, taskNumber));
		queue.offer(new RequestJob(algorithm1, str1a, str2b, taskNumber1));
		queue.offer(new RequestJob(algorithm2, str1aa, str2bb, taskNumber2));
		
		for (RequestJob requestJob : queue) {
			System.out.println(requestJob);
			Thread.sleep(1000);
		}
		
		System.out.println(queue.size());
		
		
		
	}
}