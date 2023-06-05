import java.util.Map;

public class Pedido extends Transaccion {
    private String proveedor;

    public Pedido(String id, User empleado, String proveedor) {
        super(id, empleado);
        this.proveedor = proveedor;
    }

    //Obtener proveedor de pedido
    public String getProveedor() {
        return proveedor;
    }

    //Set proveedor de pedido
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }


     
    //Metodo para finalizar el pedido---------------------------------
    public void finalizarPedido(Inventario_Bodega inventarioBodega) {
    
        // Añadir unidades del carrito a la bodega
        for (Map.Entry<Product, Integer> entry : getCarrito().entrySet()) {
            Product producto = entry.getKey();
            int cantidad = entry.getValue();

            // Buscar el producto en la bodega por ID
            Product productoBodega = inventarioBodega.buscarProductoId(producto.getId());

            if (productoBodega != null) {
                // Si el producto existe en la bodega, incrementar la cantidad
                inventarioBodega.agregar_unidades(productoBodega,cantidad);
            } else {
                // Si el producto no existe en la bodega, mostrar mensaje de error
                System.out.println("Error: El producto con ID " + producto.getId() + " no existe en la bodega.");
            }
        }

        // Mostrar mensaje de éxito y generar el recibo de la transacción
        System.out.println("Pedido finalizado. Unidades añadidas a la bodega.");
        generarRecibo("Pedido");
    }  
}