package Modelo;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class Pedido {
    private static int numeroPedidos = 0;  // Para llevar un conteo del número total de pedidos
    private int idPedido;
    private String nombreCliente;
    private String direccionCliente;
    private ArrayList<Producto> itemsPedido;

    public Pedido(String nombreCliente, String direccionCliente) {
        this.nombreCliente = nombreCliente;
        this.direccionCliente = direccionCliente;
        this.idPedido = ++numeroPedidos;  // Asigna un nuevo ID y aumenta el contador de pedidos
        this.itemsPedido = new ArrayList<>();
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void agregarProducto(Producto nuevoItem) {
        itemsPedido.add(nuevoItem);
    }

    private int getPrecioNetoPedido() {
        int total = 0;
        for (Producto item : itemsPedido) {
            total += item.getPrecio();
        }
        return total;
    }

    private int getPrecioTotalPedido() {
        // Suponiendo un IVA del 19%
        return (int) (getPrecioNetoPedido() * 1.19);
    }

    private int getPrecioIVAPedido() {
        // Suponiendo un IVA del 19%
        return (int) (getPrecioNetoPedido() * 0.19);
    }

    private String generarTextoFactura() {
        StringBuilder factura = new StringBuilder();
        factura.append("Factura para: ").append(nombreCliente).append("\n");
        factura.append("Dirección: ").append(direccionCliente).append("\n");
        factura.append("ID del pedido: ").append(idPedido).append("\n");

        for (Producto item : itemsPedido) {
            factura.append(item.getNombre()).append(" - ").append(item.getPrecio()).append("\n");
        }

        factura.append("Neto: ").append(getPrecioNetoPedido()).append("\n");
        factura.append("IVA: ").append(getPrecioIVAPedido()).append("\n");
        factura.append("Total: ").append(getPrecioTotalPedido()).append("\n");

        return factura.toString();
    }

    public void guardarFactura(File archivo) {
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write(generarTextoFactura());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
