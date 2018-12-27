package com.example.hamzamasood.realmtest.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hamzamasood.realmtest.R;
import com.example.hamzamasood.realmtest.model.Data_Access_Objects.UserDao;
import com.example.hamzamasood.realmtest.model.Data_Model.User;
import com.example.hamzamasood.realmtest.model.RealmDatabase_Manager;
import com.example.hamzamasood.realmtest.ui.adapters.UsersListAdapter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import io.realm.RealmResults;

public class AddUser_Activity extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout Et_FullName;
    TextInputLayout Et_FatherName;
    TextInputLayout Et_ContactNumber;
    MaterialButton Btn_Add,btn_get;


    String fullname, fathername, contactNumber;

    ListView users_ListView;

    List<User> userslist;

    UsersListAdapter usersListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_add_user);
            init();
            RealmDatabase_Manager.open();


            UserDao userDao = new UserDao(RealmDatabase_Manager.open());
            RealmResults realmResults = userDao.loadAllAsync();
            userslist = RealmDatabase_Manager.open().copyFromRealm(realmResults);

            usersListAdapter = new UsersListAdapter(this,userslist);
            users_ListView.setAdapter(usersListAdapter);





        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        RealmDatabase_Manager.close();
        super.onDestroy();
    }

    private void init() throws Exception {

        users_ListView = findViewById(R.id.users_ListView);

        Et_FullName = findViewById(R.id.Et_FullName);
        Et_ContactNumber = findViewById(R.id.Et_ContactNumber);
        Et_FatherName = findViewById(R.id.Et_FatherName);
        Btn_Add = findViewById(R.id.btn_add);
        Btn_Add.setOnClickListener(this);

        btn_get = findViewById(R.id.btn_get);
        btn_get.setOnClickListener(this);


    }

    private boolean ValidateForm() {

        fullname = Et_FullName.getEditText().getText().toString();
        fathername = Et_FatherName.getEditText().getText().toString();
        contactNumber = Et_ContactNumber.getEditText().getText().toString();

        if (fullname.isEmpty()) {
            Et_FullName.setError("Please Enter Full Name");
            Et_FullName.getEditText().requestFocus();
            return false;
        }

        if (fathername.isEmpty()) {
            Et_FatherName.setError("Please Enter Father Name");
            Et_FatherName.getEditText().requestFocus();
            return false;
        }

        if (contactNumber.isEmpty()) {
            Et_ContactNumber.setError("Please Enter Contact Number");
            Et_ContactNumber.getEditText().requestFocus();
            return false;
        }

        if (contactNumber.length() < 11) {

            Et_ContactNumber.setError("Please Enter Valid Contact Number");
            Et_ContactNumber.getEditText().requestFocus();
            return false;


        }

        Et_FullName.setError(null);
        Et_FatherName.setError(null);
        Et_ContactNumber.setError(null);

        return true;
    }


    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {
            case R.id.btn_add: {
                try {

                    if (ValidateForm()) {

                        User user = new User();
                        user.setU_id(getRandomNumber(1,50));
                        user.setName(fullname);
                        user.setFatherName(fathername);
                        user.setMobileNumber(contactNumber);
                        RealmDatabase_Manager.createUserDao().save(user);
                        Toast.makeText(getApplicationContext(), "Valid Form", Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error in validate Data Method", Toast.LENGTH_SHORT).show();
                }
                break;
            }


            case R.id.btn_get:
            {

//                UserDao userDao = new UserDao(RealmDatabase_Manager.open());
//                RealmResults realmResults = userDao.loadAllAsync();
//                userslist = RealmDatabase_Manager.open().copyFromRealm(realmResults);

                usersListAdapter.notifyDataSetChanged();




                break;
            }

            default: {
                return;
            }
        }

    }


    private int getRandomNumber(int min,int max) {
        return (new Random()).nextInt((max - min) + 1) + min;
    }





}
