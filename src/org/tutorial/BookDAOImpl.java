package org.tutorial;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Book DAO to get books from DB
 * 
 * @see BookDAO
 * 
 */
public class BookDAOImpl implements BookDAO {

	/**
	 * common method used to query DB
	 * 
	 * @param query
	 *            the SQL query to use
	 * @return a list of books built from the SQL query
	 */
	private List<Book> findBy(String query) {
		Connection conn = null;
		List<Book> listBooks = new ArrayList<Book>();
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getInstance().getConnection();
			if (conn != null) {
				stat = conn.createStatement();
				rs = stat.executeQuery(query);
				while (rs.next()) {
					int id = rs.getInt("id");
					String title = rs.getString("title");
					String author = rs.getString("author");
					listBooks.add(new Book(id, title, author));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception : main exception should thrown to servlet
			// layer to display error message
			e.printStackTrace();

		} finally {
			// always clean up all resources in finally block
			if (conn != null) {
				DBManager.getInstance().cleanup(conn, stat, rs);
			}
		}
		return listBooks;
	}

	public List<Book> findByAll() {
		// avoid select * queries because of performance issues,
		// only query the columns you need
		return findBy("select id,title,author from books");
	}

	public List<Book> findByTitle(String searchText) {
		// watch out : this query is case sensitive. use upper function on title
		// and searchText to make it case insensitive
		return findBy("select id,title,author from books where title like '%" + searchText + "%'");

	}
}
