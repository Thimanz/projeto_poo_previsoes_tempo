package model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Previsao {
    private final double temperaturaMin;
    private final double temperaturaMax;
    @Getter
    private final String cidade;
    private final String data;

    @Override
    public String toString() {
        return String.format("Data: %s    Temp Min: %.1f    Temp Max: %.1f\n", data, temperaturaMin, temperaturaMax);
    }
}