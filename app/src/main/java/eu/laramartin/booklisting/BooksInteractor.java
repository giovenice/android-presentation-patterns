package eu.laramartin.booklisting;


import eu.laramartin.booklisting.model.BookSearchResult;
import retrofit2.Call;

public interface BooksInteractor {
    Call<BookSearchResult> search(String search);
}
