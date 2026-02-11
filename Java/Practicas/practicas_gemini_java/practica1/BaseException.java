package Practicas.practicas_gemini_java.practica1;

public abstract class BaseException extends Exception { //clase base para excepciones personalizadas
    
    private String mensaje;

    public BaseException(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
    
}
