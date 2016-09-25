package com.example.michael.addressbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AddressList extends AppCompatActivity {
    private ListView listView;
    private ArrayList<ContactItem> contactList;
    private ArrayAdapter<ContactItem> contactsArrayAdapter;
    private Toolbar toolbar;
    private String jsonFilename = "contacts.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);

        // Define Toolbar
        toolbar = (Toolbar) findViewById(R.id.address_toolbar);
        setSupportActionBar(toolbar);

        //Create Contact ListView
        this.contactList = new ArrayList<ContactItem>();
        loadContactList();
        this.contactsArrayAdapter = new ArrayAdapter<ContactItem>(this, android.R.layout.simple_list_item_1, contactList);
        this.listView = (ListView) findViewById(R.id.contact_listView);
        this.listView.setAdapter(this.contactsArrayAdapter);
        registerForContextMenu(this.listView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            // Create Intent to call new activity to add contact
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    private void saveContactList() {
        Log.d("AddressList", "saveContactList is not defined");
    }

    private void loadContactList() {
        Log.d("AddressList", "loadContactList is not defined");
    }
}
