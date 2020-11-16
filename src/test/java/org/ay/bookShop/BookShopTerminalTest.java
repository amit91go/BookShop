package org.ay.bookShop;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;

import org.ay.bookShop.exceptions.InvalidIsbn13IdException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookShopTerminalTest {
	
	Shop shop;
	BookShopTerminal terminal;
	Book book1;
	Book book2;
	
	@BeforeEach
	public void init(){
		shop = new Shop("Bibliophilia",1050.0);
		terminal = new BookShopTerminal(shop);
		book1 = new Book("978-3608963762", "Rich Dad poor Dad", 50.0f, 550, Genre.Biography);
		book2 = new Book("978-3841335180", "Three men in a boat", 80.0f, 1050, Genre.Comic);
	}
	@Test
	public void testSellABook() {
		Customer customer = new Customer("Amit",500.0f);
		Customer customer2 = new Customer("Aravind,",50.0f);
		Book book3 = new Book("978-3161484100","World beyond seven seas",100.0f,1025,Genre.Fantasy);
		try {
			terminal.addBookToStore(book1);
			terminal.addBookToStore(book2);
		} catch (InvalidIsbn13IdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(terminal.sellABook(customer, book2));
		assertEquals(customer.getMoney(), 500.0 - book2.getPrice());
		assertFalse(terminal.getBookListWithoutDuplicates().contains(book2));
		assertEquals(shop.getSales(), 1050.0 + book2.getPrice());
		assertFalse(terminal.sellABook(customer2, book2),"Checking for low balance error");
		assertFalse(terminal.sellABook(customer,book3),"Checking for Book unavailability");
	}

	@Test
	public void testFilterBooksByGenre() {
		try {
			terminal.addBookToStore(book1);
			terminal.addBookToStore(book2);
		} catch (InvalidIsbn13IdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<Book> actaul1 = new ArrayList<Book>(terminal.filterBooksByGenre(Genre.Biography));
		ArrayList<Book> actaul2 = new ArrayList<Book>(terminal.filterBooksByGenre(Genre.Comic));
		ArrayList<Book> expected1 = new ArrayList<Book>();
		expected1.add(book1);
		ArrayList<Book> expected2 = new ArrayList<Book>();	
		expected2.add(book2);
		assertEquals(actaul1,expected1);
		assertEquals(actaul2,expected2);
		ArrayList<Book> actaul3 = new ArrayList<Book>(terminal.filterBooksByGenre(Genre.Adventure));
		assertTrue(actaul3.isEmpty(),"Checking for Books not available for this genre");
	}

	@Test
	public void testGetBookListWithoutDuplicates() {
		Book book3 = new Book("978-3608963762", "Rich Dad poor Dad", 50.0f, 550, Genre.Biography);	
		try{
		terminal.addBookToStore(book1);
		terminal.addBookToStore(book2);
		terminal.addBookToStore(book3);
		} catch (InvalidIsbn13IdException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashSet<Book> actaul = new HashSet<Book>(terminal.getBookListWithoutDuplicates());
		HashSet<Book> expected = new HashSet<Book>();
		expected.add(book1);
		expected.add(book2);
		assertEquals(actaul.size(),2);
		assertTrue(actaul.contains(book1) && actaul.contains(book2));
	}

	@Test
	public void testCompareBooks() {
		Book book3 = new Book("978-3608963762", "Rich Dad poor Dad", 50.0f, 550, Genre.Biography);
		Book book4 = new Book("978-3841335180", "Three men in a boat", 80.0f, 1050, Genre.Comic);
		assertTrue(book1.equals(book3));
		assertTrue(book2.equals(book4));
		assertFalse(book1.equals(book4));
		assertFalse(book2.equals(book3));
	}

	@Test
	public void testaddBookToStore() {
		try{
		terminal.addBookToStore(book1);
		terminal.addBookToStore(book2);
		
		} catch (InvalidIsbn13IdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(terminal.getBookListWithoutDuplicates().contains(book1));
		assertTrue(terminal.getBookListWithoutDuplicates().contains(book2));
		Book book3 = new Book("978-758245159","Sindbad, the sailor", 50.0f, 390, Genre.Adventure);
		assertThrows(InvalidIsbn13IdException.class,()-> terminal.addBookToStore(book3), "Checking for Invalid ISBN13 ID scenerio");
		
	}
	


}
