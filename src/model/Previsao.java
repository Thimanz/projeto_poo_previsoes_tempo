package model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Previsao {
    private final int codigo;
    private final double temperaturaMin;
    private final double temperaturaMax;
    private final String cidade;
    private final String data;

}
