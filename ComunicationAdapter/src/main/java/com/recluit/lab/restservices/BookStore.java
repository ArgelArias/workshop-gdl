package com.recluit.lab.restservices;

import java.util.ArrayList;
import java.util.List;

public class BookStore {
	
	private List<Book> allBooks;
	
	public BookStore(){
		allBooks = new ArrayList<Book>();
		allBooks.add(new Book("Sherlock Holmes", "Sir Arthur Conan Doyle"));
		allBooks.add(new Book("Los Caminantes", "Carlos Sisi"));
	}

	public List<Book> getAllBooks() {
		return allBooks;
	}

	public void setAllBooks(List<Book> allBooks) {
		this.allBooks = allBooks;
	}
	
	

}
