package myhealth.ufscar.br.myhealth.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.data.NCD;
import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.data.PatientMonitoring;
import myhealth.ufscar.br.myhealth.data.collect.frequency.DayOfWeek;
import myhealth.ufscar.br.myhealth.data.collect.frequency.Frequency;
import myhealth.ufscar.br.myhealth.database.DCNTFrequencyDAO;
import myhealth.ufscar.br.myhealth.database.PatientDAO;
import myhealth.ufscar.br.myhealth.ui.LoginActivity;

public class PatientMonitoringIntentService extends IntentService {

    public static final String CHANNEL_ID = "MYHEALTH_ID";

    public PatientMonitoringIntentService() {
        super("PatientMonitoringIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startForeground(3,new Notification());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("PatientMonitoringIS", "onHandleIntent - PatientMonitoringIntentService started");
        PatientDAO pDAO = new PatientDAO(this);
        DCNTFrequencyDAO fDAO = new DCNTFrequencyDAO(this);

        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");

        List<Patient> patients = pDAO.patientList();

        for (Patient patient: patients) {
            PatientMonitoring pm = fDAO.getPatientMonitoring(patient);
            for (int i = 0; i < pm.getNcds().length; i++) {
                if (pm.getNcds()[i]) {
                    NCD ncd = pm.getNcdFrequency().get(i).first;
                    Frequency frequency = pm.getNcdFrequency().get(i).second;
                    Boolean days[] = frequency.getDaysOfWeek();
                    Date hours[] = frequency.getHoursOfDay();
                    if (days[DayOfWeek.EVERYDAY.getDay()] == true) {
                        for (int j = 0; j < hours.length ; j++) {

                            Date hourAlert = hours[j];

                            /*
                            Date hourAlert = null;
                            try {
                                hourAlert = hourFormat.parse("18:47");
                            } catch (ParseException e) {
                                e.printStackTrace();
                            } */


                            if(hourFormat.format(hourAlert).equals(hourFormat.format(new Date()))) {
                                showAlertMessage(patient, ncd, hourFormat.format(hourAlert));
                            }
                        }

                    } else {
                        // customizado
                        for (int j = 1; j < days.length ; j++) {
                            if (days[j]) {
                                Date today = new Date();
                                Calendar c = Calendar.getInstance();
                                c.setTime(today);
                                int week = c.get(Calendar.DAY_OF_WEEK);
                                if (j == week) {
                                    for (int k = 0; k < hours.length ; k++) {
                                        Date hourAlert = hours[k];
                                        if(hourFormat.format(hourAlert).equals(hourFormat.format(new Date()))) {
                                            showAlertMessage(patient, ncd, hourFormat.format(hourAlert));
                                        }
                                    }
                                }

                            }

                        }


                    }


                }
            }

        }

        //pDAO.close();
        //fDAO.close();

    }


    public void showAlertMessage(Patient p, NCD n, String hour) {
        int id = (int) (System.currentTimeMillis() / 1000);
        String text = "Uma coleta está agendada para " + p.getName() + " as " + hour + " horas";


        Intent go = new Intent(this, LoginActivity.class);
        go.setAction("action-go");
        PendingIntent goPI = PendingIntent.getActivity(this, 0, go, 0);

        NotificationCompat.Action goAction = new NotificationCompat.Action(R.drawable.ic_menu_settings, "Colletar", goPI);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_heartbeat);
        builder.setTicker("MyHealth");
        builder.setContentTitle("MyHealth - Coleta de dados");
        builder.setContentText(text);
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(text));
        builder.addAction(goAction);

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "MyChanel";
            String description = "Chanel of MyHealth";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            nm.createNotificationChannel(channel);
        }



        Notification notification = builder.build();


        nm.notify(id, notification);
    }



}
