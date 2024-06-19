import atividade.enade.Veiculo;
import atividade.enade.VeiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VeiculoServiceTest {
    private VeiculoService veiculoService;

    @BeforeEach
    public void setup() {
        veiculoService = new VeiculoService();
    }

    @Test
    public void testFiltrarVeiculosPorMarca() {
        List<Veiculo> chevroletCarros = veiculoService.filtrarVeiculosPorMarca("Chevrolet", "carro");
        assertEquals(2, chevroletCarros.size(), "Deveria retornar 2 carros Chevrolet");
    }

    @Test
    public void testSomarValorTotalPorMarca() {
        double totalValorChevroletCarros = veiculoService.somarValorTotalPorMarca("Chevrolet", "carro");
        assertEquals(207000.00, totalValorChevroletCarros, 0.001, "Valor total para Chevrolet carros deve ser 207000.00");
    }

    @Test
    public void testFiltrarVeiculosPorAno() {
        List<Veiculo> veiculosNovos = veiculoService.filtrarVeiculosPorAno(2020, "carro");
        assertEquals(7, veiculosNovos.size(), "Deveria retornar 7 carros de 2020");
    }
}
