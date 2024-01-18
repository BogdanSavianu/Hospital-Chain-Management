package Model;

public class AsistentMedical {
    private int idAsistentMedical;
    private int idAngajat;
    private String tipAsistent;
    private String grad;

    public int getIdAsistentMedical() {
        return idAsistentMedical;
    }

    public void setIdAsistentMedical(int idAsistentMedical) {
        this.idAsistentMedical = idAsistentMedical;
    }

    public int getIdAngajat() {
        return idAngajat;
    }

    public void setIdAngajat(int idAngajat) {
        this.idAngajat = idAngajat;
    }

    public String getTipAsistent() {
        return tipAsistent;
    }

    public void setTipAsistent(String tipAsistent) {
        this.tipAsistent = tipAsistent;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public AsistentMedical(int idAsistentMedical, int idAngajat, String tipAsistent, String grad) {
        this.idAsistentMedical = idAsistentMedical;
        this.idAngajat = idAngajat;
        this.tipAsistent = tipAsistent;
        this.grad = grad;
    }

    public AsistentMedical() {
    }
}

