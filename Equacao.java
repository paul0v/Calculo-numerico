import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;

public class Equacao {
    private ArrayList<Double> variaveis;
    private double resposta;
    private int indiceIsolado;

    public Equacao() {
        variaveis = new ArrayList<>();

    }

    public int getIndiceIsolado() {
        return indiceIsolado;
    }

    public ArrayList<Double> getEq() {
        return variaveis;
    }

    public void criaEquacao(int n) {
        for (int i = 0; i < n; i++) {
            variaveis.add(0.0);
        }
        resposta = 0;
    }

    public void setResposta(double valor) {
        resposta = valor;
    }

    // essa funçao isola a variavel que ainda nao foi isolada e ja divide as demais
    // variaveis pelo seu coeficiente
    public void isola(ArrayList<Integer> indices) {// Espera um ArrayList com os Indices ja usados.
        double aux = 0;

        for (int i = 0; i < variaveis.size(); i++) {
            if (variaveis.get(i) != 0 && !indices.contains(i)) {
                indices.add(i);
                indiceIsolado = i;
                Collections.swap(variaveis, i, 0);
                break;
            }
        }
        aux = variaveis.get(0);

        for (int j = 0; j < variaveis.size(); j++) {
            if (j != 0) {
                variaveis.set(j, variaveis.get(j) * (-1));
            }

            variaveis.set(j, variaveis.get(j) / aux);
        }
        resposta = resposta / aux;
    }

    public int qntZero() {
        int ret = 0;
        for (int i = 0; i < variaveis.size(); i++) {
            if (variaveis.get(i) == 0) {
                ret++;
            }
        }
        return ret;
    }

    public double resolveEq(ArrayList<Double> chute) {
        BigDecimal arredondar = BigDecimal.valueOf(0);
        double ret = 0;

        for (int i = 1; i < variaveis.size(); i++) {
            if (i == indiceIsolado) {
                ret += variaveis.get(i) * chute.get(0);
            } else {
                ret += variaveis.get(i) * chute.get(i);
            }
        }
        ret += resposta;
        arredondar = BigDecimal.valueOf(ret);
        arredondar = arredondar.setScale(8,RoundingMode.HALF_UP);
        ret = arredondar.doubleValue();
        return ret;
    }

    public void imprimeEq() {
        for (int i = 0; i < variaveis.size(); i++) {
            System.out.print(variaveis.get(i) + " ");
        }
        System.out.println("= " + resposta);
    }

    public double getResposta() {
        return resposta;
    }

}
