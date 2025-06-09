package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class MainWindow extends JFrame {

    // Alumno
    private JTextField nombreField, rutField, edadField, asignaturaField;

    // Pregunta
    private JComboBox<String> tipoPreguntaCombo;
    private JTextField preguntaField;
    private JTextField opcionAField, opcionBField, opcionCField, opcionDField;
    private JComboBox<String> respuestaAlternativaCombo;
    private JComboBox<String> respuestaVFCombo;

    // Examen
    private JPanel examPanel;
    private List<Map<String, String>> preguntas;
    private int preguntaActual;
    private JLabel preguntaLabel;
    private ButtonGroup respuestaGroup;
    private JRadioButton[] opcionButtons;
    private JRadioButton verdaderoButton, falsoButton;
    private JButton siguienteButton;

    public MainWindow() {
        setTitle("Sistema de Gestión de Exámenes");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Registrar Alumno", crearPanelAlumno());
        tabbedPane.addTab("Agregar Preguntas", crearPanelPreguntas());
        tabbedPane.addTab("Realizar Examen", crearPanelExamen());

        add(tabbedPane);
        setVisible(true);
    }

    // PANEL ALUMNO
    private JPanel crearPanelAlumno() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        panel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        panel.add(nombreField);

        panel.add(new JLabel("RUT:"));
        rutField = new JTextField();
        panel.add(rutField);

        panel.add(new JLabel("Edad:"));
        edadField = new JTextField();
        panel.add(edadField);

        panel.add(new JLabel("Asignatura:"));
        asignaturaField = new JTextField();
        panel.add(asignaturaField);

        JButton guardarButton = new JButton("Guardar Alumno");
        guardarButton.addActionListener(e -> guardarAlumno());
        panel.add(guardarButton);

        return panel;
    }

    private void guardarAlumno() {
        String nombre = nombreField.getText();
        String rut = rutField.getText();
        String edad = edadField.getText();
        String asignatura = asignaturaField.getText();

        if (nombre.isEmpty() || rut.isEmpty() || edad.isEmpty() || asignatura.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (FileWriter fw = new FileWriter("alumnos.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println("Nombre: " + nombre);
            pw.println("RUT: " + rut);
            pw.println("Edad: " + edad);
            pw.println("Asignatura: " + asignatura);
            pw.println("------------------------------");

            JOptionPane.showMessageDialog(this, "Alumno guardado exitosamente.");
            nombreField.setText(""); rutField.setText(""); edadField.setText(""); asignaturaField.setText("");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el alumno.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // PANEL PREGUNTAS
    private JPanel crearPanelPreguntas() {
        JPanel panel = new JPanel(new GridLayout(10, 2, 5, 5));

        panel.add(new JLabel("Tipo de Pregunta:"));
        tipoPreguntaCombo = new JComboBox<>(new String[]{"Alternativa", "Verdadero/Falso"});
        tipoPreguntaCombo.addActionListener(e -> actualizarCamposPregunta());
        panel.add(tipoPreguntaCombo);

        panel.add(new JLabel("Pregunta:"));
        preguntaField = new JTextField();
        panel.add(preguntaField);

        // Alternativa múltiple
        panel.add(new JLabel("Opción A:"));
        opcionAField = new JTextField();
        panel.add(opcionAField);

        panel.add(new JLabel("Opción B:"));
        opcionBField = new JTextField();
        panel.add(opcionBField);

        panel.add(new JLabel("Opción C:"));
        opcionCField = new JTextField();
        panel.add(opcionCField);

        panel.add(new JLabel("Opción D:"));
        opcionDField = new JTextField();
        panel.add(opcionDField);

        panel.add(new JLabel("Respuesta Correcta (Alternativa A/B/C/D):"));
        respuestaAlternativaCombo = new JComboBox<>(new String[]{"A", "B", "C", "D"});
        panel.add(respuestaAlternativaCombo);

        // Verdadero / Falso
        panel.add(new JLabel("Respuesta Correcta (VF):"));
        respuestaVFCombo = new JComboBox<>(new String[]{"V", "F"});
        panel.add(respuestaVFCombo);

        JButton guardarPreguntaButton = new JButton("Guardar Pregunta");
        guardarPreguntaButton.addActionListener(e -> guardarPregunta());
        panel.add(guardarPreguntaButton);

        actualizarCamposPregunta();

        return panel;
    }

    private void actualizarCamposPregunta() {
        boolean esAlternativa = tipoPreguntaCombo.getSelectedItem().equals("Alternativa");

        opcionAField.setEnabled(esAlternativa);
        opcionBField.setEnabled(esAlternativa);
        opcionCField.setEnabled(esAlternativa);
        opcionDField.setEnabled(esAlternativa);
        respuestaAlternativaCombo.setEnabled(esAlternativa);

        respuestaVFCombo.setEnabled(!esAlternativa);
    }

    private void guardarPregunta() {
        String tipo = tipoPreguntaCombo.getSelectedItem().toString();
        String pregunta = preguntaField.getText();

        if (pregunta.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa la pregunta.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (FileWriter fw = new FileWriter("preguntas.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println("Tipo: " + tipo);
            pw.println("Pregunta: " + pregunta);

            if (tipo.equals("Alternativa")) {
                pw.println("OpcionA: " + opcionAField.getText());
                pw.println("OpcionB: " + opcionBField.getText());
                pw.println("OpcionC: " + opcionCField.getText());
                pw.println("OpcionD: " + opcionDField.getText());
                pw.println("RespuestaCorrecta: " + respuestaAlternativaCombo.getSelectedItem());
            } else {
                pw.println("RespuestaCorrecta: " + respuestaVFCombo.getSelectedItem());
            }

            pw.println("------------------------------");

            JOptionPane.showMessageDialog(this, "Pregunta guardada correctamente.");
            preguntaField.setText(""); opcionAField.setText(""); opcionBField.setText("");
            opcionCField.setText(""); opcionDField.setText("");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar la pregunta.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // PANEL EXAMEN
    private JPanel crearPanelExamen() {
        examPanel = new JPanel(new BorderLayout());

        preguntaLabel = new JLabel("Haz clic en 'Comenzar Examen' para empezar.", JLabel.CENTER);
        preguntaLabel.setFont(new Font("Arial", Font.BOLD, 16));
        examPanel.add(preguntaLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        respuestaGroup = new ButtonGroup();

        opcionButtons = new JRadioButton[4];
        for (int i = 0; i < 4; i++) {
            opcionButtons[i] = new JRadioButton();
            respuestaGroup.add(opcionButtons[i]);
            centerPanel.add(opcionButtons[i]);
        }

        verdaderoButton = new JRadioButton("Verdadero");
        falsoButton = new JRadioButton("Falso");
        respuestaGroup.add(verdaderoButton);
        respuestaGroup.add(falsoButton);
        centerPanel.add(verdaderoButton);
        centerPanel.add(falsoButton);

        examPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton comenzarButton = new JButton("Comenzar Examen");
        comenzarButton.addActionListener(e -> comenzarExamen());
        buttonPanel.add(comenzarButton);

        siguienteButton = new JButton("Siguiente Pregunta");
        siguienteButton.setEnabled(false);
        siguienteButton.addActionListener(e -> verificarRespuesta());
        buttonPanel.add(siguienteButton);

        examPanel.add(buttonPanel, BorderLayout.SOUTH);

        return examPanel;
    }

    private void comenzarExamen() {
        preguntas = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("preguntas.txt"))) {
            Map<String, String> pregunta = new HashMap<>();
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                if (linea.equals("------------------------------")) {
                    preguntas.add(pregunta);
                    pregunta = new HashMap<>();
                } else {
                    String[] partes = linea.split(": ", 2);
                    if (partes.length == 2) {
                        pregunta.put(partes[0], partes[1]);
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al leer preguntas.txt.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (preguntas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay preguntas guardadas.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        preguntaActual = 0;
        mostrarPregunta();

        siguienteButton.setEnabled(true);
    }

    private void mostrarPregunta() {
        respuestaGroup.clearSelection();

        if (preguntaActual < preguntas.size()) {
            Map<String, String> pregunta = preguntas.get(preguntaActual);
            String tipo = pregunta.get("Tipo");
            preguntaLabel.setText("<html><body style='width: 500px'>" + pregunta.get("Pregunta") + "</body></html>");

            boolean esAlternativa = tipo.equals("Alternativa");

            for (int i = 0; i < 4; i++) {
                opcionButtons[i].setVisible(esAlternativa);
            }

            if (esAlternativa) {
                opcionButtons[0].setText("A) " + pregunta.get("OpcionA"));
                opcionButtons[1].setText("B) " + pregunta.get("OpcionB"));
                opcionButtons[2].setText("C) " + pregunta.get("OpcionC"));
                opcionButtons[3].setText("D) " + pregunta.get("OpcionD"));
            }

            verdaderoButton.setVisible(!esAlternativa);
            falsoButton.setVisible(!esAlternativa);

        } else {
            JOptionPane.showMessageDialog(this, "Examen terminado.");
            preguntaLabel.setText("Examen terminado.");
            siguienteButton.setEnabled(false);
        }
    }

    private void verificarRespuesta() {
        if (preguntaActual >= preguntas.size()) return;

        Map<String, String> pregunta = preguntas.get(preguntaActual);
        String tipo = pregunta.get("Tipo");
        String respuestaCorrecta = pregunta.get("RespuestaCorrecta");
        String respuestaAlumno = "";

        if (tipo.equals("Alternativa")) {
            if (opcionButtons[0].isSelected()) respuestaAlumno = "A";
            else if (opcionButtons[1].isSelected()) respuestaAlumno = "B";
            else if (opcionButtons[2].isSelected()) respuestaAlumno = "C";
            else if (opcionButtons[3].isSelected()) respuestaAlumno = "D";
        } else {
            if (verdaderoButton.isSelected()) respuestaAlumno = "V";
            else if (falsoButton.isSelected()) respuestaAlumno = "F";
        }

        boolean esCorrecta = respuestaAlumno.equalsIgnoreCase(respuestaCorrecta);

        try (FileWriter fw = new FileWriter("respuestas.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println("Pregunta: " + pregunta.get("Pregunta"));
            pw.println("Respuesta del alumno: " + respuestaAlumno);
            pw.println("Respuesta correcta: " + respuestaCorrecta);
            pw.println("Resultado: " + (esCorrecta ? "CORRECTO" : "INCORRECTO"));
            pw.println("------------------------------");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar la respuesta.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        preguntaActual++;
        mostrarPregunta();
    }
}
