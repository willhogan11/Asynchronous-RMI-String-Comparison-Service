package ie.gmit.sw.Comparator;

import java.rmi.RemoteException;

public interface StringService {

	public Resultator compare(String s, String t, String algo) throws RemoteException;
}