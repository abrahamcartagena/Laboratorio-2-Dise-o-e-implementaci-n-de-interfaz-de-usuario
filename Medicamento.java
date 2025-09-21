package com.mycompany.farmacia;

/**
 * Clase que representa un medicamento dentro de la farmacia
 * @author abraham.cartagena
 */
public class Medicamento {
    
    // Atributos principales del medicamento
    private String nombre;
    private String tipo;   // Ej: antibiótico, analgésico, jarabe
    private double precio;
    private int stock;     // Cantidad disponible en inventario

    /**
     * Constructor con parámetros
     * @param nombre nombre del medicamento
     * @param tipo tipo de medicamento
     * @param precio precio del medicamento
     * @param stock cantidad en inventario
     */
    public Medicamento(String nombre, String tipo, double precio, int stock) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    // Setters
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Método para reducir el stock después de una venta
     * @param cantidad cantidad vendida
     */
    public void vender(int cantidad) {
        if (cantidad > 0 && cantidad <= stock) {
            stock -= cantidad;
        } else {
            System.out.println("No hay suficiente stock para vender " + cantidad + " unidades.");
        }
    }

    /**
     * Método para mostrar la información del medicamento
     */
    @Override
    public String toString() {
        return nombre + " (" + tipo + ") - $" + precio + " | Stock: " + stock;
    }
}
