package udd.server.model;



import javax.persistence.*;

@Entity
public class Rad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "naziv", nullable = false)
    private String naziv;

    @Column(name = "oblast", nullable = false)
    private String oblast;

    @Column(name = "apstrakt", nullable = false)
    private String apstrakt;

    @Column(name = "autor")
    private String autor;

    @Column(name = "kljucne")
    private String kljucne;

    @Column(name = "lon")
    private double lon;

    @Column(name = "lat")
    private double lat;


    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }



    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Column(name = "pdf")
    @Lob
    private byte[] pdf;

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

    public String getOblast() {
        return oblast;
    }

    public void setOblast(String oblast) {
        this.oblast = oblast;
    }

    public String getApstrakt() {
        return apstrakt;
    }

    public void setApstrakt(String apstrakt) {
        this.apstrakt = apstrakt;
    }

    public byte[] getPdf() {
        return pdf;
    }

    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }

    public Rad() {
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getKljucne() {
        return kljucne;
    }

    public void setKljucne(String kljucne) {
        this.kljucne = kljucne;
    }

    public Rad(Long id, String naziv, String oblast, String apstrakt, String autor, String kljucne, double lon, double lat, byte[] pdf) {
        this.id = id;
        this.naziv = naziv;
        this.oblast = oblast;
        this.apstrakt = apstrakt;
        this.autor = autor;
        this.kljucne = kljucne;
        this.lon = lon;
        this.lat = lat;
        this.pdf = pdf;
    }
}