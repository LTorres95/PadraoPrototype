package prototype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CertificadoPalestrante implements Certificado {

    private String aluno;
    private String palestra;
    private final String evento;
    private final int cargaHoraria;
    private final List<String> assinaturas;

    public CertificadoPalestrante(String evento, int cargaHoraria) {
        this.evento = evento;
        this.cargaHoraria = cargaHoraria;
        this.assinaturas = new ArrayList<>();
    }

    private CertificadoPalestrante(CertificadoPalestrante modelo) {
        this.aluno = modelo.aluno;
        this.palestra = modelo.palestra;
        this.evento = modelo.evento;
        this.cargaHoraria = modelo.cargaHoraria;
        this.assinaturas = new ArrayList<>(modelo.assinaturas);
    }

    @Override
    public Certificado clonar() {
        return new CertificadoPalestrante(this);
    }

    @Override
    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public void setPalestra(String palestra) {
        this.palestra = palestra;
    }

    @Override
    public void adicionarAssinatura(String assinatura) {
        assinaturas.add(assinatura);
    }

    public String getAluno() {
        return aluno;
    }

    public String getPalestra() {
        return palestra;
    }

    public String getEvento() {
        return evento;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public List<String> getAssinaturas() {
        return Collections.unmodifiableList(assinaturas);
    }

    @Override
    public String emitir() {
        return "Certificamos que " + aluno + " ministrou a palestra \"" + palestra + "\" na " + evento
                + ", com carga horária de " + cargaHoraria + " horas."
                + " Assinado por: " + String.join(", ", assinaturas) + ".";
    }
}
