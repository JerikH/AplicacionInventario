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

    //Constructor
    public Transaccion(String id, User empleado){ 
        this.id = id;
        this.carrito = new HashMap<>();
        this.fecha = new Date();
        this.empleado = empleado;
    }

    public String getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public User getEmpleado() {
        return empleado;
    }

    public void setFecha(Date nuevaFecha) {
        this.fecha = nuevaFecha;
    }

    //Método para agregar un Producto al carrito
    public void agregarProducto(Product producto, Integer qty) {
        if (carrito.containsKey(producto)) {
            int cantidadActual = carrito.get(producto);
            carrito.put(producto, cantidadActual + qty);
        } else {
            carrito.put(producto, qty);
        }
    }

    //Método para buscar un producto con Id en el carrito de compras
    public Product buscarProducto(String Id){
        for (Product producto : carrito.keySet()){
            if (producto.getId().equals(Id)){
                return producto;
            }
        }
        return null;
    }

    //Método para consultar la cantidad de unidades de un producto  
    public int consultarCantUnidades(String Id) {
        for (Product producto : carrito.keySet()){
            if (producto.getId().equals(Id)){
                return carrito.get(producto);
            }
        }
        return 0;
    }

    //Método para eliminar un producto con el Id
    public void quitarProducto(String id) {
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

    //Método para mostrar productos en el carrito en una transacción
    public void mostrarProductosEnCarrito() {
        System.out.println("ID\tNOMBRE\tCANT.\tPRE_UNIT\tPRE_TOTAL");
        for (Map.Entry<Product, Integer> entry : carrito.entrySet()) {
            Product producto = entry.getKey();
            String idproducto = producto.getId();
            String nombre = producto.getNombre();
            int cantidad = entry.getValue();
            float precio = producto.getPrecio();
            float precio_total = precio*cantidad;
            System.out.println(idproducto+"\t"+nombre+"\t"+cantidad+"\t"+precio+"\t"+precio_total);
        }
    }

    //Método para generar recibo de una transacción
    public void generarRecibo(String postfix) {
        System.out.println("Recibo de "+ postfix + ":");
        System.out.println("ID de "+ postfix+": " + id);
        System.out.println("Fecha: " + fecha);
        System.out.println("Empleado: " + this.getEmpleado());
        System.out.println("Productos:");

        for (Map.Entry<Product, Integer> entry : carrito.entrySet()) {
            Product producto = entry.getKey();
            int cantidad = entry.getValue();
            System.out.println("- " + producto.getNombre() + " (ID: " + producto.getId() + ") - Cantidad: " + cantidad + " - Precio Unitario: " + producto.getPrecio());
        }

        double total = calcularTotal();
        System.out.println("Total: " + total);
    }

    public Map<Product, Integer> getCarrito(){
      return carrito;
    }
}
