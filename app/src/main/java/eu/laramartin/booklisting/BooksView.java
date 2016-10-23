package eu.laramartin.booklisting;

import java.util.List;

import eu.laramartin.booklisting.model.Book;

public interface BooksView {

    void updateUi(List<Book> books);
}
