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
	private final int port = 3306;
	private String utilisateur = "root";
	private String motDePasse = "admin";
	
	
	private List<String> messages = new ArrayList<String>();
	String url = "jdbc:mysql://localhost:"+port+"/mydb?useSSL=false";
	
	
	public GestionBDD() {
		 /* Chargement du driver JDBC pour MySQL */
		try {
	        Class.forName( "com.mysql.jdbc.Driver" );
	    } catch ( ClassNotFoundException e ) {
	    }
	}
	

	public List<String> executerTests( HttpServletRequest request ) {
		Connection connexion = null;
	    PreparedStatement statement = null;
	    ResultSet resultat = null;
	    /* Connexion à la base de données */
	    try {
	        connexion = (Connection) DriverManager.getConnection( url, utilisateur, motDePasse );
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
