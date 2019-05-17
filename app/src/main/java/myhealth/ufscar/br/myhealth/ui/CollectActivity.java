package myhealth.ufscar.br.myhealth.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.data.NCD;
import myhealth.ufscar.br.myhealth.ui.fragments.CollectCardiacFragment;
import myhealth.ufscar.br.myhealth.ui.fragments.CollectGlycemicFragment;
import myhealth.ufscar.br.myhealth.ui.fragments.CollectObesityFragment;
import myhealth.ufscar.br.myhealth.ui.fragments.CustomFragment;

public class CollectActivity extends AppCompatActivity {

    private int ncd_type = 1;
    CustomFragment fragment = null;

    Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.ncd_type = getIntent().getIntExtra("NCDTYPE", 9);


        if (ncd_type == NCD.HYPERTENSION.getId() || ncd_type == NCD.CORONARY_ARTERY_DISEASE.getId()) {
            fragment = new CollectCardiacFragment();
        } else  if (ncd_type == NCD.DIABETES.getId()){
            fragment = new CollectGlycemicFragment();
        } else  if (ncd_type == NCD.OBESITY.getId()) {
            fragment = new CollectObesityFragment();
        }

        replaceFragment(fragment);
        initComponents();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void initComponents() {
        btnSave = (Button) findViewById(R.id.collect_btn_save);
        btnSave.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.i("CollectActivity", "save pressed");
                fragment.save();
                finish();
            }
        });
    }




    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.collect_frame, fragment);
        ft.commit();
    }

}
