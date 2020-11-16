package org.ay.bookShop;

import java.util.Objects;

public class Book {
	
	private String isbn13Id;
	private String title;
	private float price;
	private int pageNumber;
	private Genre genre;
	
	protected Book(String isbn13Id, String title, float price, int pageNumber, Genre genre) {
		super();
		this.isbn13Id = isbn13Id;
		this.title = title;
		this.price = price;
		this.pageNumber = pageNumber;
		this.genre = genre;
	}
	protected String getIsbn13Id() {
		return isbn13Id;
	}
	protected void setISBN13ID(String isbn13Id) {
		this.isbn13Id = isbn13Id;
	}
	protected String getTitle() {
		return title;
	}
	protected void setTitle(String title) {
		this.title = title;
	}
	protected float getPrice() {
		return price;
	}
	protected void setPrice(float price) {
		this.price = price;
	}
	protected int getPageNumber() {
		return pageNumber;
	}
	protected void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	protected Genre getGenre() {
		return genre;
	}
	protected void setGenre(Genre genre) {
		this.genre = genre;
	}
	
    @Override
    public int hashCode() {
        return Objects.hash(isbn13Id, title, price, pageNumber, genre);
    }
	
	@Override
	public boolean equals(Object otherbook){
		Book book = (Book)otherbook;
		if((this.title.equals(book.getTitle())) && 
				(this.pageNumber == book.getPageNumber()) &&
				(this.price == book.getPrice()) &&
				(this.genre.equals(book.getGenre())) &&
				(this.isbn13Id.equals(book.getIsbn13Id()))){
		
			return true;
		}
		return false;
		
	}
}
