package ie.gmit.sw;

import java.rmi.RemoteException;

public class ResultatorFactory {

	private static ResultatorFactory fact = new ResultatorFactory();
	
	private ResultatorFactory(){
	}
	
	public static ResultatorFactory getInstance(){
		return fact;
	}
	
	public Resultator getResultatorImpl() throws RemoteException{
		return new ResultatorImpl();
	}
}