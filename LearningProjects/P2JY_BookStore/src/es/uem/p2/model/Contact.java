package es.uem.p2.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {
	
	private String name;
	private String phone;
	private int id;
	
	public Contact() { 
		
	}
	
	public Contact(String name, String phone) {
		this.name = name;
		this.phone = phone;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public int getId() {
		return id;
	}
	
	public void setName(String mName) {
		this.name = mName;
	}
	
	public void setPhone(String mPhone) {
		this.phone = mPhone;
	}
	
	public void setId(int mId) {
		this.id = mId;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(name);
		dest.writeString(phone);		
	}
	
	private Contact(Parcel in) {
        id = in.readInt();
        name = in.readString();
        phone = in.readString();
    }
	
	public static final Parcelable.Creator<Contact> CREATOR
			= new Parcelable.Creator<Contact>() {
		public Contact createFromParcel(Parcel in) {
			return new Contact(in);
		}

		public Contact[] newArray(int size) {
			return new Contact[size];
		}
	};
	
	
	
}
