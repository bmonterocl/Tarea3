package backend;
import java.io.*;
import java.util.*;
public class Examen implements Serializable {
    private List<Pregunta> preguntas;
    public Examen() {
        preguntas = new ArrayList<>();
    }
    public void añadirPregunta(Pregunta p) {
        preguntas.add(p);
        EventManager.notifyAll(new BackendEvent("Pregunta añadida: " + p.getEnunciado()));
    }
    public void eliminarPregunta(Pregunta p) {
        preguntas.remove(p);
        EventManager.notifyAll(new BackendEvent("Pregunta eliminada."));
    }
    public List<Pregunta> getPreguntas() {
        return preguntas;
    }
    public void guardarEnArchivo(String filename) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
        oos.writeObject(this);
        oos.close();
    }
    public void cargarDesdeArchivo(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
        Examen examenCargado = (Examen) ois.readObject();
        this.preguntas = examenCargado.getPreguntas();
        ois.close();
    }
}