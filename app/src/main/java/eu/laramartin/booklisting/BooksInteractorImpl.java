package eu.laramartin.booklisting;

import eu.laramartin.booklisting.model.BookSearchResult;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by Miquel Beltran on 10/23/16
 * More on http://beltran.work
 */
public class BooksInteractorImpl implements BooksInteractor {
    private GoogleBooksService service;

    public BooksInteractorImpl() {
        // Configure Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                // Base URL can change for endpoints (dev, staging, live..)
                .baseUrl("https://www.googleapis.com")
                // Takes care of converting the JSON response into java objects
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // Create the Google Book API Service
        service = retrofit.create(GoogleBooksService.class);
    }

    @Override
    public Observable<BookSearchResult> search(String search) {
        return service.search("search+" + search).subscribeOn(Schedulers.io());
    }
}
