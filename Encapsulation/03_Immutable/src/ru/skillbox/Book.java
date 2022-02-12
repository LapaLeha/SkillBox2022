package ru.skillbox;

public class Book {

    private final String nameBook;
    private final String author;
    private final int numberOfPages;
    private final int barCode;


    public Book(String nameBook, String author, int numberOfPages, int barCode) {
        this.nameBook = nameBook;
        this.author = author;
        this.numberOfPages = numberOfPages;
        this.barCode = barCode;
    }

    public String getNameBook() {
        return nameBook;
    }

    public String getAuthor() {
        return author;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public int getBarCode() {
        return barCode;
    }
}
