package myhealth.ufscar.br.myhealth.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Pair;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Locale;

import myhealth.ufscar.br.myhealth.data.NCD;
import myhealth.ufscar.br.myhealth.data.collect.Cardiac;
import myhealth.ufscar.br.myhealth.data.collect.Glycemic;
import myhealth.ufscar.br.myhealth.data.collect.Obesity;
import myhealth.ufscar.br.myhealth.data.collect.Register;

public class RegisterDAO {

    private final String TABLE_REGISTER = "Register";
    private DBGateway gw;

    public RegisterDAO(Context context) {
        this.gw = DBGateway.getInstance(context);
    }


    public boolean save(Register register) {

        ContentValues values = new ContentValues();

        values.put("id_patient", register.getId_patient().toString());
        values.put("datetime", register.getTimestamp().toString());
        values.put("observation", register.getObservation());
        values.put("sync", 0);

        if (register instanceof Cardiac) {
            Cardiac c = (Cardiac) register;
            values.put("systolic", c.getSystolic());
            values.put("diastolic", c.getDiastolic());
            values.put("weight", c.getWeight());
            values.put("heart_beats", c.getHeartBeats());
            values.put("dcnt_type", NCD.CORONARY_ARTERY_DISEASE.getId());
        } else if( register instanceof Glycemic) {
            Glycemic g = (Glycemic) register;
            values.put("glycemic_rate", g.getGlycemicRate());
            values.put("dcnt_type", NCD.DIABETES.getId());
        } else if (register instanceof Obesity) {
            Obesity o = (Obesity) register;
            values.put("weight", o.getWeight());
            values.put("dcnt_type", NCD.OBESITY.getId());
        }

        return gw.getDb().insert(TABLE_REGISTER,null,values) > 0;
    }

    public boolean saveInLocal(Register register) {

        ContentValues values = new ContentValues();

        values.put("id_patient", register.getId_patient().toString());
        values.put("datetime", register.getTimestamp().toString());
        values.put("observation", register.getObservation());
        values.put("sync", 1);

        if (register instanceof Cardiac) {
            Cardiac c = (Cardiac) register;
            values.put("systolic", c.getSystolic());
            values.put("diastolic", c.getDiastolic());
            values.put("weight", c.getWeight());
            values.put("heart_beats", c.getHeartBeats());
            values.put("dcnt_type", NCD.CORONARY_ARTERY_DISEASE.getId());
        } else if( register instanceof Glycemic) {
            Glycemic g = (Glycemic) register;
            values.put("glycemic_rate", g.getGlycemicRate());
            values.put("dcnt_type", NCD.DIABETES.getId());
        } else if (register instanceof Obesity) {
            Obesity o = (Obesity) register;
            values.put("weight", o.getWeight());
            values.put("dcnt_type", NCD.OBESITY.getId());
        }

        return gw.getDb().insert(TABLE_REGISTER,null,values) > 0;
    }


    public List<Pair<Integer, Register>> listRegisters(int patient) {
        List<Pair<Integer, Register>> list = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_REGISTER + " WHERE id_patient = ?";
        String args[] = {String.valueOf(patient)};
        Cursor cursor = gw.getDb().rawQuery( query, args);
        while (cursor.moveToNext()) {
            Register register = null;

            int type = cursor.getInt(cursor.getColumnIndex("dcnt_type"));
            int status = cursor.getInt(cursor.getColumnIndex("sync"));

            if (type == NCD.CORONARY_ARTERY_DISEASE.getId() || type == NCD.HYPERTENSION.getId()) {
                register = new Cardiac();
                ((Cardiac) register).setSystolic( cursor.getInt(cursor.getColumnIndex("systolic")));
                ((Cardiac) register).setDiastolic( cursor.getInt(cursor.getColumnIndex("diastolic")));
                ((Cardiac) register).setHeartBeats( cursor.getInt(cursor.getColumnIndex("heart_beats")));
                ((Cardiac) register).setWeight( cursor.getFloat( cursor.getColumnIndex("weight")));

            } else if (type == NCD.DIABETES.getId()) {
                register = new Glycemic();
                ((Glycemic) register).setGlycemicRate(cursor.getInt(cursor.getColumnIndex("glycemic_rate")));
            } else if (type == NCD.OBESITY.getId()) {
                register = new Obesity();
                ((Obesity) register).setWeight(cursor.getFloat(cursor.getColumnIndex("weight")));
                ((Obesity) register).setBodyfat(cursor.getFloat(cursor.getColumnIndex("bodyfat")));
            }

            if (register != null) {
                register.setId(cursor.getInt(cursor.getColumnIndex("id")));
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

                try {
                    String sdate = cursor.getString(cursor.getColumnIndex("datetime"));
                    Date date = sdf.parse(sdate);
                    register.setTimestamp(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                register.setObservation(cursor.getString(cursor.getColumnIndex("observation")));
                register.setId_patient(cursor.getInt(cursor.getColumnIndex("id_patient")));
                list.add(new Pair<Integer, Register>(status,register));
            }
        }
        return list;
    }


    public List<Register> registerToSync() {
        List<Register> registers = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_REGISTER + " WHERE sync = ?";
        String args[] = {String.valueOf(0)};
        Cursor cursor = gw.getDb().rawQuery( query, args);
        while (cursor.moveToNext()) {
            Register register = null;

            int type = cursor.getInt(cursor.getColumnIndex("dcnt_type"));

            if (type == NCD.CORONARY_ARTERY_DISEASE.getId() || type == NCD.HYPERTENSION.getId()) {
                register = new Cardiac();
                register.setNcd(NCD.HYPERTENSION);
                ((Cardiac) register).setSystolic( cursor.getInt(cursor.getColumnIndex("systolic")));
                ((Cardiac) register).setDiastolic( cursor.getInt(cursor.getColumnIndex("diastolic")));
                ((Cardiac) register).setHeartBeats( cursor.getInt(cursor.getColumnIndex("heart_beats")));
                ((Cardiac) register).setWeight( cursor.getFloat( cursor.getColumnIndex("weight")));

            } else if (type == NCD.DIABETES.getId()) {
                register.setNcd(NCD.DIABETES);
                register = new Glycemic();
                ((Glycemic) register).setGlycemicRate(cursor.getInt(cursor.getColumnIndex("glycemic_rate")));
            } else if (type == NCD.OBESITY.getId()) {
                register.setNcd(NCD.OBESITY);
                register = new Obesity();
                ((Obesity) register).setWeight(cursor.getFloat(cursor.getColumnIndex("weight")));
                ((Obesity) register).setBodyfat(cursor.getFloat(cursor.getColumnIndex("bodyfat")));
            }

            if (register != null) {
                register.setId(cursor.getInt(cursor.getColumnIndex("id")));
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

                try {
                    String sdate = cursor.getString(cursor.getColumnIndex("datetime"));
                    Date date = sdf.parse(sdate);
                    register.setTimestamp(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                register.setObservation(cursor.getString(cursor.getColumnIndex("observation")));
                register.setId_patient(cursor.getInt(cursor.getColumnIndex("id_patient")));

                registers.add(register);
            }
        }
        return registers;
    }


    public boolean setRegisterSync(int id_register, int id_patient, int status) {
        String args[] = {String.valueOf(id_register), String.valueOf(id_patient)};
        ContentValues cv = new ContentValues();
        cv.put("sync", status);
        int result = gw.getDb().update(DbHelper.TABLE_REGISTER, cv, "id = ? AND id_patient =?", args);
        return (result > 0);
    }



}
