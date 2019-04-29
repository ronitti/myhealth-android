package myhealth.ufscar.br.myhealth.ui;

import android.support.v4.app.Fragment;

import myhealth.ufscar.br.myhealth.ui.fragments.AccessDataFragment;
import myhealth.ufscar.br.myhealth.ui.fragments.AddressFragment;
import myhealth.ufscar.br.myhealth.ui.fragments.HealthDataFragment;
import myhealth.ufscar.br.myhealth.ui.fragments.MonitoringSettingsFragment;
import myhealth.ufscar.br.myhealth.ui.fragments.PersonalDataFragment;
import myhealth.ufscar.br.myhealth.ui.fragments.RegisterSuccessFragment;

public enum SignUpStep {
    ACCESS_DATA("Dados de Acesso",0, AccessDataFragment.class),
    PERSONAL_DATA("Dados Pessoas", 1, PersonalDataFragment.class),
    ADDRESS("Endereço", 2, AddressFragment.class),
    REGISTER_SUCCESS("Cadastro realizado com sucesso", 3, RegisterSuccessFragment.class),
    HEALTH_DATA("Informações de saúde", 4, HealthDataFragment.class),
    MONITORING_HYPERTENSION_SETTINGS("Monitoramento - Hipertensão",5, MonitoringSettingsFragment.class),
    MONITORING_CORONARY_SETTINGS("Monitoramento - Doença Coronariana",6, MonitoringSettingsFragment.class),
    MONITORING_DIABETES_SETTINGS("Monitoramento - Diabetes",7, MonitoringSettingsFragment.class),
    MONITORING_OBESITY_SETTINGS("Monitoramento - Obesidade",8, MonitoringSettingsFragment.class),
    SETTINGS_SUCCESS("Monitoramento configurado com sucesso", 9,RegisterSuccessFragment.class);

    String stepTitle;
    Integer step;
    Class<? extends Fragment> fragmentClass;

    SignUpStep(String stepTitle, Integer step, Class<? extends Fragment> fragmentClass) {
        this.stepTitle = stepTitle;
        this.step = step;
        this.fragmentClass = fragmentClass;
    }

    public String getStepTitle() {
        return stepTitle;
    }

    public Integer getStep() {
        return step;
    }

    public Class<? extends Fragment> getFragmentClass() {
        return fragmentClass;
    }
}
