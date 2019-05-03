package myhealth.ufscar.br.myhealth;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Date;

import myhealth.ufscar.br.myhealth.data.Address;
import myhealth.ufscar.br.myhealth.data.NCD;
import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.data.PatientMonitoring;
import myhealth.ufscar.br.myhealth.data.collect.Cardiac;
import myhealth.ufscar.br.myhealth.data.collect.Register;
import myhealth.ufscar.br.myhealth.usecases.CollectData;
import myhealth.ufscar.br.myhealth.usecases.FrequencyRegister;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NCDTest {
    /**
     * RF4 - Registrar tipo DCNT
     */
    @Test
    public void t12_shouldNotEmptyHealthSettings(){
        System.out.println("Running test: should not register empty health settings");

        Patient patient = new Patient("ivopmendez@gmail.com", "698dc19d489c4e4db73e28a713eab07b");
        patient.setSusNumber("123 1234 1234 1234");
        patient.setName("Ivo Mendez");
        patient.setDateOfBirth("03/03/1997");
        patient.setGender('M');
        patient.setMothersName("Silvane Mendes");
        patient.setPlaceOfBirth("Belém/PA");

        Address address = new Address();
        address.setPostcode("13566-620");
        address.setThoroughfare("Rua Peru");
        address.setNumber(48);
        address.setNeighborhood("Vila Brasília");
        address.setCity("São Carlos");
        address.setState("SP");

        patient.setAddress(address);

        PatientMonitoring patientMonitoring = new PatientMonitoring();
        patientMonitoring.setPatient(patient);

        assertFalse(FrequencyRegister.execute(patientMonitoring));
    }

    @Test
    public void t13_shouldRegisterNCDProperly(){
        System.out.println("Running test: should register ncd properly");

        Patient patient = new Patient("ivopmendez@gmail.com", "698dc19d489c4e4db73e28a713eab07b");
        patient.setSusNumber("123 1234 1234 1234");
        patient.setName("Ivo Mendez");
        patient.setDateOfBirth("03/03/1997");
        patient.setGender('M');
        patient.setMothersName("Silvane Mendes");
        patient.setPlaceOfBirth("Belém/PA");

        Address address = new Address();
        address.setPostcode("13566-620");
        address.setThoroughfare("Rua Peru");
        address.setNumber(48);
        address.setNeighborhood("Vila Brasília");
        address.setCity("São Carlos");
        address.setState("SP");

        patient.setAddress(address);

        PatientMonitoring patientMonitoring = new PatientMonitoring();
        patientMonitoring.setPatient(patient);
        patientMonitoring.setNcds(new boolean[]{true, false, false, false});
        assert patientMonitoring.getNcdFrequency().get(0).second != null;
        patientMonitoring.getNcdFrequency().get(0).second.setHoursOfDay(new Date[]{new Date()});

        assertTrue(FrequencyRegister.execute(patientMonitoring));
    }

    /**
     * RF5 - Definir periodicidade de coleta de dados
     */
    @Test
    public void t14_shouldNotRegisterBadlyFormattedSettings(){
        System.out.println("Running test: should not register badly formatted settings");

        Patient patient = new Patient("ivopmendez@gmail.com", "698dc19d489c4e4db73e28a713eab07b");
        patient.setSusNumber("123 1234 1234 1234");
        patient.setName("Ivo Mendez");
        patient.setDateOfBirth("03/03/1997");
        patient.setGender('M');
        patient.setMothersName("Silvane Mendes");
        patient.setPlaceOfBirth("Belém/PA");

        Address address = new Address();
        address.setPostcode("13566-620");
        address.setThoroughfare("Rua Peru");
        address.setNumber(48);
        address.setNeighborhood("Vila Brasília");
        address.setCity("São Carlos");
        address.setState("SP");

        patient.setAddress(address);

        PatientMonitoring patientMonitoring = new PatientMonitoring();
        patientMonitoring.setPatient(patient);
        patientMonitoring.setNcds(new boolean[]{true, false, false, false});
        assert patientMonitoring.getNcdFrequency().get(0).second != null;

        assertFalse(FrequencyRegister.execute(patientMonitoring));
    }
    @Test
    public void t15_shoulRegisterMonitorSettingsProperly(){
        System.out.println("Running test: sohuld register monitor settings properly");
        Patient patient = new Patient("ivopmendez@gmail.com", "698dc19d489c4e4db73e28a713eab07b");
        patient.setSusNumber("123 1234 1234 1234");
        patient.setName("Ivo Mendez");
        patient.setDateOfBirth("03/03/1997");
        patient.setGender('M');
        patient.setMothersName("Silvane Mendes");
        patient.setPlaceOfBirth("Belém/PA");

        Address address = new Address();
        address.setPostcode("13566-620");
        address.setThoroughfare("Rua Peru");
        address.setNumber(48);
        address.setNeighborhood("Vila Brasília");
        address.setCity("São Carlos");
        address.setState("SP");

        patient.setAddress(address);

        PatientMonitoring patientMonitoring = new PatientMonitoring();
        patientMonitoring.setPatient(patient);
        patientMonitoring.setNcds(new boolean[]{true, false, false, false});
        assert patientMonitoring.getNcdFrequency().get(0).second != null;
        patientMonitoring.getNcdFrequency().get(0).second.setHoursOfDay(new Date[]{new Date()});

        assertTrue(FrequencyRegister.execute(patientMonitoring));
    }

    /**
     * RF7 - Coletar dados periódicos do paciente
     */
    @Test
    public void t16_shouldNotCollectDataWithEmptyFields(){
        System.out.println("Running test: should not collect data with empty fields");

        Patient patient = new Patient("ivopmendez@gmail.com", "698dc19d489c4e4db73e28a713eab07b");
        patient.setSusNumber("123 1234 1234 1234");
        patient.setName("Ivo Mendez");
        patient.setDateOfBirth("03/03/1997");
        patient.setGender('M');
        patient.setMothersName("Silvane Mendes");
        patient.setPlaceOfBirth("Belém/PA");

        Address address = new Address();
        address.setPostcode("13566-620");
        address.setThoroughfare("Rua Peru");
        address.setNumber(48);
        address.setNeighborhood("Vila Brasília");
        address.setCity("São Carlos");
        address.setState("SP");

        patient.setAddress(address);

        Register register = new Cardiac();
        assertFalse(CollectData.execute(patient,register));
    }

    @Test
    public void t17_shouldCollectDataProperly(){
        System.out.println("Running test: should collect data properly");

        Patient patient = new Patient("ivopmendez@gmail.com", "698dc19d489c4e4db73e28a713eab07b");
        patient.setSusNumber("123 1234 1234 1234");
        patient.setName("Ivo Mendez");
        patient.setDateOfBirth("03/03/1997");
        patient.setGender('M');
        patient.setMothersName("Silvane Mendes");
        patient.setPlaceOfBirth("Belém/PA");

        Address address = new Address();
        address.setPostcode("13566-620");
        address.setThoroughfare("Rua Peru");
        address.setNumber(48);
        address.setNeighborhood("Vila Brasília");
        address.setCity("São Carlos");
        address.setState("SP");

        patient.setAddress(address);

        Cardiac register = new Cardiac();
        register.setTimestamp(new Date());
        register.setNcd(NCD.HYPERTENSION);
        register.setDiastolic(12);
        register.setSystolic(12);
        register.setHeartBeats(90);
        register.setWeight(190.0f);

        assertTrue(CollectData.execute(patient, register));
    }
}
