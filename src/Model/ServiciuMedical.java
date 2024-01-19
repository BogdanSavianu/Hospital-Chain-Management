package Model;

public class ServiciuMedical {
    private int idServiciu;
    private String numeServiciu;
    private String specialitate;
    //private boolean necesitaCompetenta;
    private double pret;
    private int durataMinute;
    private int idMedic;

    public int getIdServiciu() {
        return idServiciu;
    }

    public void setIdServiciu(int idServiciu) {
        this.idServiciu = idServiciu;
    }

    public String getNumeServiciu() {
        return numeServiciu;
    }

    public void setNumeServiciu(String numeServiciu) {
        this.numeServiciu = numeServiciu;
    }

    public String getSpecialitate() {
        return specialitate;
    }

    public void setSpecialitate(String specialitate) {
        this.specialitate = specialitate;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public int getDurataMinute() {
        return durataMinute;
    }

    public void setDurataMinute(int durataMinute) {
        this.durataMinute = durataMinute;
    }

    public int getIdMedic() {
        return idMedic;
    }

    public void setIdMedic(int idMedic) {
        this.idMedic = idMedic;
    }

    public ServiciuMedical(Integer idServiciu, String numeServiciu, String specialitate, Double pret, Integer durataMinute) {
        this.idServiciu = idServiciu;
        this.numeServiciu = numeServiciu;
        this.specialitate = specialitate;
        this.pret = pret;
        this.durataMinute = durataMinute;
    }

    public ServiciuMedical() {
    }
}

