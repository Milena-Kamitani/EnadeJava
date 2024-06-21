package atividade.enade;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VeiculoService {
    private VeiculosJson veiculosJson;

    public VeiculoService() {
        carregarDadosDoJson();
    }

    private void carregarDadosDoJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            URL resource = getClass().getClassLoader().getResource("veiculos.json");
            File file = new File(resource.getFile());
            veiculosJson = objectMapper.readValue(file, VeiculosJson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Veiculo> filtrarVeiculosPorMarca(String marca, String tipo) {
        Stream<Veiculo> veiculosStream = getVeiculosStream(tipo);
        return veiculosStream
                .filter(veiculo -> veiculo.getMarca().equalsIgnoreCase(marca))
                .collect(Collectors.toList());
    }

    public double somarValorTotalPorMarca(String marca, String tipo) {
        Stream<Veiculo> veiculosStream = getVeiculosStream(tipo);
        return veiculosStream
                .filter(veiculo -> veiculo.getMarca().equalsIgnoreCase(marca))
                .mapToDouble(Veiculo::getValor)
                .sum();
    }

    public List<Veiculo> filtrarVeiculosPorAno(int ano, String tipo) {
        Stream<Veiculo> veiculosStream = getVeiculosStream(tipo);
        return veiculosStream
                .filter(veiculo -> veiculo.getAno() > ano)
                .collect(Collectors.toList());
    }

    private Stream<Veiculo> getVeiculosStream(String tipo) {
        List<CategoriaVeiculos> categorias = null;

        if (tipo.equalsIgnoreCase("carro")) {
            categorias = veiculosJson.getCarros();
        } else if (tipo.equalsIgnoreCase("moto")) {
            categorias = veiculosJson.getMotos();
        }

        if (categorias != null) {
            return categorias.stream()
                    .flatMap(categoria -> Stream.concat(
                            categoria.getNovos() != null ? categoria.getNovos().stream() : Stream.empty(),
                            categoria.getUsados() != null ? categoria.getUsados().stream() : Stream.empty()
                    ));
        } else {
            return Stream.empty();
        }
    }
}
