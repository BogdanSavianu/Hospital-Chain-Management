package Model;

import java.util.Date;

public class Consultatie {
    private int idRaport;
    private int idAsistentMedical;
    private int idMedic;
    private int idPacient;
    private Date dataConsultatie;
    private String istoric;
    private String simptome;
    private String investigatii;
    private String diagnostic;
    private String recomandari;
    private String parafa;

    public int getIdRaport() {
        return idRaport;
    }

    public void setIdRaport(int idRaport) {
        this.idRaport = idRaport;
    }

    public int getIdAsistentMedical() {
        return idAsistentMedical;
    }

    public void setIdAsistentMedical(int idAsistentMedical) {
        this.idAsistentMedical = idAsistentMedical;
    }

    public int getIdMedic() {
        return idMedic;
    }

    public void setIdMedic(int idMedic) {
        this.idMedic = idMedic;
    }

    public int getIdPacient() {
        return idPacient;
    }

    public void setIdPacient(int idPacient) {
        this.idPacient = idPacient;
    }

    public Date getDataConsultatie() {
        return dataConsultatie;
    }

    public void setDataConsultatie(Date dataConsultatie) {
        this.dataConsultatie = dataConsultatie;
    }

    public String getIstoric() {
        return istoric;
    }

    public void setIstoric(String istoric) {
        this.istoric = istoric;
    }

    public String getSimptome() {
        return simptome;
    }

    public void setSimptome(String simptome) {
        this.simptome = simptome;
    }

    public String getInvestigatii() {
        return investigatii;
    }

    public void setInvestigatii(String investigatii) {
        this.investigatii = investigatii;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getRecomandari() {
        return recomandari;
    }

    public void setRecomandari(String recomandari) {
        this.recomandari = recomandari;
    }

    public String getParafa() {
        return parafa;
    }

    public void setParafa(String parafa) {
        this.parafa = parafa;
    }

    public Consultatie(int idRaport, int idAsistentMedical, int idMedic, int idPacient, Date dataConsultatie, String istoric, String simptome, String investigatii, String diagnostic, String recomandari, String parafa) {
        this.idRaport = idRaport;
        this.idAsistentMedical = idAsistentMedical;
        this.idMedic = idMedic;
        this.idPacient = idPacient;
        this.dataConsultatie = dataConsultatie;
        this.istoric = istoric;
        this.simptome = simptome;
        this.investigatii = investigatii;
        this.diagnostic = diagnostic;
        this.recomandari = recomandari;
        this.parafa = parafa;
    }

    public Consultatie() {
    }
}

