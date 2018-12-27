package com.example.hamzamasood.realmtest.model.Data_Access_Objects;

import com.example.hamzamasood.realmtest.model.Data_Model.Lovs;
import com.example.hamzamasood.realmtest.model.Data_Model.User;

import androidx.annotation.NonNull;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class LovsDao {


    private Realm mRealm;

    public LovsDao(@NonNull Realm realm) {
        mRealm = realm;
    }

    public void save(final Lovs lovs) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(lovs);
            }
        });
    }


    /////////////// Use this function to get a specific lov
    public RealmResults<Lovs> getLov() {
        /* return mRealm.where(User.class).findAllSortedAsync("id");*/
        return mRealm.where(Lovs.class).findAllAsync("id");
    }


    public RealmResults<Lovs> loadAllAsync() {
        /* return mRealm.where(User.class).findAllSortedAsync("id");*/
        return mRealm.where(Lovs.class).findAllAsync("id");
    }



    public RealmObject loadBy(String Lov_Id) {
        return mRealm.where(User.class).equalTo("Lov_Id", Lov_Id).findFirst();
    }
}
