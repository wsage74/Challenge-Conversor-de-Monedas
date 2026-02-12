package conversordemoneda.util;

public class Conversor {

    public double convertir(double monto, double tasa) {
        double resultadoRedondeado = monto * tasa;
        resultadoRedondeado = Math.round(resultadoRedondeado * 100.0) / 100.0;
        return resultadoRedondeado;
    }
}
