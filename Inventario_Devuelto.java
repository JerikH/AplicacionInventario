import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Inventario_Devuelto extends Inventario_General {
    private List<Devolucion> devoluciones;
    private List<Devolucion> historico;

    public Inventario_Devuelto() {
        super();
        devoluciones = new ArrayList<>(); //verificación de lista pendiente
        historico = new ArrayList<>();
    }

    public int consultar_cantidad_devoluciones() {
      //Retorna la cantidad de devoluciones.
        return devoluciones.size() + historico.size();
    }

    public int consultar_cantidad_historico() {
        //Retorna historico de la cantidad de devoluciones.
          return historico.size();
      }
    
    public List<Devolucion> getDevoluciones() {
        // Retorna la lista de devoluciones.
        return devoluciones;
      }

    public void moverABodega(Inventario_Bodega bodega) {
        for (Devolucion devuelto : this.devoluciones) {
          Map<Product, Integer> carrito_devuelto= devuelto.getCarrito();
          for(Map.Entry<Product, Integer> entry : carrito_devuelto.entrySet()){
            Product producto = entry.getKey();
            int cantidad = entry.getValue();
            bodega.agregar_unidades(producto, cantidad);
          } 
        }
    }

    public void agregarAHistorico() {
      for (Devolucion devuelto : this.devoluciones) {
        historico.add(devuelto);
      }
      devoluciones.clear();
    }

    public void agregarADevoluciones(Devolucion devolucion) {
      devoluciones.add(devolucion);
    }

    public Devolucion buscarDevolucion(String id) {
      for (Devolucion devolucion : devoluciones) {
          if (devolucion.getId().equals(id)) {
              return devolucion;
          }
      }
      for (Devolucion devolucion : historico) {
        if (devolucion.getId().equals(id)) {
            return devolucion;
        }
      }
      return null;  
    }
    
    public List<Devolucion> get_lista_Devoluciones() {
      return devoluciones;
    }

    public List<Devolucion> get_lista_Historico() {
      return historico;
    }

    public int consultar_cantidad_unidades(Product producto) {
      // Retorna la cantidad de unidades del producto en devoluciones.
      int cantidad = 0;
      for (Devolucion d : devoluciones) {
          if (d.getId().equals(producto.getId())) {
              cantidad++;
          }
      }
      return cantidad;
    }

}
