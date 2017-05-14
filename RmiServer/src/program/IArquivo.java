package program;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IArquivo extends Remote{

	public File buscarArquivo(String consulta) throws RemoteException;
}
