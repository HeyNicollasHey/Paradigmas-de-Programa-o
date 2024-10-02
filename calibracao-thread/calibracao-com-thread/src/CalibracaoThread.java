import java.util.List;

class CalibracaoThread extends Thread {
    private List<String> calibrations;
    private int soma;
    private int count;
    private String threadName;

    public CalibracaoThread(List<String> calibrations, String threadName) {
        this.calibrations = calibrations;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        soma = 0;
        count = 0;
        for (String line : calibrations) {
            soma += Calibracao.valorCalibracao(line);
            count++;
            System.out.println(threadName + " - Soma parcial: " + soma + ", Linhas processadas: " + count);
        }
        System.out.println(threadName + " finalizou com soma: " + soma + " e total de linhas: " + count);
    }

    public int getSoma() {
        return soma;
    }

    public int getCount() {
        return count;
    }
}
