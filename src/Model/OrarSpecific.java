package Model;

public class OrarSpecific {
    private String zi;
    private int oraIncepere;
    private int oraSfarsire;
    private String locatia;
    private int idOrar;
    private int idAngajat;

    public String getZi() {
        return zi;
    }

    public void setZi(String zi) {
        this.zi = zi;
    }

    public int getOraIncepere() {
        return oraIncepere;
    }

    public void setOraIncepere(int oraIncepere) {
        this.oraIncepere = oraIncepere;
    }

    public int getOraSfarsire() {
        return oraSfarsire;
    }

    public void setOraSfarsire(int oraSfarsire) {
        this.oraSfarsire = oraSfarsire;
    }

    public String getLocatia() {
        return locatia;
    }

    public void setLocatia(String locatia) {
        this.locatia = locatia;
    }

    public int getIdOrar() {
        return idOrar;
    }

    public void setIdOrar(int idOrar) {
        this.idOrar = idOrar;
    }

    public int getIdAngajat() {
        return idAngajat;
    }

    public void setIdAngajat(int idAngajat) {
        this.idAngajat = idAngajat;
    }

    public OrarSpecific(String zi, int oraIncepere, int oraSfarsire, String locatia, int idOrar, int idAngajat) {
        this.zi = zi;
        this.oraIncepere = oraIncepere;
        this.oraSfarsire = oraSfarsire;
        this.locatia = locatia;
        this.idOrar = idOrar;
        this.idAngajat = idAngajat;
    }

    public OrarSpecific() {
    }

}

