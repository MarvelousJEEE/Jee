package bdd;

public class ConfigBDD {
	
	private final int port = 3306;
	private final String user = "root";
	private final String password = "azerty";
	private String url = "jdbc:mysql://localhost:"+port+"/mydb?useSSL=false";
	
	private ConfigBDD() {
	}
	
	private static ConfigBDD configs = new ConfigBDD();
	
	public static ConfigBDD getInstance() {
		return configs;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public String getUrl() {
		return url;
	}
	
}
