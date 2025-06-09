package backend;
import java.io.*;
import java.util.*;
public class Alumno implements Serializable {
    private String rut;
    private String nombre;
    private int edad;
    private String asignatura;
    public Alumno(String rut, String nombre, int edad, String asignatura) {
        this.rut = rut;
        this.nombre = nombre;
        this.edad = edad;
        this.asignatura = asignatura;
        EventManager.notifyAll(new BackendEvent("Alumno creado: " + nombre));
    }
    public String getRut() { return rut; }
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getAsignatura() { return asignatura; }
    public void guardarEnArchivo(String filename) throws IOException {
        List<Alumno> alumnos = cargarDesdeArchivo(filename);
        alumnos.add(this);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
        oos.writeObject(alumnos);
        oos.close();
    }
    public static List<Alumno> cargarDesdeArchivo(String filename) throws IOException {
        List<Alumno> alumnos = new ArrayList<>();
        File file = new File(filename);
        if (file.exists()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            try {
                alumnos = (List<Alumno>) ois.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
            ois.close();
        }
        return alumnos;
    }
}