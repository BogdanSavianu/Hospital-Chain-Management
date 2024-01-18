package Model;

import java.util.Date;

public class Angajat {
    private int idAngajat;
    private int idUtilizator;
    private double salariuNegociat;
    private int oreRealizate;
    private int oreContract;
    private String tipFunctie;
    private Date dataAngajare;

    public int getIdAngajat() {
        return idAngajat;
    }

    public void setIdAngajat(int idAngajat) {
        this.idAngajat = idAngajat;
    }

    public int getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(int idUtilizator) {
        this.idUtilizator = idUtilizator;
    }

    public double getSalariuNegociat() {
        return salariuNegociat;
    }

    public void setSalariuNegociat(double salariuNegociat) {
        this.salariuNegociat = salariuNegociat;
    }

    public int getOreRealizate() {
        return oreRealizate;
    }

    public void setOreRealizate(int oreRealizate) {
        this.oreRealizate = oreRealizate;
    }

    public String getTipFunctie() {
        return tipFunctie;
    }

    public void setTipFunctie(String tipFunctie) {
        this.tipFunctie = tipFunctie;
    }

    public Date getDataInceput() {
        return dataAngajare;
    }


    public int getOreContract() {
        return oreContract;
    }

    public void setOreContract(int oreContract) {
        this.oreContract = oreContract;
    }

    public Date getDataAngajare() {
        return dataAngajare;
    }

    public void setDataAngajare(Date dataAngajare) {
        this.dataAngajare = dataAngajare;
    }

    public Angajat(int idAngajat, int idUtilizator, double salariuNegociat, int oreRealizate, int oreContract, String tipFunctie, Date dataAngajare) {
        this.idAngajat = idAngajat;
        this.idUtilizator = idUtilizator;
        this.salariuNegociat = salariuNegociat;
        this.oreRealizate = oreRealizate;
        this.tipFunctie = tipFunctie;
        this.dataAngajare = dataAngajare;
        this.oreContract = oreContract;
    }

    public Angajat() {
    }
}

