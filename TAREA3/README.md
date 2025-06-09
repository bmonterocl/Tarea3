
# Sistema de Evaluación basado en la Taxonomía de Bloom

## Instrucciones para ejecución en NetBeans

1. Abrir NetBeans.
2. Ir a `File` -> `Open Project`.
3. Seleccionar la carpeta `miProyectoAnt_COMPLETO`.
4. Cargar el proyecto y ejecutar la clase `Main` ubicada en `src/frontend/Main.java`.

## Formato del archivo de ítems

El archivo de preguntas debe tener el siguiente formato CSV (sin encabezado):

```
tipo,nivel_bloom,enunciado,opciones,se_respuesta_correcta
VF,Recordar,"¿El Sol es una estrella?",Verdadero;Falso,Verdadero
MULTIPLE,Aplicar,"¿Cuál es la capital de Francia?",Paris;Madrid;Berlin,Paris
```

- `tipo`: puede ser `VF` o `MULTIPLE`.
- `nivel_bloom`: uno de Recordar, Entender, Aplicar, Analizar, Evaluar, Crear.
- `opciones`: separadas por `;`.
- `respuesta_correcta`: debe coincidir exactamente con una de las opciones.

## Supuestos del sistema

- Las preguntas no pueden repetirse entre archivos usados en años consecutivos (esto debe gestionarse externamente).
- El sistema permite avanzar y retroceder manteniendo las respuestas seleccionadas.
- Al finalizar, se entrega un resumen por tipo de ítem y nivel de Bloom.
- Se incluyen validaciones para formato de archivo inválido.
