public class Calibracao {
    public static int valorCalibracao(String linha) {
        Character primeiroCaractere = null;
        Character ultimoCaractere = null;

        for (char c : linha.toCharArray()) {
            if (Character.isDigit(c)) {
                if (primeiroCaractere == null) {
                    primeiroCaractere = c;
                }
                ultimoCaractere = c;
            }
        }

        if (primeiroCaractere != null && ultimoCaractere != null) {
            return Integer.parseInt("" + primeiroCaractere + ultimoCaractere);
        } else {
            return 0;
        }
    }
}
