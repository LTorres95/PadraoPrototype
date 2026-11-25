package prototype;

public class Certificado implements Cloneable {

    private String aluno;
    private Evento evento;

    public Certificado(String aluno, Evento evento) {
        this.aluno = aluno;
        this.evento = evento;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String emitir() {
        return "Certificamos que " + aluno + " participou da " + evento.getNome()
                + ", com carga horária de " + evento.getCargaHoraria() + " horas.";
    }

    @Override
    public Certificado clone() throws CloneNotSupportedException {
        Certificado certificadoClone = (Certificado) super.clone();
        certificadoClone.evento = evento.clone();
        return certificadoClone;
    }
}
