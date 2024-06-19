package atividade.enade;
import java.util.List;

public class VeiculosJson {
    private List<CategoriaVeiculos> carros;
    private List<CategoriaVeiculos> motos;

    public List<CategoriaVeiculos> getCarros() {
        return carros;
    }

    public void setCarros(List<CategoriaVeiculos> carros) {
        this.carros = carros;
    }

    public List<CategoriaVeiculos> getMotos() {
        return motos;
    }

    public void setMotos(List<CategoriaVeiculos> motos) {
        this.motos = motos;
    }
}
