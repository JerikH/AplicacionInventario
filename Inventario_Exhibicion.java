import java.util.HashMap;
import java.util.Map;

public class Inventario_Exhibicion extends Inventario_General {

  public Inventario_Exhibicion() {
    super();
  }

  public void mover_a_bodega(Product producto, int qty) {
    int cantidadEnExhibicion = consultar_cantidad_unidades(producto);
    if (cantidadEnExhibicion >= qty) {
      quitar_unidades(producto, qty);
      Inventario_Bodega.agregar_unidades(producto, qty);
    } else {
      System.out.println("No hay suficientes unidades en exhibición.");
    }
  }
}