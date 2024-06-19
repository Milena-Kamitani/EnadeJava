package atividade.enade;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
        List<Veiculo> resultado = new ArrayList<>();
        List<CategoriaVeiculos> categorias = null;

        if (tipo.equalsIgnoreCase("carro")) {
            categorias = veiculosJson.getCarros();
        } else if (tipo.equalsIgnoreCase("moto")) {
            categorias = veiculosJson.getMotos();
        }

        if (categorias != null) {
            for (CategoriaVeiculos categoria : categorias) {
                if (categoria.getNovos() != null) {
                    for (Veiculo veiculo : categoria.getNovos()) {
                        if (veiculo.getMarca().equalsIgnoreCase(marca)) {
                            resultado.add(veiculo);
                        }
                    }
                }
                if (categoria.getUsados() != null) {
                    for (Veiculo veiculo : categoria.getUsados()) {
                        if (veiculo.getMarca().equalsIgnoreCase(marca)) {
                            resultado.add(veiculo);
                        }
                    }
                }
            }
        }

        return resultado;
    }

    public double somarValorTotalPorMarca(String marca, String tipo) {
        double total = 0.0;
        List<CategoriaVeiculos> categorias = null;

        if (tipo.equalsIgnoreCase("carro")) {
            categorias = veiculosJson.getCarros();
        } else if (tipo.equalsIgnoreCase("moto")) {
            categorias = veiculosJson.getMotos();
        }

        if (categorias != null) {
            for (CategoriaVeiculos categoria : categorias) {
                if (categoria.getNovos() != null) {
                    for (Veiculo veiculo : categoria.getNovos()) {
                        if (veiculo.getMarca().equalsIgnoreCase(marca)) {
                            total += veiculo.getValor();
                        }
                    }
                }
                if (categoria.getUsados() != null) {
                    for (Veiculo veiculo : categoria.getUsados()) {
                        if (veiculo.getMarca().equalsIgnoreCase(marca)) {
                            total += veiculo.getValor();
                        }
                    }
                }
            }
        }

        return total;
    }

    public List<Veiculo> filtrarVeiculosPorAno(int ano, String tipo) {
        List<Veiculo> resultado = new ArrayList<>();
        List<CategoriaVeiculos> categorias = null;

        if (tipo.equalsIgnoreCase("carro")) {
            categorias = veiculosJson.getCarros();
        } else if (tipo.equalsIgnoreCase("moto")) {
            categorias = veiculosJson.getMotos();
        }

        if (categorias != null) {
            for (CategoriaVeiculos categoria : categorias) {
                if (categoria.getNovos() != null) {
                    for (Veiculo veiculo : categoria.getNovos()) {
                        if (veiculo.getAno() > ano) {
                            resultado.add(veiculo);
                        }
                    }
                }
                if (categoria.getUsados() != null) {
                    for (Veiculo veiculo : categoria.getUsados()) {
                        if (veiculo.getAno() > ano) {
                            resultado.add(veiculo);
                        }
                    }
                }
            }
        }

        return resultado;
    }
}
