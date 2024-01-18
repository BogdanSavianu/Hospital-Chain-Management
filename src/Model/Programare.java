package Model;

import java.sql.Time;
import java.util.Date;

public class Programare {
    private int idProgramare;
    private int idReceptioner;
    private int idPacient;
    private String numePacient;
    private String prenumePacient;
    private int idMedic;
    private Date dataProgramare;
    private Time oraProgramare;
    private int durataConsulta;
    private String locatie;
    private int idServiciu;

    public int getIdProgramare() {
        return idProgramare;
    }

    public void setIdProgramare(int idProgramare) {
        this.idProgramare = idProgramare;
    }

    public int getIdReceptioner() {
        return idReceptioner;
    }

    public void setIdReceptioner(int idReceptioner) {
        this.idReceptioner = idReceptioner;
    }

    public int getIdPacient() {
        return idPacient;
    }

    public void setIdPacient(int idPacient) {
        this.idPacient = idPacient;
    }

    public int getIdMedic() {
        return idMedic;
    }

    public void setIdMedic(int idMedic) {
        this.idMedic = idMedic;
    }

    public Date getDataProgramare() {
        return dataProgramare;
    }

    public void setDataProgramare(Date dataProgramare) {
        this.dataProgramare = dataProgramare;
    }

    public Time getOraProgramare() {
        return oraProgramare;
    }

    public void setOraProgramare(Time oraProgramare) {
        this.oraProgramare = oraProgramare;
    }

    public int getDurataConsulta() {
        return durataConsulta;
    }

    public void setDurataConsulta(int durataConsulta) {
        this.durataConsulta = durataConsulta;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public int getIdServiciu() {
        return idServiciu;
    }

    public void setIdServiciu(int idServiciu) {
        this.idServiciu = idServiciu;
    }

    public String getNumePacient() {
        return numePacient;
    }

    public void setNumePacient(String numePacient) {
        this.numePacient = numePacient;
    }

    public String getPrenumePacient() {
        return prenumePacient;
    }

    public void setPrenumePacient(String prenumePacient) {
        this.prenumePacient = prenumePacient;
    }

    public Programare(int idProgramare, int idReceptioner, int idPacient, String numePacient, String prenumePacient, int idMedic, Date dataProgramare, Time oraProgramare, int durataConsulta, String locatie, int idServiciu) {
        this.idProgramare = idProgramare;
        this.idReceptioner = idReceptioner;
        this.idPacient = idPacient;
        this.numePacient = numePacient;
        this.prenumePacient = prenumePacient;
        this.idMedic = idMedic;
        this.dataProgramare = dataProgramare;
        this.oraProgramare = oraProgramare;
        this.durataConsulta = durataConsulta;
        this.locatie = locatie;
        this.idServiciu = idServiciu;
    }

    public Programare() {
    }
}

