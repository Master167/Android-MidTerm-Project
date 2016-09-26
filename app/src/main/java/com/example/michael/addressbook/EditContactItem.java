package com.example.michael.addressbook;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditContactItem extends AppCompatActivity {
    private TextView nameRequiredMessage;
    private EditText editName;
    private EditText editPhone;
    private EditText editEmail;
    private EditText editStreet;
    private EditText editCity;

    public String EXTRA_CONTACT_KEY = "com.example.michael.addressbook";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact_item);

        // Get EditTexts

        // Get nameRequire TextView

        // Setup Save Contact Button

        // Validate that Name is entered

        // Clear nameRequired textView
    }

    // Build out stuff to transfer back to main activity
}
