package myhealth.ufscar.br.myhealth.data.collect;

import java.io.Serializable;
import java.util.Date;

import myhealth.ufscar.br.myhealth.data.NCD;

public class Register implements Serializable {
    private Integer id;
    private Integer id_patient;
    private NCD ncd;

    public NCD getNcd() {
        return ncd;
    }

    public void setNcd(NCD ncd) {
        this.ncd = ncd;
    }

    private Date timestamp;
    private String observation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Integer getId_patient() {
        return id_patient;
    }

    public void setId_patient(Integer id_patient) {
        this.id_patient = id_patient;
    }

    @Override
    public String toString() {
        return "Register{" +
                "id=" + id +
                ", id_patient=" + id_patient +
                ", ncd=" + ncd +
                ", timestamp=" + timestamp +
                ", observation='" + observation + '\'' +
                '}';
    }
}
