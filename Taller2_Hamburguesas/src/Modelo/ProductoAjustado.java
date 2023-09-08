package Modelo;

import java.util.ArrayList;

public class ProductoAjustado implements Producto {
    private ProductoMenu base;
    private ArrayList<Ingrediente> agregados = new ArrayList<>();
    private ArrayList<Ingrediente> eliminados = new ArrayList<>();

    public ProductoAjustado(ProductoMenu base) {
        this.base = base;
    }

    public String getNombre() {
        return base.getNombre() + " (Ajustado)";
    }

    public int getPrecio() {
        int precioBase = base.getPrecio();
        for (Ingrediente ingrediente : agregados) {
            precioBase += ingrediente.getCostoAdicional();
        }
        for (Ingrediente ingrediente : eliminados) {
            precioBase -= ingrediente.getCostoAdicional();
        }
        return precioBase;
    }

    public String generarTextoFactura() {
        StringBuilder sb = new StringBuilder();
        sb.append("Producto: ").append(getNombre()).append("\n");
        sb.append("Precio: ").append(getPrecio()).append("\n");
        sb.append("Ingredientes agregados: ");
        for (Ingrediente i : agregados) {
            sb.append(i.getNombre()).append(", ");
        }
        sb.append("\nIngredientes eliminados: ");
        for (Ingrediente i : eliminados) {
            sb.append(i.getNombre()).append(", ");
        }
        return sb.toString();
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        agregados.add(ingrediente);
    }

    public void eliminarIngrediente(Ingrediente ingrediente) {
        eliminados.add(ingrediente);
    }
}
