package udd.server.model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

@Document(indexName = "casopis", type = "rad")
public class RadSearch {

    @Id
    @Field(type = FieldType.Text, index = false, store = true)
    private String id;

    @Field(type = FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian", store = true)
    private String naziv;
    @Field(type = FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian", store = true)
    private String oblast;
    @Field(type = FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian", store = true)
    private String abstrakt;
    @Field(type = FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian", store = true)
    private String autor;
    @Field(type = FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian", store = true)
    private String text;
    @Field(type = FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian", store = true)
    private String kljucne;

    @Field(type = FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian", store = true)
    private String highlight;

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public RadSearch(String id, String naziv, String oblast, String abstrakt, String autor, String text, String kljucne, String highlight) {
        this.id = id;
        this.naziv = naziv;
        this.oblast = oblast;
        this.abstrakt = abstrakt;
        this.autor = autor;
        this.text = text;
        this.kljucne = kljucne;
        this.highlight = highlight;
    }

    public String getKljucne() {
        return kljucne;
    }

    public void setKljucne(String kljucne) {
        this.kljucne = kljucne;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getAbstrakt() {
        return abstrakt;
    }

    public void setAbstrakt(String abstrakt) {
        this.abstrakt = abstrakt;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public RadSearch() {
    }
}
