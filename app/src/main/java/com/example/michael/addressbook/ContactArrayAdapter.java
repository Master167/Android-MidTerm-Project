package com.example.michael.addressbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Michael on 9/25/2016.
 * Ran through tutorial at https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
 */

class ContactArrayAdapter extends ArrayAdapter<ContactItem> {
    public ContactArrayAdapter(Context context, ArrayList<ContactItem> contacts) {
        super(context, android.R.layout.simple_list_item_1, contacts);
    }

    @Override
    public View getView(int index, View convertView, ViewGroup group) {
        ContactItem item = getItem(index);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate((R.layout.contact_item), group);
        }
        TextView nameTextView = (TextView) convertView.findViewById(R.id.name_textView);
        nameTextView.setText(item.getName());
        return convertView;
    }
}
