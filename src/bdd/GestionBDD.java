package bdd;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.util.*;


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
	
	//Méthode de test de l'utilisateur 
	public boolean isUser(HttpServletRequest request) throws SQLException {
		boolean isUser = false;
		Connection connexion = null;
	    PreparedStatement statement = null;
	    ResultSet resultat = null;
	    ConfigBDD conf = ConfigBDD.getInstance();
    
	    String pseudo = (String) request.getParameter("pseudo");
	    String mdp = (String) request.getParameter("password");
	    
	    
	    if(pseudo != null && mdp != null) {
	    	//vérification des pseudo et mot de passe dans la base de donnée 
	    	try {
	    		Class.forName("com.mysql.jdbc.Driver");
		        connexion = (Connection) DriverManager.getConnection(conf.getUrl(), conf.getUser(), conf.getPassword());
	    		statement = (PreparedStatement) connexion.prepareStatement("SELECT * FROM Players where pseudo=? and password=?");
		        statement.setString(1, pseudo);
		        statement.setString(2,  mdp);
		        resultat = statement.executeQuery();
	        } catch ( SQLException e ) {
	        	e.printStackTrace();
		    } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				
		        if ( resultat != null ) {
		            try {
		            	//S'il y a un joueur dans la base de donnée avec le pseudo et le mot de passe voulues, isUser passe à True
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
	
	public boolean[] getStatus(HttpServletRequest request) throws SQLException {
		boolean  [] status = new boolean[3];//Position 0 : isUser //Position 1  isAdmin //Position 2 : isBanned
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
	        } catch ( SQLException e ) {
	        	e.printStackTrace();
		    } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				
		        if ( resultat != null ) {
		            try {
		            	if(hasTuple(resultat)) {
		            		status[0] = true;
		            		status[1] = resultat.getBoolean("isAdmin");
		            		status[2] = resultat.getBoolean("ban");
		            	}
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
	    return status;
	}
	
	
	public boolean isAdmin(HttpServletRequest request) throws SQLException {
		Connection connexion = null;
	    PreparedStatement statement = null;
	    ResultSet resultat = null;
	    ConfigBDD conf = ConfigBDD.getInstance();
	    boolean isAdmin = false;
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
	        } catch ( SQLException e ) {
	        	e.printStackTrace();
		    } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				
		        if ( resultat != null ) {
		            try {
		            	if(hasTuple(resultat)) {
		            	isAdmin= resultat.getBoolean("isAdmin");
		            	}
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
	    return isAdmin;
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
	        	statement = (PreparedStatement) connexion.prepareStatement("INSERT INTO Players (`pseudo`, `password`, `birthday`, `email`, `ban`, `subscription`, `isAdmin`) VALUES (?,?,?,?,0,curdate(),0);");
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
	        System.out.println(pseudo+game);
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
		   System.out.println("Ajout d'un jeu : " +name + " " + info + " " + release);
		   
		   try {
		        connexion = (Connection) DriverManager.getConnection( conf.getUrl(), conf.getUser(), conf.getPassword());

				   statement = (PreparedStatement) connexion.prepareStatement("INSERT INTO Games (`name`, `infos`, `release`, `isShowed`) VALUES (?,?,?,?)");
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
	
	
	
	public ResultSet getCurrentMatchs() {
		Connection connexion = null;
		ConfigBDD conf = ConfigBDD.getInstance();
	    PreparedStatement statement = null;

	    /* Connexion à la base de données */
	    try {
	        connexion = (Connection) DriverManager.getConnection( conf.getUrl(), conf.getUser(), conf.getPassword());
	        /* Verification pseudo */
	        statement = (PreparedStatement) connexion.prepareStatement("select * from Matchs where hEnd is NULL;");
	      return( statement.executeQuery());
	                      
	    } catch (SQLException e ) {
	    	e.printStackTrace();
	    }
		return null;
	}
	

	public ResultSet getFinishedMatchs() {
		Connection connexion = null;
		ConfigBDD conf = ConfigBDD.getInstance();
	    PreparedStatement statement = null;

	    /* Connexion à la base de données */
	    try {
	        connexion = (Connection) DriverManager.getConnection( conf.getUrl(), conf.getUser(), conf.getPassword());
	        /* Verification pseudo */
	
	        statement = (PreparedStatement) connexion.prepareStatement("select * from Matchs where hEnd is not  NULL;");
	      return( statement.executeQuery());
	                      
	    } catch (SQLException e ) {
	    	e.printStackTrace();
	    }
		return null;
	}
	
	public ResultSet getPlays(String pseudo) {
		Connection connexion = null;
		ConfigBDD conf = ConfigBDD.getInstance();
	    PreparedStatement statement = null;

	    /* Connexion à la base de données */
	    try {
	        connexion = (Connection) DriverManager.getConnection( conf.getUrl(), conf.getUser(), conf.getPassword());
	        /* Verification pseudo */
	        statement = (PreparedStatement) connexion.prepareStatement("select count(*) from Matchs where pseudo = ?;");
	       
	        statement.setString(1, pseudo);
	      return( statement.executeQuery());
	                      
	    } catch (SQLException e ) {
	    	e.printStackTrace();
	    }
		return null;
	}
	


	
	public void ban(HttpServletRequest request) {
		
		Connection connexion = null;
		ConfigBDD conf = ConfigBDD.getInstance();
	    PreparedStatement statement = null;
	    String pseudo = (String) request.getParameter("pseudo");
		   String ban = (String) request.getParameter("ban");
		   int abs = Math.abs( Integer.parseInt(ban) -1);
		   ban = Integer.toString(abs);
		
		   try {
		        connexion = (Connection) DriverManager.getConnection( conf.getUrl(), conf.getUser(), conf.getPassword());

				    statement = (PreparedStatement) connexion.prepareStatement("Update Players set ban = ? where pseudo = ?  ");
			        statement.setString(1, ban);
			        statement.setString(2, pseudo);
			        statement.execute();
			        statement.close();
			   
		                      
		    } catch (SQLException e ) {
		    	e.printStackTrace();
		    }
		
		 
	}
	
	
	public void end(HttpServletRequest request) {
		
		Connection connexion = null;
		ConfigBDD conf = ConfigBDD.getInstance();
	    PreparedStatement statement = null;
	    String id = (String) request.getParameter("id");
	
		  Date dNow = new Date();
	      SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
	     String d = ft.format(dNow) ;
			   
		   try {
		        connexion = (Connection) DriverManager.getConnection( conf.getUrl(), conf.getUser(), conf.getPassword());

				    statement = (PreparedStatement) connexion.prepareStatement("Update Matchs set hEnd = ? where idMatch = ? ;");
			        statement.setString(1, d);
			        statement.setString(2, id);
			        statement.execute();
			        statement.close();
			   	                      
		} catch (SQLException e ) {
		    	e.printStackTrace();
	    }
		
	
		 
	}

	public void setShow(HttpServletRequest request, Boolean b) {
		Connection connexion = null;
		ConfigBDD conf = ConfigBDD.getInstance();
	    PreparedStatement statement = null;
	    String name = request.getParameter("name");
	    /* Connexion à la base de données */
	    try {
	        connexion = (Connection) DriverManager.getConnection( conf.getUrl(), conf.getUser(), conf.getPassword());
	        /* Verification pseudo */
	        statement = (PreparedStatement) connexion.prepareStatement("update Games set isShowed = ? where name = ?;");
	        statement.setBoolean(1, b);
	        statement.setString(2, name);
	        statement.executeUpdate();
	                      
	    } catch (SQLException e ) {
	    	e.printStackTrace();
	    }
	}

			
	public void changeMDP(HttpServletRequest request) {
		Connection connexion = null;
		ConfigBDD conf = ConfigBDD.getInstance();
	    PreparedStatement statement = null;
	    String pseudo = (String) request.getParameter("pseudo");
	    String newMdp = (String) request.getParameter("NewPassword");
		try {
			connexion = (Connection) DriverManager.getConnection( conf.getUrl(), conf.getUser(), conf.getPassword());
			statement = (PreparedStatement) connexion.prepareStatement("Update Players set password = ? where pseudo = ? ;");
			statement.setString(1, newMdp);
			statement.setString(2, pseudo);
			statement.execute();
			statement.close();        
		} catch (SQLException e ) {
		    e.printStackTrace();
	    }
	}

	
	public String getEmail(String pseudo) {
		Connection connexion = null;
		ConfigBDD conf = ConfigBDD.getInstance();
		ResultSet resultat = null;
	    PreparedStatement statement = null;

	    /* Connexion à la base de données */
	    try {
	        connexion = (Connection) DriverManager.getConnection( conf.getUrl(), conf.getUser(), conf.getPassword());
	        statement = (PreparedStatement) connexion.prepareStatement("select email from Players where pseudo = ?;");
	        statement.setString(1, pseudo);
	        resultat = statement.executeQuery();
	        if(resultat != null && resultat.next()) {
	        	return resultat.getString("email");
	        }
	    } catch (SQLException e ) {
	    	e.printStackTrace();
	    }
		return "";
	}

	public void changeEmail(HttpServletRequest request) {
		Connection connexion = null;
		ConfigBDD conf = ConfigBDD.getInstance();
	    PreparedStatement statement = null;
	    String pseudo = (String) request.getParameter("pseudo");
	    String newEmail = (String) request.getParameter("newEmail");
		try {
			connexion = (Connection) DriverManager.getConnection( conf.getUrl(), conf.getUser(), conf.getPassword());
			statement = (PreparedStatement) connexion.prepareStatement("Update Players set email = ? where pseudo = ? ;");
			statement.setString(1, newEmail);
			statement.setString(2, pseudo);
			statement.execute();
			statement.close();        
		} catch (SQLException e ) {
		    e.printStackTrace();
	    }
	}
	
	public ResultSet getPreferedGames(HttpServletRequest request) {
		Connection connexion = null;
		ConfigBDD conf = ConfigBDD.getInstance();
		ResultSet resultat = null;
	    PreparedStatement statement = null;
	    String pseudo = (String) request.getAttribute("pseudo");
	    try {
	        connexion = (Connection) DriverManager.getConnection( conf.getUrl(), conf.getUser(), conf.getPassword());
	        statement = (PreparedStatement) connexion.prepareStatement("select * from PreferedGames where pseudo = ?;");
	        statement.setString(1, pseudo);
	        return statement.executeQuery();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
		return null;	
	}

	public void removePreferedGames(String pseudo) {
		Connection connexion = null;
		ConfigBDD conf = ConfigBDD.getInstance();
	    PreparedStatement statement = null;
		try {
			connexion = (Connection) DriverManager.getConnection( conf.getUrl(), conf.getUser(), conf.getPassword());
			statement = (PreparedStatement) connexion.prepareStatement("Delete From PreferedGames where pseudo = ? ;");
			statement.setString(1, pseudo);   
			statement.execute();
			statement.close();               
		    } catch (SQLException e ) {
		    	e.printStackTrace();
		    }
	}

	public void addPreferedGames(String pseudo, String game) {
		Connection connexion = null;
		ConfigBDD conf = ConfigBDD.getInstance();
	    PreparedStatement statement = null;
		try {
			connexion = (Connection) DriverManager.getConnection( conf.getUrl(), conf.getUser(), conf.getPassword());
			statement = (PreparedStatement) connexion.prepareStatement("INSERT INTO PreferedGames (`pseudo`, `gameName`) VALUES (?,?);");
			statement.setString(1, pseudo);
			statement.setString(2, game);
			statement.execute();
			statement.close();               
		    } catch (SQLException e ) {
		    	e.printStackTrace();
		    }
	}
		
	
	
	
}

