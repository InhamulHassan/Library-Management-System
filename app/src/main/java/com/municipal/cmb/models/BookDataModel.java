package com.municipal.cmb.models;

/**
 * Created by RazR on 7/1/2018.
 */

public class BookDataModel {
    private int BookID;
    private String Title;
    private String ISBN;
    private String ISBN13;
    private String Author;
    private int AuthorID;
    private String Category;
    private String[] Genres;
    private String ThumbnailURL;
    private String ImageURL;
    private String Publisher;
    private int[] PublicationDate;
    private int NumPages;
    private String BookFormat;
    private String Description;
    private double BookRatings;

    public BookDataModel(int bookID, String title, String ISBN, String ISBN13, String author, int authorID, String category, String[] genres, String thumbnailURL, String imageURL, String publisher, int[] publicationDate, int numPages, String bookFormat, String description, double bookRatings) {
        BookID = bookID;
        Title = title;
        this.ISBN = ISBN;
        this.ISBN13 = ISBN13;
        Author = author;
        AuthorID = authorID;
        Category = category;
        Genres = genres;
        ThumbnailURL = thumbnailURL;
        ImageURL = imageURL;
        Publisher = publisher;
        PublicationDate = publicationDate;
        NumPages = numPages;
        BookFormat = bookFormat;
        Description = description;
        BookRatings = bookRatings;
    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int bookID) {
        BookID = bookID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getISBN13() {
        return ISBN13;
    }

    public void setISBN13(String ISBN13) {
        this.ISBN13 = ISBN13;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public int getAuthorID() {
        return AuthorID;
    }

    public void setAuthorID(int authorID) {
        AuthorID = authorID;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String[] getGenres() {
        return Genres;
    }

    public void setGenres(String[] genres) {
        Genres = genres;
    }

    public String getThumbnailURL() {
        return ThumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        ThumbnailURL = thumbnailURL;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public int[] getPublicationDate() {
        return PublicationDate;
    }

    public void setPublicationDate(int[] publicationDate) {
        PublicationDate = publicationDate;
    }

    public int getNumPages() {
        return NumPages;
    }

    public void setNumPages(int numPages) {
        NumPages = numPages;
    }

    public String getBookFormat() {
        return BookFormat;
    }

    public void setBookFormat(String bookFormat) {
        BookFormat = bookFormat;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getBookRatings() {
        return BookRatings;
    }

    public void setBookRatings(double bookRatings) {
        BookRatings = bookRatings;
    }
}
