package com.example.hamzamasood.realmtest.app;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmApplication extends Application {


    private void initRealm() throws Exception {
        Realm.init(this);
        //RealmConfiguration config = new RealmConfiguration.Builder().name("myrealm.realm").build();
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("DownloadLovs.realm")
                .deleteRealmIfMigrationNeeded()

                .build();
        Realm.setDefaultConfiguration(config);
    }



    @Override
    public void onCreate() {
        super.onCreate();
        try {
            initRealm();
            } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
