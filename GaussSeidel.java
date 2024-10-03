import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class GaussSeidel {
    private Sistema sist;
    private ArrayList<Double> chute;
    private ArrayList<Double> auxiliar;


    public GaussSeidel(Sistema sist) {
        this.sist = sist;
        chute = new ArrayList<>();
        auxiliar = new ArrayList<>();
    }

    public void preencheChute(double valor) {
            chute.add(valor);
    }

    private void iteracao() {

        for (int i = 0; i < sist.getSist().size(); i++) {
            chute.set(i, sist.getSist().get(i).resolveEq(chute));
        }


    }

    private double atualizaErro() {
        double ret = 0, aux;
        BigDecimal arredondar = BigDecimal.valueOf(0);

        for (int i = 0; i < chute.size(); i++) {
            aux = (chute.get(i) - auxiliar.get(i));
            if (aux < 0) {
                aux *= -1;
            }
            if (aux > ret) {
                ret = aux;
            }
        }
        arredondar = BigDecimal.valueOf(ret);
        arredondar = arredondar.setScale(8, RoundingMode.HALF_UP);
        ret = arredondar.doubleValue();
        return ret;
    }

    public String resolvePorErro(double erro) {
        double erroAt = erro + 1;
        int qtIt = 1;
        String ret;
        sist.imprime();

        while (erroAt > erro) {
            if (qtIt > 200) {
                break;
            }
            auxiliar.clear();
            auxiliar.addAll(chute);

            iteracao();
            erroAt = atualizaErro();
            imprimeChute(qtIt, erroAt);

            System.out.println("erro atual: " + erroAt + "\n numero de Iteracoes: " + qtIt);
            qtIt++;
        }
        ret=imprimeChute(qtIt, erroAt);
        return ret;
    }

    public String resolvePorIteracao(int i) {
        int qtIt = 1;
        double erroAt=0;
        String ret;
        sist.imprime();

        while (qtIt <= i) {
            auxiliar.clear();
            auxiliar.addAll(chute);
            iteracao();
            erroAt = atualizaErro();

            imprimeChute(qtIt,erroAt);
            System.out.println("erro atual: " + erroAt + "\n numero de Iteracoes: " + qtIt);
            qtIt++;
        }
        ret=imprimeChute(qtIt, erroAt);
        return ret;
    }

    public String imprimeChute(int qtIt, double erroAt) {
        String ret="";
        for (int i = 0; i < chute.size(); i++) {
            System.out.println("x" + i + "= " + chute.get(i) + "\n");
            ret+="x" + i + "= " + chute.get(i) + "\n";
        }
        ret+= "erro atual: " + erroAt + "\nnumero de Iteracoes: " + qtIt+"\n";
        return ret;
    }
}
