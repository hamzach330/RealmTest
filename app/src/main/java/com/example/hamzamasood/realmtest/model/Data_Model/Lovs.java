package com.example.hamzamasood.realmtest.model.Data_Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Lovs extends RealmObject {

    @PrimaryKey
    String Lov_Id = "";
    String Lov_Name = "";
    String Lov_Value = "";
    String Lov_Filter = "";


    public String getLov_Id() {
        return Lov_Id;
    }

    public void setLov_Id(String lov_Id) {
        Lov_Id = lov_Id;
    }

    public String getLov_Name() {
        return Lov_Name;
    }

    public void setLov_Name(String lov_Name) {
        Lov_Name = lov_Name;
    }

    public String getLov_Value() {
        return Lov_Value;
    }

    public void setLov_Value(String lov_Value) {
        Lov_Value = lov_Value;
    }

    public String getLov_Filter() {
        return Lov_Filter;
    }

    public void setLov_Filter(String lov_Filter) {
        Lov_Filter = lov_Filter;
    }
}
