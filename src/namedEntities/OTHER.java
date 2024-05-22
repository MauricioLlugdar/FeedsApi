package namedEntities;

public class OTHER extends NamedEntity{
    private String descripcion;

    public OTHER(String label, String[] topics, String[] keywords, String descripcion){
        super(label, "OTHER", topics, keywords);
        this.descripcion = descripcion;
    }

    public String getDescription() {
        return descripcion;
    }

    public void setDescription(String description) {
        this.descripcion = descripcion;
    }
}
