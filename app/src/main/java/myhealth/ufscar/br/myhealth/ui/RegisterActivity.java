package myhealth.ufscar.br.myhealth.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.util.Pair;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.SectionData;
import myhealth.ufscar.br.myhealth.data.NCD;
import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.data.PatientMonitoring;
import myhealth.ufscar.br.myhealth.data.collect.frequency.Frequency;
import myhealth.ufscar.br.myhealth.tasks.FrequencyRegisterTask;
import myhealth.ufscar.br.myhealth.tasks.PatientRegisterTask;
import myhealth.ufscar.br.myhealth.tasks.UserRegisterTask;

public class RegisterActivity extends AppCompatActivity implements
        UserRegisterTask.OnTaskInteraction, PatientRegisterTask.OnTaskInteraction,
        FrequencyRegisterTask.OnTaskInteraction{

    private ViewPager mPager;
    private PagerAdapter pagerAdapter;

    private Button btnNext;
    private Button btnBack;
    private TextView lblStepTitle;

    private LinkedList<SignUpStep> registerSteps;
    private boolean[] activatedSteps;
    private Integer currentStepIndex;
    private SignUpStep currentStep;

    private static final int [] STEPS_ID = {
      R.id.step0,
      R.id.step1,
      R.id.step2,
      R.id.step3,
      R.id.step4
    };

    private SignUpStep nextStep(){
        while(currentStepIndex < registerSteps.size()-1 && !activatedSteps[++currentStepIndex]);
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

    public SignUpStep getCurrentStep(){
        return currentStep;
    }

    private Frequency frequency;

    public Frequency getFrequency() {
        return frequency;
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
        SectionData.PATIENT_MONITORING.setPatient(SectionData.PATIENT);

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

        frequency = new Frequency();
        mPager.setAdapter(pagerAdapter);
        lblStepTitle.setText(getString(SignUpStep.values()[0].getStepTitle()));
        setBtnNext(false);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentStep == SignUpStep.ACCESS_DATA){

                    new UserRegisterTask(RegisterActivity .this).execute(SectionData.PATIENT.getEmail(), SectionData.PATIENT.getPassword());
                }else if(currentStep == SignUpStep.ADDRESS){

                    new PatientRegisterTask(RegisterActivity.this).execute(SectionData.PATIENT);
                }else if(currentStep == SignUpStep.SETTINGS_SUCCESS) {
                    //Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    //startActivity(intent);
                    Toast.makeText(getBaseContext(), getResources().getText(R.string.msg_register_sucessfull), Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    if (currentStep == SignUpStep.MONITORING_OBESITY_SETTINGS) {
                        SectionData.PATIENT_MONITORING.getNcdFrequency().set(NCD.OBESITY.getId(),new Pair<>(NCD.OBESITY, frequency));
                        frequency = new Frequency();
                    }else if (currentStep == SignUpStep.MONITORING_HYPERTENSION_SETTINGS) {
                        SectionData.PATIENT_MONITORING.getNcdFrequency().set(NCD.HYPERTENSION.getId(),new Pair<>(NCD.HYPERTENSION, frequency));
                        frequency = new Frequency();
                    }else if(currentStep == SignUpStep.MONITORING_CORONARY_SETTINGS){
                        SectionData.PATIENT_MONITORING.getNcdFrequency().set(NCD.CORONARY_ARTERY_DISEASE.getId(),new Pair<>(NCD.CORONARY_ARTERY_DISEASE, frequency));
                        frequency = new Frequency();
                    }else if (currentStep == SignUpStep.MONITORING_DIABETES_SETTINGS) {
                        SectionData.PATIENT_MONITORING.getNcdFrequency().set(NCD.DIABETES.getId(),new Pair<>(NCD.DIABETES, frequency));
                        frequency = new Frequency();
                    }
                    SignUpStep step = nextStep();
                    if(step == SignUpStep.SETTINGS_SUCCESS){
                        new FrequencyRegisterTask(RegisterActivity.this).execute(SectionData.PATIENT_MONITORING);
                    }else {
                        mPager.setCurrentItem(step.step);
                        lblStepTitle.setText(getString(step.getStepTitle()));
                    }
                }
                setStepView(currentStep.step);
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
                setStepView(currentStep.step);
            }
        });
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
            nextStep();
            mPager.setCurrentItem(currentStep.step);
            lblStepTitle.setText(getString(currentStep.getStepTitle()));
        }
    }

    public void setStepView(int step) {
        TextView step_view;

        for (int i = 0; i < STEPS_ID.length; i++) {
            step_view = findViewById(STEPS_ID[i]);
            step_view.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_unselected));
            step_view.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.darkGray));
        }
        if (step >= STEPS_ID.length) {
            step = STEPS_ID.length-1;
        }
        step_view = findViewById(STEPS_ID[step]);
        step_view.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_selected));
        step_view.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));

    }

    public void setBtnNext (boolean status){
        this.btnNext.setEnabled(status);
        if(!status) {
            btnNext.setTextColor(getResources().getColor(R.color.gray));
        } else {
            btnNext.setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }


    private class RegisterPagerAdapter extends FragmentStatePagerAdapter {

        RegisterPagerAdapter(FragmentManager fm) {
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
