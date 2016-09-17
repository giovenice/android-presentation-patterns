package eu.laramartin.booklisting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import eu.laramartin.booklisting.model.Book;

/**
 * Created by Lara on 11/09/2016.
 */
public class BooksAdapter extends ArrayAdapter<Book> {

    public BooksAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        Book book = getItem(position);

        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        TextView title = (TextView) view.findViewById(R.id.title);
        TextView author = (TextView) view.findViewById(R.id.author);
        if (book.getVolumeInfo() != null) {
            title.setText(book.getVolumeInfo().getTitle());
            if (book.getVolumeInfo().getAuthor() != null) {
                StringBuilder builder = new StringBuilder();
                for (String authorName : book.getVolumeInfo().getAuthor()) {
                    if (builder.length() > 0) {
                        builder.append(", ");
                    }
                    builder.append(authorName);
                }
                author.setText(builder.toString());
            }
        }
        return view;
    }
}
