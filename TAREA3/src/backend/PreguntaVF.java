package backend;
public class PreguntaVF extends Pregunta {
    private boolean respuestaCorrecta;
    public PreguntaVF(String enunciado, boolean respuestaCorrecta) {
        super(enunciado, "Conocimiento");
        this.respuestaCorrecta = respuestaCorrecta;
    }
    public boolean getRespuestaCorrecta() { return respuestaCorrecta; }
}