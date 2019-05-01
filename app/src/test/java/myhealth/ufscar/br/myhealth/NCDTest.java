package myhealth.ufscar.br.myhealth;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.data.PatientMonitoring;
import myhealth.ufscar.br.myhealth.data.collect.Register;
import myhealth.ufscar.br.myhealth.usecases.CollectData;
import myhealth.ufscar.br.myhealth.usecases.DefineFrequency;
import myhealth.ufscar.br.myhealth.usecases.NCDRegister;

import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NCDTest {
    /**
     * RF4 - Registrar tipo DCNT
     */
    @Test
    public void t12_shouldNotEmptyHealthSettings(){
        System.out.println("Running test: should not register empty health settings");
        assertTrue(NCDRegister.register(new PatientMonitoring()));
    }

    @Test
    public void t13_shouldRegisterNCDProperly(){
        System.out.println("Running test: should register ncd properly");
        assertTrue(NCDRegister.register(new PatientMonitoring()));
    }

    /**
     * RF5 - Definir periodicidade de coleta de dados
     */
    @Test
    public void t14_shouldNotRegisterBadlyFormattedSettings(){
        System.out.println("Running test: should not register badly formatted settings");
        assertTrue(DefineFrequency.execute(new PatientMonitoring()));
    }
    @Test
    public void t15_shoulRegisterMonitorSettingsProperly(){
        System.out.println("Running test: sohuld register monitor settings properly");
        assertTrue(DefineFrequency.execute(new PatientMonitoring()));
    }

    /**
     * RF7 - Coletar dados periódicos do paciente
     */
    @Test
    public void t16_shouldNotCollectDataWithEmptyFields(){
        System.out.println("Running test: should not collect data with empty fields");
        assertTrue(CollectData.execute(new Patient(), new Register()));
    }

    @Test
    public void t17_shouldCollectDataProperly(){
        System.out.println("Running test: should collect data properly");
        assertTrue(CollectData.execute(new Patient(), new Register()));
    }
}
