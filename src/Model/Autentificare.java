package Model;

public class Autentificare {
    private int idUtilizator;
    private String parola;

    public int getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(int idUtilizator) {
        this.idUtilizator = idUtilizator;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public Autentificare(int idUtilizator, String parola) {
        this.idUtilizator = idUtilizator;
        this.parola = parola;
    }

    public Autentificare() {
    }
}

