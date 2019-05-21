package myhealth.ufscar.br.myhealth.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import myhealth.ufscar.br.myhealth.data.NCD;
import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.data.User;
import myhealth.ufscar.br.myhealth.data.collect.Cardiac;
import myhealth.ufscar.br.myhealth.data.collect.Glycemic;
import myhealth.ufscar.br.myhealth.data.collect.Obesity;
import myhealth.ufscar.br.myhealth.data.collect.Register;

public class PatientDAO {

    private DBGateway gw;

    public PatientDAO(Context context) {
        this.gw = DBGateway.getInstance(context);
    }


    public boolean save(Patient patient) {
        ContentValues values = new ContentValues();
        values.put("email", patient.getEmail());
        values.put("password", patient.getPassword());
        values.put("name", patient.getName());
        values.put("susNumber", patient.getSusNumber());
        return gw.getDb().insert(DbHelper.TABLE_PATIENT,null,values) > 0;
    }


    public User isLocalUser(String email, String password) {
        String query = "SELECT * FROM " + DbHelper.TABLE_PATIENT + " WHERE email = ? AND password = ?";
        String args[] = {email, password};
        Cursor cursor = gw.getDb().rawQuery(query, args);
        User user = null;
        if(cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            user.setId(id);
            user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
        }
        return user;
    }


}
