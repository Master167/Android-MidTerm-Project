package com.example.michael.addressbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AddressList extends AppCompatActivity {
    private ListView listView;
    private ArrayList<ContactItem> contactList;
    private ContactArrayAdapter contactsArrayAdapter;
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
        this.contactsArrayAdapter = new ContactArrayAdapter(this, contactList);
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
    public void OnCreateContextMenu(ContextMenu menu, View viewObj, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.contact_list_context_menu, menu);
    }

    @Override
    public boolean onContextMenuItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = adapterContextMenuInfo.position;

        switch (item.getItemId()) {
            case R.id.edit_contact_menu_item:
                // Make function call here to make intent to edit contact
                return true;
            case R.id.delete_contact_menu_item:
                this.contactList.remove(index);
                this.contactsArrayAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
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
