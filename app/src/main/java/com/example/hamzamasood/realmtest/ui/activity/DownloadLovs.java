package com.example.hamzamasood.realmtest.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.example.hamzamasood.realmtest.R;
import com.example.hamzamasood.realmtest.model.Data_Model.Lovs;
import com.example.hamzamasood.realmtest.model.RealmDatabase_Manager;
import com.google.android.material.button.MaterialButton;

import androidx.appcompat.app.AppCompatActivity;


public class DownloadLovs extends AppCompatActivity implements View.OnClickListener {
    MaterialButton Btn_getLov;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_lovs);


        try {
            Lovs lovs = new Lovs();
            lovs.setLov_Id("Fill_City");
            lovs.setLov_Name("Islamabad");
            lovs.setLov_Value("Isl");
            lovs.setLov_Filter("federal");
            RealmDatabase_Manager.open();
            RealmDatabase_Manager.createLovsDao().save(lovs);

        } catch (Exception e) {
            e.printStackTrace();
        }

        init();



    }

    private void init() {
        Btn_getLov = findViewById(R.id.Btn_getLov);
        Btn_getLov.setOnClickListener(this);

    }


    @Override
    protected void onDestroy() {
        RealmDatabase_Manager.close();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id)
        {
            case R.id.Btn_getLov:
            {

                break;
            }
        }
    }
}
