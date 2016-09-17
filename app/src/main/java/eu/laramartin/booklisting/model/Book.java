package eu.laramartin.booklisting.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Miquel Beltran on 9/17/16
 * More on http://beltran.work
 */
public class Book implements Parcelable {
    @SerializedName("volumeInfo")
    BookVolumeInfo volumeInfo;

    public BookVolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(BookVolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    protected Book(Parcel in) {
        volumeInfo = in.readParcelable(BookVolumeInfo.class.getClassLoader());
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(volumeInfo, flags);
    }
}
