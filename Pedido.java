import java.util.Date;
import java.util.Map;

public class Pedido extends Transaccion {
    private String proveedor;

    public Pedido(String id, User empleado, String proveedor) {
        super(id, empleado);
        this.proveedor = proveedor;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    //Metodo para finalizar el pedido---------------------------------
    public void finalizarPedido() { //método pendiente de revisión exhaustiva.
    // Verificar si hay artículos en el carrito de la transacción
      Map<Product, Integer> carrito = getCarrito();
      if (carrito.isEmpty()) {
          System.out.println("El carrito está vacío. No se puede finalizar el pedido.");
          return;
      }
  
      // Añadir unidades del carrito a la bodega
      for (Map.Entry<Product, Integer> entry : carrito.entrySet()) {
          Product producto = entry.getKey();
          int cantidad = entry.getValue();
          
          // Buscar el producto en la bodega por ID
          ProductoBodega productoBodega = bodega.buscarProductoPorId(producto.getId()); //????
          
          if (productoBodega != null) {
              // Si el producto existe en la bodega, incrementar la cantidad
              productoBodega.incrementarCantidad(cantidad);
          } else {
              // Si el producto no existe en la bodega, crearlo y agregarlo
              productoBodega = new ProductoBodega(producto, cantidad);
              bodega.agregarProducto(productoBodega);
          }
      }
  
      // Mostrar mensaje de éxito y generar el recibo de la transacción
      System.out.println("Pedido finalizado. Unidades añadidas a la bodega.");
      this.generarRecibo();
    }




  
}

//falta el metodo finalizar pedido
    //public void FinalizarPedido() {
        //System.out.println("Falta implementar la lógica");
        //logica para finalizar el pedido.
        //se añaden unidades del carrito a la bodega (buscar id del producto antes, en caso de no estar se crea primero)
        //no se admiten más cambios del usuario una vez que esto se hace.
    //}
