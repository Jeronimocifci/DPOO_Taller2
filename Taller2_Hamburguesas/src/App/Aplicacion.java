package App;

import Modelo.Restaurante;

import java.util.Scanner;

public class Aplicacion {

    private Restaurante restaurante;

    public Aplicacion() {
        this.restaurante = new Restaurante();
        // Cargar información del restaurante desde archivos
    }

    public static void main(String[] args) {
        Aplicacion app = new Aplicacion();
        app.mostrarMenu();
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("1. Mostrar el menú");
            System.out.println("2. Iniciar un nuevo pedido");
            System.out.println("3. Agregar un elemento a un pedido");
            System.out.println("4. Cerrar un pedido y guardar la factura");
            System.out.println("5. Consultar la información de un pedido dado su id");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            ejecutarOpcion(opcion);
        } while (opcion != 0);
    }

    public void ejecutarOpcion(int opcionSeleccionada) {
        Scanner scanner = new Scanner(System.in);
        switch (opcionSeleccionada) {
            case 1:
                // Mostrar el menú del restaurante
                // Puedes usar el método restaurante.getMenuBase() aquí para obtener los elementos del menú
                break;
            case 2:
                // Iniciar un nuevo pedido
                System.out.println("Ingrese el nombre del cliente:");
                String nombre = scanner.nextLine();
                System.out.println("Ingrese la dirección del cliente:");
                String direccion = scanner.nextLine();
                restaurante.iniciarPedido(nombre, direccion);
                break;
            case 3:
                // Agregar un elemento a un pedido
                // Aquí puedes mostrar los elementos del menú y permitir al usuario elegir qué añadir
                break;
            case 4:
                // Cerrar un pedido y guardar la factura
                restaurante.cerrarYGuardarPedido();
                break;
            case 5:
                // Consultar la información de un pedido dado su id
                // Puedes usar un método como restaurante.getPedidoPorId(id) si tienes uno
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }
}







