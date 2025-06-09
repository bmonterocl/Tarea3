package backend;
public class PreguntaAlternativa extends Pregunta {
    private String respuestaCorrecta;
    public PreguntaAlternativa(String enunciado, String respuestaCorrecta) {
        super(enunciado, "Comprensión");
        this.respuestaCorrecta = respuestaCorrecta;
    }
    public String getRespuestaCorrecta() { return respuestaCorrecta; }
}