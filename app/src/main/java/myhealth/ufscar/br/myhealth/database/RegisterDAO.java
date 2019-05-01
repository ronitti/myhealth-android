package myhealth.ufscar.br.myhealth.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import myhealth.ufscar.br.myhealth.data.NCD;
import myhealth.ufscar.br.myhealth.data.collect.Cardiac;
import myhealth.ufscar.br.myhealth.data.collect.Glycemic;
import myhealth.ufscar.br.myhealth.data.collect.Obesity;
import myhealth.ufscar.br.myhealth.data.collect.Register;
import myhealth.ufscar.br.myhealth.usecases.NCDRegister;

public class RegisterDAO {

    private final String TABLE_REGISTER = "Register";
    private DBGateway gw;

    public RegisterDAO(Context context) {
        this.gw = DBGateway.getInstance(context);
    }


    public boolean save(Register register) {

        ContentValues values = new ContentValues();

        values.put("datetime", register.getDateTime().toString());
        values.put("observation", register.getObservation());

        if (register instanceof Cardiac) {
            Cardiac c = (Cardiac) register;
            values.put("systolic", c.getSystolicPressure());
            values.put("diastolic", c.getDiastolicPressure());
            values.put("weight", c.getWeight());
            values.put("heart_beats", c.getPulse());
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


    public List<Register> listRegisters() {
        List<Register> list = new ArrayList<>();
        Cursor cursor = gw.getDb().rawQuery("SELECT * FROM " + TABLE_REGISTER, null);
        while (cursor.moveToNext()) {
            Register r = null;

            int type = cursor.getInt(cursor.getColumnIndex("dcnt_type"));

            if (type == NCD.CORONARY_ARTERY_DISEASE.getId() || type == NCD.HYPERTENSION.getId()) {
                r = new Cardiac();
                ((Cardiac) r).setSystolicPressure( cursor.getFloat(cursor.getColumnIndex("systolic")));
                ((Cardiac) r).setDiastolicPressure( cursor.getFloat(cursor.getColumnIndex("diastolic")));
                ((Cardiac) r).setPulse( cursor.getInt(cursor.getColumnIndex("heart_beats")));
                ((Cardiac) r).setWeight( cursor.getFloat( cursor.getColumnIndex("weight")));

            } else if (type == NCD.DIABETES.getId()) {
                r = new Glycemic();
                ((Glycemic) r).setGlycemicRate(cursor.getInt(cursor.getColumnIndex("glycemic_rate")));
            } else if (type == NCD.OBESITY.getId()) {
                r = new Obesity();
                ((Obesity) r).setWeight(cursor.getFloat(cursor.getColumnIndex("weight")));
                ((Obesity) r).setFatRate(cursor.getFloat(cursor.getColumnIndex("bodyfat")));
            }

            if (r != null) {
                r.setId(cursor.getInt(cursor.getColumnIndex("id")));
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

                try {
                    String sdate = cursor.getString(cursor.getColumnIndex("datetime"));
                    Date date = sdf.parse(sdate);
                    r.setDateTime(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                r.setObservation(cursor.getString(cursor.getColumnIndex("observation")));
                list.add(r);
            }
        }
        return list;
    }



}
