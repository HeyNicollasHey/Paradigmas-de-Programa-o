import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        long tempoInicial = System.currentTimeMillis();

        Path path = Paths.get(System.getProperty("user.dir") + "\\src\\new_calibration_text.txt");
        List<String> calibrations = Files.readAllLines(path);

        int numThreads = 3;
        int batchSize = calibrations.size() / numThreads;

        CalibracaoThread[] threads = new CalibracaoThread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            int start = i * batchSize;
            int end = (i == numThreads - 1) ? calibrations.size() : (i + 1) * batchSize;

            threads[i] = new CalibracaoThread(calibrations.subList(start, end), "Thread-" + (i + 1));
            threads[i].start();
        }

        int somaTotal = 0;
        int countTotal = 0;
        for (CalibracaoThread thread : threads) {
            try {
                thread.join();
                somaTotal += thread.getSoma();
                countTotal += thread.getCount();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("A soma total dos valores é: " + somaTotal);
        System.out.println("Total de linhas processadas: " + countTotal);

        long tempoFinal = System.currentTimeMillis();
        System.out.printf("%.3f ms%n", (tempoFinal - tempoInicial) / 1000d);
    }
}