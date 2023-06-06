public class Devolucion extends Transaccion {
    
    public Devolucion(String id, User empleado) {
        super(id, empleado);
    }

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
