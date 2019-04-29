package myhealth.ufscar.br.myhealth.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.SectionData;
import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.data.PatientMonitoring;
import myhealth.ufscar.br.myhealth.tasks.PatientRegisterTask;
import myhealth.ufscar.br.myhealth.tasks.UserRegisterTask;

public class RegisterActivity extends AppCompatActivity implements
        UserRegisterTask.OnTaskInteraction, PatientRegisterTask.OnTaskInteraction{

    private ViewPager mPager;
    private PagerAdapter pagerAdapter;

    private Button btnNext;
    private Button btnBack;
    private TextView lblStepTitle;

    private LinkedList<SignUpStep> registerSteps;
    private boolean[] activatedSteps;
    private Integer currentStepIndex;
    private SignUpStep currentStep;

    private SignUpStep nextStep(){
        while(currentStepIndex <= registerSteps.size() && !activatedSteps[++currentStepIndex]);
        currentStep = registerSteps.get(currentStepIndex);
        return currentStep;
    }
    private SignUpStep stepBack(){
        while(currentStepIndex > 0 && !activatedSteps[--currentStepIndex]) ;
        currentStep = registerSteps.get(currentStepIndex);
        return currentStep;
    }

    public boolean[] getActivatedSteps() {
        return activatedSteps;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerSteps = new LinkedList<>();
        activatedSteps = new boolean[]{true, true, true, true, true, false, false, false, false, true};

        registerSteps.add(SignUpStep.ACCESS_DATA);
        registerSteps.add(SignUpStep.PERSONAL_DATA);
        registerSteps.add(SignUpStep.ADDRESS);
        registerSteps.add(SignUpStep.REGISTER_SUCCESS);
        registerSteps.add(SignUpStep.HEALTH_DATA);
        registerSteps.add(SignUpStep.MONITORING_HYPERTENSION_SETTINGS);
        registerSteps.add(SignUpStep.MONITORING_CORONARY_SETTINGS);
        registerSteps.add(SignUpStep.MONITORING_DIABETES_SETTINGS);
        registerSteps.add(SignUpStep.MONITORING_OBESITY_SETTINGS);
        registerSteps.add(SignUpStep.SETTINGS_SUCCESS);

        currentStepIndex = 0;
        currentStep = registerSteps.get(currentStepIndex);

        SectionData.PATIENT = new Patient();
        SectionData.PATIENT_MONITORING = new PatientMonitoring();

        btnNext = findViewById(R.id.action_next);
        btnBack = findViewById(R.id.action_back);
        lblStepTitle = findViewById(R.id.lbl_step_title);

        mPager = findViewById(R.id.pager);
        mPager.setOffscreenPageLimit(1);
        mPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        pagerAdapter = new RegisterPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);
        lblStepTitle.setText(getString(SignUpStep.values()[0].getStepTitle()));

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentStep == SignUpStep.ACCESS_DATA){
                    new UserRegisterTask(RegisterActivity .this).execute(SectionData.PATIENT.getEmail(), SectionData.PATIENT.getPassword());
                }else if(currentStep == SignUpStep.ADDRESS){
                    new PatientRegisterTask(RegisterActivity.this).execute(SectionData.PATIENT);
                }else if(currentStep == SignUpStep.SETTINGS_SUCCESS) {
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    SignUpStep step = nextStep();
                    mPager.setCurrentItem(step.step);
                    lblStepTitle.setText(getString(step.getStepTitle()));
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPager.getCurrentItem() > 0){
                    SignUpStep step = stepBack();
                    mPager.setCurrentItem(step.step);
                    lblStepTitle.setText(getString(step.getStepTitle()));
                }
            }
        });
    }

    public ViewPager getmPager() {
        return mPager;
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        }
        if (mPager.getCurrentItem() > 0){
            SignUpStep step = stepBack();
            mPager.setCurrentItem(step.step);
            lblStepTitle.setText(getString(step.getStepTitle()));
        }
    }

    @Override
    public void onTaskFinsh(boolean result) {
        if(result) {
            mPager.setCurrentItem(mPager.getCurrentItem() + 1);
            lblStepTitle.setText(getString(nextStep().getStepTitle()));
        }
    }


    private class RegisterPagerAdapter extends FragmentStatePagerAdapter {

        public RegisterPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            try {
                return registerSteps.get(position).getFragmentClass().newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public int getCount() {
            return registerSteps.size();
        }
    }

}
