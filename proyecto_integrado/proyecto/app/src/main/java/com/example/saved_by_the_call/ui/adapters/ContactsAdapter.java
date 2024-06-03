package com.example.saved_by_the_call.ui.adapters;

import android.content.Context;
import android.content.Intent;
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
import com.example.saved_by_the_call.ui.EditContactActivity;

import java.util.ArrayList;

public class ContactsAdapter extends ArrayAdapter<Contact> {

    private final ArrayList<Contact> data;

    public ContactsAdapter(Context context, ArrayList<Contact> data) {
        super(context, R.layout.element_contact, data);
        this.data = data;
    }

    private static class ViewHolder {
        ImageView contactIgm;
        TextView name;
        TextView phone;
        Button btnEditContact;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.element_contact,
                    parent, false);
            viewHolder = new ViewHolder();
            viewHolder.contactIgm = convertView.findViewById(R.id.imgViewContactList);
            viewHolder.name = convertView.findViewById(R.id.txtViewNameContactList);
            viewHolder.phone = convertView.findViewById(R.id.txtViewPhoneContactList);
            viewHolder.btnEditContact = convertView.findViewById(R.id.btnEditContact);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String imageUri = data.get(position).getImg();

        Glide.with(getContext())
                .load(imageUri)
                .error(R.drawable.contact_def_img)
                .placeholder(R.drawable.contact_def_img)
                .into(viewHolder.contactIgm);

        viewHolder.name.setText(data.get(position).getName());
        viewHolder.phone.setText(data.get(position).getPhone());

        viewHolder.btnEditContact.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), EditContactActivity.class);
            intent.putExtra("contact_id", data.get(position).getId());
            getContext().startActivity(intent);
        });

        return convertView;
    }
}
