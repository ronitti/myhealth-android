package myhealth.ufscar.br.myhealth.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.util.Pair;
import android.util.Log;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import myhealth.ufscar.br.myhealth.data.NCD;
import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.data.PatientMonitoring;
import myhealth.ufscar.br.myhealth.data.collect.frequency.Frequency;
import myhealth.ufscar.br.myhealth.data.collect.frequency.FrequencyType;

public class DCNTFrequencyDAO {

    private DBGateway gw;

    public DCNTFrequencyDAO(Context context) {
        this.gw = DBGateway.getInstance(context);
    }


    public boolean savePatientMonitoring(PatientMonitoring monitoring) {
        Log.i("DCNTFrequencyDAO", "savePatientMonitoring - " + monitoring.toString());
        boolean f_saved = false;
        for (Pair<NCD, Frequency> ncdmon: monitoring.getNcdFrequency()) {
            NCD type = ncdmon.first;
            Frequency frequency = ncdmon.second;
            Log.i("DCNTFrequencyDAO", "Monitoring type - " + type.getType());
            int id_type = (int) saveDCNT(monitoring.getPatient().getId(), type);
            if (id_type > 0) {
                int id_frequency = (int) saveFrequency(id_type, frequency);
                f_saved = true;
            }
        }
        return f_saved;
    }

    public PatientMonitoring getPatientMonitoring(Patient patient) {
        PatientMonitoring pm = null;
        Log.i("DCNTFrequencyDAO", "getPatientMonitoring - Patient:" + patient.getId());
        List<Pair<Integer, NCD>> ncds = getIdDCNTofPatient(patient.getId());
        if (ncds != null && ncds.size() > 0) {
            pm = new PatientMonitoring();
            pm.setPatient(patient);
            pm.setNcds(new boolean[] {false, false, false, false});
            for (Pair<Integer, NCD> incd: ncds) {
                Log.i("DCNTFrequencyDAO", "getPatientMonitoring - loading dcnt " + incd.second.getId());
                pm.getNcds()[incd.second.getId()] = true;
                List<Frequency> frequencies = listOfFrequencies(incd.first);
                if (frequencies != null && frequencies.size() > 0) {
                    Frequency frequency = frequencies.get(0);
                    pm.getNcdFrequency().set(incd.second.getId(), new Pair<NCD, Frequency>(incd.second, frequency));
                }
            }
            Log.i("DCNTFrequencyDAO", "getPatientMonitoring - loaded " + pm.toString());
        } else {
            Log.i("DCNTFrequencyDAO", "getPatientMonitoring - Not finded");
        }

        return pm;
    }




    public long saveDCNT(int id_patient, NCD ncd) {
        ContentValues values = new ContentValues();
        values.put("id_patient", id_patient);
        values.put("type_dcnt", ncd.getId());
        return  gw.getDb().insert(DbHelper.TABLE_DCNT_PATIENT, null, values);
    }

    public long saveFrequency(int id_dcnt, Frequency frequency) {
        String days_of_week = "";
        String start_date = "";
        String hours_of_date = "";

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        start_date = dateFormat.format(frequency.getStartDate());

        Boolean days[] = frequency.getDaysOfWeek();
        for (int i = 0; i < days.length; i++) {
            days_of_week += days[i] +"|";
        }

        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        Date hours[] = frequency.getHoursOfDay();
        for (int i = 0; i < hours.length; i++) {
            hours_of_date += hourFormat.format(hours[i]) + "|";
        }

        Log.i(getClass().getSimpleName(), "Days of week: " +days_of_week);
        Log.i(getClass().getSimpleName(), "Start date: " +start_date);
        Log.i(getClass().getSimpleName(), "hours of date: " +hours_of_date);

        ContentValues values = new ContentValues();
        values.put("id_dcnt", id_dcnt);
        values.put("frequency_type", frequency.getFrequencyType().getType());
        values.put("custon_every", frequency.getCustomEvery());
        values.put("times_day", frequency.getTimesADay());
        values.put("days_week", days_of_week);
        values.put("start_date", start_date);
        values.put("hours_of_date", hours_of_date);

        return gw.getDb().insert(DbHelper.TABLE_FREQUENCY, null, values);
    }

