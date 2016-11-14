package com.castor.castorsdk;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;


/**
 * An object reprisenting a 3D model available for sale on the Castor store.
 *
 * This activity is used to display different layout resources for a tutorial on user interface design.

 */
public class Product implements Parcelable {
    private String name;
    private String id;
    private String url;


    public  Product(JSONObject JSON){
        try {

            name = JSON.getString("name");
            id = JSON.getString("id");
            url = JSON.getString("url");
        } catch (JSONException  e) {
            e.printStackTrace();
        }

    }
    protected String getUrl() {
        return url;
    }


    /**
     * A getter method for the product name
     *
     * @return The name of the product
     */
    public String getName() {
        return name;
    }


    /**
     * A getter method for the product id
     *
     * @return The id of the product
     */
    public String getId() {
        return id;
    }



    //Parcelable implementation
    protected Product(Parcel in) {
        name = in.readString();
        id = in.readString();
        url = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);

        parcel.writeValue(url);

    }
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
    public String toString(){

        return name;
    }
}
