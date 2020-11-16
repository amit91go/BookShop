package org.ay.bookShop;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ay.bookShop.exceptions.InvalidIsbn13IdException;
import org.ay.bookShop.Book;
import org.ay.bookShop.Customer;
import org.ay.bookShop.Shop;

/**
 * 
 * @author Amit Yadav
 * A simple class to provide basic functionalities to a Book Store.
 */
public class BookShopTerminal {
	
	final static Log logger = LogFactory.getLog(BookShopTerminal.class);
	private Shop shop;
	
	public BookShopTerminal(Shop shop){
		this.shop = shop;		
	}
	/**
	 * Function to sell a book to a customer.
	 * @param customer 
	 * @param book
	 * @return true on success.
	 */
	public boolean sellABook(Customer customer, Book book){
		if(customer.getMoney() >= book.getPrice()){
		if(shop.getBooks().contains(book)){
		customer.setMoney(customer.getMoney() - book.getPrice());
		shop.removeABookFromBookList(book);
		shop.setSales(shop.getSales()+ book.getPrice());
		customer.addABooktoOwnedBookList(book);	
		return true;
		}
		else{
			logger.error("No such book available: " + book.getTitle());
			return false;	
		}
		}
		else{
			logger.error("Customer balance is low: " + customer.getMoney());
			return false;
		}
	}
	
	/**
	 * Function to filter books based on Genre.
	 * @param genre
	 * @return a List of book for that Genre.
	 */
	public List<Book> filterBooksByGenre(Genre genre){
		List<Book> filteredBookList = new ArrayList<Book>();
		if(shop.getBooks().isEmpty()){
			logger.error("No Books are available");
			return new ArrayList<Book>();
		}
		for(Book book: shop.getBooks()){
			if(book.getGenre() == genre){
				filteredBookList.add(book);
			}
		}
		if(filteredBookList.isEmpty()){
			logger.error("No Books are available for this genre");
			return new ArrayList<Book>();

		}
		return filteredBookList;
	}
	
	/**
	 * Function to provide a list of available books without duplicates.
	 * @return unique set of books.
	 */
	public Set<Book> getBookListWithoutDuplicates(){
		if(shop.getBooks().isEmpty()){
			logger.error("No Books are available");
			return null;
		}
		Set<Book> books = new HashSet<Book>(shop.getBooks());
		return books;
	}
	
	/**
	 * Function to compare two books.
	 * @param book1
	 * @param book2
	 * @return true if both books are same.
	 */
	public boolean compareBooks(Book book1, Book book2){
		return book1.equals(book2);		
	}
	
	/**
	 * Function to add a book to the list of books. 
	 * @param book
	 * @return true on success.
	 * @throws InvalidIsbn13IdException
	 */
	public boolean addBookToStore(Book book) throws InvalidIsbn13IdException{
		if(checkIsbn13Validity(book.getIsbn13Id())){
			shop.addABooktoBookList(book);
			return true;
		}
		throw new InvalidIsbn13IdException("ISBN13 ID is invalid: "+book.getIsbn13Id());
	}
	
	/**
	 * Function to verify the validity of ISBN13 ID.
	 * @param isbn13Id
	 * @return
	 */
	private Boolean checkIsbn13Validity(String isbn13Id){
		System.out.println(isbn13Id);
		logger.debug("ISBN13 ID: "+isbn13Id);
		isbn13Id = isbn13Id.replace("-", "");
		if(isbn13Id.length() != 13){
			System.out.println("first fail");
			return false;
		}

		int sum = 0; 
		int digit = 0; 
		for(int i=0;i<isbn13Id.length();i++){
			try{				
				digit = Character.getNumericValue(isbn13Id.charAt(i));
			}catch(Exception e){
				return false;
			}
			if(i%2 == 1){
				sum += digit*3;
			}
			else{
				sum += digit;
			}
		}
		logger.debug("ISBN13 ID sum: "+sum);
		if(sum% 10 == 0){
			return true;
		}
		logger.error("Invalid SBN13 ID");
		return false;

	}
	
}
