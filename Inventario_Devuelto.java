import java.util.List;
import java.util.ArrayList;

public class Inventario_Devuelto extends Inventario_General {
    private List<Product> devoluciones;
    private List<Product> historico;

    public Inventario_Devuelto() {
        super();
        devoluciones = new ArrayList<>(); //verificación de lista pendiente
        historico = new ArrayList<>();
    }

    public int consultar_cantidad_devoluciones() {
      //Retorna la cantidad de devoluciones.
        return devoluciones.size();
    }

    public int consultar_cantidad_historico() {
        //Retorna historico de la cantidad de devoluciones.
          return historico.size();
      }
    
    public List<Product> getDevoluciones() {
        // Retorna la lista de devoluciones.
        return devoluciones;
      }

   public void agregarProductoAHistorico(Product producto, int cantidad) {
      for (int i = 0; i < cantidad; i++) {
          historico.add(producto);
        }
      }
      
    public void moverProductoABodega(Product producto, int cantidad, Inventario_Bodega bodega) {
      int cantidadEnDevoluciones = consultar_cantidad_unidades(producto);
      if (cantidadEnDevoluciones >= cantidad) {
            for (int i = 0; i < cantidad; i++) {
                devoluciones.remove(producto);
            }
            bodega.agregar_unidades(producto, cantidad);
        } else {
            System.out.println("No hay suficientes unidades en devoluciones.");
        }
    }

    public void mostrarDevoluciones() {
      if (devoluciones.isEmpty()) {
          System.out.println("No hay devoluciones.");
      } else {
          for (Product producto : devoluciones) {
              System.out.println("Id: " + producto.getId());
              System.out.println("Nombre: " + producto.getNombre());
              System.out.println("Descripción: " + producto.getDescripcion());
              System.out.println("------------------");
          }
      }
    }

    public void mostrarHistorico() {
      if (historico.isEmpty()) {
          System.out.println("No hay productos en el historico de devoluciones.");
      } else {
          for (Product producto : historico) {
              System.out.println("Id: " + producto.getId());
              System.out.println("Nombre: " + producto.getNombre());
              System.out.println("Descripción: " + producto.getDescripcion());
              System.out.println("------------------");
          }
      }
    }

    public int consultar_cantidad_unidades(Product producto) {
      // Retorna la cantidad de unidades del producto en devoluciones.
      int cantidad = 0;
      for (Product p : devoluciones) {
          if (p.getId().equals(producto.getId())) {
              cantidad++;
          }
      }
      return cantidad;
    }

}
