package bdd;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class GestionBDD {
	
	
	
	private List<String> messages = new ArrayList<String>();
	
	
	private static GestionBDD gestion = new GestionBDD();
	
	private GestionBDD() {
		 /* Chargement du driver JDBC pour MySQL */
		try {
	        Class.forName( "com.mysql.jdbc.Driver" );
	    } catch ( ClassNotFoundException e ) {
	    	e.printStackTrace();
	    }
	}
	
	public static GestionBDD getInstance() {
		return gestion;
	}
	
	public boolean isUser(HttpServletRequest request) throws SQLException {
		boolean isUser = false;
		Connection connexion = null;
	    PreparedStatement statement = null;
	    ResultSet resultat = null;
	    ConfigBDD conf = ConfigBDD.getInstance();
    
	    String pseudo = (String) request.getParameter("pseudo");
	    String mdp = (String) request.getParameter("password");
	    
	    if(pseudo != null && mdp != null) {
	    	try {
	    		Class.forName("com.mysql.jdbc.Driver");
		        connexion = (Connection) DriverManager.getConnection(conf.getUrl(), conf.getUser(), conf.getPassword());
	    		statement = (PreparedStatement) connexion.prepareStatement("SELECT * FROM Players where pseudo=? and password=?");
		        statement.setString(1, pseudo);
		        statement.setString(2,  mdp);
		        resultat = statement.executeQuery();
		        System.out.println(resultat.toString());
	        } catch ( SQLException e ) {
	        	e.printStackTrace();
		    } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				
		        if ( resultat != null ) {
		            try {
		            	isUser=hasTuple(resultat);
		                resultat.close();
		            } catch ( SQLException ignore ) {
		            	ignore.printStackTrace();
		            }
		        }
		        if ( statement != null ) {
		            try {
		                statement.close();
		            } catch ( SQLException ignore ) {
		            }
		        }
		        if ( connexion != null ) {
		            try {
		                connexion.close();
		            } catch ( SQLException ignore ) {
		            	ignore.printStackTrace();
		            }
		        }
			}
	    }
	    return isUser;
	}
	
	public boolean hasTuple(ResultSet r) throws SQLException {
		return r.next();
	}

	
	
	/**
	 * enregisterJoueur : ajoute dans la BDD un nouveau joueur
	 * @param pseudo
	 * @param password
	 * @param dateOfBirth
	 * @param email
	 * @return succes/echec
	 */
	public boolean enregisterJoueur(String pseudo, String password, String dateOfBirth, String email) {
		Connection connexion = null;
		ConfigBDD conf = ConfigBDD.getInstance();
	    PreparedStatement statement = null;
	    ResultSet resultat = null;
	    /* Connexion à la base de données */
	    try {
	        connexion = (Connection) DriverManager.getConnection( conf.getUrl(), conf.getUser(), conf.getPassword());
	        /* Verification pseudo */
	        statement = (PreparedStatement) connexion.prepareStatement("SELECT * FROM Players WHERE Players.pseudo = ? ;");
	        statement.setString(1, pseudo);
	        resultat = statement.executeQuery();
	        
	        if(!resultat.next()) {
		        statement = (PreparedStatement) connexion.prepareStatement("INSERT INTO Players (`pseudo`, `password`, `birthday`, `email`, `ban`) VALUES (?,?,?,?,0)");
		        statement.setString(1, pseudo);
		        statement.setString(2, password);
		        statement.setString(3, dateOfBirth);
		        statement.setString(4, email);
		        statement.execute();
		        statement.close();
		        System.out.println("Inscription reussi");
		        return true;
	        } else {
	        	// pseudo deja utilise
	        	System.out.println("pseudo deja utilise");
	        	return false;
	        }	        
	    } catch (SQLException e ) {
	    	e.printStackTrace();
	    }
		return false;
	}
	

	/**
	 * enregisterPartie : ajoute dans la BDD une nouvelle Partie
	 * @param pseudo
	 * @param game
	 * @return succes/echec
	 */
	public void enregisterPartie(String pseudo, String game) {
		Connection connexion = null;
		ConfigBDD conf = ConfigBDD.getInstance();
	    PreparedStatement statement = null;
	    /* Connexion à la base de données */
	    try {
	        connexion = (Connection) DriverManager.getConnection( conf.getUrl(), conf.getUser(), conf.getPassword());
	        statement = (PreparedStatement) connexion.prepareStatement("INSERT INTO Matchs (`pseudo`, `gameName`, `hBegin`) VALUES (?,?,CURRENT_TIMESTAMP());");
	        statement.setString(1, pseudo);
	        statement.setString(2, game);
	        statement.execute();
	        statement.close();
	        System.out.println("Le jeu va commencé");
	    } catch (SQLException e ) {
		   	e.printStackTrace();
		}
	}
	
	public void enregistrerStop(String pseudo, String game) {
		Connection connexion = null;
		ConfigBDD conf = ConfigBDD.getInstance();
	    PreparedStatement statement = null;
	    /* Connexion à la base de données */
	    try {
	        connexion = (Connection) DriverManager.getConnection( conf.getUrl(), conf.getUser(), conf.getPassword());
	        statement = (PreparedStatement) connexion.prepareStatement("UPDATE Matchs SET hEnd=CURRENT_TIMESTAMP() WHERE idMatch in (SELECT * FROM (SELECT max(idMatch) FROM Matchs WHERE pseudo=?) AS tmp) ;");
	        statement.setString(1,pseudo);
	        statement.execute();
	        statement.close();
	        System.out.println("Le partie est terminée");
	    } catch (SQLException e ) {
		   	e.printStackTrace();
		}
	}


	public ResultSet getGames() {
		Connection connexion = null;
		ConfigBDD conf = ConfigBDD.getInstance();
	    PreparedStatement statement = null;

	    /* Connexion à la base de données */
	    try {
	        connexion = (Connection) DriverManager.getConnection( conf.getUrl(), conf.getUser(), conf.getPassword());
	        /* Verification pseudo */
	        statement = (PreparedStatement) connexion.prepareStatement("SELECT * FROM Games;");
	      return( statement.executeQuery());
	                      
	    } catch (SQLException e ) {
	    	e.printStackTrace();
	    }
		return null;
	}
	
	public void deleteGame( HttpServletRequest request) {
		Connection connexion = null;
		ConfigBDD conf = ConfigBDD.getInstance();
	    PreparedStatement statement = null;
	    	String name = (String) request.getParameter("name");
	    	

		   try {
		        connexion = (Connection) DriverManager.getConnection( conf.getUrl(), conf.getUser(), conf.getPassword());

				   statement = (PreparedStatement) connexion.prepareStatement("Delete From Games where name = ? ;");
			        statement.setString(1, name);
			   
			        statement.execute();
			        statement.close();
			   
		                      
		    } catch (SQLException e ) {
		    	e.printStackTrace();
		    }
		
	}
	
	public void addGame(HttpServletRequest request) {
		Connection connexion = null;
		ConfigBDD conf = ConfigBDD.getInstance();
	    PreparedStatement statement = null;
	    String name = (String) request.getParameter("name");
		   String info = (String) request.getParameter("infos");
		   String release = (String) request.getParameter("release");
		   
		   try {
		        connexion = (Connection) DriverManager.getConnection( conf.getUrl(), conf.getUser(), conf.getPassword());

				   statement = (PreparedStatement) connexion.prepareStatement("INSERT INTO Games (`name`, `infos`, `release`, `show`) VALUES (?,?,?,?)");
			        statement.setString(1, name);
			        statement.setString(2, info);
			        statement.setString(3, release);
			        statement.setString(4, "1");
			        statement.execute();
			        statement.close();
			   
		                      
		    } catch (SQLException e ) {
		    	e.printStackTrace();
		    }
		   
		
		 
	}
	
	public ResultSet getPlayers() {
		Connection connexion = null;
		ConfigBDD conf = ConfigBDD.getInstance();
	    PreparedStatement statement = null;

	    /* Connexion à la base de données */
	    try {
	        connexion = (Connection) DriverManager.getConnection( conf.getUrl(), conf.getUser(), conf.getPassword());
	        /* Verification pseudo */
	        statement = (PreparedStatement) connexion.prepareStatement("SELECT * FROM Players;");
	      return( statement.executeQuery());
	                      
	    } catch (SQLException e ) {
	    	e.printStackTrace();
	    }
		return null;
	}
	

	
	
}

