package prototype;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CertificadoTest {

    private CertificadoParticipacao modelo;

    @BeforeEach
    void criarModelo() {
        modelo = new CertificadoParticipacao("Semana da Computação UFJF", 20);
        modelo.adicionarAssinatura("Coordenação do DCC");
    }

    @Test
    void deveCriarCloneComOsMesmosDadosDoModelo() {
        CertificadoParticipacao clone = (CertificadoParticipacao) modelo.clonar();

        assertNotSame(modelo, clone);
        assertEquals("Semana da Computação UFJF", clone.getEvento());
        assertEquals(20, clone.getCargaHoraria());
        assertEquals(List.of("Coordenação do DCC"), clone.getAssinaturas());
    }

    @Test
    void deveAlterarAlunoDoCloneSemAlterarModelo() {
        Certificado clone = modelo.clonar();

        clone.setAluno("Ana");

        assertNull(modelo.getAluno());
        assertEquals("Ana", ((CertificadoParticipacao) clone).getAluno());
    }

    @Test
    void deveCopiarListaDeAssinaturasDoModelo() {
        Certificado clone = modelo.clonar();

        clone.adicionarAssinatura("Direção do ICE");

        assertEquals(List.of("Coordenação do DCC"), modelo.getAssinaturas());
        assertEquals(List.of("Coordenação do DCC", "Direção do ICE"),
                ((CertificadoParticipacao) clone).getAssinaturas());
    }

    @Test
    void deveEmitirCertificadoDeParticipacao() {
        Certificado certificado = modelo.clonar();
        certificado.setAluno("Ana");

        assertEquals("Certificamos que Ana participou da Semana da Computação UFJF,"
                + " com carga horária de 20 horas. Assinado por: Coordenação do DCC.", certificado.emitir());
    }

    @Test
    void deveEmitirCertificadoDePalestrante() {
        CertificadoPalestrante modeloPalestrante = new CertificadoPalestrante("Semana da Computação UFJF", 2);
        modeloPalestrante.adicionarAssinatura("Coordenação do DCC");

        CertificadoPalestrante certificado = (CertificadoPalestrante) modeloPalestrante.clonar();
        certificado.setAluno("Pedro");
        certificado.setPalestra("Padrões de Projeto na Prática");

        assertEquals("Certificamos que Pedro ministrou a palestra \"Padrões de Projeto na Prática\""
                + " na Semana da Computação UFJF, com carga horária de 2 horas."
                + " Assinado por: Coordenação do DCC.", certificado.emitir());
        assertNull(modeloPalestrante.getPalestra());
    }

    @Test
    void deveEntregarCopiasNovasDoRegistro() {
        RegistroCertificados registro = new RegistroCertificados();
        registro.registrar("participacao", modelo);

        Certificado primeiro = registro.obter("participacao");
        Certificado segundo = registro.obter("participacao");
        primeiro.setAluno("Ana");
        segundo.setAluno("Pedro");

        assertNotSame(primeiro, segundo);
        assertNotSame(modelo, primeiro);
        assertEquals("Certificamos que Pedro participou da Semana da Computação UFJF,"
                + " com carga horária de 20 horas. Assinado por: Coordenação do DCC.", segundo.emitir());
        assertNull(modelo.getAluno());
    }

    @Test
    void deveLancarExcecaoParaModeloNaoRegistrado() {
        RegistroCertificados registro = new RegistroCertificados();

        assertThrows(IllegalArgumentException.class, () -> registro.obter("inexistente"));
    }
}
