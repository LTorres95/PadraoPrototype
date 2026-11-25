package prototype;

import java.util.HashMap;
import java.util.Map;

public class RegistroCertificados {

    private final Map<String, Certificado> modelos = new HashMap<>();

    public void registrar(String tipo, Certificado modelo) {
        modelos.put(tipo, modelo);
    }

    public Certificado obter(String tipo) {
        Certificado modelo = modelos.get(tipo);
        if (modelo == null) {
            throw new IllegalArgumentException("Modelo de certificado não encontrado: " + tipo);
        }
        return modelo.clonar();
    }
}
