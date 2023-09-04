import java.util.Scanner;

public class Unamoneda {
    public static void main(String[] args) {
        // Definir el valor del cambio deseado
        int valorCambioDeseado = 200;

        // Definir los productos y sus precios
        String[] productos = {"Paquete de gomas", "Wafers Nucita", "Agua con gas", "Agua Natural", "Agua Manantial"};
        int[] precios = {2300, 800, 1200, 1000, 1500};

        // Definir las denominaciones de billetes y monedas disponibles
        int[] denominacionesBilletes = {50000, 20000, 10000, 5000, 2000};
        int[] denominacionesMonedas = {1000, 500, 200, 100, 50};

        // Leer el valor del billete o moneda ingresado por el usuario
        Scanner scanner = new Scanner(System.in);
        int billete = 0;
        int moneda = 0;
        int saldo = 0;

        while (true) {
            System.out.print("Ingrese el valor del billete o moneda en pesos colombianos (0 para finalizar): ");
            int valor = scanner.nextInt();

            if (valor == 0) {
                break; // Si el usuario ingresa 0, finalizar el programa
            }

            // Validar si el valor ingresado es una denominación válida
            boolean esDenominacionValida = false;
            for (int denominacion : denominacionesBilletes) {
                if (valor == denominacion) {
                    esDenominacionValida = true;
                    billete += valor;
                    break;
                }
            }

            for (int denominacion : denominacionesMonedas) {
                if (valor == denominacion) {
                    esDenominacionValida = true;
                    moneda += valor;
                    break;
                }
            }

            if (!esDenominacionValida) {
                System.out.println("Denominación no válida, ingrese un valor de billete o moneda válido.");
            }

            saldo = billete + moneda;

            // Mostrar saldo actual
            System.out.println("Saldo actual: " + saldo + " pesos colombianos");
        }

        // Permitir al usuario seleccionar productos
        boolean transaccionCancelada = false;
        while (true) {
            System.out.println("Productos disponibles:");
            for (int i = 0; i < productos.length; i++) {
                System.out.println((i + 1) + ". " + productos[i] + " - Precio: " + precios[i] + " pesos");
            }

            System.out.print("Seleccione un producto (1-" + productos.length + ", 0 para finalizar): ");
            int opcion = scanner.nextInt();

            if (opcion == 0) {
                break; // Si el usuario selecciona 0, finalizar el programa
            }

            // Verificar si el saldo es suficiente para comprar el producto
            if (opcion >= 1 && opcion <= productos.length) {
                int precioProducto = precios[opcion - 1];
                if (saldo >= precioProducto) {
                    saldo -= precioProducto;
                    System.out.println("Producto '" + productos[opcion - 1] + "' comprado. Saldo restante: " + saldo + " pesos colombianos");
                } else {
                    System.out.print("Saldo insuficiente. ¿Desea ingresar más dinero para completar la cantidad? (s/n): ");
                    String respuesta = scanner.next();
                    if (respuesta.equalsIgnoreCase("s")) {
                        // Permitir al usuario ingresar solo denominaciones válidas
                        boolean esDenominacionValida = false;
                        while (!esDenominacionValida) {
                            System.out.print("Ingrese la cantidad adicional en pesos colombianos (0 para finalizar): ");
                            int cantidadAdicional = scanner.nextInt();
                            for (int denominacion : denominacionesBilletes) {
                                if (cantidadAdicional == denominacion) {
                                    esDenominacionValida = true;
                                    saldo += cantidadAdicional;
                                    break;
                                }
                            }
                            for (int denominacion : denominacionesMonedas) {
                                if (cantidadAdicional == denominacion) {
                                    esDenominacionValida = true;
                                    saldo += cantidadAdicional;
                                    break;
                                }
                            }
                            if (!esDenominacionValida) {
                                System.out.println("Denominación no válida, ingrese un valor de billete o moneda válido.");
                            }
                        }
                        // Reintentar la compra después de agregar más dinero
                        continue;
                    } else {
                        break;
                    }
                }
            } else {
                System.out.println("Opción no válida. Seleccione un número válido.");
            }
        }
            int cantidadMonedas = 0;
            while (saldo >= valorCambioDeseado) {
                saldo -= valorCambioDeseado;
                cantidadMonedas++;
            }
            if (saldo == 0){
            	System.out.println("Cantidad de monedas de " + valorCambioDeseado + " en cambio: " + cantidadMonedas);
            }
            else{
            	System.out.println("Transacción Cancelada, no se dispone de cambio suficiente. Devolviendo $" + billete + " pesos colombianos en billete y $" + moneda + " pesos colombianos en moneda.");
    	}
        System.out.println("Gracias por usar la máquina de vending. Hasta luego.");
    }
}

