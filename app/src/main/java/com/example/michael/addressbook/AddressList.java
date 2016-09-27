package com.example.michael.addressbook;

import android.content.Context;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * You need to:
 * -Build Save ContactList logic
 * -Build Read ContactList logic
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
        this.contactsArrayAdapter = new ContactArrayAdapter(this, contactList);
        this.listView = (ListView) findViewById(R.id.contact_listView);
        this.listView.setAdapter(this.contactsArrayAdapter);
        registerForContextMenu(this.listView);

        // Load Contacts
        loadContactList();
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
        JSONObject json = new JSONObject();
        JSONObject tempJson;
        ContactItem item;
        Writer writer = null;
        try {
            for(int i = 0; i < contactList.size(); i++) {
                item = contactList.get(i);
                tempJson = new JSONObject();
                tempJson.put("name", item.getName());
                tempJson.put("phone", item.getPhoneNumber());
                tempJson.put("email", item.getEmailAddress());
                tempJson.put("street", item.getStreetAddress());
                tempJson.put("city", item.getCityAddress());
                json.put(Integer.toString(i), tempJson.toString());
            }
            OutputStream out = openFileOutput(jsonFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            Log.d("AddressListSave", json.toString());
            writer.write(json.toString());
            writer.close();
        }
        catch (IOException ex) {
            Log.d("AddressList", "IO Exception: ", ex);
        }
        catch (JSONException ex) {
            Log.d("AddressList", "JSON Exception: ", ex);
        }
    }

    private void loadContactList() {
        BufferedReader reader = null;
        ContactItem item;
        try {
            InputStream input = openFileInput(jsonFilename);
            reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            Log.d("AddressListLoad", jsonString.toString());
            JSONObject json = (JSONObject) new JSONTokener(jsonString.toString()).nextValue();

            for(int i = 0; i < json.length(); i++) {
                JSONObject obj = new JSONObject(json.getString(Integer.toString(i)));
                item = new ContactItem();

                if (obj.has("phone")) {
                    item.setPhoneNumber((String) obj.get("phone"));
                }
                if (obj.has("email")) {
                    item.setEmailAddress((String) obj.get("email"));
                }
                if (obj.has("street")) {
                    item.setStreetAddress((String) obj.get("street"));
                }
                if (obj.has("city")) {
                    item.setCityAddress((String) obj.get("city"));
                }
                if (obj.has("name")) {
                    item.setName((String) obj.get("name"));
                    contactList.add(item);
                }
            }
            reader.close();
            contactsArrayAdapter.notifyDataSetChanged();
        }
        catch (IOException ex) {
            Log.d("AddressList", "IO Exception", ex);
        }
        catch (JSONException ex) {
            Log.d("AddressList", "JSON Exception", ex);
        }
    }


}
