package bdd;

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
		            }
		        }
			}
	    }
	    return isUser;
	   
	}
	
	public boolean hasTuple(ResultSet r) throws SQLException {
		return r.next();
	}

	public List<String> executerTests( HttpServletRequest request ) {
		Connection connexion = null;
		ConfigBDD conf = ConfigBDD.getInstance();
	    PreparedStatement statement = null;
	    ResultSet resultat = null;
	    /* Connexion à la base de données */
	    try {
	        connexion = (Connection) DriverManager.getConnection( conf.getUrl(), conf.getUser(), conf.getPassword());
	        /* Préparation de la requete*/
	        statement = (PreparedStatement) connexion.prepareStatement("SELECT * FROM Players;");

	        resultat = statement.executeQuery();
	 

	        while ( resultat.next() ) {
	            String pseudo = resultat.getString( "pseudo" );
	            String emailUtilisateur = resultat.getString( "email" );
	            String mdp = resultat.getString( "password" );
	            String birthday = resultat.getString( "birthday" );

	            messages.add( "Données retournées par la requête : pseudo = " + pseudo + ", email = " + emailUtilisateur
	                    + ", motdepasse = "
	                    + mdp + ", nom = " + birthday + "." );
	        }
	    } catch ( SQLException e ) {
	        messages.add( "Erreur lors de la connexion : <br/>"
	                + e.getMessage() );
	    } finally {
	        if ( resultat != null ) {
	            try {
	                resultat.close();
	            } catch ( SQLException ignore ) {
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
	            }
	        }
	    }
	    return messages;
	}
}