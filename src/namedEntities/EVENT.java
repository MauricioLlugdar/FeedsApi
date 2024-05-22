package namedEntities;

public class EVENT extends NamedEntity {
    private String dresscode, ciudad;

    public EVENT(String label, String[] topics, String[] keywords, String dresscode, String ciudad) {
        super(label, "EVENT", topics, keywords);
        this.dresscode = dresscode;
        this.ciudad = ciudad;
    }

    public String getDresscode() {
        return dresscode;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setDresscode(String dresscode) {
        this.dresscode = dresscode;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
