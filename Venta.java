import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Venta extends Transaccion {
  
    public Venta(String id, Map<Product, Integer> carrito, Date fecha, User empleado) { 
        super(id, empleado);
    }

    public void finalizarVenta(Inventario_Vendido inventarioVendido) {
        inventarioVendido.agregarVenta(this);
        this.generarRecibo();
    }
}
