package myhealth.ufscar.br.myhealth.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.Calendar;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.SectionData;
import myhealth.ufscar.br.myhealth.receiver.RegisterBroadcastReceiver;
import myhealth.ufscar.br.myhealth.data.NCD;
import myhealth.ufscar.br.myhealth.service.RegisterIntentService;
import myhealth.ufscar.br.myhealth.ui.fragments.AccessCodeFragment;
import myhealth.ufscar.br.myhealth.ui.fragments.ListRegisterFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragment;

    private FloatingActionMenu fab_menu;
    private FloatingActionButton fabHypertension;
    private FloatingActionButton fabDiabetes;
    private FloatingActionButton fabObesity;

    private TextView txtNameOfPatient;
    private TextView txtEmailOfPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeComponents();
        fragment = new ListRegisterFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_content, fragment);
        fragmentTransaction.commit();


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_home && !(fragment instanceof ListRegisterFragment)) {
            fragment = new ListRegisterFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_content, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_access_code && !(fragment instanceof AccessCodeFragment)) {
            fragment = new AccessCodeFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_content, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_exit) {
            SectionData.PATIENT = null;
            SectionData.PATIENT_REGISTERS = null;
            SectionData.PATIENT_MONITORING = null;
            Intent intent = new Intent( this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void initializeComponents() {

        this.fab_menu = findViewById(R.id.fab);

        this.fabHypertension = findViewById(R.id.fab_item_hypertension);
        this.fabDiabetes = findViewById(R.id.fab_item_diabetes);
        this.fabObesity = findViewById(R.id.fab_item_obesity);



        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        this.txtNameOfPatient = headerView.findViewById(R.id.txt_name_patient);
        this.txtEmailOfPatient = headerView.findViewById(R.id.txt_email_patient);


        fabHypertension.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab_menu.close(true);
                launchCollect(NCD.HYPERTENSION);
            }
        });

        fabDiabetes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab_menu.close(true);
                launchCollect(NCD.DIABETES);
            }
        });

        fabObesity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab_menu.close(true);
                launchCollect(NCD.OBESITY);
            }
        });

        if(SectionData.PATIENT != null) {
            Log.i("MainActivity", SectionData.PATIENT.toString());
            this.txtNameOfPatient.setText(SectionData.PATIENT.getName());
            this.txtEmailOfPatient.setText(SectionData.PATIENT.getEmail());
        }

        stopAlarm();
        startAlarm();

    }


    public void launchCollect(NCD type) {
        Intent intent = new Intent(this, CollectActivity.class);
        intent.putExtra("NCDTYPE", type.getId() );
        startActivity(intent);
    }


    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int resultCode = intent.getIntExtra("resultCode", RESULT_CANCELED);
            if (resultCode == RESULT_OK) {
                if(fragment instanceof ListRegisterFragment)
                    ((ListRegisterFragment) fragment).updateList();
                Toast.makeText(MainActivity.this, getResources().getText(R.string.msg_synchronized), Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(RegisterIntentService.ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);

        if(fragment instanceof ListRegisterFragment)
            ((ListRegisterFragment) fragment).updateList();
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void startAlarm() {

        Intent intent = new Intent(this, RegisterBroadcastReceiver.class);

        boolean activeAlarme = (PendingIntent.getBroadcast(this, 0, intent,PendingIntent.FLAG_NO_CREATE ) == null);

        if (!activeAlarme) {
            Log.i("MaingActivity", "New Alarm");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());


            AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
            int interval = 60 * 1000;
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), interval, pendingIntent);
        } else {
            Log.i("MaingActivity", "Alarm is active");
        }
    }

    public void stopAlarm() {
        Intent intent = new Intent(this, RegisterBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        Log.i("MainActivity", "Alarm is cancelled");
    }

}
