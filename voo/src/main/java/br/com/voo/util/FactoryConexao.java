package br.com.voo.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryConexao {

		private static Connection connection = null;

		private FactoryConexao(){
			
		}
		
		public static Connection getConnection() {
	        if (connection != null){
	            return connection;
			}else {
	            try {
//	                String url = "jdbc:postgresql://localhost:5432/vooprod";
//	                String user = "postgres";
//	                String password = "123456";
	            	
	            	InetAddress addr;
	                addr = InetAddress.getLocalHost();
	                String hostname = addr.getHostName();
	           	
	                Class.forName("org.postgresql.Driver");
	                connection = DriverManager.getConnection(FactoryConfiguracao.getConfiguracao(hostname));
	                
	            } catch (ClassNotFoundException e) {
	                e.printStackTrace();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            } catch (UnknownHostException e) {
					e.printStackTrace();
				}
	            return connection;
	        }

	    }
	
	
}
