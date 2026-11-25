package prototype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CertificadoParticipacao implements Certificado {

    private String aluno;
    private final String evento;
    private final int cargaHoraria;
    private final List<String> assinaturas;

    public CertificadoParticipacao(String evento, int cargaHoraria) {
        this.evento = evento;
        this.cargaHoraria = cargaHoraria;
        this.assinaturas = new ArrayList<>();
    }

    private CertificadoParticipacao(CertificadoParticipacao modelo) {
        this.aluno = modelo.aluno;
        this.evento = modelo.evento;
        this.cargaHoraria = modelo.cargaHoraria;
        this.assinaturas = new ArrayList<>(modelo.assinaturas);
    }

    @Override
    public Certificado clonar() {
        return new CertificadoParticipacao(this);
    }

    @Override
    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    @Override
    public void adicionarAssinatura(String assinatura) {
        assinaturas.add(assinatura);
    }

    public String getAluno() {
        return aluno;
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
        return "Certificamos que " + aluno + " participou da " + evento
                + ", com carga horária de " + cargaHoraria + " horas."
                + " Assinado por: " + String.join(", ", assinaturas) + ".";
    }
}
