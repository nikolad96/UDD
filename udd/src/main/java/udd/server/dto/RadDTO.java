package udd.server.dto;

public class RadDTO {
    private Long id;
    private String naziv;

    public RadDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public RadDTO(Long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }
}
