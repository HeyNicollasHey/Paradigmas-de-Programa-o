package balanca.factory;

import balanca.enums.TipoBalanca;
import balanca.interfaces.IBalanca;
import balanca.models.Produto;
import balanca.services.FilizolaSmart;
import balanca.services.ToledoMGV6;
import balanca.services.UranoIntegra;

import java.io.IOException;

public class BalancaFactory {
    public static IBalanca<Produto> getBalanca(TipoBalanca tipo) {

        switch (tipo) {
            case FINIZOLA_SMART:
                return new FilizolaSmart();
            case TOLEDO_MGV6:
                return new ToledoMGV6();
            case URANO_INTEGRA:
                return new UranoIntegra();
            default:
                return null;
        }
    }
}
