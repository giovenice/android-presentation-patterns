package eu.laramartin.booklisting;

import eu.laramartin.booklisting.model.BookSearchResult;
import rx.Observable;

/**
 * Created by Miquel Beltran on 10/23/16
 * More on http://beltran.work
 */
public class BooksInteractorMock implements BooksInteractor {
    @Override
    public Observable<BookSearchResult> search(String search) {
        return Observable.just(getMockedBookSearchResult());
    }

    private BookSearchResult getMockedBookSearchResult() {
        BookSearchResult bookSearchResult = new BookSearchResult();
//        bookSearchResult.setBooks(myListOfBooks);
        return bookSearchResult;
    }
}
