import java.util.ArrayList;

public class Sistema {
    ArrayList<Equacao> sist;

    public Sistema() {
        sist = new ArrayList<>();
    }

    public ArrayList<Equacao> getSist() {
        return this.sist;
    }

    public void criarSist(int linha, int coluna) {
        for (int j = 0; j < linha; j++) {
            Equacao eq = new Equacao();
            eq.criaEquacao(coluna);
            sist.add(eq);
        }
    }

    public void preencheSist(int i, int j, double valor){
        sist.get(i).getEq().set(j, valor);
    }
    public void setResposta(int i,double valor){
        sist.get(i).setResposta(valor);
    }


    //essa funcao isola  as equacoes do sistema e ordena: x,y,z...
    public void isolaSist() {
        ArrayList<Integer> aux = new ArrayList<>();

        for (int i = 0; i < sist.size(); i++) {
            sist.get(i).isola(aux);
        }
        aux.clear();

    }

    public void imprime() {
        for (int i = 0; i < sist.size(); i++) {
            sist.get(i).imprimeEq();
        }
        System.out.println("\n\n\n");
    }

}


