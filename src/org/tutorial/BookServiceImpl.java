package org.tutorial;

import java.util.List;


public class BookServiceImpl implements BookService {

	// choose the DAO data source : DB or Mock
	private BookDAO bookDao = new BookDAOImpl(); // or new BookDAOMockImpl();

	@Override
	public List<Book> getAllBooks() {
		List<Book> listBooks = bookDao.findByAll();
		return listBooks;
	}

	@Override
	public List<Book> getBooksByTitle(String searchText) {
		List<Book> listBooks = bookDao.findByTitle(searchText);
		return listBooks;
	}

}
