package es.uem.p2.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable{

	private String title;
	private String author;
	private int id;
	
	public Book() {
		
	}
	
	public Book(String t, String a) {
		this.title = t;
		this.author = a;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/* -------------------------------------------------------------------------
	 * Metodos implementados para el Parcelable
	 * @see android.os.Parcelable
	 * -------------------------------------------------------------------------
	 */	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(title);
		dest.writeString(author);
	}
	
	private Book(Parcel in) {
        id = in.readInt();
        title = in.readString();
        author = in.readString();
    }
	
	public static final Parcelable.Creator<Book> CREATOR
			= new Parcelable.Creator<Book>() {
		public Book createFromParcel(Parcel in) {
			return new Book(in);
		}

		public Book[] newArray(int size) {
			return new Book[size];
		}
	};
}
