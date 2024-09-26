package factory;

import imposto.*;

public class ImpostoFactory {

    public static Imposto imposto(TipoImposto tipoImposto) {
        switch (tipoImposto) {
            case TIPO_ICMS -> {
                return new ICMS();
            }
            case TIPO_IPI -> {
                return new IPI();
            }
            case TIPO_ISS -> {
                return new ISS();
            }
            case TIPO_PIS -> {
                return new PIS();
            }
            default -> throw new IllegalArgumentException("Tipo de imposto desconhecido");
        }
    }
}
