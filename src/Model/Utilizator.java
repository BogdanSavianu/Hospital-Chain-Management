package Model;

import java.util.Date;

public class Utilizator {
    private int idUtilizator;
    private String cnp;
    private String nume;
    private String prenume;
    private String adresa;
    private String telefon;
    private String email;
    private String iban;
    private int numarContract;
    private Date dataAngajarii;
    private String functie;
    private String tipAdministrator;

    public int getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(int idUtilizator) {
        this.idUtilizator = idUtilizator;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public int getNumarContract() {
        return numarContract;
    }

    public void setNumarContract(int numarContract) {
        this.numarContract = numarContract;
    }

    public Date getDataAngajarii() {
        return dataAngajarii;
    }

    public void setDataAngajarii(Date dataAngajarii) {
        this.dataAngajarii = dataAngajarii;
    }

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    public String getTipAdministrator() {
        return tipAdministrator;
    }

    public void setTipAdministrator(String tipAdministrator) {
        this.tipAdministrator = tipAdministrator;
    }

    public Utilizator(int idUtilizator, String cnp, String nume, String prenume, String adresa, String telefon, String email, String iban, int numarContract, Date dataAngajarii, String functie, String tipAdministrator) {
        this.idUtilizator = idUtilizator;
        this.cnp = cnp;
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
        this.telefon = telefon;
        this.email = email;
        this.iban = iban;
        this.numarContract = numarContract;
        this.dataAngajarii = dataAngajarii;
        this.functie = functie;
        this.tipAdministrator = tipAdministrator;
    }

    public Utilizator() {
    }
}

