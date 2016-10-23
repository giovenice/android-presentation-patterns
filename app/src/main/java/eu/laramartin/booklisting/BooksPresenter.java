package eu.laramartin.booklisting;


import eu.laramartin.booklisting.model.BookSearchResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BooksPresenter {
    BooksView view;
    private BooksInteractor interactor;

    public BooksPresenter(BooksInteractor interactor) {
        this.interactor = interactor;
    }

    public void bind(BooksView view) {
        this.view = view;
    }

    public void unbind() {
        view = null;
    }

    public void performSearch(String userInput) {
        String formatUserInput = userInput.trim().replaceAll("\\s+", "+");
        // Just call the method on the GoogleBooksService
        interactor.search("search+" + formatUserInput)
                // enqueue runs the request on a separate thread
                .enqueue(new Callback<BookSearchResult>() {

                    // We receive a Response with the content we expect already parsed
                    @Override
                    public void onResponse(Call<BookSearchResult> call,
                                           Response<BookSearchResult> books) {
                        if (view != null)
                            view.updateUi(books.body().getBooks());
                    }

                    // In case of error, this method gets called
                    @Override
                    public void onFailure(Call<BookSearchResult> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
