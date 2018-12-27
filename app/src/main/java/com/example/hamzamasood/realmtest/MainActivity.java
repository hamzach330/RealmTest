package com.example.hamzamasood.realmtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hamzamasood.realmtest.model.RealmDatabase_Manager;
import com.example.hamzamasood.realmtest.ui.activity.AddUser_Activity;
import com.example.hamzamasood.realmtest.ui.activity.DownloadLovs;
import com.google.android.material.button.MaterialButton;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MaterialButton Btn_add_user_act;
    MaterialButton Btn_downloadLovs;
    MaterialButton Btn_deleteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void init() throws Exception {

        Btn_add_user_act = findViewById(R.id.Btn_add_user_act);
        Btn_downloadLovs = findViewById(R.id.Btn_downloadLovs);
        Btn_deleteDatabase = findViewById(R.id.Btn_deleteDatabase);

        Btn_add_user_act.setOnClickListener(this);
        Btn_downloadLovs.setOnClickListener(this);
        Btn_deleteDatabase.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.Btn_add_user_act: {
                Intent intent = new Intent(MainActivity.this, AddUser_Activity.class);
                startActivity(intent);

                break;
            }

            case R.id.Btn_downloadLovs: {
                Intent intent = new Intent(MainActivity.this, DownloadLovs.class);
                startActivity(intent);

                break;
            }

            case R.id.Btn_deleteDatabase: {
                RealmDatabase_Manager.open();
                RealmDatabase_Manager.clear();

                break;
            }
        }
    }
}
