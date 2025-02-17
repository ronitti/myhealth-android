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
        values.put("id", patient.getId());
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
            user = new User();
            user.setId(id);
            user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
        }
        return user;
    }

    public boolean isStored(int id_patient) {
        String query = "SELECT * FROM " + DbHelper.TABLE_PATIENT + " WHERE id= ?";
        String args[] = {String.valueOf(id_patient)};
        Cursor cursor = gw.getDb().rawQuery(query, args);
        if(cursor != null && cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    public boolean isStoredEmail(String email) {
        String query = "SELECT * FROM " + DbHelper.TABLE_PATIENT + " WHERE email= ?";
        String args[] = {email};
        Cursor cursor = gw.getDb().rawQuery(query, args);
        if(cursor != null && cursor.getCount() > 0) {
            return true;
        }
        return false;
    }


    public Patient getPatientById(int id) {
        Patient patient = null;
        String query = "SELECT * FROM " + DbHelper.TABLE_PATIENT + " WHERE id= ?";
        String args[] = {String.valueOf(id)};
        Cursor cursor = gw.getDb().rawQuery(query, args);
        if(cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            patient = new Patient();
            patient.setId(id);
            patient.setEmail( cursor.getString(cursor.getColumnIndex("email")));
            patient.setName( cursor.getString(cursor.getColumnIndex("name")));
            patient.setSusNumber(cursor.getString(cursor.getColumnIndex("susNumber")));
            patient.setPassword(cursor.getString(cursor.getColumnIndex("password")));
        }
        return patient;
    }

    public List<Patient> patientList() {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM " + DbHelper.TABLE_PATIENT;
        Cursor cursor = gw.getDb().rawQuery(query, null);
        if(cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Patient patient = new Patient();
                patient.setId(cursor.getInt(cursor.getColumnIndex("id")));
                patient.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                patient.setName(cursor.getString(cursor.getColumnIndex("name")));
                patient.setSusNumber(cursor.getString(cursor.getColumnIndex("susNumber")));
                patient.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                patients.add(patient);
            }
        }
        return patients;
    }


    public void close() {
        gw.getDb().close();
    }


}
