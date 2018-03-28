package org.tutorial;

import java.util.ArrayList;
import java.util.List;

public class BookDAOMockImpl implements BookDAO {

	@Override
	public List<Book> findByAll() {

		List<Book> listBooks = new ArrayList<Book>();
		listBooks.add(new Book(1, "Alice in wonderland", "Lewis Carroll"));

		return listBooks;
	}

	@Override
	public List<Book> findByTitle(String searchText) {
		return findByAll(); // search will return all books for testing purpose
	}

}
