package conversordemoneda.principal;

import conversordemoneda.service.ConexionAPI;
import conversordemoneda.util.Conversor;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        ConexionAPI servicio = new ConexionAPI();
        Conversor conversor = new Conversor();

        int op;

        do {
            op = menu();

            switch (op) {
                case 1:
                    realizarConversion(servicio, conversor, "COP", "USD");
                    break;
                case 2:
                    realizarConversion(servicio, conversor, "USD", "COP");
                    break;
                case 3:
                    realizarConversion(servicio, conversor, "COP", "CLP");
                    break;
                case 4:
                    realizarConversion(servicio, conversor, "CLP", "USD");
                    break;
                case 5:
                    realizarConversion(servicio, conversor, "COP", "ARS");
                    break;
                case 6:
                    realizarConversion(servicio, conversor, "ARS", "USD");
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }

        } while (op != 0);
    }
    public static int menu() {
        int eleccion;

        System.out.println("""
                \nBienvenido al Conversor de Divisas
                
                1. COP a USD
                2. USD a COP
                3. COP a CLP(Peso chileno)
                4. CLP a USD
                5. COP a ARS
                6. ARS a USD
               
                0. Salir
                """);
        Scanner lectura = new Scanner(System.in);
        eleccion = Integer.parseInt(lectura.nextLine());
        return eleccion;
    }
    private static void realizarConversion(
            ConexionAPI servicio,
            Conversor conversor,
            String moneda1,
            String moneda2) throws Exception {

        System.out.println("Ingrese el monto:");
        Scanner lectura = new Scanner(System.in);
        double monto = Double.parseDouble(lectura.nextLine());

        double tasa = servicio.obtenerTasa(moneda1, moneda2);
        double resultado = conversor.convertir(monto, tasa);

        System.out.println("El monto: " + monto + " " + moneda1 + " Es igual a: " + resultado + " " + moneda2);
        System.out.printf("Tasa actual: %.6f%n", tasa);

    }
}