package Model;

public class Pacient {
    private int id;
    private String cnp;
    private String nume;
    private String prenume;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Pacient(int id, String cnp, String nume, String prenume) {
        this.id = id;
        this.cnp = cnp;
        this.nume = nume;
        this.prenume = prenume;
    }

    public Pacient() {
    }
}
