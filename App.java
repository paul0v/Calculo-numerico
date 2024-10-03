public class App {
    // Test o escaloamento
    public static void main(String[] args) {
        Sistema sist = new Sistema();
        sist.criarSist(3, 3);
        sist.preencheSist(0, 0, 1);
        sist.preencheSist(0, 1, 2);
        sist.preencheSist(0, 2, 3);
        sist.setResposta(0, 1);
        sist.preencheSist(1, 0, 4);
        sist.preencheSist(1, 1, 5);
        sist.preencheSist(1, 2, 6);
        sist.setResposta(1, 2);
        sist.preencheSist(2, 0, 7);
        sist.preencheSist(2, 1, 8);
        sist.preencheSist(2, 2, 9);
        sist.setResposta(2, 3);
        sist.imprime();

        Escalonamento escalonamento = new Escalonamento();
        escalonamento.escalonarSistema(sist);
        sist.imprime();
        escalonamento.resolverSistema(sist);
        sist.imprime();

    }
}
