package eu.laramartin.booklisting;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import eu.laramartin.booklisting.model.Book;
import eu.laramartin.booklisting.model.BookSearchResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    ImageButton imageButton;
    BooksAdapter adapter;
    ListView listView;
    TextView textNoDataFound;
    static final String SEARCH_RESULTS = "booksSearchResults";
    GoogleBooksService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configure Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                // Base URL can change for endpoints (dev, staging, live..)
                .baseUrl("https://www.googleapis.com")
                // Takes care of converting the JSON response into java objects
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // Create the Google Book API Service
        service = retrofit.create(GoogleBooksService.class);


        editText = (EditText) findViewById(R.id.editText);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        textNoDataFound = (TextView) findViewById(R.id.text_no_data_found);

        adapter = new BooksAdapter(this, -1);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInternetConnectionAvailable()) {
                    performSearch();
                } else {
                    Toast.makeText(MainActivity.this, R.string.error_no_internet,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (savedInstanceState != null) {
            Book[] books = (Book[]) savedInstanceState.getParcelableArray(SEARCH_RESULTS);
            adapter.addAll(books);
        }
    }

    private void performSearch() {
        String formatUserInput = getUserInput().trim().replaceAll("\\s+", "+");
        // Just call the method on the GoogleBooksService
        service.search("search+" + formatUserInput)
                // enqueue runs the request on a separate thread
                .enqueue(new Callback<BookSearchResult>() {

                    // We receive a Response with the content we expect already parsed
                    @Override
                    public void onResponse(Call<BookSearchResult> call, Response<BookSearchResult> books) {
                        updateUi(books.body().getBooks());
                    }

                    // In case of error, this method gets called
                    @Override
                    public void onFailure(Call<BookSearchResult> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    private boolean isInternetConnectionAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork.isConnectedOrConnecting();
    }

    private void updateUi(List<Book> books) {
        if (books.isEmpty()) {
            // if no books found, show a message
            textNoDataFound.setVisibility(View.VISIBLE);
        } else {
            textNoDataFound.setVisibility(View.GONE);
        }
        adapter.clear();
        adapter.addAll(books);
    }

    private String getUserInput() {
        return editText.getText().toString();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Book[] books = new Book[adapter.getCount()];
        for (int i = 0; i < books.length; i++) {
            books[i] = adapter.getItem(i);
        }
        outState.putParcelableArray(SEARCH_RESULTS, (Parcelable[]) books);
    }
}
