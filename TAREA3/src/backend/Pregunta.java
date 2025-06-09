package backend;
import java.io.Serializable;
public abstract class Pregunta implements Serializable {
    protected String enunciado;
    protected String nivelBloom;
    public Pregunta(String enunciado, String nivelBloom) {
        this.enunciado = enunciado;
        this.nivelBloom = nivelBloom;
    }
    public String getEnunciado() { return enunciado; }
    public String getNivelBloom() { return nivelBloom; }
}