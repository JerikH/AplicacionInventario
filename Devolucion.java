/*import java.util.HashMap;
import java.util.Map;

public class Devolucion extends Transaccion { 
    private Map<Product, Integer> productosDevueltos;

    public Devolucion(String id, Map<Product, Integer> carrito, String fecha, User empleado) {
        super(id, carrito, fecha, empleado); //actualizar valores en Transaccion y después comprobar continuidad de este error type mismatched error.
        this.productosDevueltos = new HashMap<>();
    }

    public void finalizarDevolución() {
        Inventario_Devuelto inventarioDevuelto = new Inventario_Devuelto(); //no existe inventario devuelto
        inventarioDevuelto.agregarDevuelto(this); //no existe inventario devuelto
    }
}
*/