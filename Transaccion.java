import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Transaccion {
    private String id;
    private Map<Product, Integer> carrito;
    private Date fecha;
    private User empleado;

    public Transaccion(String id, User empleado){ 
        this.id = id;
        this.carrito = new HashMap<>();
        this.fecha = new Date();
        this.empleado = empleado;
    }

    public String consultar_id() {
        return id;
    }

    public Date consultar_fecha() {
        return fecha;
    }

    public User consultar_empleado() {
        return empleado;
    }

    public void modificar_fecha(Date nuevaFecha) {
        this.fecha = nuevaFecha;
    }

    public void agregarProducto(Product producto, Integer qty) {
        if (carrito.containsKey(producto)) {
            int cantidadActual = carrito.get(producto);
            carrito.put(producto, cantidadActual + qty);
        } else {
            carrito.put(producto, qty);
        }
    }

    public void quitarProducto(Integer id) {
        List<Product> productos = new ArrayList<>(carrito.keySet());
        for (Product producto : productos) {
            if (producto.getId().equals(String.valueOf(id))) {
                carrito.remove(producto);
                break;
            }
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : carrito.entrySet()) {
            Product producto = entry.getKey();
            int cantidad = entry.getValue();
            total += producto.getPrecio() * cantidad;
        }
        return total;
    }

    public void mostrarProductosEnCarrito() {
        System.out.println("Productos en el carrito:");
        for (Map.Entry<Product, Integer> entry : carrito.entrySet()) {
            Product producto = entry.getKey();
            int cantidad = entry.getValue();
            System.out.println(producto.getNombre() + " - Cantidad: " + cantidad);
        }
    }

    public void generarRecibo() {
        System.out.println("Recibo de Transacción:");
        System.out.println("ID de Transacción: " + id);
        System.out.println("Fecha: " + fecha);
        System.out.println("Empleado: " + consultar_empleado());
        System.out.println("Productos:");

        for (Map.Entry<Product, Integer> entry : carrito.entrySet()) {
            Product producto = entry.getKey();
            int cantidad = entry.getValue();
            System.out.println("- " + producto.getNombre() + " (ID: " + producto.getId() + ") - Cantidad: " + cantidad + " - Precio Unitario: " + producto.getPrecio());
        }

        float total = calcularTotal();
        System.out.println("Total: " + total);
    }

    public HashMap consultar_Carrito(){
      return carrito;
    }
}
