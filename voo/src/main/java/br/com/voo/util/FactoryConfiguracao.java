package br.com.voo.util;


public class FactoryConfiguracao {
	
	public FactoryConfiguracao(){
		
	}
	
	public static String getConfiguracao(String hostname){
		System.out.println(""+hostname);
		if("Thiago".equals(hostname)){
			return thiago();
		}else if("localhost.localdomain".equals(hostname)){
			return bruno();
		}else if("ubuntu-notebook".equals(hostname)){
			return cristiane();
		}
		
		return "";
	}
	
	private static String bruno(){
		String url = "jdbc:postgresql://localhost:5432/voocom";
        String user = "postgres";
        String password = "123456";
        return url+"?user="+user+"&password="+password;
	}
	
	private static String thiago(){
		String url = "jdbc:postgresql://localhost:5432/voocom";
        String user = "postgres";
        String password = "123456";
        return url+"?user="+user+"&password="+password;                                                                                                                                                                    
	}
	
	private static String cristiane(){
		String url = "jdbc:postgresql://localhost:5432/voocom";
        String user = "postgres";
        String password = "123456";
        return url+"?user="+user+"&password="+password;
	}
	
	
	
	
	
	
}
