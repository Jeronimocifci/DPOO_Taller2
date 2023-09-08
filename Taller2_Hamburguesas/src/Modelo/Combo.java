package Modelo;

import java.util.ArrayList;
//combos.add(new Combo(nombreCombo, productosCombo));
public class Combo implements Producto {
    private double descuento;
    private String nombreCombo;
    private ArrayList<Producto> productosCombo;

    public Combo(String nombre, double descuento) {
        this.nombreCombo = nombre;
        this.descuento = descuento;
        this.productosCombo = new ArrayList<>();
    }

    public void agregarItemACombo(Producto itemCombo) {
    	productosCombo.add(itemCombo);
    }

    @Override
    public int getPrecio() {
        int precioTotal = 0;
        for (Producto item : productosCombo) {
            precioTotal += item.getPrecio();
        }
        return (int) (precioTotal * (1 - descuento));
    }

    @Override
    public String getNombre() {
        return nombreCombo;
    }

    public String generarTextoFactura() {
        StringBuilder facturaTexto = new StringBuilder();
        facturaTexto.append("Combo: ").append(this.nombreCombo)
                    .append(", Precio con Descuento: ").append(this.getPrecio())
                    .append(", Items en Combo: ");

        for (Producto item : productosCombo) {
            facturaTexto.append(item.getNombre()).append(", ");
        }

        // Elimina la última coma y espacio
        if (productosCombo.size() > 0) {
            facturaTexto.delete(facturaTexto.length() - 2, facturaTexto.length());
        }

        return facturaTexto.toString();
    }
}

