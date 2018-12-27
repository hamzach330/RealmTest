package com.example.hamzamasood.realmtest.ui.adapters;


import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hamzamasood.realmtest.R;
import com.example.hamzamasood.realmtest.model.Data_Model.User;

import java.util.List;

import io.realm.RealmResults;

public class UsersListAdapter extends ArrayAdapter<User> {

    private int resourceLayout;
    private Context mContext;
    private List<User> usersList;

    public UsersListAdapter(Context context, List<User> items) {
        super(context, R.layout.userlist_layout, items);
        this.resourceLayout = R.layout.userlist_layout;
        this.mContext = context;
        this.usersList = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        //Item p = getItem(position);
        User user = usersList.get(position);

        if (user != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.fullname_TV);
            TextView tt2 = (TextView) v.findViewById(R.id.fathername_TV);
            TextView tt3 = (TextView) v.findViewById(R.id.mobileNumber_TV);

            if (tt1 != null) {
                tt1.setText(user.getName());
            }

            if (tt2 != null) {
                tt2.setText(user.getFatherName());
            }

            if (tt3 != null) {
                tt3.setText(user.getMobileNumber());
            }
        }

        return v;
    }

}
