public class Venta extends Transaccion {
  
    public Venta(String id,  User empleado) { 
        super(id, empleado);
    }


    public void finalizarVenta(Inventario_Vendido inventarioVendido, Inventario_Exhibicion inventario_exhibicion) {
        
        for (Product producto : carrito.keySet()){
            String idProducto = producto.getId();
            if (!inventario_exhibicion.buscarProductoId(idProducto).equals(null)){// Verificar que el producto exista en el inventario de exhibición.
                inventario_exhibicion.agregar_unidades(producto, (carrito.get(producto)*-1));//Disminuir unidades
                
            } else {
                // Si el producto no existe en exhibición, mostrar mensaje de error
                System.out.println("Error: El producto con ID " + producto.getId() + " no está en el inventario de exhibición.");
            }
        }
        //Mostrar que la venta fue realizada exitosamente y después mostrar el recibo
        inventarioVendido.agregarVenta(this);
        System.out.println("----------VENTA FINALIZADA----------");
        this.generarRecibo("Venta");//Cambiar método generar recibo.
    }
}
