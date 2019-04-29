package myhealth.ufscar.br.myhealth.ui;

import android.support.v4.app.Fragment;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.ui.fragments.AccessDataFragment;
import myhealth.ufscar.br.myhealth.ui.fragments.AddressFragment;
import myhealth.ufscar.br.myhealth.ui.fragments.HealthDataFragment;
import myhealth.ufscar.br.myhealth.ui.fragments.MonitoringSettingsFragment;
import myhealth.ufscar.br.myhealth.ui.fragments.PersonalDataFragment;
import myhealth.ufscar.br.myhealth.ui.fragments.RegisterSuccessFragment;

public enum SignUpStep {
    ACCESS_DATA(R.string.register_lbl_access_data,0, AccessDataFragment.class),
    PERSONAL_DATA(R.string.register_lbl_personal_data, 1, PersonalDataFragment.class),
    ADDRESS(R.string.register_lbl_address, 2, AddressFragment.class),
    REGISTER_SUCCESS(R.string.register_lbl_register_success, 3, RegisterSuccessFragment.class),
    HEALTH_DATA(R.string.register_lbl_health_info, 4, HealthDataFragment.class),
    MONITORING_HYPERTENSION_SETTINGS(R.string.register_lbl_monitoring_hypertension,5, MonitoringSettingsFragment.class),
    MONITORING_CORONARY_SETTINGS(R.string.register_lbl_monitoring_coronary,6, MonitoringSettingsFragment.class),
    MONITORING_DIABETES_SETTINGS(R.string.register_lbl_monitoring_diabetes,7, MonitoringSettingsFragment.class),
    MONITORING_OBESITY_SETTINGS(R.string.register_lbl_monitoring_obesity,8, MonitoringSettingsFragment.class),
    SETTINGS_SUCCESS(R.string.register_lbl_settings_success, 9,RegisterSuccessFragment.class);

    Integer stepTitle;
    Integer step;
    Class<? extends Fragment> fragmentClass;

    SignUpStep(Integer stepTitle, Integer step, Class<? extends Fragment> fragmentClass) {
        this.stepTitle = stepTitle;
        this.step = step;
        this.fragmentClass = fragmentClass;
    }

    public Integer getStepTitle() {
        return stepTitle;
    }

    public Integer getStep() {
        return step;
    }

    public Class<? extends Fragment> getFragmentClass() {
        return fragmentClass;
    }
}
