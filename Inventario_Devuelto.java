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

    //Método para agregar productos a la lista de devoluciones.
    public void agregarDevuelto(Product producto, int qty) {
        for(int i = 0; qty > i; i++){
            devoluciones.add(producto);
        }
    }

    //Método para agregar productos a la lista de historico y eliminar de devoluciones.
    public void agregarHistorico(Product producto, int qty) {
        for(int i = 0; qty > i; i++){
            historico.add(producto);
        }
    }

    public void mover_a_bodega(Product producto, int qty, Inventario_Bodega inventario_bodega) {
        int cantidadEnExhibicion = consultar_cantidad_unidades(producto);
        if (cantidadEnExhibicion >= qty) {
          quitar_unidades(producto, qty);
          agregarHistorico(producto, qty);
          inventario_bodega.agregar_unidades(producto, qty);
        } else {
          System.out.println("No hay suficientes unidades en devolucion.");
        }
      }
}
