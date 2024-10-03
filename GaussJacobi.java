import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class GaussJacobi {
    private Sistema sist;
    private ArrayList<Double> chute;
    private ArrayList<Double> auxiliar;


    public GaussJacobi(Sistema sist) {
        this.sist = sist;
        chute = new ArrayList<>();
        auxiliar = new ArrayList<>();
    }

    public void preencheChute(Double valor) {
            chute.add(valor);
        
    }

    private void iteracao() {

        for (int i = 0; i < sist.getSist().size(); i++) {
            auxiliar.add(sist.getSist().get(i).resolveEq(chute));
        }


    }

    private double atualizaErro() {
        double ret = 0, aux;
        BigDecimal arredondar = BigDecimal.valueOf(0);

        for (int i = 0; i < auxiliar.size(); i++) {
            aux = (auxiliar.get(i) - chute.get(i));
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
            iteracao();
            erroAt = atualizaErro();
            imprimeChute(qtIt,erroAt);
            System.out.println("erro atual: " + erroAt + "\n numero de Iteracoes: " + qtIt);

            chute.clear();
            chute.addAll(new ArrayList<>(auxiliar));
            auxiliar.clear();
            qtIt++;
        }
        ret=imprimeChute(qtIt, erroAt);
        return ret;
    }   

    public String resolvePorIteracao(int i) {
        double erroAt=0;
        int qtIt = 1;
        String ret="";
        sist.imprime();
        System.out.println("\n\n");
        while (qtIt <= i) {
            iteracao();
            erroAt = atualizaErro();

            chute.clear();
            chute.addAll(new ArrayList<>(auxiliar));
            auxiliar.clear();
            qtIt++;
            System.out.println("\n");
            imprimeChute(qtIt, erroAt);
            System.out.println("erro atual: " + erroAt + "\n numero de Iteracoes: " + qtIt);
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
