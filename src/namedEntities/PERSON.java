package namedEntities;

public class PERSON extends NamedEntity{
    private String edad, nombre, ocupacion;

    public PERSON(String label, String[] topics, String[] keywords, String edad, String nombre, String ocupacion){
        super(label, "PERSON", topics, keywords);
        this.edad = edad;
        this.nombre = nombre;
        this.ocupacion = ocupacion;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }
}
