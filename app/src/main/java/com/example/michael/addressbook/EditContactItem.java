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
    TextView nameRequiredMessage;
    EditText editName;
    EditText editPhone;
    EditText editEmail;
    EditText editStreet;
    EditText editCity;

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
