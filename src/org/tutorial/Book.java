package org.tutorial;

/**
 * Book modelisation
 * 
 */
public class Book {

	// book db id
	private int id;

	// book title
	private String title;

	// book author
	private String author;

	public Book() {
		// nothing to do
	}

	public Book(int id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
