package eu.laramartin.booklisting;


import eu.laramartin.booklisting.model.BookSearchResult;
import rx.Observable;

/**
 * Created by Miquel Beltran on 10/23/16
 * More on http://beltran.work
 */
public interface BooksInteractor {
    Observable<BookSearchResult> search(String search);
}
