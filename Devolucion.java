public class Devolucion extends Transaccion {
    
    public Devolucion(String id, User empleado) {
        super(id, empleado);
    }

    //Metodo para finalizar la devolucion---------------------------------
    // public void finalizarDevolucion(Inventario_Devuelto inventarioDevuelto, Inventario_General inventarioGeneral) {
    //     // Añadir unidades del carrito a la lista de devolucion
    //     // for (Map.Entry<Product, Integer> entry : getCarrito().entrySet()) {
    //     //     Product producto = entry.getKey();
    //     //     int cantidad = entry.getValue();
    
    //     //     // Buscar el producto en el inventario general por ID
    //     //     Product productoDevuelto = inventarioGeneral.buscarProductoId(producto.getId());
    
    //     //     if (productoDevuelto != null) {
    //     //         // Si el producto existe en el inventario general se agrega
    //     //         inventarioDevuelto.agregarADevoluciones(productoDevuelto, cantidad);
    //     //     } else {
    //     //         // Si el producto no existe mostramos un mensaje de error
    //     //         System.out.println("ERROR: El producto que se quiere devolver no se maneja en el establecimiento");
    //     //     }
    //     // }
    
    //     // Mostrar mensaje de que se guardó con éxito, queda pendiente a verificación
    //     System.out.println("Devolucion finalizada. Unidades añadidas a devolución.");
    // }   
    
    public void agregarProducto(Product producto) {
        if (carrito.containsKey(producto)) {
            int cantidadActual = carrito.get(producto);
            carrito.put(producto, cantidadActual + 1);
        } else {
            carrito.put(producto, 1);
        }
    }
    
    public void eliminarProducto(Product producto) {
        carrito.remove(producto);
    }
    
}
