import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {
        final int NUMERO_THREADS = 3;

        CyclicBarrier barreira = new CyclicBarrier(NUMERO_THREADS, () -> {
            System.out.println("Todas as threads atingiram a barreira e estão sincronizadas. Continuando a execução!");
        });

        LeitorDespesas leitorDespesas = new LeitorDespesas();
        LeitorReceitas leitorReceitas = new LeitorReceitas();
        Thread threadDespesas = new Thread(new TrabalhadorBarreira(barreira, leitorDespesas), "Thread-Despesas");
        Thread threadReceitas = new Thread(new TrabalhadorBarreira(barreira, leitorReceitas), "Thread-Receitas");
        Thread threadProvisao = new Thread(new TrabalhadorBarreira(barreira, new LeitorProvisao()), "Thread-Provisão");

        threadDespesas.start();
        threadReceitas.start();
        threadProvisao.start();

        try {
            threadDespesas.join();
            threadReceitas.join();
            threadProvisao.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Todas as threads finalizaram suas execuções.");

        double valorTotalReceitas = leitorReceitas.obterTotalReceitas();
        double valorTotalDespesas = leitorDespesas.obterTotalDespesas();
        double resultadoFinanceiro = valorTotalReceitas - valorTotalDespesas;

        System.out.println("Total de Receitas: " + valorTotalReceitas);
        System.out.println("Total de Despesas: " + valorTotalDespesas);
        System.out.println("Resultado Financeiro: " + resultadoFinanceiro);
    }
}
