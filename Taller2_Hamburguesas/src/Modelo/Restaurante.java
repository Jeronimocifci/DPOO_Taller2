package Modelo; 

import java.io.File;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Restaurante {
    
    // Atributos
    private ArrayList<Ingrediente> ingredientes = new ArrayList<>();
    private ArrayList<Producto> menuBase = new ArrayList<>();
    private ArrayList<Combo> combos = new ArrayList<>();
    private Pedido pedidoEnCurso;
    private ArrayList<Pedido> pedidos = new ArrayList<>();

    // Constructor
    public Restaurante() {
        // Carga inicial de los datos
        cargarInformacionRestaurante(new File("ingredientes.txt"), new File("menu.txt"), new File("combos.txt"));
    }

    // Métodos públicos
    public void iniciarPedido(String nombreCliente, String direccionCliente) {
        pedidoEnCurso = new Pedido(nombreCliente, direccionCliente);
    }

    public void cerrarYGuardarPedido() {
        // Guarda el pedido en un archivo o en una base de datos
        pedidos.add(pedidoEnCurso);
        pedidoEnCurso = null;
    }
    
    public Pedido getPedidoEnCurso() {
        return pedidoEnCurso;
    }

    public ArrayList<Producto> getMenuBase() {
        return menuBase;
    }

    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos) {
        cargarIngredientes(archivoIngredientes);
        cargarMenu(archivoMenu);
        cargarCombos(archivoCombos);
    }

    private void cargarIngredientes(File archivoIngredientes) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoIngredientes))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 2) {
                    String nombre = partes[0];
                    int costoAdicional = Integer.parseInt(partes[1]);
                    ingredientes.add(new Ingrediente(nombre, costoAdicional));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarMenu(File archivoMenu) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoMenu))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 2) {
                    String nombre = partes[0];
                    int precioBase = Integer.parseInt(partes[1]);
                    menuBase.add((Producto) new ProductoMenu(nombre, precioBase));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarCombos(File archivoCombos) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCombos))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length >= 3) { 
                    String nombreCombo = partes[0];
                    double descuento = Double.parseDouble(partes[1].replace("%", "")) / 100; 
                    
                    Combo nuevoCombo = new Combo(nombreCombo, descuento);
                    
                    boolean comboValido = true;

                    for (int i = 2; i < partes.length; i++) { 
                        String nombreProducto = partes[i];
                        ProductoMenu producto = buscarProductoEnMenuBase(nombreProducto);

                        if (producto == null) {
                            System.out.println("Error: El producto '" + nombreProducto + "' no se encuentra en el menú base.");
                            comboValido = false;
                            break;
                        }

                        nuevoCombo.agregarItemACombo(producto);
                    }

                    if (comboValido) {
                        combos.add(nuevoCombo);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private ProductoMenu buscarProductoEnMenuBase(String nombreProducto) {
        for (Producto producto : menuBase) {
            if (producto.getNombre().equals(nombreProducto)) {
                return (ProductoMenu) producto;
            }
        }
        return null;
    }
}