package com.castor.castor;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.castor.castorsdk.Castor;
import com.castor.castorsdk.CastorStoreFragment;
import com.castor.castorsdk.CompletionStatus;
import com.castor.castorsdk.Product;

public class MainActivity extends Activity implements CastorStoreFragment.CastorStoreFragmentListener{

    private static final String STORE_FRAG = "storeFragment";
    private ListView mListView;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Castor.getInstance().configure("W3sibmFtZSI6IkNsYXNoIG9mIENsYW5zIiwiaWQiOjEsInVybCI6Imh0dHA6Ly8zZGNhc3Rvci53aXhzaXRlLmNvbS9jYXN0b3IvcHJvZHVjdC1wYWdlIn0seyJuYW1lIjoiV2FycmllciIsImlkIjoyLCJ1cmwiOiJodHRwOi8vM2RjYXN0b3Iud2l4c2l0ZS5jb20vY2FzdG9yL3Byb2R1Y3QtcGFnZSJ9XQ==");
       View view = getLayoutInflater().inflate(R.layout.main_activity, null);
        setContentView(view);
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(new ArrayAdapter<Product>(this, R.layout.list_product, Castor.getInstance().getProductArray()));
        mListView.setTextFilterEnabled(true);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Toast.makeText(getApplicationContext(),
                        ((TextView) view).getText(), Toast.LENGTH_SHORT).show();


                Fragment fragment = CastorStoreFragment.newInstance(Castor.getInstance().getProductArray().get(position));
                FragmentTransaction ft = getFragmentManager().beginTransaction();

                ft.add(R.id.fragment_container , fragment , STORE_FRAG).addToBackStack( "tag" ).commit();


            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }



    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void complete(CompletionStatus result) {
        Log.w("result","done displaying fragment");
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        Fragment f = getFragmentManager().findFragmentByTag("first");
        if(f!=null) ft.remove(f).commit();
    }
}
