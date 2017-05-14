package program;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {

	public Servidor() throws RemoteException, NotBoundException {
		Buscador();
	}
	private void Buscador() throws RemoteException, NotBoundException {
		
		Registry reg = LocateRegistry.createRegistry(5678);
		IArquivo arquivo = (IArquivo) reg.lookup("Busca");
		reg.rebind("busca", arquivo);
		System.out.println("Objeto cadastrado");

	}
	public static void main(String[] args) {
		try {
			new Servidor();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
