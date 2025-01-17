import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner (System.in);
        Conversor conversor = new Conversor();

        while (true) {
            System.out.println("  ¡¡Bienvenido al conversor de divisas!!  ");
            System.out.println(" Por favor seleccione una opción: \n" +
                    "1. Convertir moneda \n" +
                    "2. Salir");

            int opcion = teclado.nextInt();
            if (opcion == 1) {
                System.out.println("Selecione el tipo de moneda que desea convertir: \n");
                System.out.println("1. USD - (Dolar USA) \n" +
                        "2. EUR (Euro) \n" +
                        "3. GBP (Libra esterlina) \n" +
                        "4. JPY (Yen japones) \n" +
                        "5. CNY (Yuan Chino) \n" +
                        "6. COP (Peso colombiano) \n" +
                        "7. ARS (Peso Argentino) \n" +
                        "8. MXN (Peso mexicano) \n" +
                        "9. CLP (Peso chileno)\n" +
                        "10. PEN (Sol peruano) ");
                String moneda = teclado.next().toUpperCase();
                System.out.println("Ingrese el tipo de moneda que desea obtener");
                String monedaConvertida = teclado.next().toUpperCase();
                System.out.println("Ingrese el monto a convertir");
                double monto = teclado.nextDouble();

                double montoConvertido = conversor.convertirMoneda(moneda, monedaConvertida, monto);
                System.out.println(String.format("El monto en %s es: %.2f %s\n", monedaConvertida, montoConvertido, monedaConvertida));

            } else if (opcion == 2) {
                conversor.almacenar();
                conversor.mostrarResumenConversiones();
                break;  // Salir del ciclo infinito
            } else {
                System.out.println("Oopción invalida, por favor ingrese una opción valida.");
            }
        }

        teclado.close();
    }
}




