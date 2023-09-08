package Modelo;

public class ProductoMenu implements Producto {
    private String nombre;
    private int precioBase;

    public ProductoMenu(String nombre, int precioBase) {
        this.nombre = nombre;
        this.precioBase = precioBase;
    }

    // Implementando el método getNombre() de la interfaz Producto
    public String getNombre() {
        return nombre;
    }

    public int getPrecioBase() {
        return precioBase;
    }

    // Implementando el método getPrecio() de la interfaz Producto
    public int getPrecio() {
        return precioBase;
    }

    public String generarTextoFactura() {
        // Implementación del método para generar una línea de texto para la factura
        StringBuilder facturaTexto = new StringBuilder();
        facturaTexto.append("Producto: ").append(this.nombre)
                    .append(", Precio Base: ").append(this.precioBase);
        
        return facturaTexto.toString();
    }
}


