package Practicas.practicas_gemini_java.practica1;
import java.math.BigDecimal;

public class MontoInvalidoException extends BaseException {

    public MontoInvalidoException(String mensaje) {
        super(mensaje);
    }

    public MontoInvalidoException(BigDecimal monto) {
        super(String.format("Monto inválido: $%.2f. El monto debe ser positivo.", monto));
    }

    

    
}
