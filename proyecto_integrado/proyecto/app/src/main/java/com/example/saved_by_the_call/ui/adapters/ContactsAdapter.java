package com.example.saved_by_the_call.ui.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.saved_by_the_call.R;
import com.example.saved_by_the_call.cp.Contact;

import java.util.ArrayList;

public class ContactsAdapter extends ArrayAdapter<Contact> {

    private final ArrayList<Contact> data;

    public ContactsAdapter(Context context, ArrayList<Contact> data) {
        super(context, R.layout.element_contact, data);
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater show = LayoutInflater.from(getContext());
        View element = show.inflate(R.layout.element_contact, parent, false);

        ImageView contactIgm = element.findViewById(R.id.imgViewContactList);
        String imageUri = data.get(position).getImg();

        try {
            Glide.with(getContext()).load(Uri.parse(imageUri)).into(contactIgm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TextView name = element.findViewById(R.id.txtViewNameContactList);
        name.setText(data.get(position).getName());

        TextView phone = element.findViewById(R.id.txtViewPhoneContactList);
        phone.setText(data.get(position).getPhone());

        Button btnEditContact = element.findViewById(R.id.btnEditContact);

        return element;
    }
}
