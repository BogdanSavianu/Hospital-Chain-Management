package Model;

public class Medic {
    private Integer idMedic;
    private Integer idAngajat;
    private String specializare;
    private String grad;
    private String codParafa;
    private String competente;
    private String titluStiintific;
    private Double procentComision;
    private String postDidactic;

    public int getIdMedic() {
        return idMedic;
    }

    public void setIdMedic(int idMedic) {
        this.idMedic = idMedic;
    }

    public int getIdAngajat() {
        return idAngajat;
    }

    public void setIdAngajat(int idAngajat) {
        this.idAngajat = idAngajat;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getCodParafa() {
        return codParafa;
    }

    public void setCodParafa(String codParafa) {
        this.codParafa = codParafa;
    }

    public String getCompetente() {
        return competente;
    }

    public void setCompetente(String competente) {
        this.competente = competente;
    }

    public String getTitluStiintific() {
        return titluStiintific;
    }

    public void setTitluStiintific(String titluStiintific) {
        this.titluStiintific = titluStiintific;
    }

    public double getProcentComision() {
        return procentComision;
    }

    public void setProcentComision(double procentComision) {
        this.procentComision = procentComision;
    }

    public String getPostDidactic() {
        return postDidactic;
    }

    public void setPostDidactic(String postDidactic) {
        this.postDidactic = postDidactic;
    }

    public Medic(Integer idMedic, Integer idAngajat, String specializare, String grad, String codParafa, String competente, String titluStiintific, Double procentComision, String postDidactic) {
        this.idMedic = idMedic;
        this.idAngajat = idAngajat;
        this.specializare = specializare;
        this.grad = grad;
        this.codParafa = codParafa;
        this.competente = competente;
        this.titluStiintific = titluStiintific;
        this.procentComision = procentComision;
        this.postDidactic = postDidactic;
    }
    public String toString() {
        return String.valueOf(idAngajat);
    }

    public Medic() {
    }
}

