package org.ay.bookShop;

import java.util.ArrayList;
import java.util.List;

public class Shop {
	
	private String name;
	private List<Book> books;
	private double sales;
	
	protected Shop(String name, double sales) {
		super();
		this.name = name;
		this.books = new ArrayList<Book>();
		this.sales = sales;
	}
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	protected List<Book> getBooks() {
		return books;
	}

	protected double getSales() {
		return sales;
	}
	protected void setSales(double sales) {
		this.sales = sales;
	}
	
	protected void addABooktoBookList(Book book){	
		books.add(book);
	}
	
	protected void removeABookFromBookList(Book book){
		books.remove(book);
	}

}
