package eu.laramartin.booklisting;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import eu.laramartin.booklisting.model.Book;
import eu.laramartin.booklisting.model.BookSearchResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class BooksViewModel extends ViewModel {

    private GoogleBooksService service;

    public BooksViewModel() {
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

    private MutableLiveData<List<Book>> books;

    public void setQuery(String query) {
        loadBooks(query);
    }

    LiveData<List<Book>> getBooks() {
        if (books == null) {
            books = new MutableLiveData<List<Book>>();
        }
        return books;
    }

    private void loadBooks(String query) {
        service.search("search+" + query)
                // enqueue runs the request on a separate thread
                .enqueue(new Callback<BookSearchResult>() {

                    // We receive a Response with the content we expect already parsed
                    @Override
                    public void onResponse(Call<BookSearchResult> call, Response<BookSearchResult> response) {
                        books.setValue(response.body().getBooks());
                    }

                    // In case of error, this method gets called
                    @Override
                    public void onFailure(Call<BookSearchResult> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
