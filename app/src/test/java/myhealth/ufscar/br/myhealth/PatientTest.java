package myhealth.ufscar.br.myhealth;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import myhealth.ufscar.br.myhealth.data.Address;
import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.exception.ExistingSusNumberException;
import myhealth.ufscar.br.myhealth.exception.NonRegisteredUserException;
import myhealth.ufscar.br.myhealth.usecases.PatientLoad;
import myhealth.ufscar.br.myhealth.usecases.PatientRegister;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PatientTest {

    @Test
    public void t08_shouldRegisterPatientProperly(){
        System.out.println("Running test: should register patient properly");

        Patient patient = new Patient("teste@teste.com", "teste");
        patient.setSusNumber("0000");
        patient.setName("Novo");
        patient.setDateOfBirth("01/01/2001");
        patient.setGender('F');
        patient.setMothersName("Nova Mãe");
        patient.setPlaceOfBirth("Belém/PA");

        Address address = new Address();
        address.setPostcode("13566-620");
        address.setThoroughfare("Rua Peru");
        address.setNumber(48);
        address.setNeighborhood("Vila Brasília");
        address.setCity("São Carlos");
        address.setState("SP");

        patient.setAddress(address);

        assertTrue(PatientRegister.register(patient));
    }

    @Test (expected = ExistingSusNumberException.class)
    public void t09_shouldNotRegisterAnExistingSusNumber(){
        System.out.println("Running test: should not register an existing SUS number");

        Patient patient = new Patient("teste@teste.com", "teste");
        patient.setSusNumber("0000");
        patient.setName("Novo");
        patient.setDateOfBirth("01/01/2001");
        patient.setGender('F');
        patient.setMothersName("Nova Mãe");
        patient.setPlaceOfBirth("Belém/PA");

        Address address = new Address();
        address.setPostcode("13566-620");
        address.setThoroughfare("Rua Peru");
        address.setNumber(48);
        address.setNeighborhood("Vila Brasília");
        address.setCity("São Carlos");
        address.setState("SP");

        patient.setAddress(address);

        PatientRegister.register(patient);
    }

    @Test (expected = NonRegisteredUserException.class)
    public void t10_shouldNotLoadNonexistentPatient(){
        System.out.println("Running test: should not load nonexistent patient");
        PatientLoad.load("0001");
    }

    @Test
    public void t11_shouldLoadPatientSuccessfully(){
        System.out.println("Running test: should load patient successfully");
        assertNotNull(PatientLoad.load("0000"));
    }
}