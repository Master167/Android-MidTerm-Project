package com.example.michael.addressbook;

import android.content.Intent;
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

/**
 * You need to:
 * -Build Out EditContactItem activity to create a new contact
 * -Pull Information from EditContactItem activity to add to listView
 * -Build Save ContactList logic
 * -Build Read ContactList logic
 * -Build out delete contact item logic
 * -Build out Edit Contact item logic
 */
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

        ContactItem item = new ContactItem("Name", "", "", "", "");
        ContactItem item1 = new ContactItem("Name1", "", "", "", "");
        ContactItem item2 = new ContactItem("Name2", "", "", "", "");
        ContactItem item3 = new ContactItem("Name3", "", "", "", "");
        contactList.add(item);
        contactList.add(item1);
        contactList.add(item2);
        contactList.add(item3);
        contactsArrayAdapter.notifyDataSetChanged();

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
            createNewContact();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.contact_list_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = adapterContextMenuInfo.position;

        switch (item.getItemId()) {
            case R.id.edit_contact_menu_item:
                // Make function call here to make intent to edit contact
                Log.d("AddressList", "Edit Item");
                editContact(index);
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
        saveContactList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }

        ContactItem contactItem = (ContactItem) data.getExtras().get(EditContactItem.EXTRA_CONTACT_KEY);
        int index = (int) data.getExtras().get(EditContactItem.EXTRA_INDEX_KEY);
        if (index != -1) {
            contactList.set(index, contactItem);
        }
        else {
            contactList.add(contactItem);
        }
        contactsArrayAdapter.notifyDataSetChanged();
    }

    private void editContact(int index) {
        ContactItem item = contactList.get(index);
        Intent intent = new Intent(AddressList.this, EditContactItem.class);
        intent.putExtra(EditContactItem.EXTRA_CONTACT_KEY, item);
        intent.putExtra(EditContactItem.EXTRA_INDEX_KEY, index);
        startActivityForResult(intent, 0);
    }

    private void createNewContact() {
        Intent intent = new Intent(AddressList.this, EditContactItem.class);
        startActivityForResult(intent, 0);
    }

    private void saveContactList() {
        Log.d("AddressList", "saveContactList is not defined");
    }

    private void loadContactList() {
        Log.d("AddressList", "loadContactList is not defined");
    }


}
