package com.example.michael.addressbook;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditContactItem extends AppCompatActivity {

    public static final String EXTRA_CONTACT_KEY = "com.example.michael.addressbook.contact";
    public static final String EXTRA_INDEX_KEY = "com.example.michael.addressbook.index";

    private TextView nameRequiredMessage;
    private EditText editName;
    private EditText editPhone;
    private EditText editEmail;
    private EditText editStreet;
    private EditText editCity;
    private Button saveContactButton;

    private int addressIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact_item);

        // Get EditTexts
        this.editName = (EditText) findViewById(R.id.name_field);
        this.editPhone = (EditText) findViewById(R.id.phone_number_field);
        this.editEmail = (EditText) findViewById(R.id.email_address_field);
        this.editStreet = (EditText) findViewById(R.id.street_address_field);
        this.editCity = (EditText) findViewById(R.id.city_state_zip_field);

        // Get nameRequire TextView
        this.nameRequiredMessage = (TextView) findViewById(R.id.name_required_message_textview);

        // Setup Save Contact Button
        this.saveContactButton = (Button) findViewById(R.id.save_contact_button);
        this.saveContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate that Name is entered
                if (editName.getText().toString().equals("") || editName.getText().toString() == null) {
                    nameRequiredMessage.setText(R.string.name_required_message);
                    nameRequiredMessage.setTextColor(R.color.colorAccent);
                    return;
                }
                saveContactInfo();
            }
        });

        // Clear nameRequired textView
        nameRequiredMessage.setText("");

        loadContactInfo();
    }

    public void loadContactInfo() {
        Intent intent = getIntent();
        this.addressIndex = intent.getIntExtra(EXTRA_INDEX_KEY, -1);
        if (intent.hasExtra(EXTRA_CONTACT_KEY)) {
            ContactItem item = (ContactItem) intent.getExtras().get(EXTRA_CONTACT_KEY);
            editName.setText(item.getName());
            editPhone.setText(item.getPhoneNumber());
            editEmail.setText(item.getEmailAddress());
            editStreet.setText(item.getStreetAddress());
            editCity.setText(item.getCityAddress());
        }
    }

    public void saveContactInfo() {
        ContactItem contactItem = new ContactItem(editName.getText().toString(), editPhone.getText().toString(), editEmail.getText().toString(), editStreet.getText().toString(), editCity.getText().toString());
        Intent intent = new Intent();
        intent.putExtra(EXTRA_CONTACT_KEY, contactItem);
        intent.putExtra(EXTRA_INDEX_KEY, addressIndex);
        setResult(RESULT_OK, intent);
        finish();
    }
}
