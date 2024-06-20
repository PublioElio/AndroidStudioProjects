package com.example.saved_by_the_call.ui.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.example.saved_by_the_call.R;
import com.example.saved_by_the_call.cp.Call;
import com.example.saved_by_the_call.cp.Contact;
import com.example.saved_by_the_call.cp.FakeCallsProvider;
import com.example.saved_by_the_call.ui.CallListActivity;

import java.util.ArrayList;

public class CallsAdapter extends ArrayAdapter<Call> {

    private final ArrayList<Call> data;
    private final CallListActivity activity;

    public CallsAdapter(CallListActivity activity, ArrayList<Call> data) {
        super(activity, R.layout.element_call, data);
        this.data = data;
        this.activity = activity;
    }

    // test
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

        if (contact == null) {
            Log.e("CallsAdapter", "Contact is null for contact ID: " + data.get(position).getContact());
        } else {
            Log.d("CallsAdapter", "Contact Name: " + contact.getName() + ", Phone: " + contact.getPhone());

            String imageUri = contact.getImg();
            Glide.with(getContext())
                    .load(imageUri)
                    .error(R.drawable.contact_def_img)
                    .placeholder(R.drawable.contact_def_img)
                    .into(viewHolder.callIgm);

            viewHolder.callName.setText(data.get(position).getName());
            viewHolder.contactName.setText(contact.getName());
            viewHolder.phone.setText(contact.getPhone());
            viewHolder.date.setText(data.get(position).getDate());
        }

        viewHolder.btnEditCall.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    getContext(),
                    R.style.AlertDialogCustom).setTitle(R.string.alert_dialog_title_upper_call);
            builder.setMessage(R.string.alert_verify_delete_call_msg);
            builder.setPositiveButton(R.string.btn_ok, (dialog, which) -> {
                activity.deleteCall((int) data.get(position).getId());
                activity.refreshActivity();
            });
            builder.setNegativeButton(R.string.btn_no, (dialog, which) -> dialog.dismiss());
            builder.show();
        });

        return convertView;
    }
}
