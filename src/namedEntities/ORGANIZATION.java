package namedEntities;

public class ORGANIZATION extends NamedEntity{
    private String tipo, pais; //"type" refers to Educative Institution, Government, For-Profit, Non-Profit

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
