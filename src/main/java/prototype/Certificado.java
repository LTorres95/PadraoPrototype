package prototype;

public interface Certificado {

    Certificado clonar();

    void setAluno(String aluno);

    void adicionarAssinatura(String assinatura);

    String emitir();
}
