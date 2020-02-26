package udd.server.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Recenzent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ime", nullable = false)
    private String ime;


    private Long rad_id;
    private double longitude;
    private double latitude;

    public Recenzent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }


    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Recenzent(Long id, String ime, List<Rad> radList, double longitude, double latitude) {
        this.id = id;
        this.ime = ime;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
