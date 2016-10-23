package eu.laramartin.booklisting;


import eu.laramartin.booklisting.model.BookSearchResult;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by Miquel Beltran on 10/23/16
 * More on http://beltran.work
 */
public class BooksViewModel {

    private BooksInteractor interactor;
    private Scheduler scheduler;

    public BooksViewModel(BooksInteractor interactor, Scheduler scheduler) {
        this.interactor = interactor;
        this.scheduler = scheduler;
    }

    public Observable<BookSearchResult> search(String search) {
        return interactor.search(search).observeOn(scheduler);
    }
}
