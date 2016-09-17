package eu.laramartin.booklisting.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Miquel Beltran on 9/17/16
 * More on http://beltran.work
 */
public class BookSearchResult {
    @SerializedName("items")
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
