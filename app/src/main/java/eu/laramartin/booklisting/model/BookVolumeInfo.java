package eu.laramartin.booklisting.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Miquel Beltran on 9/17/16
 * More on http://beltran.work
 */
public class BookVolumeInfo implements Parcelable {
    @SerializedName("authors")
    private List<String> author;
    @SerializedName("title")
    private String title;

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    protected BookVolumeInfo(Parcel in) {
        author = in.createStringArrayList();
        title = in.readString();
    }

    public static final Creator<BookVolumeInfo> CREATOR = new Creator<BookVolumeInfo>() {
        @Override
        public BookVolumeInfo createFromParcel(Parcel in) {
            return new BookVolumeInfo(in);
        }

        @Override
        public BookVolumeInfo[] newArray(int size) {
            return new BookVolumeInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(author);
        dest.writeString(title);
    }
}
