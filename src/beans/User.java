package beans;

public class User {
	public User() {
		super();
	}
	
	private String pseudo;
	private boolean isAdmin;
	
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public String toString() {
		return pseudo + " "+isAdmin;
	}
	
}
