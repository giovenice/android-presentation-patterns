package eu.laramartin.booklisting;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
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

public class MainActivity extends LifecycleActivity {

    EditText editText;
    ImageButton imageButton;
    BooksAdapter adapter;
    ListView listView;
    TextView textNoDataFound;
    static final String SEARCH_RESULTS = "booksSearchResults";

    BooksViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        model = ViewModelProviders.of(this).get(BooksViewModel.class);
        model.getBooks().observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(@Nullable List<Book> books) {
                updateUi(books);
            }
        });
    }

    private void performSearch() {
        String formatUserInput = getUserInput().trim().replaceAll("\\s+", "+");
        // Just call the method on the GoogleBooksService
        model.loadBooks(formatUserInput);
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
}
