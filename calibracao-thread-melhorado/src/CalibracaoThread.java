import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class CalibracaoThread extends Thread {
    private List<String> calibrations;
    private AtomicInteger somaTotal;
    private AtomicInteger countTotal;
    private String threadName;
    private CountDownLatch latch;

    public CalibracaoThread(List<String> calibrations, String threadName,
                            AtomicInteger somaTotal, AtomicInteger countTotal,
                            CountDownLatch latch) {
        this.calibrations = calibrations;
        this.threadName = threadName;
        this.somaTotal = somaTotal;
        this.countTotal = countTotal;
        this.latch = latch;
    }

    @Override
    public void run() {
        int soma = 0;
        int count = 0;
        for (String line : calibrations) {
            soma += Calibracao.valorCalibracao(line);
            count++;
            System.out.println(threadName + " - Soma parcial: " + soma + ", Linhas processadas: " + count);
        }
        somaTotal.addAndGet(soma);
        countTotal.addAndGet(count);
        System.out.println(threadName + " finalizou com soma: " + soma + " e total de linhas: " + count);
        latch.countDown();
    }
}