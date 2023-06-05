import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.AbstractMap.SimpleEntry;

public class Inventario_Vendido extends Inventario_General {
    private List<Venta> ventas;

    public Inventario_Vendido() {
        super();
        ventas = new ArrayList<>(); //verificación de lista pendiente
    }

    public SimpleEntry<Pair<Map<User, Double>, Map<User, Integer>>, Double> GenerarReporte() {
        Map<User, Double> totalVentasPorEmpleado = new HashMap<>();
        Map<User, Integer> cantidadVentasPorEmpleado = new HashMap<>();
        double totalVentas = 0;

        for (Venta venta : ventas) {
            User empleado = venta.getEmpleado();
            double ventaTotal = venta.calcularTotal();

            totalVentasPorEmpleado.put(empleado, totalVentasPorEmpleado.getOrDefault(empleado, 0.0) + ventaTotal);
            cantidadVentasPorEmpleado.put(empleado, cantidadVentasPorEmpleado.getOrDefault(empleado, 0) + 1);
            totalVentas += ventaTotal;
        }

        return new SimpleEntry<>(new Pair<>(totalVentasPorEmpleado, cantidadVentasPorEmpleado), totalVentas);
    }

    public int consultar_cantidad() {
      //Retorna la cantidad de ventas realizadas históricamente
        return ventas.size();
    }

    //Método para agregar una venta a la lista Ventas
    public void agregarVenta(Venta venta) {
        ventas.add(venta);
    }

    //Busca una venta en el inventario por medio de su ID.
    public Venta buscarVenta(String id) {
      for (Venta venta : ventas) {
          if (venta.getId().equals(id)) {
              return venta;
          }
      }
      return null;  
    }
    
    public List<Venta> get_lista_Ventas() {
        return ventas;
    }

}
