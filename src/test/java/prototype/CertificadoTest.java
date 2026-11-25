package prototype;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class CertificadoTest {

    @Test
    void deveClonarCertificadoComOsMesmosDados() throws CloneNotSupportedException {
        Certificado modelo = new Certificado("Ana", new Evento("Semana da Computação UFJF", 20));

        Certificado clone = modelo.clone();

        assertNotSame(modelo, clone);
        assertEquals("Ana", clone.getAluno());
        assertEquals("Semana da Computação UFJF", clone.getEvento().getNome());
        assertEquals(20, clone.getEvento().getCargaHoraria());
    }

    @Test
    void deveAlterarAlunoDoCloneSemAlterarOriginal() throws CloneNotSupportedException {
        Certificado modelo = new Certificado("Ana", new Evento("Semana da Computação UFJF", 20));

        Certificado clone = modelo.clone();
        clone.setAluno("Pedro");

        assertEquals("Ana", modelo.getAluno());
        assertEquals("Pedro", clone.getAluno());
    }

    @Test
    void deveClonarTambemOEvento() throws CloneNotSupportedException {
        Certificado modelo = new Certificado("Ana", new Evento("Semana da Computação UFJF", 20));

        Certificado clone = modelo.clone();

        assertNotSame(modelo.getEvento(), clone.getEvento());
    }

    @Test
    void deveAlterarEventoDoCloneSemAlterarOriginal() throws CloneNotSupportedException {
        Certificado modelo = new Certificado("Ana", new Evento("Semana da Computação UFJF", 20));

        Certificado clone = modelo.clone();
        clone.getEvento().setNome("Maratona de Programação");
        clone.getEvento().setCargaHoraria(8);

        assertEquals("Semana da Computação UFJF", modelo.getEvento().getNome());
        assertEquals(20, modelo.getEvento().getCargaHoraria());
    }

    @Test
    void deveEmitirCertificadoDoClone() throws CloneNotSupportedException {
        Certificado modelo = new Certificado("Ana", new Evento("Semana da Computação UFJF", 20));

        Certificado clone = modelo.clone();
        clone.setAluno("Pedro");

        assertEquals("Certificamos que Pedro participou da Semana da Computação UFJF,"
                + " com carga horária de 20 horas.", clone.emitir());
    }
}
