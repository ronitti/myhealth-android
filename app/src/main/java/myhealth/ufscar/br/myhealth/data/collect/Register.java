package myhealth.ufscar.br.myhealth.data.collect;

import java.io.Serializable;
import java.util.Date;

public class Register implements Serializable {
    private Integer id;
    private Date dateTime;
    private String observation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}
