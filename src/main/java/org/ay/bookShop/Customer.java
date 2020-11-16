package org.ay.bookShop;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple model class to represent a Customer. 
 * @author Amit Yadav
 *
 */

public class Customer {
	
	private String name;
	private List<Book> ownedBooks;
	private float money;
	
	protected Customer(String name, float money) {
		super();
		this.name = name;
		this.ownedBooks = new ArrayList<Book>();
		this.money = money;
	}
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	protected List<Book> getOwnedBooks() {
		return ownedBooks;
	}

	protected float getMoney() {
		return money;
	}
	protected void setMoney(float money) {
		this.money = money;
	}
	
	protected void addABooktoOwnedBookList(Book book){
		ownedBooks.add(book);
	}
	
	protected void removeABookFromOwnedBookList(Book book){
		ownedBooks.remove(book);
	}
	

}
