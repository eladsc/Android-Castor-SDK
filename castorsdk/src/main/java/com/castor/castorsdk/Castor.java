package com.castor.castorsdk;

import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by eladschiller on 09/11/2016.
 */
public class Castor {
    private Map<String, Product> products = new HashMap<String , Product>();

    private static Castor ourInstance = new Castor();

    public static Castor getInstance() {
        return ourInstance;
    }
private static boolean configured = false;
    public void configure(String key){
        if (configured) {
            return;
        }
        try {
            byte[] data = Base64.decode(key, Base64.DEFAULT);

            String text = new String(data, "UTF-8");

            JSONArray jsonArray = new JSONArray(text);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject productJSON = jsonArray.getJSONObject(i);
                Product product = new Product(productJSON);
                products.put(product.getId(),product);
            }
            configured = true;

        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
    private Castor() {
    }

    /**
     * The  products available for sale.
     * @return The  products available for sale.
     **/
public List<Product> getProductArray(){
    List<Product> list = new ArrayList<Product>(products.values());
    return list;

}
/**
    * The  products available for sale.
 * @return The  products available for sale. The key is the ID of the product and the value is the Product object.
 **/
    public Map<String, Product> getProducts() {
        return products;
    }
}
