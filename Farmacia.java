package com.mycompany.farmacia;

/**
 * Clase principal para probar la aplicación de la farmacia
 * @author abraham.cartagena
 */
public class Farmacia {

    public static void main(String[] args) {
        
        // Crear algunos medicamentos de ejemplo
        Medicamento m1 = new Medicamento("Paracetamol", "Analgésico", 1500.0, 20);
        Medicamento m2 = new Medicamento("Amoxicilina", "Antibiótico", 2500.0, 15);

        // Mostrar los medicamentos creados
        System.out.println("Inventario inicial:");
        System.out.println(m1);
        System.out.println(m2);

        // Simular una venta
        m1.vender(5);
        System.out.println("\nDespués de vender 5 unidades de Paracetamol:");
        System.out.println(m1);

        // Intentar vender más de lo que hay
        m2.vender(30);
        System.out.println("\nDespués de intentar vender 30 unidades de Amoxicilina:");
        System.out.println(m2);
    }
}
