package com.example.saved_by_the_call.ui.adapters;

import android.content.Context;
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
import com.example.saved_by_the_call.cp.Call;
import com.example.saved_by_the_call.cp.Contact;
import com.example.saved_by_the_call.cp.FakeCallsProvider;

import java.util.ArrayList;

public class CallsAdapter extends ArrayAdapter<Call> {

    private final ArrayList<Call> data;

    public CallsAdapter(Context context, ArrayList<Call> data) {
        super(context, R.layout.element_call, data);
        this.data = data;
    }

    private static class ViewHolder {
        ImageView callIgm;
        TextView callName;
        TextView contactName;
        TextView phone;
        TextView date;
        Button btnEditCall;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.element_call,
                    parent, false);
            viewHolder = new ViewHolder();
            viewHolder.callIgm = convertView.findViewById(R.id.imgViewCallList);
            viewHolder.callName = convertView.findViewById(R.id.txtViewCallNameCallList);
            viewHolder.contactName = convertView.findViewById(R.id.txtViewContactNameCallList);
            viewHolder.phone = convertView.findViewById(R.id.txtViewPhoneCallList);
            viewHolder.date = convertView.findViewById(R.id.txtViewDateCallList);
            viewHolder.btnEditCall = convertView.findViewById(R.id.btnEditCall);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Contact contact = FakeCallsProvider.getContactById(getContext(), data.get(position).getContact());

        String imageUri = contact.getImg();

        Glide.with(getContext())
                .load(imageUri)
                .error(R.drawable.contact_def_img)
                .placeholder(R.drawable.contact_def_img)
                .into(viewHolder.callIgm);

        viewHolder.callName.setText(data.get(position).getName());
        viewHolder.contactName.setText(contact.getName());
        viewHolder.phone.setText(contact.getPhone());

        viewHolder.btnEditCall.setOnClickListener(view -> {
        });

        return convertView;
    }
}
