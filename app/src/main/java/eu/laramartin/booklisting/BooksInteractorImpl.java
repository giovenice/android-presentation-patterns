package eu.laramartin.booklisting;

import eu.laramartin.booklisting.model.BookSearchResult;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    public Call<BookSearchResult> search(String search) {
        return service.search(search);
    }
}
