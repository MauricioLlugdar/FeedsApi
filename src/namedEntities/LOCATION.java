package namedEntities;

public class LOCATION extends NamedEntity {
    private String latitud, longitud, pais;

    public LOCATION(String label, String[] topics, String[] keywords, String latitud, String longitud, String pais){
        super(label, "LOCATION", topics, keywords);
        this.latitud = latitud;
        this.longitud = longitud;
        this.pais = pais;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