    public List<Pair<Integer, NCD>> getIdDCNTofPatient(int id_patient) {
        List<Pair<Integer, NCD>> ids = new ArrayList<>();
        String query = "SELECT id, type_dcnt FROM " + DbHelper.TABLE_DCNT_PATIENT + " WHERE id_patient = ?";
        String args[] = {String.valueOf(id_patient)};
        Cursor cursor = gw.getDb().rawQuery(query,args);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int type = cursor.getInt(cursor.getColumnIndex("type_dcnt"));
            NCD type_dcnt = null;
            if (type == NCD.HYPERTENSION.getId()) type_dcnt = NCD.HYPERTENSION;
            else if (type == NCD.CORONARY_ARTERY_DISEASE.getId()) type_dcnt = NCD.CORONARY_ARTERY_DISEASE;
            else if (type == NCD.DIABETES.getId()) type_dcnt = NCD.DIABETES;
            else if (type == NCD.OBESITY.getId()) type_dcnt = NCD.OBESITY;
            ids.add(new Pair<Integer, NCD>(id,type_dcnt));
        }
        return ids;
    }

    public List<Frequency> listOfFrequencies(int id_type) {
        List<Frequency> frequencies = frequencies = new ArrayList<>();
        String query = "SELECT * FROM " + DbHelper.TABLE_FREQUENCY + " WHERE id_dcnt = ?";
        String args[] = {String.valueOf(id_type)};
        Cursor cursor = gw.getDb().rawQuery(query, args);
        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {
                Frequency frequency = new Frequency();

                frequency.setFrequencyType(getFrequencyType(cursor.getInt(cursor.getColumnIndex("frequency_type"))));
                frequency.setDaysOfWeek(processDaysOfWeek(cursor.getString(cursor.getColumnIndex("days_week"))));
                frequency.setCustomEvery(cursor.getInt(cursor.getColumnIndex("custon_every")));
                frequency.setTimesADay(cursor.getInt(cursor.getColumnIndex("times_day")));
                String sStartDate = cursor.getString(cursor.getColumnIndex("start_date"));
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date startDate = null;
                try {
                    startDate = dateFormat.parse(sStartDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                frequency.setStartDate(startDate);

                frequency.setHoursOfDay(processHoursOfDay(cursor.getString(cursor.getColumnIndex("hours_of_date"))));
                frequencies.add(frequency);
            }

        }
        return frequencies;
    }

    private FrequencyType getFrequencyType(int type) {
        if (type == FrequencyType.DAILY.getType()) return FrequencyType.DAILY;
        else if (type == FrequencyType.WEEKLY.getType()) return FrequencyType.WEEKLY;
        else if (type == FrequencyType.CUSTOM_DAYS.getType()) return FrequencyType.CUSTOM_DAYS;
        else if (type == FrequencyType.CUSTOM_WEEKS.getType()) return FrequencyType.CUSTOM_WEEKS;
        else return null;
    }

    private Boolean[] processDaysOfWeek(String days) {
        Log.i("DCNTFrequencyDao", "String days: " + days);
        Boolean daysOfWeek[] = {false,false,false,false,false,false,false,false};
        String sdays[] = days.split(Pattern.quote("|"));
        for (int i = 0; i < daysOfWeek.length; i++) {

            daysOfWeek[i] = ((sdays[i]).equalsIgnoreCase("true") ? true : false );
            Log.i("DCNTFrequencyDao", "Day i: "+ i+ " Bool: " + daysOfWeek[i] + " sdays: " + sdays[i]);

        }
        return daysOfWeek;
    }

    private Date[] processHoursOfDay(String hours) {
        Log.i("DCNTFrequencyDao", "String hours: " + hours);
        String sHours[] = hours.split(Pattern.quote("|"));
        Log.i("DCNTFrequencyDao", "Size of sHours: " + sHours.length);
        Date hoursOfDay[] = new Date[sHours.length];
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        for (int i = 0; i < hoursOfDay.length; i++) {
            Date d = null;
            try {
                d = hourFormat.parse(sHours[i]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            hoursOfDay[i] = d;

        }
        return hoursOfDay;
    }

    public void close() {
        gw.getDb().close();
    }


}
