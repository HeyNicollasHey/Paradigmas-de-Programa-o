import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        long tempoInicial = System.currentTimeMillis();

        Path path = Paths.get(System.getProperty("user.dir"), "src", "new_calibration_text.txt");
        List<String> calibrations = Files.readAllLines(path);

        int numThreads = 3;
        int batchSize = calibrations.size() / numThreads;

        CountDownLatch latch = new CountDownLatch(numThreads);

        AtomicInteger somaTotal = new AtomicInteger(0);
        AtomicInteger countTotal = new AtomicInteger(0);

        for (int i = 0; i < numThreads; i++) {
            int start = i * batchSize;
            int end = (i == numThreads - 1) ? calibrations.size() : (i + 1) * batchSize;

            new CalibracaoThread(
                    calibrations.subList(start, end),
                    "Thread-" + (i + 1),
                    somaTotal,
                    countTotal,
                    latch
            ).start();
        }

        latch.await();

        System.out.println("A soma total dos valores é: " + somaTotal.get());
        System.out.println("Total de linhas processadas: " + countTotal.get());

        long tempoFinal = System.currentTimeMillis();
        System.out.printf("Tempo total: %.3f segundos%n", (tempoFinal - tempoInicial) / 1000d);
    }
}