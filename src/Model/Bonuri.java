package Model;

public class Bonuri {
    private Integer idConsultatie;
    private Integer idServiciu;

    public Integer getIdConsultatie() {
        return idConsultatie;
    }

    public void setIdConsultatie(Integer idConsultatie) {
        this.idConsultatie = idConsultatie;
    }

    public Integer getIdServiciu() {
        return idServiciu;
    }

    public void setIdServiciu(Integer idServiciu) {
        this.idServiciu = idServiciu;
    }

    public Bonuri(Integer idConsultatie, Integer idServiciu) {
        this.idConsultatie = idConsultatie;
        this.idServiciu = idServiciu;
    }
}
