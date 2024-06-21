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
        assertEquals(3, chevroletCarros.size(), "Deveria retornar 3 carros Chevrolet");
    }

    @Test
    public void testSomarValorTotalPorMarca() {
        double totalValorChevroletCarros = veiculoService.somarValorTotalPorMarca("Chevrolet", "carro");
        assertEquals(259000.00, totalValorChevroletCarros, 0.001, "Valor total para Chevrolet carros deve ser 259000.00");
    }

    @Test
    public void testFiltrarVeiculosPorAno() {
        List<Veiculo> veiculosNovos = veiculoService.filtrarVeiculosPorAno(2020, "carro");
        assertEquals(7, veiculosNovos.size(), "Deveria retornar 7 carros novos");
    }
}
