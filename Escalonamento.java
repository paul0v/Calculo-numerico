import java.util.ArrayList;

public class Escalonamento {

    public boolean escalonarSistema(Sistema sist) {
        int numRows = sist.getSist().size();
        int numCols = sist.getSist().get(0).getEq().size() - 1; // Exclude the response column

        for (int i = 0; i < numRows; i++) {
            double pivot = sist.getSist().get(i).getEq().get(i);

            if (pivot == 0) {
                return false; // System is inconsistent or indeterminate
            }

            // Scale the current equation
            for (int j = i; j <= numCols; j++) {
                sist.getSist().get(i).getEq().set(j, sist.getSist().get(i).getEq().get(j) / pivot);
            }

            // Eliminate other equations
            for (int k = 0; k < numRows; k++) {
                if (k != i) {
                    double factor = sist.getSist().get(k).getEq().get(i);
                    for (int j = i; j <= numCols; j++) {
                        sist.getSist().get(k).getEq().set(j, sist.getSist().get(k).getEq().get(j) - factor * sist.getSist().get(i).getEq().get(j));
                    }
                }
            }
        }

        return true;
    }

    public String resolverSistema(Sistema sist) {
        int numRows = sist.getSist().size();
        String ret = "";
        ArrayList<Double> solution = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            solution.add(sist.getSist().get(i).getEq().get(numRows));
            ret+= "x" + (i+1) + " = " + solution.get(i) + "\n";
        }

        return ret;
    }
}
