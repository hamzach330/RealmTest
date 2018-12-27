package com.example.hamzamasood.realmtest.model.Data_Access_Objects;



import com.example.hamzamasood.realmtest.model.Data_Model.User;

import androidx.annotation.NonNull;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class UserDao {

    private Realm mRealm;

    public UserDao(@NonNull Realm realm) {
        mRealm = realm;
    }

    public void save(final User user) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(user);
            }
        });
    }

    public RealmResults<User> loadAllAsync() {
       /* return mRealm.where(User.class).findAllSortedAsync("id");*/
        return mRealm.where(User.class).findAll();
    }

    public RealmObject loadBy(long id) {
        return mRealm.where(User.class).equalTo("id", id).findFirst();
    }



}
