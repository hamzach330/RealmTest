package com.example.hamzamasood.realmtest.model;

import com.example.hamzamasood.realmtest.model.Data_Access_Objects.LovsDao;
import com.example.hamzamasood.realmtest.model.Data_Access_Objects.UserDao;
import com.example.hamzamasood.realmtest.model.Data_Model.Lovs;
import com.example.hamzamasood.realmtest.model.Data_Model.User;

import io.realm.Realm;

public class RealmDatabase_Manager {
    private static Realm mRealm;

    public static Realm open() {
        mRealm = Realm.getDefaultInstance();
        return mRealm;
    }

    public static void close() {
        if (mRealm != null) {
            mRealm.close();
        }
    }

    public static UserDao createUserDao() {
        checkForOpenRealm();
        return new UserDao(mRealm);
    }

    public static LovsDao createLovsDao() {
        checkForOpenRealm();
        return new LovsDao(mRealm);
    }

    public static void clear() {
        checkForOpenRealm();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(User.class);
                realm.delete(Lovs.class);
                //clear rest of your dao classes
            }
        });
    }




    private static void checkForOpenRealm() {
        if (mRealm == null || mRealm.isClosed()) {
            throw new IllegalStateException("RealmManager: Realm is closed, call open() method first");
        }
    }
}
