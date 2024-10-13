import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TrabalhadorBarreira implements Runnable {
    private final CyclicBarrier barreira;
    private final Object leitorDados;

    public TrabalhadorBarreira(CyclicBarrier barreira, Object leitorDados) {
        this.barreira = barreira;
        this.leitorDados = leitorDados;
    }

    @Override
    public void run() {
        try {
            if (leitorDados instanceof LeitorDespesas) {
                System.out.println(((LeitorDespesas) leitorDados).lerDespesas());
            } else if (leitorDados instanceof LeitorReceitas) {
                System.out.println(((LeitorReceitas) leitorDados).lerReceitas());
            } else if (leitorDados instanceof LeitorProvisao) {
                System.out.println(((LeitorProvisao) leitorDados).lerProvisao());
            }

            System.out.println(Thread.currentThread().getName() + " concluiu sua tarefa e está aguardando na barreira.");
            barreira.await();

            System.out.println(Thread.currentThread().getName() + " ultrapassou a barreira e continua a execução.");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}