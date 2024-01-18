package Model;

import java.util.Date;

public class Concedii {
    private int idConcediu;
    private int idAngajat;
    private Date dataInceput;
    private Date dataSfarsit;

    public int getIdConcediu() {
        return idConcediu;
    }

    public void setIdConcediu(int idConcediu) {
        this.idConcediu = idConcediu;
    }

    public int getIdAngajat() {
        return idAngajat;
    }

    public void setIdAngajat(int idAngajat) {
        this.idAngajat = idAngajat;
    }

    public Date getDataInceput() {
        return dataInceput;
    }

    public void setDataInceput(Date dataInceput) {
        this.dataInceput = dataInceput;
    }

    public Date getDataSfarsit() {
        return dataSfarsit;
    }

    public void setDataSfarsit(Date dataSfarsit) {
        this.dataSfarsit = dataSfarsit;
    }

    public Concedii(int idConcediu, int idAngajat, Date dataInceput, Date dataSfarsit) {
        this.idConcediu = idConcediu;
        this.idAngajat = idAngajat;
        this.dataInceput = dataInceput;
        this.dataSfarsit = dataSfarsit;
    }

    public Concedii() {
    }
}

