package udd.server.dto;

public class QueryDTO {
    private String polje;
    private String vrednost;

    public QueryDTO(String polje, String vrednost) {
        this.polje = polje;
        this.vrednost = vrednost;
    }

    public String getPolje() {
        return polje;
    }

    public void setPolje(String polje) {
        this.polje = polje;
    }

    public String getVrednost() {
        return vrednost;
    }

    public void setVrednost(String vrednost) {
        this.vrednost = vrednost;
    }

    public QueryDTO() {
    }
}
